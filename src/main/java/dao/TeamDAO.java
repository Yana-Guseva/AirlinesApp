package dao;

import com.yana.model.Team;
import controller.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yana on 01.12.2015.
 */
public class TeamDAO extends AbstractDAO<Team> {
    public TeamDAO(ConnectionPool pool) {
        super(pool);
    }

    @Override
    public void add(Team team) {
        String sql = "INSERT INTO team VALUES (" + team.getTeamId() + ", " + team.getNumber() + ")";
        executeModifyQuery(sql);
    }

    @Override
    public void edit(Team team) {
        String sql = "UPDATE team SET number=" + team.getNumber() + "WHERE id=" + team.getTeamId();
        executeModifyQuery(sql);
    }

    @Override
    public void delete(Team team) {
        String sql = "DELETE * FROM team WHERE id=" + team.getTeamId();
        executeModifyQuery(sql);
    }

    @Override
    public Team getItem(int id) {
        Team team = new Team();
        String sql = "SELECT * FROM team WHERE id=" + id;
        Connection conn = pool.getConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                team.setTeamId(rs.getInt("id"));
                team.setNumber(rs.getInt("number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(conn);
        }
        return team;
    }

    @Override
    public List<Team> getAll() {
        ArrayList<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM team";

        Connection conn = pool.getConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            Team team = new Team();
            while (rs.next()) {
                team.setTeamId(rs.getInt("id"));
                team.setNumber(rs.getInt("number"));
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(conn);
        }
        return teams;
    }
}
