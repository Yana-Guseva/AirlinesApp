package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yana on 28.11.2015.
 */
public abstract class AbstractDAO<T> implements DAOInterface<T> {
    private Connection conn;

    public AbstractDAO(Connection conn) {
        this.conn = conn;
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver error");
            e.printStackTrace();
        }
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

    private static Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/airline_db";
        String jdbcUser = "root";
        String jdbcPassword = "root";
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }
}
