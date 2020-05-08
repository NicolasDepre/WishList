package be.LaPireTeam.wishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import be.LaPireTeam.wishlist.DAO.DAO;
import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.DAO.IDUtility;
import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;
import be.LaPireTeam.wishlist.R;

public class NewListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
    }

    public void createNewList(View view) {
        EditText inputName = (EditText) findViewById(R.id.new_list_name_inputfield);
        String name = inputName.getText().toString();
        if(name.length() == 0){
            TextView alert = (TextView) findViewById(R.id.alerteNewList);
            alert.setText("Please fill in a name");
            alert.setVisibility(View.VISIBLE);
            return;
        }
        EditText inputFriends = (EditText) findViewById(R.id.new_list_share_inputfield);
        String friendsInput = inputFriends.getText().toString();
        String[] friendsUsernames = friendsInput.split(" ");
        List l = new List(IDUtility.getNewListID(new DAO(this)));
        l.setName(name);
        User user = Session.getInstance().getU();
        DAOFactory.listDAO(this).insert_list(l);
        for (String username : friendsUsernames) {
            User friend = DAOFactory.userDAO(this).getUserFromID(username);
            if (friend != null) {
                Log.i("ADD USER", "ADD user with username " + friend.getID());
                if (DAOFactory.userDAO(this).areFriends(user, friend)) {
                    DAOFactory.listDAO(this).shareListWithUser(l, friend, 1);
                }
            }
        }
        Intent intent = new Intent(this, MyListsActivity.class);
        startActivity(intent);
    }
}
