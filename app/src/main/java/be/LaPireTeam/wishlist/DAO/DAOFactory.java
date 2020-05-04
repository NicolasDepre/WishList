package be.LaPireTeam.wishlist.DAO;

import android.content.Context;

public class DAOFactory {


    public static UserDAO userDAO(Context c) {
        return new UserDAO(c);
    }

    public static ListDao listDAO(Context c){
        return new ListDao(c);
    }

    public static WishDAO WishDAO(Context c){
        return new WishDAO(c);
    }


}
