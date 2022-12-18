package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoSQLImpl extends AbstractDao<User>implements UserDao{

    public UserDaoSQLImpl() {
        super("Users");
    }

    @Override
    public User rowToObject(ResultSet rs) throws OrderException {
        User user=new User();
        try {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setAddress(rs.getString("address"));
            user.setTelephoneNumber(rs.getString("telephone_number"));
            return user;
        } catch (SQLException e) {
            throw new OrderException(e.getMessage());
        }
    }

    @Override
    public User getByEmail(String email) throws OrderException {
        try{
            PreparedStatement statement= getConnection().prepareStatement("SELECT * FROM User WHERE email = ?");
            statement.setObject(1,email);
            ResultSet queryResult=statement.executeQuery();
            if(!queryResult.next())
                throw new OrderException("Object not Found");
            User user = rowToObject(queryResult);
            queryResult.close();
            return user;
        }catch(SQLException e){
            throw new OrderException(e.getMessage());
        }
    }

    @Override
    public User getByTelephoneNumber(String telephoneNumber) throws OrderException {
        try{
            PreparedStatement statement= getConnection().prepareStatement("SELECT * FROM User WHERE telephoneNumber = ?");
            statement.setObject(1,telephoneNumber);
            ResultSet queryResult=statement.executeQuery();
            if(!queryResult.next())
                throw new OrderException("Object not Found");
            User user = rowToObject(queryResult);
            queryResult.close();
            return user;
        }catch(SQLException e){
            throw new OrderException(e.getMessage());
        }
    }

    @Override
    public List<User> searchByNameAndSurname(String name, String surname) throws OrderException {
        try{
            PreparedStatement statement= getConnection().prepareStatement("SELECT * FROM User WHERE name = ? AND surname = ?");
            statement.setObject(1,name);
            statement.setObject(2,surname);
            ResultSet queryResult=statement.executeQuery();
            List<User> users = new ArrayList<User>();
            while(queryResult.next()) {
                User record = rowToObject(queryResult);
                users.add(record);
            }
            if(users.size()==0)
                throw new OrderException("Object not found");
            queryResult.close();
            return users;
        }catch(SQLException e){
            throw new OrderException(e.getMessage());
        }
    }

    @Override
    public List<User> getByAddress(String address) throws OrderException {
        try{
            PreparedStatement statement= getConnection().prepareStatement("SELECT * FROM User WHERE address = ?");
            statement.setObject(1,address);
            ResultSet queryResult=statement.executeQuery();
            List<User> users = new ArrayList<>();
            while(queryResult.next()) {
                User record = rowToObject(queryResult);
                users.add(record);
            }
            if(users.size()==0)
                throw new OrderException("Object not found");
            queryResult.close();
            return users;
        }catch(SQLException e){
            throw new OrderException(e.getMessage());
        }
    }
}
