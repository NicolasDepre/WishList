package be.LaPireTeam.wishlist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;
import be.LaPireTeam.wishlist.R;
import be.LaPireTeam.wishlist.Objects.Wish;

public class SeeWishesActivity extends AppCompatActivity {

    ListView listView;
    List currentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_wishes_in_list);

        currentList = Session.getInstance().getLastClickedList();

        FloatingActionButton fab = findViewById(R.id.addNewWishButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewWishActivity();
            }
        });

        FloatingActionButton deleteListButton = findViewById(R.id.deleteListButton);
        deleteListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteList();
            }
        });

        FloatingActionButton shareListButton = findViewById(R.id.shareListButton);
        shareListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToShareListView();
            }
        });

        listView = (ListView) findViewById(R.id.see_my_wishes);

        final Wish[] wishes = DAOFactory.wishDAO(this).getWishes(currentList);
        ArrayList<String> myWishesNames = new ArrayList<>();
        if (wishes != null) {
            for (Wish w : wishes) {
                myWishesNames.add(w.getName());
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myWishesNames);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Session.getInstance().setLastClickedWish(wishes[position]);
                openSeeDetailsWishActivity();
            }
        });
    }

    private void openNewWishActivity() {
        Intent newWishIntent = new Intent(this, NewWishActivity.class);
        //newWishIntent.putExtra(EXTRA_ARGUMENT_LIST_ID, list_id);
        startActivity(newWishIntent);
    }

    private void openSeeDetailsWishActivity() {
        Intent intent = new Intent(this, SeeDetailsWish.class);
        startActivity(intent);
    }

    private void deleteList() {
        if(Session.getInstance().getLastClickedFriend() == null) {
            DAOFactory.listDAO(this).removeList(this, currentList);
            Intent intent = new Intent(this, MyListsActivity.class);
            startActivity(intent);
        }else {
            DAOFactory.listDAO(this).removeList(this, currentList);
            Intent intent = new Intent(this, FriendsListsActivity.class);
            startActivity(intent);
        }
    }

    private void goToShareListView(){
        setContentView(R.layout.share_list);
    }

    public void shareListButton(View view){
        User user = Session.getInstance().getU();
        EditText inputFriends = (EditText) findViewById(R.id.share_list_inputfield);
        String friendsInput = inputFriends.getText().toString();
        String[] friendsUsernames = friendsInput.split(" ");

        for (String username : friendsUsernames) {
            User friend = DAOFactory.userDAO(this).getUserFromID(username);
            if (friend != null) {
                if (DAOFactory.userDAO(this).areFriends(user, friend)) {
                    DAOFactory.listDAO(this).shareListWithUser(currentList, friend, 1);
                }
            }
        }
        Log.d("SHARING LIST", "END SHARING LIST");
        Intent intent = new Intent(this, SeeWishesActivity.class);
        startActivity(intent);
    }
}
