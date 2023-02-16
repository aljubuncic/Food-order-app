package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Bean for User
 */
public class User implements Identifiable{
    private int id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String address;
    private String telephoneNumber;
    public User(){}

    public User(String name, String surname, String username, String password, String telephoneNumber) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
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
        this.id = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username=" + username +
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
        return id == user.id && name.equals(user.name) && surname.equals(user.surname) && username.equals(user.username) && Objects.equals(email, user.email) && password.equals(user.password) && Objects.equals(address, user.address) && telephoneNumber.equals(user.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, username, email, password, address, telephoneNumber);
    }
}
