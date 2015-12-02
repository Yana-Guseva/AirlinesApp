package dao;

import controller.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Yana on 28.11.2015.
 */
public abstract class AbstractDAO<T> implements DAOInterface<T> {
    protected ConnectionPool pool;

    public AbstractDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    public void executeModifyQuery(String sql) {
        try {
            Connection conn = pool.getConnection();
            conn.createStatement().execute(sql);
            pool.freeConnection(conn);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
