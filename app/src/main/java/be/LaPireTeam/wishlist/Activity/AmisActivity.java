package be.LaPireTeam.wishlist.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;
import be.LaPireTeam.wishlist.R;

public class AmisActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);

        FloatingActionButton fab = findViewById(R.id.addNewFriendButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        listView = (ListView)findViewById(R.id.listview_friends);
        User user = Session.getInstance().getU();

        User[] friends = DAOFactory.userDAO(this).getFriends(user);

        ArrayList<String> myFriendsNames = new ArrayList<>();
        if(friends != null) {
            for (User friend : friends) {
                myFriendsNames.add(friend.getID());
            }
        }
        //récupérer un arraylist de la base de données
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myFriendsNames);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //position représente l'index de l'élément clické dans la view
            }
        });


    }

    public void addNewFriendButton(View view) {}

}
