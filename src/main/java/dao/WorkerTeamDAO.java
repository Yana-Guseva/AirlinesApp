package dao;

import com.yana.model.Worker;
import com.yana.model.WorkerTeam;
import controller.ConnectionPool;
import org.omg.PortableInterceptor.ServerRequestInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yana on 01.12.2015.
 */
public class WorkerTeamDAO extends AbstractDAO<WorkerTeam> {
    public WorkerTeamDAO(ConnectionPool pool) {
        super(pool);
    }

    @Override
    public void add(WorkerTeam workerTeam) {
        String sql = "INSERT INTO worker_team VALUES (" + workerTeam.getWorkerId() + ", " +
                workerTeam.getTeamId() + ")";
        executeModifyQuery(sql);
    }

    @Override
    public void edit(WorkerTeam workerTeam) {
    }

    @Override
    public void delete(WorkerTeam workerTeam) {
    }

    @Override
    public WorkerTeam getItem(int id) {
        return null;
    }

    @Override
    public List<WorkerTeam> getAll() {
        return null;
    }

    public ArrayList<Worker> getAllWorkersInTeam(int teamId) {
        ArrayList<Worker> allWorkersInTeam = new ArrayList<>();
        String sql = "SELECT * from worker_team WHERE id_team=" + teamId;
        Connection conn = pool.getConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                WorkerDAO workerDAO = new WorkerDAO(pool);
                allWorkersInTeam.add(workerDAO.getItem(rs.getInt("id_worker")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allWorkersInTeam;
    }

    public void deleteFromTeam(int teamId, int workerId) {
        String sql = "DELETE FROM worker_team WHERE id_team=" + teamId + " AND id_worker=" + workerId;
        executeModifyQuery(sql);
    }

    public void addToTeam(int teamId, int workerId) {
        String sql = "INSERT INTO worker_team VALUES(" + teamId + ", " + workerId + ")";
        executeModifyQuery(sql);
    }

    public Worker getWorker(int teamId, int workerId) {
        ArrayList<Worker> workers = getAllWorkersInTeam(teamId);
        for (Worker worker : workers) {
            if (worker.getWorkerId() == workerId)
                return worker;
        }
        return null;
    }
}
