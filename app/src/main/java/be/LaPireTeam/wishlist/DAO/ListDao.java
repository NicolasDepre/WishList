package be.LaPireTeam.wishlist.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import be.LaPireTeam.wishlist.List;
import be.LaPireTeam.wishlist.User;

public class ListDao {

    public DAO dao;

    public ListDao(Context c){
        dao = DAO.getInstance(c);
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

    private List[] cursorToList(Cursor c){
        List[] lists = new List[c.getCount()];
        int counter = 0;
        while(c.moveToNext()){
            List l = new List(c.getInt(c.getColumnIndex("ID")));
            //l.setCreationDate(); //TODO Gestion la date
            l.setName(c.getString(c.getColumnIndex("Name")));
            //TODO ajouter le bon ID a la liste
            lists[counter]  = l;
            counter++;
        }
        return lists;
    }

}
