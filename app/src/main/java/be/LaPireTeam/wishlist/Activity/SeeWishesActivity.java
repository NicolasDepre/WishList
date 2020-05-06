package be.LaPireTeam.wishlist.Activity;

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
import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.R;
import be.LaPireTeam.wishlist.Objects.Wish;

public class SeeWishesActivity extends AppCompatActivity {

    public static final String EXTRA_ARGUMENT_LIST_ID = "be.LaPireTeam.wishlist.EXTRA_LIST_ID";
    public static final String EXTRA_ARGUMENT_WISH_ID = "be.LaPireTeam.wishlist.EXTRA_WISH_ID";
    ListView listView;
    int list_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_wishes_in_list);

        Intent intent = getIntent();
        //int list_id = Integer.parseInt( intent.getStringExtra(MyListsActivity.EXTRA_ARGUMENT_LIST_ID) );
        list_id = intent.getIntExtra(EXTRA_ARGUMENT_LIST_ID, -1);

        FloatingActionButton fab = findViewById(R.id.addNewWishButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewWishActivity(list_id);
            }
        });

        FloatingActionButton deleteListButton = findViewById(R.id.deleteListButton);
        deleteListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteList();
            }
        });

        listView = (ListView)findViewById(R.id.see_my_wishes);



        //Wish[] wishes = List.getWishesFromListID(this, list_id);
        final Wish[] wishes;
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
                openSeeDetailsWishActivity(wishes[position].ID, list_id);
            }
        });
    }

    private void openNewWishActivity(int list_id){
        Intent newWishIntent = new Intent(this, NewWishActivity.class);
        newWishIntent.putExtra(EXTRA_ARGUMENT_LIST_ID, list_id);
        startActivity(newWishIntent);
    }

    private void openSeeDetailsWishActivity(int wish_id, int list_id){
        Intent intent = new Intent(this, SeeDetailsWish.class);
        intent.putExtra(EXTRA_ARGUMENT_WISH_ID, wish_id);
        intent.putExtra(EXTRA_ARGUMENT_LIST_ID, list_id);
        startActivity(intent);
    }

    private void deleteList(){
        List l = DAOFactory.listDAO(this).getListFromListID(list_id);
        DAOFactory.listDAO(this).removeList(this, l);
        Intent intent = new Intent(this, MyListsActivity.class);
        intent.putExtra(EXTRA_ARGUMENT_LIST_ID, list_id);
        startActivity(intent);
    }
}
