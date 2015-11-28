package dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.yana.model.Flight;

import javax.activation.DataSource;
import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Yana on 27.11.2015.
 */
public class FlightDAO {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver error");
            e.printStackTrace();
        }
    }

    public FlightDAO() {
    }

    public void addFlight(Flight flight) {
        String sql = "INSERT INTO flight VALUES ('" + flight.getCityOfDeparture() + "', '" +
                flight.getCityOfDestination() + "', '" + flight.getDate() + "', '" +
                flight.getTime() + "', " + flight.getDuration() + "', " + flight.getTeamId();
        executeModifyQuery(sql);
    }

    public void editFlight(Flight flight) {
        String sql = "UPDATE flight SET city_depart='" + flight.getCityOfDeparture() + "', city_dest='" +
                flight.getCityOfDestination() + "', date_of_flight='" + flight.getDate() + "', time_of_flight='" +
                flight.getTime() + "', duration=" + flight.getDuration() + "', id_team=" + flight.getTeamId() + "WHERE id=" + flight.getFlightId();
        executeModifyQuery(sql);
    }

    public void deleteFlight(Flight flight) {
        String sql = "DELETE FROM flight WHERE id=" + flight.getFlightId();
        executeModifyQuery(sql);
    }

    private static Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/airline_db";
        String jdbcUser = "root";
        String jdbcPassword = "root";
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    public void executeModifyQuery(String sql) {
        try {
            Connection conn = getConnection();
            conn.createStatement().execute(sql);
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public ResultSet executeFetchQuery(String sql) {
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            rs = conn.createStatement().executeQuery(sql);
            conn.close();
        } catch (SQLException e) {
            System.err.println("executeFetchQuery" + e);
        }
        return rs;
    }

    public Flight getFlight(int flightId) {
        Flight flight = new Flight();
        String sql = "SELECT * FROM flight WHERE id = " + flightId;

//        ResultSet rs = executeFetchQuery(sql);
        try {
            Connection conn = getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                flight.setFlightId(rs.getInt("id"));
                flight.setCityOfDeparture(rs.getString("city_depart"));
                flight.setCityOfDestination(rs.getString("city_dest"));
                flight.setDate(rs.getDate("date_of_flight"));
                flight.setTime(rs.getTime("time_of_flight"));
                flight.setTime(rs.getTime("duration"));
                flight.setTeamId(rs.getInt("id_team"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    public ArrayList<Flight> getAllFlight() {
        ArrayList<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flight";
//        ResultSet rs = executeFetchQuery(sql);
        try {
            Connection conn = getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("id"));
                flight.setCityOfDeparture(rs.getString("city_depart"));
                flight.setCityOfDestination(rs.getString("city_dest"));
                flight.setDate(rs.getDate("date_of_flight"));
                flight.setTime(rs.getTime("time_of_flight"));
                flight.setDuration(rs.getTime("duration"));
                flight.setTeamId(rs.getInt("id_team"));
                flights.add(flight);
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
}
