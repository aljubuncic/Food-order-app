package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Bean of Meal
 */

public class Meal implements Identifiable{
    private int id;
    private String name;
    private double price;
    private int quantity;
    public Meal(){}

    public Meal(String name, double price, int quantity, String typeOfMeal) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.typeOfMeal = typeOfMeal;
    }

    private String typeOfMeal;

    public int getId() {
        return id;
    }

    public void setId(int idMeal) {
        this.id = idMeal;
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

   public String getType() {
        return typeOfMeal;
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

    public void setType(String typeOfMeal){
        this.typeOfMeal= typeOfMeal;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", typeOfMeal=" + typeOfMeal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return id == meal.id && Double.compare(meal.price, price) == 0 && quantity == meal.quantity && name.equals(meal.name) && typeOfMeal.equals(meal.typeOfMeal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity, typeOfMeal);
    }
}
