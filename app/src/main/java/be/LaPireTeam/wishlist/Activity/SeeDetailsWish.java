package be.LaPireTeam.wishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.Objects.Wish;
import be.LaPireTeam.wishlist.R;

public class SeeDetailsWish extends AppCompatActivity {

    public static final String EXTRA_ARGUMENT_LIST_ID = "be.LaPireTeam.wishlist.EXTRA_LIST_ID";
    int list_id;
    int wish_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_details_wish);

        Intent intent = getIntent();
        list_id = intent.getIntExtra(SeeWishesActivity.EXTRA_ARGUMENT_LIST_ID, -1);
        wish_id = intent.getIntExtra(SeeWishesActivity.EXTRA_ARGUMENT_WISH_ID, -1);
        Log.i("INFO", "wishID = " + wish_id);
        Wish wish = DAOFactory.WishDAO(this).getWishFromWishID(wish_id);
        if (wish == null) {
            Log.i("INFO", "wish is null");
        }
        if (wish.getBookingStatus()) {
            Log.i("INFOOOOOOOOOOOOOO", "wish created with bookingstatus true");
        } else {
            Log.i("INFOOOOOOOOOOOOOO", "wish created with bookingstatus false");
        }

        TextView name = (TextView) findViewById(R.id.wish_name_detail_textview);
        name.setText(wish.getName());
        TextView priority = (TextView) findViewById(R.id.new_wish_priority_detail_textview);
        priority.setText(String.valueOf(wish.getPriority()));
        TextView com = (TextView) findViewById(R.id.wish_comments_detail_textview);
        com.setText(wish.getComment());
        TextView product = (TextView) findViewById(R.id.wish_product_detail_textview);
        product.setText(wish.getProduct());
        TextView status = (TextView) findViewById(R.id.wish_status_detail_textview);
        Button booking = (Button) findViewById(R.id.bookingButton);

        if (wish.getBookingStatus()) {
            status.setText("Wish already booked by someone");
            booking.setText("Remove Reservation Wish");
            //booking.setVisibility(View.INVISIBLE);
        } else {
            status.setText("Not yet reserved");
            booking.setText("Book wish");
            //booking.setVisibility(View.VISIBLE);
        }
    }

    public void bookingButton(View view) {

        Wish w = DAOFactory.WishDAO(this).getWishFromWishID(wish_id);
        if (w.getBookingStatus()) {
            if (DAOFactory.WishDAO(this).changeBookingStatus(wish_id, false)) {
                Log.i("INFOOO", "c'est passséééé");
            }
            w.setBookingStatus(false);
            Log.i("INFO", "status changed to false");
        } else {
            if (DAOFactory.WishDAO(this).changeBookingStatus(wish_id, true)) {
                Log.i("INFOOO", "c'est passséééé");
            }
            w.setBookingStatus(true);
            Log.i("INFO", "status changed to true");
        }

        Intent intent = new Intent(this, SeeWishesActivity.class);
        intent.putExtra(EXTRA_ARGUMENT_LIST_ID, list_id);
        startActivity(intent);
    }

    public void deleteWish(View view) {
        Wish wish = DAOFactory.WishDAO(this).getWishFromWishID(wish_id);
        DAOFactory.WishDAO(this).removeWish(wish);

        Intent intent = new Intent(this, SeeWishesActivity.class);
        intent.putExtra(EXTRA_ARGUMENT_LIST_ID, list_id);
        startActivity(intent);
    }

}
