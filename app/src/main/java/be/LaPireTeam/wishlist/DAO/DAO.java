package be.LaPireTeam.wishlist.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO {

    private static DAO instance;
    public SQLiteOpenHelper openHelper;
    public SQLiteDatabase db;

    public DAO(Context c) {
        this.openHelper = new DataBaseOpenHelper(c);
        this.db = openHelper.getWritableDatabase();
    }

    public static DAO getInstance(Context c) {
        if (instance == null) instance = new DAO(c);
        return instance;
    }

    public SQLiteDatabase getDB() {
        return this.db;
    }


}
