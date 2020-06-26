package filters.profile;

import db.User;
import org.modelmapper.ModelMapper;
import services.DbService;
import services.models.UserServiceModel;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileHandleUserViaIdParameterFilter implements Filter {
    @Inject
    private DbService dbService;
    @Inject
    private ModelMapper modelMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            Integer id = Integer.parseInt(request.getParameter("id"));

            User viewedUser = dbService.getEntityById(User.class, id);

            if (viewedUser != null) {
                request.setAttribute("viewedUser", modelMapper.map(viewedUser, UserServiceModel.class));
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
