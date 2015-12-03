package dao;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.yana.model.User;
import controller.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yana on 02.12.2015.
 */
public class UserDAO extends AbstractDAO<User> {
    public UserDAO(ConnectionPool pool) {
        super(pool);
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO user VALUES ('" + user.getName() + "', '" + user.getLogin() + "', '" +
                user.getPassword() + "', '" + user.getRole() + "')";
        executeModifyQuery(sql);
    }

    @Override
    public void edit(User object) {

    }

    @Override
    public void delete(User object) {

    }

    @Override
    public User getItem(int id) {

        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public User getUser(String login) {
        String sql = "SELECT * FROM user WHERE login = '" + login + "'";
        Connection conn = pool.getConnection();
        User user = null;
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                user = new User();;
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            user = null;
        }
        pool.freeConnection(conn);
        return user;
    }
}
