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
        String request = "INSERT";
        int couou = 2;
        this.dbHelper.getReadableDatabase().execSQL(request);
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
