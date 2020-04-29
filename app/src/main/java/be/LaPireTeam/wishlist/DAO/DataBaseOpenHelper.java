package be.LaPireTeam.wishlist.DAO;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseOpenHelper extends SQLiteAssetHelper {

    private static final String DB_NAME = "WishListDB.db";
    private final static int DB_VERSION = 1;

    public DataBaseOpenHelper(Context c) {

        super(c, DB_NAME, null, DB_VERSION);
    }
}



