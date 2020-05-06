package be.LaPireTeam.wishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.Objects.User;
import be.LaPireTeam.wishlist.Objects.Wish;
import be.LaPireTeam.wishlist.R;

public class SeeDetailsWish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_details_wish);

        Intent intent = getIntent();
        final int wish_id = intent.getIntExtra(SeeWishesActivity.EXTRA_ARGUMENT_WISH_ID, -1);
        Wish wish = DAOFactory.WishDAO(this).getWishFromWishID(wish_id);

        TextView name = (TextView) findViewById(R.id.wish_name_detail_textview);
        name.setText(wish.getName());
        TextView priority = (TextView) findViewById(R.id.new_wish_priority_detail_textview);
        priority.setText(String.valueOf(wish.getPriority()));
        TextView com = (TextView) findViewById(R.id.wish_comments_detail_textview);
        com.setText(wish.getCommentary());
        TextView product = (TextView) findViewById(R.id.wish_product_detail_textview);
        product.setText(wish.getProduct());
    }

}
