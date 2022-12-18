package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Order_Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.ResultSet;

public class Order_MealDaoSQLImpl extends AbstractDao<Order_Meal> implements Order_MealDao{

    public Order_MealDaoSQLImpl(){
        super("Orders_Meals");
    }
    @Override
    public Order_Meal rowToObject(ResultSet rs) throws OrderException {
        return null;
    }
}
