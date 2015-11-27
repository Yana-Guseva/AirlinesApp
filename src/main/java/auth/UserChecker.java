package auth;

import java.sql.*;

/**
 * Created by Yana on 22.11.2015.
 */
public class UserChecker {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось подключиться к базе данных");
            e.printStackTrace();
        }
    }

    public static boolean isUserCorrect(String login, String pwd) {
        if (login == null || pwd == null) {
            return false;
        }

        String sql = "SELECT id FROM user WHERE login = ? AND password = ?";
        int result = 0;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, pwd);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("id");
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result != 0;
    }

    public boolean isUserAdmin(String login, String pwd) {
        String sql = "SELECT role FROM user WHERE login = ? AND password = ?";
        String result = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, pwd);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("role");
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.equals("administrator");
    }

    public boolean isUserDispatcher(String login, String pwd) {
        String sql = "SELECT role FROM user WHERE login = ? AND password = ?";
        String result = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, pwd);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("role");
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.equals("dispatcher");
    }

    public boolean isUser(String login, String pwd) {
        String sql = "SELECT role FROM user WHERE login = ? AND password = ?";
        String result = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, pwd);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("role");
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.equals("user");
    }

    private static Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/airline_db";
        String jdbcUser = "root";
        String jdbcPassword = "root";
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }
}
