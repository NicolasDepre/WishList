package be.LaPireTeam.wishlist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        User u = Session.getInstance().getU();

        EditText name = (EditText) findViewById(R.id.profile_name);
        name.setText(u.getFirstName() + " " + u.getLastName());
        EditText adress = (EditText) findViewById(R.id.profile_adress);
        adress.setText(u.getAddress());
        EditText preferences = (EditText) findViewById(R.id.profile_preferences);
        String s = "";
        for(String string : u.getPreferences()) s += string + " ";
        preferences.setText(s);

    }

    public void changePictureProfile () {}

    public void changeAdresse () {}
}
