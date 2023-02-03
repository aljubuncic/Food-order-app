package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.util.List;

public class MealManager {
    public List<Meal> getAll() throws OrderException {
        return DaoFactory.mealDao().getAll();
    }
}
