package be.LaPireTeam.wishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    ArrayList<List> showedLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_lists);

        listView = (ListView)findViewById(R.id.listViewFriendsLists);
        User user = Session.getInstance().getU();


        User friend = Session.getInstance().getLastClickedFriend();
        TextView title = (TextView) findViewById(R.id.title_friends_lists);
        title.setText("Lists of " + friend.getID());

        lists = DAOFactory.listDAO(this).getLists(friend);

        ArrayList<String> listsNames = new ArrayList<>();
        showedLists = new ArrayList<>();
        if(lists != null) {
            for (List l : lists) {
                if(DAOFactory.listDAO(this).can_see(l, user)) {
                    listsNames.add(l.getName());
                    showedLists.add(l);
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
                Session.getInstance().setLastClickedList(showedLists.get(position));
                openSeeWishesActivity();
            }
        });
    }

    public void openSeeWishesActivity(){
        Intent intent = new Intent(this, SeeWishesActivity.class);
        startActivity(intent);
    }
}
