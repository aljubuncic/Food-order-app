package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * Test class for Order
 */
public class OrderTest {
    /**
     * This method tests equals method
     */
    @Test
    void equalsMethodTest(){
        User user = new User("name","surname","username","password","007");
        Date date = new Date();
        Order order1 = new Order(user,date,20);
        Order order2 = new Order(user,date,20);
        Assertions.assertTrue(order1.equals(order2));
    }
    /**
     * This method tests toString method
     */
    @Test
    void toStringMethodTest(){
        User user = new User("name","surname","username","password","007");
        Date date = new Date();
        Order order = new Order(user,date,20);
        String string = "Order{id=0, user="+user.toString()+", dateOfOrder="+date+", price=20.0, confirmationEmail='null', address='null'}";
        Assertions.assertEquals(order.toString(), string);
    }
}
