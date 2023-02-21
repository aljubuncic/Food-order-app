package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserDaoSQLImpl extends AbstractDao<User>implements UserDao{
    private static UserDaoSQLImpl instance = null;

    private UserDaoSQLImpl() {
        super("Users");
    }
    public static UserDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new UserDaoSQLImpl();
        return instance;
    }

    @Override
    public User rowToObject(ResultSet rs) throws OrderException {
        User user=new User();
        try {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setAddress(rs.getString("address"));
            user.setTelephoneNumber(rs.getString("telephoneNumber"));
            return user;
        } catch (SQLException e) {
            throw new OrderException(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> objectToRow(User object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id",object.getId());
        item.put("name",object.getName());
        item.put("surname",object.getSurname());
        item.put("username",object.getUsername());
        item.put("email",object.getEmail());
        item.put("password",object.getPassword());
        item.put("address",object.getAddress());
        item.put("telephoneNumber",object.getTelephoneNumber());
        return item;
    }

    @Override
    public User getByUsername(String username) throws OrderException {
        try{
            PreparedStatement statement= getConnection().prepareStatement("SELECT * FROM Users WHERE username = ?");
            statement.setObject(1,username);
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
}
