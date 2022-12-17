package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Bean of Order
 */

public class Order implements Identifiable{
    private int id;
    private int idUser;
    private Date dateOfOrder;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int idOrder) {
        this.id = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + id +
                ", idUser=" + idUser +
                ", dateOfOrder=" + dateOfOrder +
                ", price=" + price +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && idUser == order.idUser && Double.compare(order.price, price) == 0 && dateOfOrder.equals(order.dateOfOrder);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, dateOfOrder, price);
    }
}
