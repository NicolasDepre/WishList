package be.LaPireTeam.wishlist.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class IDUtility {

    public static int getNewWishID(DAO dao) {

        SQLiteDatabase db = dao.getDB();
        Cursor c = getIDCursor(db);
        c.moveToFirst();
        int wishID = c.getInt(c.getColumnIndex("WishID"));

        ContentValues values = new ContentValues();
        values.put("WishID", wishID + 1);
        values.put("ListID", c.getInt(c.getColumnIndex("ListID")));
        values.put("GroupID", c.getInt(c.getColumnIndex("GroupID")));
        db.update("MaxID", values, "ID = 'True'", null);
        return wishID;
    }

    public static int getNewListID(DAO dao) {
        SQLiteDatabase db = dao.getDB();
        Cursor c = getIDCursor(db);
        c.moveToFirst();
        int listID = c.getInt(c.getColumnIndex("ListID"));

        ContentValues values = new ContentValues();
        values.put("WishID", c.getInt(c.getColumnIndex("WishID")));
        values.put("ListID", listID + 1);
        values.put("GroupID", c.getInt(c.getColumnIndex("GroupID")));
        db.update("MaxID", values, "ID = 'True'", null);
        Log.i("INFO", String.valueOf(listID));
        return listID;
    }


    private static Cursor getIDCursor(SQLiteDatabase db) {
        String query = "SELECT * FROM MaxID";
        return db.rawQuery(query, null);
    }
}
