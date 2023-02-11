package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.util.List;
/**
 * Business Logic Layer for management of Meals
 */
public class MealManager {
    public List<Meal> getAll() throws OrderException {
        return DaoFactory.mealDao().getAll();
    }
    public void delete(Meal meal) throws OrderException {
        new Order_MealManager().deleteMeal(meal);
        DaoFactory.mealDao().delete(meal.getId());
    }
    public Meal add(Meal meal) throws OrderException {
        return DaoFactory.mealDao().add(meal);
    }
    public Meal update(Meal meal) throws OrderException {
        return DaoFactory.mealDao().update(meal);
    }
}
