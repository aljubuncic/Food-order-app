package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.util.List;
/**
 * Business Logic Layer for management of Meals
 */
public class MealManager {
    /**
     * Checks if the specified meal with the same name and quantity (portion) already exists in the database
     * @param meal
     * @throws OrderException in case of meal already existing in the databse
     */
    public void doesMealAlreadyExist(Meal meal) throws OrderException{
        try{
            DaoFactory.mealDao().getByNameAndQuantity(meal.getName(), meal.getQuantity());
        }catch(OrderException e){
            return;
        }
        throw new OrderException("Meal with same portion name and quantity (portion) already exists");
    }
    public List<Meal> getAll() throws OrderException {
        return DaoFactory.mealDao().getAll();
    }
    public void delete(Meal meal) throws OrderException {
        new Order_MealManager().deleteMeal(meal);
        DaoFactory.mealDao().delete(meal.getId());
    }
    public Meal add(Meal meal) throws OrderException {
        doesMealAlreadyExist(meal);
        return DaoFactory.mealDao().add(meal);
    }
    public Meal update(Meal meal) throws OrderException {
        doesMealAlreadyExist(meal);
        return DaoFactory.mealDao().update(meal);
    }
}
