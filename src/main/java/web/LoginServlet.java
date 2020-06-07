package web;

import exceptions.InvalidDataException;
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
    private UserActionService userActionService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            UserServiceModel userServiceModel = userActionService.login(username, password);

            request.getSession().setAttribute("loggedUser", userServiceModel);
            response.sendRedirect("/home");
        } catch (InvalidDataException e) {
            e.printStackTrace();
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/login.jsp").forward(request, response);
    }
}
