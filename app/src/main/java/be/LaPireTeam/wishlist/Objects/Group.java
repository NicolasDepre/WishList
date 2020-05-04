package be.LaPireTeam.wishlist.Objects;

import java.util.Date;

public class Group {


    private  int ID;
    private String name, description, picture;
    private User[] members;
    private Date creationDate;

    public Group(int ID, String name, String description, String picture, User[] members, Date creationDate){
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.picture = this.picture;
        this.members = members;
        this.creationDate = creationDate;
    }

    public boolean addMember (User user, int accessLevel){ return true; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictur() {
        return picture;
    }

    public void setPictur(String pictur) {
        this.picture = pictur;
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

    public String strMembers(){
        String str = "";
        for(User u : this.members){
            str +=  u.getID();
        }
        return str;
    }



}
