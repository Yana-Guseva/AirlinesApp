package dao;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.util.List;

/**
 * Created by Yana on 28.11.2015.
 */
public interface DAOInterface<T> {
    public void add(T object) throws MySQLIntegrityConstraintViolationException;
    public void edit(T object);
    public void delete(T object);
    public T getItem(int id);
    public List<T> getAll();
}
