package be.LaPireTeam.wishlist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.R;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;

public class ProfilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        User u = Session.getInstance().getU();

        EditText firstName = findViewById(R.id.profile_first_name);
        if (u.getFirstName() != null) {
            firstName.setText(u.getFirstName());
        } else {
            firstName.setHint("First name");
        }
        EditText lastName = findViewById(R.id.profile_last_name);
        if (u.getLastName() != null) {
            lastName.setText(u.getLastName());
        } else {
            lastName.setHint("Last name");
        }
        EditText adress = findViewById(R.id.profile_adress);
        if (u.getAddress() != null) {
            adress.setText(u.getAddress());
        } else {
            adress.setHint("Adress");
        }
        EditText prefs = findViewById(R.id.profile_preferences);
        if (u.getPreferences() != null) {
            prefs.setText(u.getPreferences());
        } else {
            prefs.setHint("Preferences");
        }


    }

    public void saveButton(View view) {
        EditText first_name = (EditText) findViewById(R.id.profile_first_name);
        String fn = first_name.getText().toString();
        EditText last_name = (EditText) findViewById(R.id.profile_last_name);
        String ln = last_name.getText().toString();
        EditText adress = (EditText) findViewById(R.id.profile_adress);
        String a = adress.getText().toString();
        EditText preferences = (EditText) findViewById(R.id.profile_preferences);
        String p = preferences.getText().toString();
        User user = Session.getInstance().getU();
        user.setFirstName(fn);
        user.setLastName(ln);
        user.setAddress(a);
        user.setPreferences(p);
        Session.getInstance().setU(user);
        DAOFactory.userDAO(this).updateUserInDB(user);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
