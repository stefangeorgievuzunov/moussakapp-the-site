package filters.profile;

import services.models.UserServiceModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ProfileCheckPassedUrlParametersFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Map<String, String[]> urlParams = request.getParameterMap();

        if(urlParams.isEmpty()){
            Object user=request.getSession(false).getAttribute("user");

            if(user instanceof UserServiceModel){
                request.setAttribute("loggedUser",user);
                response.sendRedirect("/profile?id="+((UserServiceModel) user).getId());
            }else{
                response.sendRedirect("/login");
            }
        }else if(!urlParams.containsKey("id")){
            response.sendRedirect("/home"); //on page not found
        }else{
            filterChain.doFilter(request,response);
        }
    }
}
