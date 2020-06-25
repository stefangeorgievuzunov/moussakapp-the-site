package services.impl.recipe;

import db.Ingredient;
import db.Instruction;
import db.Recipe;
import db.RecipeCategory;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeManagementServiceImpl implements RecipeManagementService {
    private final UploadImageService uploadImageService;
    private final DbService dbService;

    @Inject
    RecipeManagementServiceImpl(UploadImageService uploadImageService, DbService dbService) {
        this.uploadImageService = uploadImageService;
        this.dbService = dbService;
    }

    @Override
    public void uploadRecipe(String title, String description, String[] ingredients, String[] instructions, String category, Integer prepareTime, Integer cookingTime, Integer servings, Part uploadedFile) throws InvalidDataException, IOException {
        Recipe recipe = new Recipe();

        recipe.setTitle(title);
        recipe.setDescription(description);

        List<Ingredient> ingredientList = new ArrayList<>();
        for (String i:ingredients){
            if (!i.isEmpty()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(i);
                ingredientList.add(ingredient);
            }
        }

        List<Instruction> instructionList = new ArrayList<>();
        for (String i:instructions){
            if (!i.isEmpty()) {
                Instruction instruction = new Instruction();
                instruction.setName(i);
                instructionList.add(instruction);
            }
        }

        recipe.setIngredients(ingredientList);
        recipe.setInstructions(instructionList);

        List<RecipeCategory> recipeCategory =dbService.select(new DbServiceImpl.Query<RecipeCategory, RecipeCategory>(RecipeCategory.class,RecipeCategory.class) {
            @Override
            protected Selection<? extends RecipeCategory> select() {
                return null;
            }

            @Override
            protected Predicate where() {
                return builder().equal(root().get("name"),category);
            }
        });

        if (!recipeCategory.isEmpty()){
            recipe.setCategory(recipeCategory.get(0));
        }

        recipe.setPrepareTime(prepareTime);
        recipe.setCookingTime(cookingTime);
        recipe.setServings(servings);

        if (uploadedFile != null && uploadImageService.isCorrect(uploadedFile)) {
            recipe.setImage(IOUtils.toByteArray(uploadedFile.getInputStream()));
        }

        dbService.persist(recipe);
    }
}
