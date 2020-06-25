package services;

import exceptions.InvalidDataException;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface RecipeManagementService {
    void uploadRecipe(String title, String description, String[] ingredients, String[] instructions, String category, Integer prepareTime, Integer cookingTime, Integer servings, Part uploadedFile) throws InvalidDataException, IOException;
}
