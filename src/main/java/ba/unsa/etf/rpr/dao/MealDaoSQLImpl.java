package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MealDaoSQLImpl extends AbstractDao<Meal> implements MealDao {

    public MealDaoSQLImpl(){
        super("Meals");
    }
    @Override
    public Meal rowToObject(ResultSet rs) throws OrderException {
        Meal meal=new Meal();
        try{
            meal.setId(rs.getInt("id"));
            meal.setName(rs.getString("name"));
            meal.setPrice(rs.getDouble("price"));
            meal.setType(rs.getString("type"));
            return meal;
        }catch(Exception e){
            throw new OrderException(e.getMessage());
        }
    }

    @Override
    public List<Meal> searchByType(String typeOfMeal) throws OrderException {
        return null;
    }
}
