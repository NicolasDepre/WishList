package be.LaPireTeam.wishlist.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Connection;

public abstract class DAO<T>{

    private static final String DB_NAME = "WishList.db";
    public DBHelper dbHelper;


    public DAO(){
        dbHelper = new DBHelper(null, DB_NAME, null, 1);
    }


    public abstract boolean create(T obj);
    public abstract boolean delete(T obj);

    public abstract boolean update(T obj);
    public abstract T find(int id);
}
