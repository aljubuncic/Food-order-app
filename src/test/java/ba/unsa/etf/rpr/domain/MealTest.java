package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for Meal
 */
public class MealTest {
    /**
     * This method tests equals method
     */
    @Test
    void equalsMethodTest(){
        Meal meal1 = new Meal("Pizza",4,250,"Main dish");
        Meal meal2 = new Meal("Pizza",4,250,"Main dish");
        Assertions.assertTrue(meal1.equals(meal2));
    }

    /**
     * This method tests toString method
     */
    @Test
    void toStringMethodTest(){
        Meal meal = new Meal("Pizza",4,250,"Main dish");
        String string = "Meal{id=0, name='Pizza', price=4.0, quantity=250, typeOfMeal=Main dish}";
        Assertions.assertEquals(meal.toString(), string);
    }
}
