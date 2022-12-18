package ba.unsa.etf.rpr.domain;

import java.util.Objects;


/**
 * Bean of Order_Meal
 */
public class Order_Meal implements Identifiable{
    private int id;
    private Order order;
    private Meal meal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @Override
    public String toString() {
        return "Order_Meal{" +
                "idOrder_Meal=" + id +
                ", idOrder=" + order +
                ", idMeal=" + meal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order_Meal that = (Order_Meal) o;
        return id == that.id && order == that.order && meal == that.meal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, meal);
    }
}
