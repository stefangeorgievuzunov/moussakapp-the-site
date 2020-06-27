package web;

import db.Recipe;
import services.DbService;
import services.impl.db.DbServiceImpl;
import services.models.UserServiceModel;

import javax.inject.Inject;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/profile")
public class AdminProfileServlet extends HttpServlet {
    @Inject
    private DbService dbService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceModel user= (UserServiceModel) request.getSession(false).getAttribute("loggedUser");

        List<Recipe> recipeList= dbService.createQuery(new DbServiceImpl.Query<Recipe, Recipe>(Recipe.class,Recipe.class) {
            @Override
            protected Selection<? extends Recipe> select() {
                return null;
            }

            @Override
            protected Predicate where() {
                root().fetch("author", JoinType.LEFT);
                return builder().equal(root().get("author").get("id"),user.getId());
            }
        });

        request.setAttribute("recipes",recipeList);
        request.getRequestDispatcher("/html/admin-profile.jsp").forward(request,response);
    }
}
