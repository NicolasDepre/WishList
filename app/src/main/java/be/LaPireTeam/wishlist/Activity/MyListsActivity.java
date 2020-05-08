package be.LaPireTeam.wishlist.Activity;

import android.content.Intent;
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

public class MyListsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<List> showedLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lists);

        FloatingActionButton fab = findViewById(R.id.addNewListButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("INFO", "CREATION NOUVELLE LIST");
                openNewListActivity();
            }
        });

        listView = (ListView) findViewById(R.id.mylists);

        User user = Session.getInstance().getU();

        final List[] lists = DAOFactory.listDAO(this).getLists(user);
        ArrayList<String> myListsNames = new ArrayList<>();
        //listIDs = new ArrayList<>();
        showedLists = new ArrayList<>();
        if (lists != null) {
            for (List l : lists) {
                if (DAOFactory.listDAO(this).owns_list(l, user)) {
                    myListsNames.add(l.getName());
                    showedLists.add(l);
                }
            }
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myListsNames);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Session.getInstance().setLastClickedList(showedLists.get(position));
                openSeeWishesActivity(view);
            }
        });
    }

    public void openSeeWishesActivity(View view) {
        Intent intent = new Intent(this, SeeWishesActivity.class);
        startActivity(intent);
    }

    public void openNewListActivity() {
        Intent newListIntent = new Intent(this, NewListActivity.class);
        startActivity(newListIntent);
    }
}
