package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.util.List;

/**
 * Business Logic Layer for management of Order_Meal objects
 */

public class Order_MealManager {
    public List<Meal> getMealsFromOrder(Order order) throws OrderException {
        return DaoFactory.order_MealDao().getMealsFromOrder(order);
    }
}