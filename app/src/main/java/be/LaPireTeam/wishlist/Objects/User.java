package be.LaPireTeam.wishlist.Objects;

import java.util.Date;

public class User {

    private String lastName, ID, firstName, address, picture, password, preferences;
    private Date birthday;

    // Constructeur à faire
    public User(String pseudo) {
        this.ID = pseudo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    //TODO Changer dans le diagramme de Classe le return value de la fonction
    public boolean addFriend(User u) {
        return true;
    }

    public boolean unFriend(User u) {
        return true;
    }

    //TODO change attribute

    public boolean deleteAccount() {
        return true;
    }

    public boolean createGroup(String name) {
        return true;
    }

    public User[] loadFriends() {
        return null;
    }

}
