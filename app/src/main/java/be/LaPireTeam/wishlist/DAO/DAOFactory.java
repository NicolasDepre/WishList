package be.LaPireTeam.wishlist.DAO;

import android.content.Context;

import be.LaPireTeam.wishlist.User;

public class DAOFactory {


    public static UserDAO userDAO(Context c) {
        return new UserDAO(c);
    }
}
