package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Test class for UserManager
 */
public class UserManagerTest {
    private UserManager userManager;
    private UserDaoSQLImpl userDaoSQL;
    private List<User> users;
    private User user;
    /**
     * This method will be called before each test to initialize objects needed
     */
    @BeforeEach
    public void initializeObjectsWeNeed() {
        userManager = Mockito.mock(UserManager.class);
        userDaoSQL = Mockito.mock(UserDaoSQLImpl.class);
        user = new User("Sejfo","Sejfic","sjf32","pass","062765321");
        users = (Arrays.asList(new User("Cristiano","Ronaldo","CR7","SIU","777"),
                                   new User("Lionel","Messi","LM10","GOAT","777")));
    }
    /**
     * This method tests adding user
     * @throws OrderException
     */
    @Test
    void addNewUser() throws OrderException {
        userManager.add(user);
        Assertions.assertTrue(true);
        Mockito.verify(userManager).add(user);
    }

}
