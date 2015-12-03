package controller;

import com.yana.model.Worker;
import dao.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

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
        DAOFactory factory = new DAOFactory();
        ConnectionPool pool = factory.getConnectionPool();

        String action = req.getParameter("action");
        String idStr = req.getParameter("workerId");
        int workerId = 0;
        if (idStr != null && !idStr.equals("")) {
            workerId = Integer.parseInt(idStr);
        }
        String post = req.getParameter("post");
        String name = req.getParameter("name");
        int year = 0;
        String yearStr = req.getParameter("year");
        if (yearStr != null && !yearStr.equals("")) {
            year = Integer.parseInt(yearStr);
        }
        int experience = 0;
        String experienceStr = req.getParameter("experience");
        if (experienceStr != null && !experienceStr.equals("")) {
            experience = Integer.parseInt(experienceStr);
        }

        String teamIdStr = req.getParameter("teamId");
        int teamId = 0;
        if (teamIdStr != null && !teamIdStr.equals("")) {
            teamId = Integer.parseInt(req.getParameter("teamId"));
        } else {
            Object teamIdObj = req.getSession().getAttribute("teamId");
            teamId = (int) teamIdObj;
        }

        Worker worker = new Worker(workerId, post, name, year, experience);
        WorkerTeamDAO wtDAO = new WorkerTeamDAO(pool);
        WorkerDAO workerDAO = new WorkerDAO(pool);
        if ("Add".equalsIgnoreCase(action)) {
            workerDAO.add(worker);
            wtDAO.addToTeam(teamId, workerId);
        } else if ("Delete".equalsIgnoreCase(action)) {
            wtDAO.deleteFromTeam(teamId, workerId);
        } else if ("Edit".equalsIgnoreCase(action)) {
            workerDAO.edit(worker);
        } else if ("Search".equalsIgnoreCase(action)) {
            worker = wtDAO.getWorker(teamId, workerId);
        }

        req.setAttribute("worker", worker);
        req.setAttribute("allWorkers", workerDAO.getAll());

        WorkerTeamDAO workerTeamDAO = new WorkerTeamDAO(pool);
        HttpSession session = req.getSession();
        session.setAttribute("teamId", teamId);
        req.setAttribute("teamId", teamId);
        req.setAttribute("workersInTeam", workerTeamDAO.getAllWorkersInTeam(teamId));
        if (req.getSession().getAttribute("role").equals("dispatcher")) {
            req.getRequestDispatcher("dispatcherTeamInfo.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("teamInfo.jsp").forward(req, resp);
        }
    }
}