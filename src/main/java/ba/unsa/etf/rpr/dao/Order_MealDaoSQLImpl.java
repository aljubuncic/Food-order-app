package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Order_Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class Order_MealDaoSQLImpl extends AbstractDao<Order_Meal> implements Order_MealDao{
    private static Order_MealDaoSQLImpl instance = null;
    private Order_MealDaoSQLImpl(){
        super("Orders_Meals");
    }
    public static Order_MealDaoSQLImpl getInstance(){
        if(instance == null)
            instance = new Order_MealDaoSQLImpl();
        return instance;
    }
    @Override
    public Order_Meal rowToObject(ResultSet rs) throws OrderException {
        Order_Meal order_meal=new Order_Meal();
        try {
            order_meal.setId(rs.getInt("id"));
            order_meal.getOrder().setId(rs.getInt("idOrder"));
            order_meal.getMeal().setId(rs.getInt("idMeal"));
            return order_meal;
        } catch (SQLException e) {
            throw new OrderException(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> objectToRow(Order_Meal object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id",object.getId());
        item.put("idOrder",object.getOrder().getId());
        item.put("idMeal",object.getMeal().getId());
        return item;
    }
}
