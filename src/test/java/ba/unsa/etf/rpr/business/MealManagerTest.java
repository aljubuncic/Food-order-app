package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.MealDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

/**
 * Test class for MealManager
 */
public class MealManagerTest {
    private MealManager mealManager;
    private Meal meal;
    private MealDaoSQLImpl mealDaoSQL;
    private List<Meal> meals;

    /**
     * This method will be called before each test to initialize objects needed
     */
    @BeforeEach
    public void initializeObjectsWeNeed(){
        mealManager = Mockito.mock(MealManager.class);
        mealDaoSQL = Mockito.mock(MealDaoSQLImpl.class);
        meal = new Meal("Cevapi",5,300,"Main dish");
    }

    /**
     * This method tests adding meal
     * @throws OrderException
     */
    @Test
    void addNewMeal() throws OrderException {
        mealManager.add(meal);
        Assertions.assertTrue(true);
        Mockito.verify(mealManager).add(meal);
    }
    /**
     * This method tests updating meal
     * @throws OrderException
     */
    @Test
    void updateMeal() throws OrderException {
        mealManager.update(meal);
        Assertions.assertTrue(true);
        Mockito.verify(mealManager).update(meal);
    }
}
