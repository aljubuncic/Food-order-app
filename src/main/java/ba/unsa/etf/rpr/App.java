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

    /**
     * The main method for executing the commands given by the user
     * @param args - eventual arguments of commands
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cl = commandLineParser.parse(options, args);
        if((cl.hasOption(register.getOpt()) || cl.hasOption(register.getLongOpt())) && cl.hasOption((register.getLongOpt()))){
            User user = new User(cl.getArgList().get(0),cl.getArgList().get(1),cl.getArgList().get(2),cl.getArgList().get(3),cl.getArgList().get(4));
            try {
                userManager.add(user);
            }
            catch (OrderException e){
                System.out.println(e.getMessage());
                System.exit(1);
            }
            System.out.println("You have successfully registered");
        }
        else if((cl.hasOption(displayMeals.getOpt()) || cl.hasOption(displayMeals.getLongOpt())) && cl.hasOption((displayMeals.getLongOpt()))){
            mealManager.getAll().forEach(meal-> System.out.println(meal.getId() + " " + meal.getName() + " " + meal.getQuantity() + " " + meal.getPrice() + " "+ meal.getType()));
            System.out.println("To order please select your meals by typing the meal IDs (first number in a row)");
        }
        else if ((cl.hasOption(order.getOpt()) || cl.hasOption(order.getLongOpt())) && cl.hasOption((order.getLongOpt()))){
            if(cl.getArgList().size()==2) {
                System.out.println("Please select a meal");
                return;
            }
            User activeUser;
            try {
                String username = cl.getArgList().get(0);
                String password = cl.getArgList().get(1);
                activeUser = userManager.validateLoginCredentials(username, password);
            }
            catch (OrderException e){
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("You have successfully logged in");
            List<Meal> orderList = new LinkedList<>();
            double priceOfOrder=0;
            for(int i = 2;i<cl.getArgList().size();i++) {
                Meal meal = mealManager.getById(Integer.parseInt(cl.getArgList().get(i)));
                orderList.add(meal);
                priceOfOrder+=meal.getPrice();
            }
            Order order = new Order(activeUser,new Date(),priceOfOrder);
            try {
                orderManager.add(order, orderList);
            }
            catch (OrderException e){
                System.out.println(e.getMessage());
                System.exit(-1);
            }
            System.out.println("Order has been successfully placed. ID of your order is " + order.getId());
            System.out.println("Total price to pay for: " + order.getPrice());
            System.out.println("Bon appetite!");
        }
        else {
            printFormattedOptions(options);
            System.exit(-1);
        }
    }

}
