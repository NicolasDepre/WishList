package be.LaPireTeam.wishlist;

import java.util.Date;

public class List {
    private String name;
    private Date creationDate;
    public final int ID;

    public List() {
        this.ID = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Wish addNewWish(Wish w){
        return null;
    }

    public boolean deleteWish(Wish w){
        return false;
    }

    public boolean share(User u){
        return false;
    }

    public boolean unShare(User u){
        return false;
    }

    public boolean setGrade(User u, int grade){
        return false;
    }

    public boolean hasAccess(User u){
        return false;
    }

    public Wish[] getWishes(){
        //retourne la liste des wish dans la liste
        //fait appel au DAO qui récupère les donnée dans la base de données
        return new Wish[1];
    }

}
