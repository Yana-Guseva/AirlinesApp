package servlets;

import auth.UserChecker;
import com.yana.model.User;
import controller.ConnectionPool;
import dao.DAOFactory;
import dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Created by Yana on 22.11.2015.
 */
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("user");
        String password = req.getParameter("pwd");

        if (UserChecker.isUserCorrect(username, password)) {
            DAOFactory factory = new DAOFactory();
            ConnectionPool pool = factory.getConnectionPool();
            Connection conn = pool.getConnection();
            UserDAO userDAO = new UserDAO(pool);
            User user = userDAO.getUser(username);

            HttpSession session = req.getSession();
            session.setAttribute("user", username);
            session.setAttribute("role", user.getRole());
            session.setMaxInactiveInterval(30*60);
            Cookie loginCookie = new Cookie("user", username);
            loginCookie.setMaxAge(30*60);
            resp.addCookie(loginCookie);
            resp.sendRedirect("./FlightServlet");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(req, resp);
        }
    }
}
