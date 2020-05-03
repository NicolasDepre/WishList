package be.LaPireTeam.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import be.LaPireTeam.wishlist.DAO.DAOFactory;

public class SeeWishesActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_wishes_in_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.addNewWishButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create action for new list button
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                 */
            }
        });

        listView = (ListView)findViewById(R.id.see_my_wishes);


        Intent intent = getIntent();
        //int list_id = Integer.parseInt( intent.getStringExtra(MyListsActivity.EXTRA_ARGUMENT_LIST_ID) );
        int list_id = intent.getIntExtra(MyListsActivity.EXTRA_ARGUMENT_LIST_ID, -1);
        //Wish[] wishes = List.getWishesFromListID(this, list_id);
        Wish[] wishes;
        if(list_id != -1) {
            List l = List.getListFromID(this, list_id);
            wishes = DAOFactory.WishDAO(this).getWishes(l);
        }else {
            wishes = null;
        }
        ArrayList<String> myWishesNames = new ArrayList<>();
        if(wishes != null) {
            for (Wish w : wishes) {
                myWishesNames.add(w.getName());
            }
        }

        //récupérer un arraylist de la base de données
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myWishesNames);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //i représente l'index de l'élément clické dans la view
                //lancer activité see_wish particulière

                return;
            }
        });
    }

}
