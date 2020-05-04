package be.LaPireTeam.wishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import be.LaPireTeam.wishlist.R;

public class NewListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
    }

    public void createNewList(View view){
        EditText inputName = (EditText) findViewById(R.id.new_list_name_inputfield);
        String name = inputName.getText().toString();
        EditText inputFriends = (EditText) findViewById(R.id.new_list_share_inputfield);
        String friends = inputFriends.getText().toString();
        //List l = new List(-1);
        //l.setName(name);
        //TODO récupérer les friends et users to share et les ajouter
        //TODO add list to database when created
        Intent intent = new Intent(this, MyListsActivity.class);
        startActivity(intent);
    }
}
