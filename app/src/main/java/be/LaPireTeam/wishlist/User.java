package be.LaPireTeam.wishlist;

import java.util.Date;

public class User {

    public final int ID; //TODO generate an ID
    private String name, FirstName, Address, Picture, Password;
    private String[] Preferences;
    private Date Birthday;

    // Constructeur à faire
    public User() {
        this.ID = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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
