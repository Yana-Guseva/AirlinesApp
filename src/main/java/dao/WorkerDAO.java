package dao;

import com.yana.model.Worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Yana on 28.11.2015.
 */
public class WorkerDAO {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver error");
            e.printStackTrace();
        }
    }

    public WorkerDAO() {}

    public void addWorker(Worker worker) {
        String sql = "INSERT INTO worker VALUES (" + worker.getWorkerId() + ", '" + worker.getName() +
                "', " + worker.getYear() + ", " + worker.getExperience() + ")";
        executeModifyQuery(sql);
    }

    public void editWorker(Worker worker) {
        String sql = "UPDATE worker SET id_post=" + worker.getWorkerId() + ", name='" +
                worker.getName() + "', year=" + worker.getYear() + ", experience=" + worker.getExperience();
        executeModifyQuery(sql);
    }

    public void deleteWorker(Worker worker) {
        String sql = "DELETE * FROM worker WHERE id=" + worker.getWorkerId();
        executeModifyQuery(sql);
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

    public Worker getWorker(int workerId) {
        Worker worker = new Worker();
        String sql = "SELECT * FROM worker WHERE id=" + workerId;
        try {
            Connection conn = getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                worker.setWorkerId(rs.getInt("id"));
                worker.setPostId(rs.getInt("id_post"));
                worker.setName(rs.getString("name"));
                worker.setYear(rs.getInt("year"));
                worker.setExperience(rs.getInt("experience"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    public ArrayList<Worker> getAllWorker() {
        ArrayList<Worker> workers = new ArrayList<>();
        String sql = "SELECT * FROM worker";
        try {
            Connection conn = getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setWorkerId(rs.getInt("id"));
                worker.setPostId(rs.getInt("id_post"));
                worker.setName(rs.getString("name"));
                worker.setYear(rs.getInt("year"));
                worker.setExperience(rs.getInt("experience"));
                workers.add(worker);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    private static Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/airline_db";
        String jdbcUser = "root";
        String jdbcPassword = "root";
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }
}
