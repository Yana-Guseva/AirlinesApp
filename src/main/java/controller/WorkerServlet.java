package controller;

import dao.WorkerDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yana on 28.11.2015.
 */
public class WorkerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WorkerDAO workerDAO = new WorkerDAO();
        req.setAttribute("allWorkers", workerDAO.getAllWorker());
        req.getRequestDispatcher("teamInfo.jsp").forward(req, resp);
    }
}
