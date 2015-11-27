package dao;

import com.yana.model.Flight;

import javax.activation.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Yana on 27.11.2015.
 */
public class FlightDAO {
    private DataSource ds;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось подключиться к базе данных");
            e.printStackTrace();
        }
    }

    public void addFlight(Flight flight) {
        String sql = "INSERT INTO flight VALUES ('" + flight.getCityOfDeparture() + "', '" +
                flight.getCityOfDestination() + "', '" + flight.getDate() + "', '" +
                flight.getTime() + "', id_team=" + flight.getTeamId();
        executeModifyQuery(sql);
    }

    public void editFlight(Flight flight) {
        String sql = "UPDATE flight SET city_depart='" + flight.getCityOfDeparture() + "', city_dest='" +
                flight.getCityOfDestination() + "', date_of_flight='" + flight.getDate() + "', time_in_flight='" +
                flight.getTime() + "', id_team=" + flight.getTeamId() + "WHERE id=" + flight.getFilghtId();
        executeModifyQuery(sql);
    }

    public void deleteFlught(Flight flight) {
        String sql = "DELETE FROM flight WHERE id=" + flight.getFilghtId();
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
            System.err.println(e);
        }
        return rs;
    }

    public Flight getFlught(int flightId) {
        Flight flight = new Flight();
        String sql = "SELECT * FROM flight WHERE id = " + flightId;
        ResultSet rs = executeFetchQuery(sql);
        try {
            if (rs.next()) {
                flight.setFilghtId(rs.getInt("id"));
                flight.setCityOfDeparture(rs.getString("city_depart"));
                flight.setCityOfDestination(rs.getString("city_dest"));
                flight.setDate(rs.getDate("date_of_flight"));
                flight.setTime(rs.getTime("time_in_flight"));
                flight.setTeamId(rs.getInt("id_team"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return flight;
    }

    public ArrayList<Flight> getAllFlight() {
        ArrayList<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flight";
        ResultSet rs = executeFetchQuery(sql);
        try {
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFilghtId(rs.getInt("id"));
                flight.setCityOfDeparture(rs.getString("city_depart"));
                flight.setCityOfDestination(rs.getString("city_dest"));
                flight.setDate(rs.getDate("date_of_flight"));
                flight.setTime(rs.getTime("time_in_flight"));
                flight.setTeamId(rs.getInt("id_team"));
                flights.add(flight);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return flights;
    }
}
