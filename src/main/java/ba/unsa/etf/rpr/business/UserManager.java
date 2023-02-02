package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;

/**
 * Business Logic Layer for management of Users
 */

public class UserManager {
    public void validateLoginCredentials(String username, String password) throws OrderException {
        User user;
        try{
            user = DaoFactory.userDao().getByUsername(username);
        }
        catch(OrderException e){
            throw new OrderException("User with username " + username + " does not exist");
        }
        if(!user.getPassword().equals(password))
            throw new OrderException("Incorrect password");
    }

    public User getByUsername(String username) throws OrderException {
        return DaoFactory.userDao().getByUsername(username);
    }
    public User add(User user) throws OrderException {
        try {
            return DaoFactory.userDao().add(user);
        } catch (OrderException e) {
            if (e.getMessage().contains("UNIQUE")) {
                int beginIndex = e.getMessage().indexOf('.') + 1;
                int endIndex = e.getMessage().indexOf('_');
                throw new OrderException("User with the same " + e.getMessage().substring(beginIndex, endIndex) + " already exists");
            }
        }
        return null;
    }
}
