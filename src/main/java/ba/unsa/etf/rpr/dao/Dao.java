package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.OrderException;

import java.util.List;

/**
 * Root interface for all DAO classes
 */
public interface Dao <Type>{
    /**
     * get entity from database base on ID
     * @param id primary key of entity
     * @return Entity from database
     */
    Type getById(int id) throws OrderException;

    /**
     * Saves entity into database
     * @param item bean for saving to database
     * @return saved item with id field populated
     */
    Type add(Type item) throws OrderException;

    /**
     * Fully updates entity in database based on id (primary) match.
     * @param item - bean to be updated. id must be populated
     * @return updated version of bean
     */
    Type update(Type item) throws OrderException;

    /**
     * Hard delete of item from database with given id
     * @param id - primary key of entity
     */
    void delete(int id) throws OrderException;

    /**
     * Lists all entities from database.
     * WARNING: Very slow operation because it reads all records.
     * @return List of entities from database
     */
    List<Type> getAll() throws OrderException;
}
