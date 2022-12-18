package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.ResultSet;
import java.util.List;

public class MealDaoSQLImpl extends AbstractDao<Meal> implements MealDao {

    public MealDaoSQLImpl(){
        super("Meal");
    }
    @Override
    public Meal rowToObject(ResultSet rs) throws OrderException {
        return null;
    }

    @Override
    public List<Meal> searchByType(String typeOfMeal) throws OrderException {
        return null;
    }
}
