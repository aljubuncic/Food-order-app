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
}
