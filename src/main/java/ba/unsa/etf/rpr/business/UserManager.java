package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.util.List;

/**
 * Business Logic Layer for management of Users
 */

public class UserManager {
    /**
     * Checks if user with specified credentials exists in the database
     * @param username of the user
     * @param password of the user
     * @return user
     * @throws OrderException in case of user not existing in the database or password not being correct
     */
    public User validateLoginCredentials(String username, String password) throws OrderException {
        User user;
        try{
            user = DaoFactory.userDao().getByUsername(username);
        }
        catch(OrderException e){
            throw new OrderException("User with username " + username + " does not exist");
        }
        if(!user.getPassword().equals(password))
            throw new OrderException("Incorrect password");
        return user;
    }

    /**
     * Adds the user in the database
     * @param user to be added
     * @return added user
     * @throws OrderException in case of user already existing in the database
     */
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
    /**
     * Returns all users from database
     * @return List of users
     * @throws OrderException
     */
    public List<User> getAll() throws OrderException {
        return DaoFactory.userDao().getAll();
    }

    /**
     * Deletes all rows in Orders associated with the user and then deletes a user from database
     * @param user to be deleted
     * @throws OrderException
     */
    public void delete(User user) throws OrderException {
        OrderManager orderManager = new OrderManager();
        List<Order> orders =  orderManager.getByUser(user);
        for(Order order : orders)
            orderManager.delete(order);
        DaoFactory.userDao().delete(user.getId());
    }
}
