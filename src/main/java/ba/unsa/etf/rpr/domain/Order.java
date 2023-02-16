package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Bean of Order
 */

public class Order implements Identifiable{
    private int id;
    private User user;
    private Date dateOfOrder;
    private double price;
    private String confirmationEmail;
    private String address;
    public Order(){}

    public Order(User user, Date dateOfOrder, double price) {
        this.user = user;
        this.dateOfOrder = dateOfOrder;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int idOrder) {
        this.id = idOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getConfirmationEmail() {
        return confirmationEmail;
    }

    public void setConfirmationEmail(String confirmationEmail) {
        this.confirmationEmail = confirmationEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", dateOfOrder=" + dateOfOrder +
                ", price=" + price +
                ", confirmationEmail='" + confirmationEmail + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.price, price) == 0 && user.equals(order.user) && dateOfOrder.equals(order.dateOfOrder) && Objects.equals(confirmationEmail, order.confirmationEmail) && Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, dateOfOrder, price, confirmationEmail, address);
    }
}
