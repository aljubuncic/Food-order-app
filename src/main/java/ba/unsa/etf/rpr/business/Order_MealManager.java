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
    /**
     * Deletes all rows in the database associated with the order
     * @param order Order object
     * @throws OrderException
     */
    public void deleteOrder(Order order) throws OrderException{
        DaoFactory.order_MealDao().deleteOrder(order.getId());
    }

    /**
     * Deletes all rows in the database associated with the meal
     * @param meal Meal object
     * @throws OrderException
     */
    public void deleteMeal(Meal meal) throws OrderException{
        DaoFactory.order_MealDao().deleteMeal(meal.getId());
    }

    /**
     * Returns all meals from specified order
     * @param order which contains meals
     * @return list of meals
     * @throws OrderException
     */
    public List<Meal> getMealsFromOrder(Order order) throws OrderException {
        return DaoFactory.order_MealDao().getMealsFromOrder(order);
    }
}
