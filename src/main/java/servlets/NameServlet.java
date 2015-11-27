package servlets;

import auth.UserChecker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yana on 22.11.2015.
 */
//@WebServlet("/login")
public class NameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("user");
        String password = req.getParameter("pwd");

        if (UserChecker.isUserCorrect(username, password)) {
//            String encodedURL = resp.encodeRedirectURL("LoginSuccess.jsp");
            HttpSession session = req.getSession();
            session.setAttribute("user", username);
            session.setMaxInactiveInterval(30*60);
            Cookie loginCookie = new Cookie("user", username);
            loginCookie.setMaxAge(30*60);
            resp.addCookie(loginCookie);
            resp.sendRedirect("/info.jsp");
//            filterChain.doFilter(servletRequest, servletResponse);
        } else {
//            request.getRequestDispatcher("error.html").forward(request, response);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= resp.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(req, resp);
        }
    }
}
