package be.LaPireTeam.wishlist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.MessageDigest;

import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;

public class UserDAO {

    public DAO dao;

    public UserDAO(Context c) {
        dao = DAO.getInstance(c);
    }

    public static String hashedPassword(String password) {
        try {
            MessageDigest diggest = MessageDigest.getInstance("MD5");
            diggest.update(password.getBytes());
            byte[] bytes = diggest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public User getUserFromID(String pseudo) {
        SQLiteDatabase db = dao.getDB();
        String query = "SELECT * FROM User WHERE User.Pseudo == '" + pseudo + "'";
        Cursor c = db.rawQuery(query, null);
        User[] users = cursor_to_user(c);
        if (users.length == 0) return null;
        return users[0];
    }

    public User login(String username, String password) {

        SQLiteDatabase db = dao.getDB();
        username = username.trim();
        password = password.trim();
        password = hashedPassword(password);
        String query = "SELECT * FROM User WHERE User.Pseudo == '" + username + "' AND User.Password == '" + password + "'";
        Cursor c = db.rawQuery(query, null);
        User[] users = cursor_to_user(c);
        if (users.length == 0) {
            return null;
        }
        return users[0];
    }

    public User[] cursor_to_user(Cursor c) {
        User[] users = new User[c.getCount()];
        int index = 0;
        try {
            while (c.moveToNext()) {
                User u = new User(c.getString(c.getColumnIndex("Pseudo")));
                u.setLastName(c.getString(c.getColumnIndex("LastName")));
                u.setFirstName(c.getString(c.getColumnIndex("FirstName")));
                u.setPassword(c.getString(c.getColumnIndex("Password")));
                u.setAddress(c.getString(c.getColumnIndex("Address")));
                u.setPicture(c.getColumnName(c.getColumnIndex("ProfilePicture")));
                u.setPreferences(c.getString(c.getColumnIndex("Preferences")));
                users[index] = u;
                index++;
            }
        } catch (Exception e) {
            return null;
        } finally {
            c.close();
        }
        return users;
    }

    public boolean addUserToDB(User u) {

        SQLiteDatabase db = dao.getDB();
        ContentValues vals = new ContentValues();

        vals.put("FirstName", u.getFirstName());
        vals.put("LastName", u.getLastName());
        vals.put("Pseudo", u.getID());
        vals.put("Password", hashedPassword(u.getPassword()));
        vals.put("Address", u.getAddress());
        vals.put("Preferences", u.getPreferences().toString());
        try {
            db.insert("User", null, vals);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean idAlreadyExists(String pseudo) {
        SQLiteDatabase db = dao.getDB();
        String query = "SELECT * FROM User WHERE User.Pseudo == '" + pseudo + "'";
        Cursor c = db.rawQuery(query, null);
        return c.getCount() > 0;
    }

    public boolean updateUserInDB(User user) {
        if (idAlreadyExists(user.getID())) {
            SQLiteDatabase db = dao.getDB();
            ContentValues vals = new ContentValues();
            try {
                vals.put("FirstName", user.getFirstName());
            } catch (Exception e) {
            }
            try {
                vals.put("LastName", user.getLastName());
            } catch (Exception e) {
            }
            try {
                vals.put("Address", user.getAddress());
            } catch (Exception e) {
            }
            try {
                vals.put("Preferences", user.getPreferences());
            } catch (Exception e) {
            }
            String whereString = "Pseudo = '" + user.getID() + "'";
            try {
                db.update("User", vals, whereString, null);
            } catch (Exception e) {
                return false;
            }
            return true;
        } else {
            addUserToDB(user);
            return true;
        }
    }

    public boolean addNewFriend(User friend) {
        SQLiteDatabase db = dao.getDB();
        ContentValues vals1 = new ContentValues();
        ContentValues vals2 = new ContentValues();
        User u = Session.getInstance().getU();

        vals1.put("PseudoFriend", friend.getID());
        vals1.put("Pseudo", u.getID());
        vals2.put("PseudoFriend", u.getID());
        vals2.put("Pseudo", friend.getID());

        try {
            db.insert("Friends", null, vals1);
            db.insert("Friends", null, vals2);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User[] getFriends(User u) {
        SQLiteDatabase db = dao.getDB();

        String query = "SELECT * FROM User " +
                "WHERE User.Pseudo in " +
                "(SELECT PseudoFriend FROM Friends WHERE Friends.Pseudo = '" + u.getID() + "')";
        Cursor c = db.rawQuery(query, null);
        User[] friends = cursor_to_user(c);
        if (friends.length == 0) return null;
        return friends;
    }

    public boolean areFriends(User u, User friend) {
        SQLiteDatabase db = dao.getDB();
        String query = "SELECT * FROM Friends WHERE Pseudo = '" + u.getID() + "' and PseudoFriend = '" + friend.getID() + "'";
        Cursor c = db.rawQuery(query, null);
        return c.getCount() > 0;
    }
}