package web.authozation;

import exceptions.InvalidDataException;
import services.JSONParserService;
import services.UserActionService;
import web.models.RegisterViewModel;
import web.models.response.RegisterResponse;

import javax.inject.Inject;
import javax.servlet.ServletException;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RegisterResponse registerResponse=new RegisterResponse();

        try {
            RegisterViewModel model=json.read(request.getReader(),RegisterViewModel.class);

            if (model!=null){
                userActionService.register(model.getUsername(),model.getPassword(),model.getFirstName(),model.getLastName());

                registerResponse.setRedirect("/login");
                registerResponse.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof InvalidDataException){
                registerResponse.setError(e.getMessage());
            }else{
                registerResponse.setError("Something went wrong.. :(");
            }
        }

        json.write(response.getWriter(),registerResponse);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/login");
    }
}
