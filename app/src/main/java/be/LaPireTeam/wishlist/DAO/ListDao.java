package be.LaPireTeam.wishlist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;

public class ListDao {

    public DAO dao;

    public ListDao(Context c){
        dao = DAO.getInstance(c);
    }

    public boolean insert_list(List l){

        SQLiteDatabase db = dao.getDB();
        ContentValues wishValues = new ContentValues();

        wishValues.put("ListID",l.ID);
        wishValues.put("Name",l.getName());

        ContentValues userList = new ContentValues();
        User u = Session.getInstance().getU();
        userList.put("Pseudo", u.getID());
        userList.put("ListID", l.ID);

        try{
            db.insert("List",null, wishValues);
            db.insert("UserList", null,userList);
        }catch (Exception e) {return false;}

        return true;
    }


    public List[] getLists(User u){

        SQLiteDatabase db = dao.getDB();

        String request = "SELECT * FROM UserList, List WHERE UserList.Pseudo == '"+u.getID()+"' and List.ListID = UserList.ListID";
        Cursor c = db.rawQuery(request,null);
        if(c.getCount() ==0){
            return null;
        }
        return cursorToList(c);

    }

    public List getListFromListID(int id){
        SQLiteDatabase db = dao.getDB();
        String request = "SELECT * FROM List WHERE List.ListID = " + id ;
        Cursor c = db.rawQuery(request,null);
        if(c.getCount() ==0){
            return null;
        }
        List[] lists = cursorToList(c);
        return lists[0];
    }

    private List[] cursorToList(Cursor c){
        List[] lists = new List[c.getCount()];
        int counter = 0;
        while(c.moveToNext()){
            List l = new List(c.getInt(c.getColumnIndex("ListID")));
            //l.setCreationDate(); //TODO Gestion la date
            l.setName(c.getString(c.getColumnIndex("Name")));
            //TODO ajouter le bon ID a la liste
            lists[counter]  = l;
            counter++;
        }
        return lists;
    }

}
