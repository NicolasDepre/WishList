package be.LaPireTeam.wishlist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.R;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

    }

    public void gotoRegisterView(View view) {
        setContentView(R.layout.register);
    }

    public void gotoLoginView(View view) {
        setContentView(R.layout.login_page);
    }

    public void registerButton(View view) {
        /*
         *   Lance l'activity profil si il y l'utilisateur est bien créé.
         *   Et termine l'activity en cour.
         */
        User user = registerUser(view);
        if (user == null) {
            return;
        }
        Intent intent = new Intent(this, ProfilActivity.class);
        Intent intentParent = new Intent(this, MenuActivity.class);
        startActivity(intentParent);
        startActivity(intent);
        finish();
    }

    public void loginButton(View view) {
        /*
         *   Lance l'activity menu si il y un user, sinon met un message d'erreur.
         *   Et termine l'activity en cour;
         */
        User user = loginUser(view);
        if (user == null) {
            return;
        }
        Session.getInstance().setU(user);
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    private User loginUser(View view) {
        /*
         *   Vérifie que l'utilisateur existe et que le mot de passe est exacte.
         *   Return l'utilisateur si les informations sont bonne sinon null.
         */
        EditText usernameTxt = (EditText) findViewById(R.id.username_field_loginpage);
        EditText passwordTxt = (EditText) findViewById(R.id.password_field_loginpage);
        String username = usernameTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        User user = DAOFactory.userDAO(this).login(username, password);

        TextView alerteText = (TextView) findViewById(R.id.alerteTextLogin);
        alerteText.setVisibility(View.VISIBLE);
        if (user == null) {
            alerteText.setText("ERREUR UTILISATEUR INVALIDE");
            return null;
        }
        alerteText.setText("UTILISATEUR LOGIN");
        return user;
    }

    private User registerUser(View view) {
        /*
         *   Enregistre le nouvelle utilisateur si tout les paremetres sont exactes
         *   sinon retourne null et affiche une erreur.
         */
        EditText usernameInput = (EditText) findViewById(R.id.username_field_registerpage);
        EditText password1Input = (EditText) findViewById(R.id.password_field1_registerpage);
        EditText password2Input = (EditText) findViewById(R.id.password_field2_registerpage);
        String username = usernameInput.getText().toString().trim();
        String password1 = password1Input.getText().toString();
        String password2 = password2Input.getText().toString();
        TextView msgAlert = (TextView) findViewById(R.id.alerteTextRegister);
        if (username.length() == 0) {
            msgAlert.setText("Empty Username");
            msgAlert.setVisibility(View.VISIBLE);
            return null;
        }
        if (DAOFactory.userDAO(this).idAlreadyExists(username)) {
            msgAlert.setText("Username already in use");
            msgAlert.setVisibility(View.VISIBLE);
            return null;
        }
        if (!password1.equals(password2)) {
            msgAlert.setText("2 passwords aren't the same");
            msgAlert.setVisibility(View.VISIBLE);
            return null;
        }
        if (password1.length() == 0 || password2.length() == 0) {
            msgAlert.setText("Empty Password");
            return null;
        }
        User user = new User(username);
        user.setPassword(password1);
        Session.getInstance().setU(user);
        return user;
    }

}