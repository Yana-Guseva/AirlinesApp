package dao;

import com.yana.model.Worker;
import controller.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Yana on 28.11.2015.
 */
public class WorkerDAO extends AbstractDAO<Worker>{

    @Override
    public void add(Worker worker) {
        String sql = "INSERT INTO worker VALUES (" + worker.getWorkerId() + ", '" + worker.getPost() + "', '" + worker.getName() +
                "', " + worker.getYear() + ", " + worker.getExperience() + ")";
        executeModifyQuery(sql);
    }

    @Override
    public void edit(Worker worker) {
        String sql = "UPDATE worker SET post='" + worker.getPost() + "', name='" +
                worker.getName() + "', year=" + worker.getYear() + ", experience=" + worker.getExperience() +
                " WHERE id=" + worker.getWorkerId();
        executeModifyQuery(sql);
    }

    @Override
    public void delete(Worker worker) {
        String sql = "DELETE * FROM worker WHERE id=" + worker.getWorkerId();
        executeModifyQuery(sql);
    }

    @Override
    public Worker getItem(int workerId) {
        Worker worker = new Worker();
        String sql = "SELECT * FROM worker WHERE id=" + workerId;
        try {
            Connection conn = pool.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                worker.setWorkerId(rs.getInt("id"));
                worker.setPost(rs.getString("post"));
                worker.setName(rs.getString("name"));
                worker.setYear(rs.getInt("year"));
                worker.setExperience(rs.getInt("experience"));
            }
            pool.freeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public ArrayList<Worker> getAll() {
        ArrayList<Worker> workers = new ArrayList<>();
        String sql = "SELECT * FROM worker";
        try {
            Connection conn = pool.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setWorkerId(rs.getInt("id"));
                worker.setPost(rs.getString("post"));
                worker.setName(rs.getString("name"));
                worker.setYear(rs.getInt("year"));
                worker.setExperience(rs.getInt("experience"));
                workers.add(worker);
            }
            pool.freeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    public WorkerDAO(ConnectionPool pool) {
        super(pool);
    }
}
