package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MealDaoSQLImpl extends AbstractDao<Meal> implements MealDao {
    private static MealDaoSQLImpl instance = null;
    private MealDaoSQLImpl(){
        super("Meals");
    }
    public static MealDaoSQLImpl getInstance(){
        if(instance == null)
            instance = new MealDaoSQLImpl();
        return instance;
    }
    @Override
    public Meal rowToObject(ResultSet rs) throws OrderException {
        Meal meal=new Meal();
        try{
            meal.setId(rs.getInt("id"));
            meal.setName(rs.getString("name"));
            meal.setPrice(rs.getDouble("price"));
            meal.setQuantity(rs.getInt("quantity"));
            meal.setType((rs.getString("type")));
            return meal;
        }catch(Exception e){
            throw new OrderException(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> objectToRow(Meal object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id",object.getId());
        item.put("name",object.getName());
        item.put("price",object.getPrice());
        item.put("quantity",object.getQuantity());
        item.put("type",object.getType());
        return item;
    }

    @Override
    public List<Meal> searchByType(String typeOfMeal) throws OrderException {
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM Meals WHERE type = ?");
            statement.setObject(1, typeOfMeal);
            ResultSet queryResult = statement.executeQuery();
            List<Meal> meals = new ArrayList<>();
            while (queryResult.next()) {
                Meal object = rowToObject(queryResult);
                meals.add(object);
            }
            if (meals.size() == 0)
                throw new OrderException("No " + typeOfMeal + " found");
            queryResult.close();
            return meals;
        } catch (SQLException e) {
            throw new OrderException(e.getMessage());
        }
    }

    @Override
    public Meal getByNameQuantityAndType(String name, int quantity,String type) throws OrderException {
        try{
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM Meals WHERE name = ? AND quantity = ? AND type = ?");
            statement.setObject(1, name);
            statement.setObject(2,quantity);
            statement.setObject(3,type);
            ResultSet queryResult = statement.executeQuery();
            if(!queryResult.next())
                throw new OrderException("Meal not found");
            return rowToObject(queryResult);
      }   catch (SQLException e) {
        throw new OrderException(e.getMessage());
    }
    }
}
