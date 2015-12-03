package auth;

import controller.ConnectionPool;
import dao.DAOFactory;

import java.sql.*;

/**
 * Created by Yana on 22.11.2015.
 */
public class UserChecker {

    public static boolean isUserCorrect(String login, String pwd) {
        if (login == null || pwd == null) {
            return false;
        }

        String sql = "SELECT * FROM user WHERE login = ? AND password = ?";
        String resLogin = "";

        DAOFactory factory = new DAOFactory();
        ConnectionPool pool = factory.getConnectionPool();

        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, pwd);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                resLogin = resultSet.getString("login");
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !resLogin.equals("");
    }
}
