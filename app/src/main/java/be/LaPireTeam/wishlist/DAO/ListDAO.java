package be.LaPireTeam.wishlist.DAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import be.LaPireTeam.wishlist.List;

public class ListDAO extends DAO<List> {

    public final static String TABLE_NAME = "List";
    public final static String COLUMN_NAME = "Name";
    public final static String COLUMN_ID = "ListID";
    public final static String COLUMN_DATE = "CreationDate";

    public ListDAO(){
        super();
    }

    @Override
    public boolean create(List l)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues cont  = new ContentValues();
        cont.put(COLUMN_NAME, l.getName());
        cont.put(COLUMN_ID, l.ID);
        cont.put(COLUMN_DATE,l.getCreationDate().toString());
        db.insert(TABLE_NAME, null, cont); 

        return false;
    }

    @Override
    public boolean delete(List obj) {
        return false;
    }

    @Override
    public boolean update(List obj) {
        return false;
    }

    @Override
    public List find(int id) {
        return null;
    }
}
