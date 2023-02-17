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
     * @param order
     * @param orderList
     * @return
     * @throws OrderException
     */
    public Order add (Order order, List<Meal> orderList) throws OrderException {
        DaoFactory.orderDao().add(order);
        for(Meal meal : orderList) {
            Order_Meal order_meal = new Order_Meal();
            order_meal.setOrder(order);
            order_meal.setMeal(meal);
            DaoFactory.order_MealDao().add(order_meal);
        }
        return order;
    }
    public List<Order> getAll() throws OrderException {
        return DaoFactory.orderDao().getAll();
    }
    public void delete(Order order) throws OrderException {
        new Order_MealManager().deleteOrder(order);
        DaoFactory.orderDao().delete(order.getId());
    }
    public List<Order> getByUser(User user) throws OrderException {
        return DaoFactory.orderDao().getByUser(user);
    }
}
