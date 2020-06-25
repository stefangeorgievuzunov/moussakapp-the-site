package web.ajax;

import exceptions.InvalidDataException;
import services.JSONParserService;
import services.RecipeManagementService;

import web.models.response.AjaxResponse;
import web.models.view.RecipeViewModel;

import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@MultipartConfig
@WebServlet("/admin/new/recipe/add")
public class AdminNewRecipeAddServlet extends HttpServlet {
    @Inject
    private RecipeManagementService recipeManagementService;
    @Inject
    private JSONParserService json;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part jsonObject = request.getPart("json");

        RecipeViewModel recipeViewModel = json.read(new BufferedReader(new InputStreamReader(jsonObject.getInputStream(), StandardCharsets.UTF_8)), RecipeViewModel.class);

        AjaxResponse ajaxResponse = new AjaxResponse();

        try {
            Integer prepareTime = Integer.parseInt(recipeViewModel.getPrepareTime());
            Integer cookingTime = Integer.parseInt(recipeViewModel.getCookingTime());
            Integer servings = Integer.parseInt(recipeViewModel.getServings());

            recipeManagementService.uploadRecipe(recipeViewModel.getTitle(), recipeViewModel.getDescription(), recipeViewModel.getIngredients(), recipeViewModel.getInstructions(), recipeViewModel.getCategory(), prepareTime, cookingTime, servings, request.getPart("uploadedFile"));
            ajaxResponse.setSuccess(true);
            ajaxResponse.setUrl("/home");

        } catch (Exception e) {
            e.printStackTrace();

            if (e instanceof InvalidDataException) {
                ajaxResponse.setError(e.getMessage());
            } else if (e instanceof NumberFormatException) {
                ajaxResponse.setError("Полетата за приготвяне, готвене и порции, трябва да бъдат цели числа !");
            } else {
                ajaxResponse.setError("Something went wrong.. :(");
            }
        }

        response.setContentType("application/json; charset=utf-8");
        json.write(response.getWriter(), recipeViewModel);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
