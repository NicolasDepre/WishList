package be.LaPireTeam.wishlist.Activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import be.LaPireTeam.wishlist.R;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;

public class ProfilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        User u = Session.getInstance().getU();

        //TODO permettre de venir avec un nouveau compte
        /*
        EditText name = (EditText) findViewById(R.id.profile_name);
        name.setText(u.getFirstName() + " " + u.getLastName());
        EditText adress = (EditText) findViewById(R.id.profile_adress);
        adress.setText(u.getAddress());
        EditText preferences = (EditText) findViewById(R.id.profile_preferences);
        String s = "";
        for(String string : u.getPreferences()) s += string + " ";
        preferences.setText(s);
        */
    }

    public void changePictureProfile () {}

    public void changeAdresse () {}
}
