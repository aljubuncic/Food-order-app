package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for User
 */
public class UserTest {
    /**
     * This method tests equals method
     */
    @Test
    void equalsMethodTest(){
        User user1 = new User("name","surname","username","password","007");
        User user2 = new User("name","surname","username","password","007");
        Assertions.assertTrue(user1.equals(user2));
    }
    /**
     * This method tests toString method
     */
    @Test
    void toStringMethodTest(){
        User user = new User("name","surname","username","password","007");
        String string = "User{id=0, name='name', surname='surname', username=username, email='null', password='password', address='null', telephoneNumber='007'}";
        Assertions.assertEquals(user.toString(), string);
    }
}
