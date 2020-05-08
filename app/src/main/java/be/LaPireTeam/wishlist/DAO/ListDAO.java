package be.LaPireTeam.wishlist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;
import be.LaPireTeam.wishlist.Objects.Wish;

public class ListDAO {

    public DAO dao;

    public ListDAO(Context c) {
        dao = DAO.getInstance(c);
    }

    public boolean insert_list(List l) {

        SQLiteDatabase db = dao.getDB();
        ContentValues wishValues = new ContentValues();

        wishValues.put("ListID", l.ID);
        wishValues.put("Name", l.getName());

        ContentValues userList = new ContentValues();
        User u = Session.getInstance().getU();
        userList.put("Pseudo", u.getID());
        userList.put("ListID", l.ID);

        try {
            db.insert("List", null, wishValues);
            db.insert("UserList", null, userList);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    public List[] getLists(User u) {

        SQLiteDatabase db = dao.getDB();

        String request = "SELECT * FROM UserList, List WHERE UserList.Pseudo == '"+ u.getID() +"' and List.ListID = UserList.ListID";
        Cursor c = db.rawQuery(request,null);
        if(c.getCount() ==0){
            return null;
        }
        return cursorToList(c);

    }

    public List getListFromListID(int id) {
        SQLiteDatabase db = dao.getDB();
        String request = "SELECT * FROM List WHERE List.ListID = " + id;
        Cursor c = db.rawQuery(request, null);
        if (c.getCount() == 0) {
            return null;
        }
        List[] lists = cursorToList(c);
        return lists[0];
    }

    private List[] cursorToList(Cursor c) {
        List[] lists = new List[c.getCount()];
        int counter = 0;
        while (c.moveToNext()) {
            List l = new List(c.getInt(c.getColumnIndex("ListID")));
            l.setName(c.getString(c.getColumnIndex("Name")));
            lists[counter] = l;
            counter++;
        }
        return lists;
    }

    public boolean shareListWithUser(List list, User user, int acces) {
        SQLiteDatabase db = dao.getDB();
        ContentValues values = new ContentValues();
        values.put("Pseudo", user.getID());
        values.put("ListID", list.ID);
        values.put("Access", acces);
        try {
            db.insert("UserList", null, values);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean owns_list(List list, User user){
        SQLiteDatabase db = dao.getDB();
        String query = "SELECT * FROM UserList WHERE Pseudo = '" + user.getID() + "' and ListID = " + list.ID;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("Access")) == 0;
    }

    public boolean can_edit(List list, User user){
        SQLiteDatabase db = dao.getDB();
        String query = "SELECT * FROM UserList WHERE Pseudo = '" + user.getID() + "' and ListID = " + list.ID;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("Access")) <= 1;
    }

    public boolean can_see(List list, User user){
        SQLiteDatabase db = dao.getDB();
        String query = "SELECT * FROM UserList WHERE Pseudo = '" + user.getID() + "' and ListID = " + list.ID;
        Cursor c = db.rawQuery(query, null);
        try {
            c.moveToFirst();
            return c.getInt(c.getColumnIndex("Access")) <= 2;
        }catch (Exception e){
            return false;
        }
    }

    public boolean removeList(Context c, List list) {
        SQLiteDatabase db = dao.getDB();
        User user = Session.getInstance().getU();
        if (!owns_list(list, user)) {
            try {
                db.delete("UserList", "ListID = " + list.ID + " and Pseudo = '" + user.getID() + "'", null);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        try {
            db.delete("UserList", "ListID = " + list.ID, null);
            for (Wish wish : DAOFactory.wishDAO(c).getWishes(list)) {
                DAOFactory.wishDAO(c).removeWish(wish);
            }
            db.delete("List", "ListID = " + list.ID, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
