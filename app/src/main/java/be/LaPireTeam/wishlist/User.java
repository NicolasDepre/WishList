package be.LaPireTeam.wishlist;

import java.util.Date;

public class User {

    public final String ID; //TODO generate an ID
    private String name, firstName, address, picture, password;
    private String[] Preferences;
    private Date Birthday;

    // Constructeur à faire
    public User() {
        this.ID = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String[] getPreferences() {
        return Preferences;
    }

    public void setPreferences(String[] preferences) {
        Preferences = preferences;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    //TODO Changer dans le diagramme de Classe le return value de la fonction
    public boolean addFriend(User u){
        return true;
    }

    public boolean unFriend(User u){
        return true;
    }

    //TODO change attribute

    public boolean deleteAccount(){
        return true;
    }

    public boolean createGroup(String name){
        return true;
    }

    public User[] loadFriends(){
        return null;
    }
}
