package web.authozation;

import exceptions.InvalidDataException;
import services.JSONParserService;
import services.UserActionService;
import web.models.view.RegisterViewModel;
import web.models.response.AuthorizationResponse;

import javax.inject.Inject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register/authorization")
public class RegisterAuthorizationServlet extends HttpServlet {
    @Inject
    private UserActionService userActionService;
    @Inject
    private JSONParserService json;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        AuthorizationResponse authorizationResponse = new AuthorizationResponse();

        try {
            RegisterViewModel model = json.read(request.getReader(), RegisterViewModel.class);

            if (model != null) {
                userActionService.register(model.getUsername(), model.getPassword(), model.getFirstName(), model.getLastName());

                authorizationResponse.setRedirect("/login");
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
        response.sendRedirect("/register");
    }
}
