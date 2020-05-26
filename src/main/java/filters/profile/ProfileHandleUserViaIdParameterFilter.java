package filters.profile;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileHandleUserViaIdParameterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try{
            Integer id=Integer.parseInt(request.getParameter("id"));

        }catch(NumberFormatException e){
            response.sendRedirect("/home"); //wrong ID format type
            e.printStackTrace();
        }
    }
}
