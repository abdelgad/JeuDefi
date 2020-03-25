package dao;

import java.sql.Connection;
import java.util.LinkedList;

abstract class DAO<T>
{
    Connection connection = ConnectionOracle.getConnectionInstance();

    //public abstract T find(String username, String password);
    //public abstract boolean create(T objectToInsertInDB);
    //public abstract LinkedList<T> findAll();
}
