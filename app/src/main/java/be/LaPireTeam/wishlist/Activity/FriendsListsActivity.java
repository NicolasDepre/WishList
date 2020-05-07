package be.LaPireTeam.wishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;
import be.LaPireTeam.wishlist.R;

public class FriendsListsActivity extends AppCompatActivity {

    ListView listView;
    List[] lists;
    String pseudoFriend;
    ArrayList<Integer> listIDs;

    public static final String EXTRA_ARGUMENT_LIST_ID = "be.LaPireTeam.wishlist.EXTRA_LIST_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_lists);

        listView = (ListView)findViewById(R.id.listViewFriendsLists);
        User user = Session.getInstance().getU();

        Intent intent = getIntent();
        pseudoFriend = intent.getStringExtra(AmisActivity.EXTRA_ARGUMENT_FRIEND_ID);
        User friend = DAOFactory.userDAO(this).getUserFromID(pseudoFriend);

        lists = DAOFactory.listDAO(this).getLists(friend);

        ArrayList<String> listsNames = new ArrayList<>();
        listIDs = new ArrayList<>();
        if(lists != null) {
            for (List l : lists) {
                if(DAOFactory.listDAO(this).can_see(l, user)) {
                    listsNames.add(l.getName());
                    listIDs.add(l.ID);
                }
            }
        }
        //récupérer un arraylist de la base de données
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listsNames);

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

    public void openSeeWishesActivity(View view, int id){
        Intent intent = new Intent(this, SeeWishesActivity.class);
        intent.putExtra(EXTRA_ARGUMENT_LIST_ID, id);
        startActivity(intent);
    }
}
