package ba.unsa.etf.rpr.domain;

import java.util.Objects;

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

   /* public type getType() {
        return type;
    }*/

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /*public void setType(type typeOfMeal){
        type=typeOfMeal;
    }*/

    @Override
    public String toString() {
        return "Meal{" +
                "idMeal=" + idMeal +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return idMeal == meal.idMeal && Double.compare(meal.price, price) == 0 && quantity == meal.quantity && name.equals(meal.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idMeal, name, price, quantity);
    }
}
