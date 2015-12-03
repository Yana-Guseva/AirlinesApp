package controller;

import com.yana.model.Flight;
import dao.DAOFactory;
import dao.FlightDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yana on 27.11.2015.
 */
public class FlightServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = new DAOFactory();
        ConnectionPool pool = factory.getConnectionPool();
        FlightDAO flightDAO = factory.getFlightDAO(pool);

        String action = req.getParameter("action");
        String flightIdStr = req.getParameter("flightId");
        int flightId = 0;
        if (flightIdStr != null) {
            flightId = Integer.parseInt(flightIdStr);
        }
        req.setAttribute("id", flightId);
        String cityOfDepart = req.getParameter("cityOfDepart");
        String cityOfDest = req.getParameter("cityOfDest");
        String teamIdStr = req.getParameter("teamId");
        int teamId = 0;
        if (teamIdStr != null) {
            teamId = Integer.parseInt(teamIdStr);
        }
        String dateStr = req.getParameter("date");
        String timeStr = req.getParameter("time");
        String durationStr = req.getParameter("duration");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date = null;
        Date time = null;
        Date duration = null;
        try {
            if (dateStr != null) {
                date = dateFormat.parse(dateStr);
            }
            if (timeStr != null) {
                time = timeFormat.parse(timeStr);
            }
            if (durationStr != null) {
                duration = timeFormat.parse(durationStr);
            }
        } catch (ParseException e) {
            System.err.println(e);
        }
        req.setAttribute("date", dateStr);
        Flight flight = new Flight(flightId, cityOfDepart, cityOfDest, date, time, duration, teamId);

        if ("Add".equalsIgnoreCase(action)) {
            flightDAO.add(flight);
        } else if ("Delete".equalsIgnoreCase(action)) {
            flightDAO.delete(flight);
        } else if ("Edit".equalsIgnoreCase(action)) {
            flightDAO.edit(flight);
        } else if ("Search".equalsIgnoreCase(action)) {
            flight = flightDAO.getItem(flightId);
        }

        req.setAttribute("flight", flight);
        req.setAttribute("size", flightDAO.getAll().size());
        req.setAttribute("allFlights", flightDAO.getAll());
        if (req.getSession().getAttribute("role").equals("administrator")) {
            req.getRequestDispatcher("adminFlightsInfo.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("flightsInfo.jsp").forward(req, resp);
        }
    }
}
