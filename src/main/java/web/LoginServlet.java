package web;

import exceptions.InvalidDataException;
import services.RecipeManagementService;
import services.UserActionService;
import services.models.UserServiceModel;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Inject
    private RecipeManagementService recipeManagementService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long totalRecipes=recipeManagementService.getTotalRecipes();
        request.setAttribute("totalRecipes",totalRecipes);

        request.getRequestDispatcher("/html/login.jsp").forward(request, response);
    }
}
