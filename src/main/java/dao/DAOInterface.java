package dao;

import java.util.List;

/**
 * Created by Yana on 28.11.2015.
 */
public interface DAOInterface<T> {
    public void add(T object);
    public void edit(T object);
    public void delete(T object);
//    public T getItem();
//    public List<T> getAll();
}
