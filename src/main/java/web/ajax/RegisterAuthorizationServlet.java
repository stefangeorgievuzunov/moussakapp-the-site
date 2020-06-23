package web.ajax;

import exceptions.InvalidDataException;
import services.JSONParserService;
import services.UserActionService;
import web.models.response.AjaxResponse;
import web.models.view.RegisterViewModel;

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

        AjaxResponse ajaxResponse = new AjaxResponse();

        try {
            RegisterViewModel model = json.read(request.getReader(), RegisterViewModel.class);

            if (model != null) {
                userActionService.register(model.getUsername(), model.getPassword(), model.getFirstName(), model.getLastName());

                ajaxResponse.setUrl("/login");
                ajaxResponse.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (e instanceof InvalidDataException) {
                ajaxResponse.setError(e.getMessage());
            } else {
                ajaxResponse.setError("Something went wrong.. :(");
            }
        }

        response.setContentType("application/json; charset=utf-8");
        json.write(response.getWriter(), ajaxResponse);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN); //403
    }
}
