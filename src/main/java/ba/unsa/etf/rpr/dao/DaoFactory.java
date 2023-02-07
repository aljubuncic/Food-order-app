package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 * Prevents establishing multiple connections to the database more than it is needed
 */

public class DaoFactory {
    private static final UserDao userDao = UserDaoSQLImpl.getInstance();
    private static final OrderDao orderDao = OrderDaoSQLImpl.getInstance();
    private static final Order_MealDao order_MealDao = Order_MealDaoSQLImpl.getInstance();
    private static final MealDao mealDao = MealDaoSQLImpl.getInstance();

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
