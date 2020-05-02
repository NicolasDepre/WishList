package be.LaPireTeam.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import be.LaPireTeam.wishlist.DAO.DAOFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
    }

    public void gotoRegisterView(View view) { setContentView(R.layout.register); }

    public void gotoLoginView(View view) {
        setContentView(R.layout.login_page);
    }

    public void registerButton(View view) {
        /*
        *   Lance l'activity profil si il y l'utilisateur est bien créé.
        *   Et termine l'activity en cour.
         */
        User user = registerUser(view);
        if (user == null) {return;}
        Session.getInstance().setU(user);
        // Doit définir l'activity parent en menu.
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void loginButton(View view) {
        /*
        *   Lance l'activity menu si il y un user, sinon met un message d'erreur.
        *   Et termine l'activity en cour;
         */
        TextView alerteText = (TextView) findViewById(R.id.alerteTextLogin);
        User user = loginUser(view);
        if (user == null) {
            alerteText.setText("ERREUR UTILISATEUR INVALIDE");
            return;
        }
        alerteText.setText("UTILISATEUR LOGIN");
        Session.getInstance().setU(user);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    private User loginUser(View view){
        /*
         *   Vérifie que l'utilisateur existe et que le mot de passe est exacte.
         *   Return l'utilisateur si les informations sont bonne sinon null.
         */
        EditText usernameTxt = (EditText) findViewById(R.id.username_field_loginpage);
        EditText passwordTxt = (EditText) findViewById(R.id.password_field_loginpage);
        String username = usernameTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        User login = DAOFactory.userDAO(this).login(username,password);
        if(login == null) usernameTxt.setText("ERREUR UTILISATEUR INVALIDE");
        else usernameTxt.setText("UTILISATEUR LOGIN");

    }

    int activite;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Connexion:
                activite = Connexion;

            case R.id.Inscription:
                activite = Inscription;
                break;
        }
    }

    public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener {

        private static final int ValidInscription = 5;
        public Button[] listeBoutons = new Button[1];

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.register);
            listeBoutons[ValidInscription] = ((Button) this.findViewById(R.id.ValidInscription));
            for (int i = 0; i < listeBoutons.length; i++) {
                listeBoutons[i].setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            }
        }
    }

    private User registerUser(View view){
        /*
         *   Retourne l'utilisateur si il a bien été créé.
         *   Dans les autres cas celui qui ecrit la fonction voit les cas possibles
         *   et voit comme il affiche les messages d'erreurs dans la fonction registerButton
         */
        // TODO
        return null;
    }
}