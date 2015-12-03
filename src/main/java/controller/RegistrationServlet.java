package controller;

import com.yana.model.User;
import dao.DAOFactory;
import dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yana on 02.12.2015.
 */
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equalsIgnoreCase("Registration")) {
            if (req.getParameter("login") != null) {
                String name = req.getParameter("user");
                String login = req.getParameter("login");
                String password = req.getParameter("pwd");
                String role = req.getParameter("role");
                User user = new User();
                user.setName(name);
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(role);

                DAOFactory factory = new DAOFactory();
                ConnectionPool pool = factory.getConnectionPool();
                UserDAO userDAO = new UserDAO(pool);
                if ((userDAO.getUser(user.getLogin()) == null) && !user.getLogin().equals("")) {
                    userDAO.add(user);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                    PrintWriter out = resp.getWriter();
                    out.println("<font color=green>Registration success! Please, login.</font>");
                    rd.include(req, resp);
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/registration.html");
                    PrintWriter out = resp.getWriter();
                    out.println("<font color=red>This user already exists.</font>");
                    rd.include(req, resp);
                }
            }
        }
        if (req.getParameter("action").equalsIgnoreCase("Cancel")) {
            req.getRequestDispatcher("/login.html").forward(req, resp);
        }
    }
}
