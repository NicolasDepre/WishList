package be.LaPireTeam.wishlist.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO {

    public SQLiteOpenHelper openHelper;
    public SQLiteDatabase db;
    private static DAO instance;

    protected DAO(Context c){

        this.openHelper = new DataBaseOpenHelper(c);
        this.db = openHelper.getWritableDatabase();
    }
    public SQLiteDatabase getDB(){
        return this.db;
    }

    public void close(){
        if(this.db != null){
            this.db.close();
        }
    }

    public static DAO getInstance(Context c) {
        if(instance == null) instance = new DAO(c);
        return instance;
    }



}
