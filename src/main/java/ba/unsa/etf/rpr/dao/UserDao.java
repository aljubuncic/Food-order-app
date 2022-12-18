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

    /**
     * Returns user with specified telephone number
     * @param telephoneNumber
     * @return User
     * @throws OrderException
     */
    User getByTelephoneNumber(String telephoneNumber) throws OrderException;

    /**
     * Returns all users with their specified name and surname
     * @param name
     * @param surname
     * @return list of Users
     * @throws OrderException
     */
    List<User> searchByNameAndSurname(String name, String surname) throws OrderException;

    /**
     * Returns user with specified address
     * @param address
     * @return User
     * @throws OrderException
     */
    List<User> getByAddress(String address) throws OrderException;
}
