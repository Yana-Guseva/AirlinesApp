package dao;

import com.yana.model.Flight;
import controller.ConnectionPool;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Yana on 27.11.2015.
 */
public class FlightDAO extends AbstractDAO<Flight>{

    public FlightDAO(ConnectionPool pool) {
        super(pool);
    }

    @Override
    public void add(Flight flight) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        String sql = "INSERT INTO flight VALUES(" + flight.getFlightId() + ", '" + flight.getCityOfDeparture() + "', '" +
                flight.getCityOfDestination() + "', '" + formatter.format(flight.getDate()) + "', '" +
                timeFormatter.format(flight.getTime()) + "', '" + timeFormatter.format(flight.getDuration()) +
                "', " + flight.getTeamId() + ")";
        executeModifyQuery(sql);
    }

    @Override
    public void edit(Flight flight) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        String sql = "UPDATE flight SET city_depart='" + flight.getCityOfDeparture() + "', city_dest='" +
                flight.getCityOfDestination() + "', date_of_flight='" + formatter.format(flight.getDate()) + "', time_of_flight='" +
                timeFormatter.format(flight.getTime()) + "', duration='" + timeFormatter.format(flight.getDuration()) +
                "', id_team=" + flight.getTeamId() + " WHERE id=" + flight.getFlightId();
        executeModifyQuery(sql);
    }

    @Override
    public void delete(Flight flight) {
        String sql = "DELETE FROM flight WHERE id=" + flight.getFlightId();
        executeModifyQuery(sql);
    }

    @Override
    public Flight getItem(int flightId) {
        Flight flight = new Flight();
        String sql = "SELECT * FROM flight WHERE id = " + flightId;

        try {
            String stringDateFormat = "yyyy-MM-dd";
            String stringTimeFormat = "HH:mm";
            SimpleDateFormat dateFormat = new SimpleDateFormat(stringDateFormat);
            SimpleDateFormat timeFormat = new SimpleDateFormat(stringTimeFormat);
            Connection conn = pool.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                flight.setFlightId(rs.getInt("id"));
                flight.setCityOfDeparture(rs.getString("city_depart"));
                flight.setCityOfDestination(rs.getString("city_dest"));
                flight.setDate(rs.getDate("date_of_flight"));
                flight.setTime(rs.getTime("time_of_flight"));
                flight.setDuration(rs.getTime("duration"));
//                try {
//                    flight.setDate(dateFormat.parse(rs.getString("date_of_flight")));
//                    flight.setTime(timeFormat.parse(rs.getString("time_of_flight")));
//                    flight.setDuration(timeFormat.parse(rs.getString("duration")));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                flight.setTeamId(rs.getInt("id_team"));
            }
            pool.freeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    @Override
    public ArrayList<Flight> getAll() {
        ArrayList<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flight";
        String stringDateFormat = "yyyy-MM-dd";
        String stringTimeFormat = "HH:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(stringDateFormat);
        SimpleDateFormat timeFormat = new SimpleDateFormat(stringTimeFormat);
        try {
            Connection conn = pool.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("id"));
                flight.setCityOfDeparture(rs.getString("city_depart"));
                flight.setCityOfDestination(rs.getString("city_dest"));
                flight.setDate(rs.getDate("date_of_flight"));
                flight.setTime(rs.getTime("time_of_flight"));
                flight.setDuration(rs.getTime("duration"));
//                try {
//                    flight.setDate(dateFormat.parse(rs.getString("date_of_flight")));
//                    flight.setTime(timeFormat.parse(rs.getString("time_of_flight")));
//                    flight.setDuration(timeFormat.parse(rs.getString("duration")));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                flight.setTeamId(rs.getInt("id_team"));
                flights.add(flight);
            }
            pool.freeConnection(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
}
