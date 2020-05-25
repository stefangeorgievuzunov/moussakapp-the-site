package web;

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
    @Inject private UserActionService userActionService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        UserServiceModel userServiceModel=userActionService.login(username,password);
        if (userServiceModel!=null){
            request.getSession().setAttribute("user",userServiceModel);
            response.sendRedirect("/home");
        }else{
            doGet(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/login.jsp").forward(request,response);
    }
}
