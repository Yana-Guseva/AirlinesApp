package auth;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yana on 22.11.2015.
 */
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String usernmae = request.getParameter("user");
//        String password = request.getParameter("pwd");
//
//        if (UserChecker.isUserCorrect(usernmae, password)) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
////            request.getRequestDispatcher("error.html").forward(request, response);
//            RequestDispatcher rd = request.getRequestDispatcher("/login.html");
//            response.setContentType("text/html");
//            PrintWriter out= response.getWriter();
//            out.println("<font color=red>Username or password is wrong.</font>");
//            rd.include(request, response);
//        }
    }

    @Override
    public void destroy() {

    }
}
