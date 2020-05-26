package web;

import enums.Gender;
import services.user.UserActionService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Inject private  UserActionService userActionService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String description = request.getParameter("description");

        Integer age = 69; //test purposes only
        Gender gender = Gender.MALE; //test purposes only

        if(userActionService.register(username,password,rePassword,firstName,lastName,gender,age,description)){
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/register.jsp").forward(request, response);
    }
}
