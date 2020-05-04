package be.LaPireTeam.wishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import be.LaPireTeam.wishlist.DAO.DAO;
import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.Objects.Product;
import be.LaPireTeam.wishlist.Objects.Wish;
import be.LaPireTeam.wishlist.R;

public class NewWishActivity extends AppCompatActivity {
    public static final String EXTRA_ARGUMENT_LIST_ID = "be.LaPireTeam.wishlist.EXTRA_LIST_ID";
    int list_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_wish);
        Intent intent = getIntent();
        list_id = intent.getIntExtra(SeeWishesActivity.EXTRA_ARGUMENT_LIST_ID, -1);

    }

    public void createNewWish(View view){
        EditText inputName = (EditText) findViewById(R.id.new_wish_name_inputfield);
        String name = inputName.getText().toString();
        EditText inputPriority = (EditText) findViewById(R.id.new_wish_priority_inputfield);
        int priority = Integer.parseInt(inputPriority.getText().toString());
        EditText inputComments = (EditText) findViewById(R.id.new_wish_comments_inputfield);
        String comments = inputComments.getText().toString();
        EditText inputProduct = (EditText) findViewById(R.id.new_wish_product_inputfield);
        String product = inputName.getText().toString();
        Product p = new Product();
        p.ID = 200;
        Wish w = new Wish(-1, name, priority, comments.split(" "), new Product());
        DAOFactory.WishDAO(this).insert_wish(w,list_id)
        //TODO add wish to database when created
        Intent intent = new Intent(this, SeeWishesActivity.class);
        intent.putExtra(EXTRA_ARGUMENT_LIST_ID, list_id);
        startActivity(intent);
    }
}
