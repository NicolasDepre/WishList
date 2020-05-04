package be.LaPireTeam.wishlist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import be.LaPireTeam.wishlist.Objects.User;

public class UserDAO{

    public DAO dao;

    public UserDAO(Context c){
        dao = DAO.getInstance(c);
    }



    public User login(String username, String password){

        SQLiteDatabase db = dao.getDB();
        username = username.trim();
        password = password.trim();
        Log.i("INFO",username+password);
        String query = "SELECT * FROM User WHERE User.Pseudo == '" + username + "' AND User.Password == '" + password +"'";
        Cursor c = db.rawQuery(query, null);
        User[] users = cursor_to_user(c);
        if(users.length == 0) return null;
        return users[0];

    }


    public User[] cursor_to_user(Cursor c){
        User[] users = new User[c.getCount()];
        int index = 0;
        Log.i("INFO",String.format("SIZE REQUEST %d",users.length));
        try {
            while(c.moveToNext()) {
                User u = new User();
                u.setLastName(c.getString(c.getColumnIndex("LastName")));
                u.setFirstName(c.getString(c.getColumnIndex("FirstName")));
                u.setID(c.getString(c.getColumnIndex("Pseudo")));
                u.setPassword(c.getString(c.getColumnIndex("Password")));
                //u.setBirthday((Date) c.getString(c.getColumnIndex("DateOfBirth"))); TODO Gestion de la date
                u.setAddress(c.getString(c.getColumnIndex("Address")));
                u.setPicture(c.getColumnName(c.getColumnIndex("ProfilePicture")));
                u.setPreferences(c.getString(c.getColumnIndex("Preferences")).split(" "));
                users[index] = u;
                index ++;
            }
        }catch(Exception e){ return null;}
        finally {
            c.close();
        }
        return users;
    }

    public boolean addUserToDB(User u){

        SQLiteDatabase db = dao.getDB();
        ContentValues vals = new ContentValues();

        vals.put("FirstName", u.getFirstName());
        vals.put("LastName", u.getLastName());
        vals.put("Pseudo",u.getID());
        vals.put("Password",u.getPassword());
        vals.put("Address", u.getAddress());
        vals.put("ProfilePicture",u.getPicture());
        vals.put("Preferences",u.getPreferences().toString());

        try {
            db.insert("User", null, vals);
        }catch (Exception e){
            return false;
        }
        return true;
    }


}