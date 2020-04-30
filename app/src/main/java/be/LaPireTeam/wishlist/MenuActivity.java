package be.LaPireTeam.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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
        Intent intent = new Intent(this, MyListsActivity.class);
        startActivity(intent);
    }

}
