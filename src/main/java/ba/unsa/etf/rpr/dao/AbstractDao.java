package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Identifiable;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.io.FileReader;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Abstract class which implements core Dao method form interface Dao for all entities in the database
 */

public abstract class AbstractDao <Type extends Identifiable> implements Dao<Type> {

    private Connection connection;
    private String tableName;

    public AbstractDao(String tableName) {

    }

    public abstract Type rowToObject(ResultSet rs) throws OrderException;

    @Override
    public Type getById(int id) throws OrderException {
        return null;
    }

    @Override
    public Type add(Type item) throws OrderException {
        return null;
    }

    @Override
    public Type update(Type item) throws OrderException {
        return null;
    }

    @Override
    public void delete(int id) throws OrderException {
        return;
    }

    @Override
    public List<Type> getAll() throws OrderException {
        return null;
    }
}
