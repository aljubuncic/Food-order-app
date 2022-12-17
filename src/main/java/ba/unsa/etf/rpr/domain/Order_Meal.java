package ba.unsa.etf.rpr.domain;

import java.util.Objects;


/**
 * Bean of Order_Meal
 */
public class Order_Meal implements Identifiable{
    private int id;
    private int idOrder;
    private int idMeal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(int idMeal) {
        this.idMeal = idMeal;
    }

    @Override
    public String toString() {
        return "Order_Meal{" +
                "idOrder_Meal=" + id +
                ", idOrder=" + idOrder +
                ", idMeal=" + idMeal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order_Meal that = (Order_Meal) o;
        return id == that.id && idOrder == that.idOrder && idMeal == that.idMeal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idOrder, idMeal);
    }
}
