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

    public User loginUser(View view){
        EditText usernameTxt = (EditText) findViewById(R.id.username_field_loginpage);
        EditText passwordTxt = (EditText) findViewById(R.id.password_field_loginpage);
        String username = usernameTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        User login = DAOFactory.userDAO(this).login(username,password);
        return login;
    }

    public void loginButton(View view) {
        TextView alerteText = (TextView) findViewById(R.id.alerteTextLogin);
        if (loginUser(view) == null) {
            alerteText.setText("ERREUR UTILISATEUR INVALIDE");
            return;
        }
        alerteText.setText("UTILISATEUR LOGIN");
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}