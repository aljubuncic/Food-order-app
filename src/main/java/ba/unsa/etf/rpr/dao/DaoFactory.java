package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 * Prevents establishing multiple connections to the database more than it is needed
 */

public class DaoFactory {
    private static final UserDao userDao = new UserDaoSQLImpl();
    private static final OrderDao orderDao = new OrderDaoSQLImpl();
    private static final Order_MealDao order_MealDao = new Order_MealDaoSQLImpl();
    private static final MealDao mealDao = new MealDaoSQLImpl();

    private DaoFactory(){}

    public static UserDao userDao(){
        return userDao;
    }

    public static OrderDao orderDao(){
        return orderDao;
    }

    public static Order_MealDao order_MealDao(){
        return order_MealDao;
    }

    public static MealDao mealDao(){
        return mealDao;
    }
}
