package dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.activation.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Yana on 28.11.2015.
 */
public class DAOManager {
    private MysqlDataSource dataSource;
    private Connection conn;



    public DAOManager() {
        dataSource.setUrl("jdbc:mysql://localhost:3306/airline_db");
        dataSource.setUser("root");
        dataSource.setPassword("root");
    }

    public void open() {
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
