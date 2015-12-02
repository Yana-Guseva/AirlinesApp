package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Yana on 29.11.2015.
 */
public class ConnectionPool {
    private String url;
    private String user;
    private String password;
    private int maxConn;
    private static ConnectionPool instance;
    private ArrayList<Connection> connectionList;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver error");
            e.printStackTrace();
        }
    }

    private ConnectionPool(String url, String user, String password, int maxConn) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.maxConn = maxConn;

        connectionList = new ArrayList<Connection>();
        try {
            for (int i = 0; i < maxConn; i++) {
                Connection conn = DriverManager.getConnection(url, user, password);
                connectionList.add(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionPool getInstance(String url, String user, String password, int maxConn) {
        if (instance == null) {
            instance = new ConnectionPool(url, user, password, maxConn);
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection conn = null;
        if (!connectionList.isEmpty()) {
            conn = connectionList.get(connectionList.size() - 1);
            connectionList.remove(conn);

            try {
                if (conn.isClosed()) {
                    conn = getConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println("Can't create a new connection");
                e.printStackTrace();
            }
        }
        return conn;
    }

    public synchronized void freeConnection(Connection conn) {
        if ((conn != null) && (connectionList.size() < maxConn)) {
            connectionList.add(conn);
        } else {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void removeAll() {
        for (Connection conn : connectionList) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Can't close connection for pool");
                e.printStackTrace();
            }
            connectionList.clear();
        }
    }
}
