package dao;

import controller.ConnectionPool;

/**
 * Created by Yana on 29.11.2015.
 */
public class DAOFactory {
    public FlightDAO getFlightDAO(ConnectionPool pool) {
        return new FlightDAO(pool);
    }

    public WorkerDAO getWorkerDAO(ConnectionPool pool) {
        return new WorkerDAO(pool);
    }

    public ConnectionPool getConnectionPool() {
        return ConnectionPool.getInstance("jdbc:mysql://localhost:3306/airline_db", "root", "root", 10);
    }
}
