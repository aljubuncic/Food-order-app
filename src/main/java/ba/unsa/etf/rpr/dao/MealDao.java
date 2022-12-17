package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.util.List;

/**
 * Dao interface for Meal domain bean
 */

public interface MealDao extends Dao<Meal>{

    /**
     * Returns meals of specified type
     * @param typeOfMeal
     * @return list of Meals
     * @throws OrderException
     */

    List<Meal> searchByType(String typeOfMeal) throws OrderException;
}
