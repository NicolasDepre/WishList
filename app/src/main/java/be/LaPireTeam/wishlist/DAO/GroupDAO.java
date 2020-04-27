package be.LaPireTeam.wishlist.DAO;

import android.database.sqlite.SQLiteDatabase;



import be.LaPireTeam.wishlist.Group;

public class GroupDAO extends DAO<Group> {

    public GroupDAO(){
        super();

    }

    @Override
    public boolean create(Group obj) {
        return false;
    }


    public boolean addGroup(Group g) {
        String myRequest = "";
        this.dbHelper.getReadableDatabase().execSQL(myRequest);
        return true;
    }

    @Override
    public boolean delete(Group obj) {

        String request = "";

        this.dbHelper.getWritableDatabase().execSQL(request);
        return true;
    }

    @Override
    public boolean update(Group obj) {
        return false;
    }

    @Override
    public Group find(int id) {
        return null;
    }


}
