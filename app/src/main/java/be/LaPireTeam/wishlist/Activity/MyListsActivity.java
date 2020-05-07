package be.LaPireTeam.wishlist.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.R;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;

public class MyListsActivity extends AppCompatActivity {

    public static final String EXTRA_ARGUMENT_LIST_ID = "be.LaPireTeam.wishlist.EXTRA_LIST_ID";

    ListView listView;
    ArrayList<Integer> listIDs;

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


        listView = (ListView)findViewById(R.id.mylists);

        User user = Session.getInstance().getU();

        final List[] lists = DAOFactory.listDAO(this).getLists(user);
        ArrayList<String> myListsNames = new ArrayList<>();
        listIDs = new ArrayList<>();
        if(lists != null) {
            for (List l : lists) {
                if(DAOFactory.listDAO(this).owns_list(l, user)) {
                    myListsNames.add(l.getName());
                    listIDs.add(l.ID);
                }
            }
        }
        //récupérer un arraylist de la base de données
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myListsNames);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //i représente l'index de l'élément clické dans la view
                //lancer activité see_list particulière
                int list_id = listIDs.get(position);
                openSeeWishesActivity(view, list_id);
            }
        });
    }

    public void openSeeWishesActivity(View view, int id) {
        Intent intent = new Intent(this, SeeWishesActivity.class);
        intent.putExtra(EXTRA_ARGUMENT_LIST_ID, id);
        startActivity(intent);
    }

    public void openNewListActivity() {
        Intent newListIntent = new Intent(this, NewListActivity.class);
        startActivity(newListIntent);
    }

}
