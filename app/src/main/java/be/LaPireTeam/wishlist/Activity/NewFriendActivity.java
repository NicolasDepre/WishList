package be.LaPireTeam.wishlist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;
import be.LaPireTeam.wishlist.R;

public class NewFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);

    }

    public void addFriendButton(View view){
        EditText usernameInput = (EditText) findViewById(R.id.new_friends_username_inputfield);
        String username = usernameInput.getText().toString();
        User u = DAOFactory.userDAO(this).getUserFromID(username);
        TextView alert = (TextView) findViewById(R.id.alerteTextNewFriend);
        if(u == null){
            alert.setText("No user with that username found\n please retry");
        }
        else{
            if(DAOFactory.userDAO(this).areFriends(Session.getInstance().getU(), u)){
                alert.setText("You are already friends");
            }else {
                DAOFactory.userDAO(this).addNewFriend(u);
                Intent intent = new Intent(this, AmisActivity.class);
                startActivity(intent);
            }
        }
    }

}
