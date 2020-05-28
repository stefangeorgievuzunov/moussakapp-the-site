package filters.profile;

import db.Recipe;
import db.User;
import filters.HomeFilter;
import services.DataRetrievingService;
import services.models.UserServiceModel;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileHandleUserViaIdParameterFilter implements Filter {
    @Inject
    private  DataRetrievingService dataRetrievingService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            Integer id = Integer.parseInt(request.getParameter("id"));

            UserServiceModel viewedUser = dataRetrievingService.getEntityById(User.class, UserServiceModel.class, id);

            if (viewedUser != null) {
                request.setAttribute("viewedUser", viewedUser);
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            e.printStackTrace();
        }
    }
}
