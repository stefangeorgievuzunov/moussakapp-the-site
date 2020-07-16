package services.impl.recipe;

import db.*;
import exceptions.InvalidDataException;
import org.apache.commons.io.IOUtils;
import services.DbService;
import services.RecipeManagementService;
import services.UploadImageService;
import services.impl.db.DbServiceImpl;

import javax.inject.Inject;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import javax.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class RecipeManagementServiceImpl implements RecipeManagementService {
    private final UploadImageService uploadImageService;
    private final DbService dbService;

    @Inject
    RecipeManagementServiceImpl(UploadImageService uploadImageService, DbService dbService) {
        this.uploadImageService = uploadImageService;
        this.dbService = dbService;
    }

    @Override
    public void uploadRecipe(Integer authorId, String title, String description, String[] ingredients, String[] instructions, String category, Integer prepareTime, Integer cookingTime, Integer servings, Part uploadedFile) throws InvalidDataException, IOException {
        Recipe recipe = new Recipe();

        recipe.setTitle(title);
        recipe.setDescription(description);

        List<Ingredient> ingredientList = new ArrayList<>();
        for (String i : ingredients) {
            if (!i.isEmpty()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(i);
                ingredient.setRecipe(recipe);
                ingredientList.add(ingredient);
            }
        }

        List<Instruction> instructionList = new ArrayList<>();
        for (String i : instructions) {
            if (!i.isEmpty()) {
                Instruction instruction = new Instruction();
                instruction.setName(i);
                instruction.setRecipe(recipe);
                instructionList.add(instruction);
            }
        }

        recipe.setIngredients(ingredientList);
        recipe.setInstructions(instructionList);

        List<RecipeCategory> recipeCategory = dbService.createQuery(new DbServiceImpl.Query<RecipeCategory, RecipeCategory>() {
            @Override
            protected Selection<? extends RecipeCategory> select() {
                return null;
            }

            @Override
            protected Predicate where() {
                return builder().equal(root().get("name"), category);
            }
        });

        if (!recipeCategory.isEmpty()) {
            recipe.setCategory(recipeCategory.get(0));
        }

        List<User> authors = dbService.createQuery(new DbServiceImpl.Query<User, User>() {
            @Override
            protected Selection<? extends User> select() {
                return null;
            }

            @Override
            protected Predicate where() {
                return builder().equal(root().get("id"), authorId);
            }
        });

        recipe.setAuthor(authors.get(0));

        recipe.setPrepareTime(prepareTime);

        recipe.setCookingTime(cookingTime);

        recipe.setServings(servings);

        if (uploadedFile != null && uploadImageService.isCorrect(uploadedFile)) {
            Avatar avatar = new Avatar();
            avatar.setName(Paths.get(uploadedFile.getSubmittedFileName()).getFileName().toString());
            avatar.setData(IOUtils.toByteArray(uploadedFile.getInputStream()));

            recipe.setAvatar(avatar);
        }

        dbService.persist(recipe);
    }

    @Override
    public Long getTotalRecipes() {
        List<Long> data = dbService.createQuery(new DbServiceImpl.Query<Recipe, Long>() {
            @Override
            protected Selection<? extends Long> select() {
                return builder().count(root());
            }
            @Override
            protected Predicate where() {
                return null;
            }
        });

        return data.get(0);
    }
}
