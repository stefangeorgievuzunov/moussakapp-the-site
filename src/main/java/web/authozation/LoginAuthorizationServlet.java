package web.authozation;

import exceptions.InvalidDataException;
import services.JSONParserService;
import services.UserActionService;
import services.models.UserServiceModel;
import web.models.response.AuthorizationResponse;
import web.models.view.LoginViewModel;


import javax.inject.Inject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login/authorization")
public class LoginAuthorizationServlet extends HttpServlet {
    @Inject
    private UserActionService userActionService;
    @Inject
    private JSONParserService json;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        AuthorizationResponse authorizationResponse = new AuthorizationResponse();

        try {
            LoginViewModel model = json.read(request.getReader(), LoginViewModel.class);

            if (model != null) {
                UserServiceModel userServiceModel = userActionService.login(model.getUsername(), model.getPassword());

                request.getSession().setAttribute("loggedUser", userServiceModel);

                authorizationResponse.setRedirect("/home");
                authorizationResponse.setSuccess(true);
            }

        } catch (Exception e) {
            e.printStackTrace();

            if (e instanceof InvalidDataException) {
                authorizationResponse.setError(e.getMessage());
            } else {
                authorizationResponse.setError("Something went wrong.. :(");
            }
        }

        response.setContentType("application/json; charset=utf-8");
        json.write(response.getWriter(), authorizationResponse);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN); //403
    }
}
