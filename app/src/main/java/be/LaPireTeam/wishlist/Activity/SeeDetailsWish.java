package be.LaPireTeam.wishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.Wish;
import be.LaPireTeam.wishlist.R;

public class SeeDetailsWish extends AppCompatActivity {

    List currentList;
    Wish currentWish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_details_wish);

        currentWish = Session.getInstance().getLastClickedWish();
        currentList = Session.getInstance().getLastClickedList();
        TextView name = (TextView) findViewById(R.id.wish_name_detail_textview);
        name.setText(currentWish.getName());
        TextView priority = (TextView) findViewById(R.id.new_wish_priority_detail_textview);
        priority.setText(String.valueOf(currentWish.getPriority()));
        TextView com = (TextView) findViewById(R.id.wish_comments_detail_textview);
        com.setText(currentWish.getComment());
        TextView product = (TextView) findViewById(R.id.wish_product_detail_textview);
        product.setText(currentWish.getProduct());
        TextView status = (TextView) findViewById(R.id.wish_status_detail_textview);
        Button booking = (Button) findViewById(R.id.bookingButton);

        if (currentWish.getBookingStatus()) {
            status.setText("Wish already booked by someone");
            booking.setText("Remove Reservation Wish");
        } else {
            status.setText("Not yet reserved");
            booking.setText("Book wish");
        }
    }

    public void bookingButton(View view) {

        if(currentWish.getBookingStatus()){
            DAOFactory.wishDAO(this).changeBookingStatus(currentWish.ID, false);
            currentWish.setBookingStatus(false);
            Session.getInstance().setLastClickedWish(currentWish);
        }else{
            DAOFactory.wishDAO(this).changeBookingStatus(currentWish.ID, true);
            currentWish.setBookingStatus(true);
            Session.getInstance().setLastClickedWish(currentWish);
        }
        Intent intent = new Intent(this, SeeWishesActivity.class);
        startActivity(intent);
    }

    public void deleteWish(View view) {
        DAOFactory.wishDAO(this).removeWish(currentWish);

        Intent intent = new Intent(this, SeeWishesActivity.class);
        startActivity(intent);
    }

}
