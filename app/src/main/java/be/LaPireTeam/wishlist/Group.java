package be.LaPireTeam.wishlist;

import java.util.Date;

public class Group {


    public final int ID;
    public boolean addMember (User user, int accessLevel){
        this.ID = 0;
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPictur() {
        return pictur;
    }

    public void setPictur(String pictur) {
        this.pictur = pictur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    private String name, pseudo, pictur, description;
    private Date creationDate;



}
