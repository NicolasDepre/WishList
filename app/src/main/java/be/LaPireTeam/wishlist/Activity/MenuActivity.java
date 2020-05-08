package be.LaPireTeam.wishlist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.R;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void openProfilButton(View view) {
        /*
         *   Lance l'activity profil.
         */
        Intent intent = new Intent(this, ProfilActivity.class);
        startActivity(intent);
    }

    public void openAmisButton(View view) {
        /*
         *   Lance l'activity Amis.
         */
        Intent intent = new Intent(this, AmisActivity.class);
        startActivity(intent);
    }

    public void openListButton(View view) {
        /*
         *   Lance l'activity Mylists.
         */
        Session.getInstance().setLastClickedFriend(null);
        Intent intent = new Intent(this, MyListsActivity.class);
        startActivity(intent);
    }
}
