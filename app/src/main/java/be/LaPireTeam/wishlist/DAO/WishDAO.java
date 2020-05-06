package be.LaPireTeam.wishlist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;

import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.Objects.Wish;

public class WishDAO {
    public DAO dao;
    public WishDAO(Context c){
        dao = DAO.getInstance(c);
    }

    public Wish[] getWishes(List l){

        SQLiteDatabase db = dao.getDB();

        //String request = "SELECT * FROM Whishes, ListWish WHERE List.ListID == '"+l.getID+"' and  = UserList.ListID";
        String request = "SELECT * FROM Wishs, ListWish WHERE ListWish.ListID == '" + l.ID + "'and Wishs.WishID == ListWish.WishID";
        Cursor c = db.rawQuery(request,null);
        if(c.getCount() ==0){
            return null;
        }
        return cursorToList(c);

    }

    public boolean insert_wish(Wish w, int listID){

        SQLiteDatabase db = dao.getDB();
        ContentValues wishValues = new ContentValues();

        wishValues.put("WishID",w.ID);
        wishValues.put("ProductID",w.getProduct());
        wishValues.put("Name",w.getName());
        wishValues.put("Priority", w.getPriority());
        wishValues.put("Comments", w.getCommentary());
        wishValues.put("Status",w.getBookingStatus());



        ContentValues listValues = new ContentValues();
        listValues.put("ListID",listID);
        listValues.put("WishID",w.ID);

        try{
            db.insert("Wishs",null, wishValues);
            db.insert("ListWish", null,listValues);
        }catch (Exception e) {return false;}

        return true;
    }

    public Wish getWishFromWishID(int id){
        SQLiteDatabase db = dao.getDB();
        String request = "SELECT * FROM Wishs WHERE Wishs.WishID = " + id ;
        Cursor c = db.rawQuery(request,null);
        if(c.getCount() ==0){
            return null;
        }
        Wish[] wishes = cursorToList(c);
        return wishes[0];
    }

    private Wish[] cursorToList(Cursor c){
        Wish[] wishes = new Wish[c.getCount()];
        int counter = 0;
        while(c.moveToNext()){
            Wish w = new Wish(
                    c.getInt(c.getColumnIndex("WishID")),
                    c.getString(c.getColumnIndex("Name")),
                    c.getInt(c.getColumnIndex("Priority")),
                    c.getString(c.getColumnIndex("Comments")),
                    c.getString(c.getColumnIndex("ProductID"))
                    );
            //w.setName(c.getString(c.getColumnIndex("Name")));

            wishes[counter]  = w;
            counter++;
        }
        return wishes;
    }
}
