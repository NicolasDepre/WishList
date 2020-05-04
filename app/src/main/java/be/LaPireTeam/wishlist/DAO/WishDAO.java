package be.LaPireTeam.wishlist.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public Wish getWishFromWishID(int id){
        SQLiteDatabase db = dao.getDB();
        String request = "SELECT * FROM Whishs WHERE Whishs.WishID = " + id ;
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
                    c.getColumnIndex("Priority"),
                    null,
                    null
                    );
            //w.setName(c.getString(c.getColumnIndex("Name")));

            wishes[counter]  = w;
            counter++;
        }
        return wishes;
    }
}
