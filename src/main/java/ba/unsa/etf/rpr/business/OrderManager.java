package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.Order_Meal;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.util.Date;
import java.util.List;

/**
 * Business Logic Layer for management of Orders
 */
public class OrderManager {
    /**
     * Adds a new order and order list in the database
     * @param user
     * @param orderList
     * @param priceOfOrder
     * @param confirmationEmail
     * @param address
     * @throws OrderException
     */
    public void add (User user, List<Meal> orderList,double priceOfOrder,String confirmationEmail, String address) throws OrderException {
        Order order = new Order();
        order.setUser(user);
        order.setDateOfOrder(new Date());
        order.setPrice(priceOfOrder);
        if(!confirmationEmail.isEmpty())
            order.setConfirmationEmail(confirmationEmail);
        if(!address.isEmpty())
            order.setAddress(address);
        DaoFactory.orderDao().add(order);
        for(Meal meal : orderList) {
            Order_Meal order_meal = new Order_Meal();
            order_meal.setOrder(order);
            order_meal.setMeal(meal);
            DaoFactory.order_MealDao().add(order_meal);
        }
    }
}