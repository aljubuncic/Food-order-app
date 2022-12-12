package ba.unsa.etf.rpr.domain;

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
}
