package be.LaPireTeam.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.DAO.UserDAO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("INFO", "STARTUP");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        TextView v = (TextView) findViewById(R.id.username_field_loginpage);
        User u = new User();
        u.setID("Test2");
        u.setPreferences(new String[]{"Test", "Test"});
        u.setFirstName("Test");
        u.setLastName("Test");
        u.setAddress("Rue de la gare 10");
        u.setPicture("Test");
        u.setPassword("Test");
        if(DAOFactory.userDAO(this).addUserToDB(u)) v.setText("CA A MARCHE");
        else v.setText("ECHEC");

        User u2 = DAOFactory.userDAO(this).login("Test2","Test");
        v.setText(u2.getAddress());

    }
}
