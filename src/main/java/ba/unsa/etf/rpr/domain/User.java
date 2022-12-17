package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Bean for User
 */
public class User implements Identifiable{
    private int idUser;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String address;
    private String telephoneNumber;

    public int getId() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setId(int idUser) {
        this.idUser = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser && name.equals(user.name) && surname.equals(user.surname) && Objects.equals(email, user.email) && password.equals(user.password) && address.equals(user.address) && Objects.equals(telephoneNumber, user.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, name, surname, email, password, address, telephoneNumber);
    }
}
