package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Identifiable;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Abstract class which implements core Dao method form interface Dao for all entities in the database
 */

public abstract class AbstractDao <Type extends Identifiable> implements Dao<Type> {

    private Connection connection;
    private String tableName;

    public AbstractDao(String tableName) {
        try {
            this.tableName = tableName;
            Properties properties=new Properties();
            FileReader reader=new FileReader(".idea/db.properties");
            properties.load(reader);
            String url = properties.getProperty("connection");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            this.connection = DriverManager.getConnection(url,username,password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public abstract Type rowToObject(ResultSet rs) throws OrderException;

    @Override
    public Type getById(int id) throws OrderException {
        try{
            PreparedStatement statement=this.connection.prepareStatement("SELECT * FROM "+this.tableName+"WHERE id = ?");
            statement.setInt(1,id);
            ResultSet queryResult = statement.executeQuery();
            if(queryResult.next()){
                Type record= rowToObject(queryResult);
                queryResult.close();
                return record;
            }
            else
                throw new OrderException("Object not found");
        }
        catch(SQLException e){
            throw new OrderException(e.getMessage());
        }
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
        try{
            PreparedStatement statement=this.connection.prepareStatement("SELECT * FROM "+tableName);
            ResultSet queryResult = statement.executeQuery();
            List<Type> records = new ArrayList<Type>();
            while(queryResult.next()){
                Type object = rowToObject(queryResult);
                records.add(object);
            }
            queryResult.close();
            return records;
        }catch(SQLException e){
            throw new OrderException(e.getMessage());
        }
    }
}
