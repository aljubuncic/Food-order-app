package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.MealManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for CLI (Command Line Interface) implementation
 */
public class App
{
    private static final Option register = new Option("r","register",false,"Register - enter your name, surname, username, password and telephone number");
    private static final Option displayMeals = new Option("m","display-meals",false,"Display all available meals");
    private static final Option order = new Option("o","order",false,"Add new order to OrderDatabase (first and second argument are username and password, the rest of arguments are IDs of meals)");

    private static final UserManager userManager = new UserManager();
    private static final MealManager mealManager = new MealManager();
    private static final OrderManager orderManager = new OrderManager();

    /**
     * Prints all the options on the console
     * @param options - options to be printed
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar Food-order-app.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    /**
     * Adds option objects to Options object
     * @return - Options object
     */
    public static Options addOptions() {
        Options options = new Options();
        options.addOption(order);
        options.addOption(register);
        options.addOption(displayMeals);
        return options;
    }



}
