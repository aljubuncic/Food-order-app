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
     * Checks if the specified meal with the same name, quantity (portion) and type already exists in the database
     * @param meal to be checked
     * @throws OrderException in case of meal already existing in the database
     */
    public void doesMealAlreadyExist(Meal meal) throws OrderException{
        try{
            DaoFactory.mealDao().getByNameQuantityAndType(meal.getName(), meal.getQuantity(), meal.getType());
        }catch(OrderException e){
            return;
        }
        throw new OrderException("Meal with same name, quantity (portion) and type already exists");
    }

    /**
     * Returns all meals from database
     * @return List of meals
     * @throws OrderException
     */
    public List<Meal> getAll() throws OrderException {
        return DaoFactory.mealDao().getAll();
    }

    /**
     * Deletes all rows in Orders_Meals associated with the meal and then deletes a meal from database
     * @param meal to be deleted
     * @throws OrderException
     */
    public void delete(Meal meal) throws OrderException {
        new Order_MealManager().deleteMeal(meal);
        DaoFactory.mealDao().delete(meal.getId());
    }

    /**
     * Checks if the meal already exists, and adds it to the database
     * @param meal to be added
     * @return added meal
     * @throws OrderException
     */
    public Meal add(Meal meal) throws OrderException {
        doesMealAlreadyExist(meal);
        return DaoFactory.mealDao().add(meal);
    }

    /**
     * Checks if the meal already exists, and updates it to the database
     * @param meal to be updated
     * @return updated meal
     * @throws OrderException
     */
    public Meal update(Meal meal) throws OrderException {
        doesMealAlreadyExist(meal);
        return DaoFactory.mealDao().update(meal);
    }

    /**
     * Returns a meal with the specified id
     * @param id of the meal to be returned
     * @return meal
     * @throws OrderException
     */
    public Meal getById(int id) throws OrderException {
        return DaoFactory.mealDao().getById(id);
    }
}
