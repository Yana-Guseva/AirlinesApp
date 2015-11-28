package controller;

/**
 * Created by Yana on 29.11.2015.
 */
public class DBConnectionPool {
    private String url;
    private String user;
    private String password;
    private int maxConn;
    private static DBConnectionPool instance;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver error");
            e.printStackTrace();
        }
    }

    private DBConnectionPool(String url, String user, String password, int maxConn) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.maxConn = maxConn;
    }

    public DBConnectionPool getInstance(String url, String user, String password, int maxConn) {
        if (instance == null) {
            instance = new DBConnectionPool(url, user, password, maxConn);
        }
        return instance;
    }
}
