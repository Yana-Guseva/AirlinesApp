package controller;

import com.yana.model.Flight;
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
        FlightDAO flightDAO = new FlightDAO();
//        Date date = null;
//        Date time = null;
//        String stringDateFormat = "dd-MM-yyyy HH:mm:ss";
//        SimpleDateFormat dateFormat = new SimpleDateFormat(stringDateFormat);
//        String stringTimeFormat = "HH:mm:ss";
//        SimpleDateFormat timeFormat = new SimpleDateFormat(stringTimeFormat);
//
//        int flightId = Integer.parseInt(req.getParameter("id"));
//        String cityOfDepart = req.getParameter("city_depart");
//        String cityOfDest = req.getParameter("city_dest");
//        int teamId = Integer.parseInt(req.getParameter("id_team"));
//        String action = req.getParameter("operation");
//        try {
//            date = dateFormat.parse(req.getParameter("date_of_flight"));
//            time = timeFormat.parse(req.getParameter("time_in_flight"));
//        } catch (ParseException e) {
//            System.err.println(e);
//        }
//
//        Flight flight = new Flight(flightId, cityOfDepart, cityOfDest, date, time, teamId);
//
//        if(action.equalsIgnoreCase("Add")) {
//            flightDAO.addFlight(flight);
//        } else if(action.equalsIgnoreCase("Delete")) {
//            flightDAO.deleteFlught(flight);
//        } else if (action.equalsIgnoreCase("Edit")) {
//            flightDAO.editFlight(flight);
//        } else if (action.equalsIgnoreCase("Search")) {
//            flight = flightDAO.getFlight(flightId);
//        }
//
//        req.setAttribute("flight", flight);
        req.setAttribute("size", flightDAO.getAllFlight().size());
        req.setAttribute("allFlights", flightDAO.getAllFlight());
        req.getRequestDispatcher("flightsInfo.jsp").forward(req, resp);
    }
}
