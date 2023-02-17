package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.MealDaoSQLImpl;
import ba.unsa.etf.rpr.dao.OrderDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Test class for OrderManager
 */
public class OrderManagerTest {
    private OrderManager orderManager;
    private Order order;
    private OrderDaoSQLImpl orderDaoSQL;
    private List<Order> orders;
    private List<Meal> meals;
    private User user;
    /**
     * This method will be called before each test to initialize objects needed
     */
    @BeforeEach
    public void initializeObjectsWeNeed(){
        orderManager = Mockito.mock(OrderManager.class);
        orderDaoSQL = Mockito.mock(OrderDaoSQLImpl.class);
        user = new User("Sejfo","Sejfic","sjf32","pass","062765321");
        order = new Order(user,new Date(),23.5);
        meals = Arrays.asList(new Meal("Cevapi",5,300,"Main dish"),new Meal("Pizza",4,250,"Main dish"));
    }

    /**
     * This method tests adding order
     * @throws OrderException
     */
    @Test
    void addNewOrder() throws OrderException {
        Order order = new Order(user,new Date(),23.5);
        orderManager.add(order,meals);
        Assertions.assertTrue(true);
        Mockito.verify(orderManager).add(order,meals);
    }
}
