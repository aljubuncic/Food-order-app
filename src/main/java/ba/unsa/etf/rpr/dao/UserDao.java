package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.util.List;

/**
 * Dao interface for User domain bean
 */

public interface UserDao extends Dao<User>{
    /**
     * Returns user with specified email
     * @param email
     * @return User
     * @throws OrderException
     */
    User getByEmail(String email) throws OrderException;


}
