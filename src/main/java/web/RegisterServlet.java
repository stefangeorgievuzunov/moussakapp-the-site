package web;

import enums.Gender;
import exceptions.InvalidDataException;
import services.RecipeManagementService;
import services.UserActionService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Inject
    private RecipeManagementService recipeManagementService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long totalRecipes=recipeManagementService.getTotalRecipes();
        request.setAttribute("totalRecipes",totalRecipes);

        request.getRequestDispatcher("/html/register.jsp").forward(request, response);
    }
}
