package be.LaPireTeam.wishlist.DAO;

import android.content.Context;

public class DAOFactory {


    public static UserDAO userDAO(Context c) {
        return new UserDAO(c);
    }

    public static ListDAO listDAO(Context c) {
        return new ListDAO(c);
    }

    public static WishDAO wishDAO(Context c) {
        return new WishDAO(c);
    }


}
