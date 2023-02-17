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

}
