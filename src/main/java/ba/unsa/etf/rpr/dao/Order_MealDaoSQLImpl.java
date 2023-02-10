package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.Order_Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
            order_meal.setOrder(DaoFactory.orderDao().getById(rs.getInt("idOrder")));
            order_meal.setMeal(DaoFactory.mealDao().getById(rs.getInt("idMeal")));
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
    @Override
    public List<Meal> getMealsFromOrder(Order order) throws OrderException {
        try{
            PreparedStatement statement = getConnection().prepareStatement("SELECT idMeal FROM Orders_Meals WHERE idOrder = ?");
            statement.setObject(1,order.getId());
            ResultSet queryResult = statement.executeQuery();
            List<Meal> meals = new ArrayList<>();
            while(queryResult.next()){
                Meal meal = DaoFactory.mealDao().getById(queryResult.getInt("idMeal"));
                meals.add(meal);
            }
            queryResult.close();
            return meals;
        }
        catch (SQLException e){
            throw new OrderException(e.getMessage());
        }
    }
    @Override
    public void deleteOrder(int idOrder) throws OrderException {
        try{
            PreparedStatement statement= getConnection().prepareStatement("DELETE FROM Orders_Meals WHERE idOrder = ?");
            statement.setObject(1,idOrder);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new OrderException(e.getMessage());
        }
    }
}
