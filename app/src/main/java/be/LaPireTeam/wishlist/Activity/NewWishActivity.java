package be.LaPireTeam.wishlist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import be.LaPireTeam.wishlist.DAO.DAO;
import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.DAO.IDUtility;
import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.Wish;
import be.LaPireTeam.wishlist.R;

public class NewWishActivity extends AppCompatActivity {
    List currentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_wish);

        currentList = Session.getInstance().getLastClickedList();
    }

    public void createNewWish(View view) {
        EditText inputName = findViewById(R.id.new_wish_name_inputfield);
        String name = inputName.getText().toString();
        EditText inputPriority = findViewById(R.id.new_wish_priority_inputfield);

        Integer priority;
        try {
            priority = Integer.parseInt(inputPriority.getText().toString());
        } catch (Exception e) {
            priority = null;
        }
        EditText inputComments = findViewById(R.id.new_wish_comments_inputfield);
        String comments = inputComments.getText().toString();
        EditText inputProduct = findViewById(R.id.new_wish_product_inputfield);
        String product = inputProduct.getText().toString();

        if (name.length() == 0) {
            TextView alert = findViewById(R.id.alerteNewWish);
            alert.setText("Please fill a name");
            alert.setVisibility(View.VISIBLE);
            return;
        }

        Wish w = new Wish(IDUtility.getNewWishID(new DAO(this)), name, priority, comments, product);
        DAOFactory.wishDAO(this).insert_wish(w, currentList.ID);
        Intent intent = new Intent(this, SeeWishesActivity.class);
        startActivity(intent);
    }
}
