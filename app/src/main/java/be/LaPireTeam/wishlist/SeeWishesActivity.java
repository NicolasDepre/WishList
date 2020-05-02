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

        /*
        Intent intent = getIntent();
        int list_id = Integer.parseInt( intent.getStringExtra(MyListsActivity.EXTRA_ARGUMENT_LIST_ID) );

        Wish[] wishes = List.getWishesFromListID(list_id);

         */

        ArrayList<String> myWishesNames = new ArrayList<>();
        /*
        for(Wish w : wishes){
            myWishesNames.add(w.getName());
        }

         */
        myWishesNames.add("Mon cadeau idéal");
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
