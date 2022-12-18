package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoSQLImpl extends AbstractDao<User>implements UserDao{

    public UserDaoSQLImpl() {
        super("User");
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
        return null;
    }

    @Override
    public User getByTelephoneNumber(String telephoneNumber) throws OrderException {
        return null;
    }

    @Override
    public List<User> searchByNameAndSurname(String name, String surname) throws OrderException {
        return null;
    }

    @Override
    public User getByAddress(String address) throws OrderException {
        return null;
    }
}
