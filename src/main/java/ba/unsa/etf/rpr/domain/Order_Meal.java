package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Order_Meal {
    private int idOrder_Meal;
    private int idOrder;
    private int idMeal;

    public int getIdOrder_Meal() {
        return idOrder_Meal;
    }

    public void setIdOrder_Meal(int idOrder_Meal) {
        this.idOrder_Meal = idOrder_Meal;
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
                "idOrder_Meal=" + idOrder_Meal +
                ", idOrder=" + idOrder +
                ", idMeal=" + idMeal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order_Meal that = (Order_Meal) o;
        return idOrder_Meal == that.idOrder_Meal && idOrder == that.idOrder && idMeal == that.idMeal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder_Meal, idOrder, idMeal);
    }
}
