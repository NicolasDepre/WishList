package be.LaPireTeam.wishlist.Objects;

import android.content.Context;

import java.util.Date;

import be.LaPireTeam.wishlist.DAO.DAOFactory;

public class List {
    private String name;
    private Date creationDate;
    public final int ID;

    public List(int id) {
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
