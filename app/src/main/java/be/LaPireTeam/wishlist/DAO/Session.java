package be.LaPireTeam.wishlist.DAO;

import be.LaPireTeam.wishlist.User;

public class Session {

    private static User connectedUser;
    private static String status = "DISCONNECTED";

    public static User getConnectedUser(){
        return connectedUser;
    }

    public static String getStatus(){
        return status;
    }
}
