package be.LaPireTeam.wishlist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.R;

/**
 * Activité qui affiche le menu et qui gère les actions des boutons du menu
 */
public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    /**
     * Fonction appelée par le bouton "Profil" pour lancer l'activité pour afficher son profil
     * @param view contient la view de là ou le bouton a été cliqué
     */
    public void openProfilButton(View view) {
        Intent intent = new Intent(this, ProfilActivity.class);
        startActivity(intent);
    }

    /**
     * Fonction appelée par le bouton "Amis" pour lancer l'activité affichant les amis de l'utilisateur connecté
     * @param view contient la view de là ou le bouton a été cliqué
     */
    public void openAmisButton(View view) {
        Intent intent = new Intent(this, AmisActivity.class);
        startActivity(intent);
    }

    /**
     * Fonction appelée par le bouton "Lists" pour lancer l'activité affichant les listes de l'utilisateur connecté
     * @param view contient la view de là ou le bouton a été cliqué
     */
    public void openListButton(View view) {
        Session.getInstance().setLastClickedFriend(null);
        Intent intent = new Intent(this, MyListsActivity.class);
        startActivity(intent);
    }
}
