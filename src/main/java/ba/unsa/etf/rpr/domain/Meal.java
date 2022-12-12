package ba.unsa.etf.rpr.domain;

/**
 * Bean of Meal
 */

public class Meal {
    private int idMeal;
    private String name;
    private double price;
    private int quantity;
    private  enum type{Appetizer,MainDish,Desert,Drink};

    public int getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(int idMeal) {
        this.idMeal = idMeal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
