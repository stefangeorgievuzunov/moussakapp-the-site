package filters.profile.admin;

import services.models.UserServiceModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminProfileAuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Object user = request.getSession(false).getAttribute("loggedUser");

        if(user instanceof UserServiceModel){
            filterChain.doFilter(request,response);
        }else{
            response.sendRedirect("/login");
        }
    }
}
