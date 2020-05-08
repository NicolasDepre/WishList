package be.LaPireTeam.wishlist.Objects;

import android.content.Context;

import java.util.Date;

import be.LaPireTeam.wishlist.DAO.DAOFactory;

public class List {
    private String name;
    private Date creationDate;
    public final int ID;

    //TODO generate other ID than 0
    public List(int id) {
        this.ID = id;
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

    public Wish addNewWish(Wish w) {
        return null;
    }

    public boolean deleteWish(Wish w) {
        return false;
    }

    public boolean share(User u) {
        return false;
    }

    public boolean unShare(User u) {
        return false;
    }

    public boolean setGrade(User u, int grade) {
        return false;
    }

    public boolean hasAccess(User u) {
        return false;
    }

    public Wish[] getWishes(Context context) {
        //retourne la liste des wish dans la liste
        //fait appel au DAO qui récupère les donnée dans la base de données
        return DAOFactory.wishDAO(context).getWishes(this);
    }

    public static List getListFromID(Context context, int id) {
        return DAOFactory.listDAO(context).getListFromListID(id);
    }

    public static Wish[] getWishesFromListID(Context context, int id) {
        return getListFromID(context, id).getWishes(context);
    }

}
