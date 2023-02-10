package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.Order_Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.util.List;

public interface Order_MealDao extends Dao<Order_Meal>{
    /**
     * Returns a list of meals from a specified order
     * @param order
     * @return
     * @throws OrderException
     */
    public List<Meal> getMealsFromOrder(Order order) throws OrderException;

    /**
     * Deletes all rows associated with the specified order
     * @param idOrder
     * @throws OrderException
     */
    public void deleteOrder(int idOrder) throws OrderException;
}
