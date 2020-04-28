package be.LaPireTeam.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int Connexion = 0 ;
    private static final int Inscription = 1 ;
    private static final int Wishlist = 2 ;
    private static final int Amis = 3 ;
    private static final int Profile = 4 ;


    public Button[] listeBoutons = new Button[50] ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        listeBoutons[Connexion] = ((Button) this.findViewById(R.id.Connexion)) ;
        listeBoutons[Inscription] = ((Button) this.findViewById(R.id.Inscription));
        for (int i = 0 ; i < listeBoutons.length ; i ++){
            listeBoutons[i].setOnClickListener(this);
        }

    }



    int activite ;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Connexion :
                activite = Connexion ;
                break ;
            case R.id.Inscription :
                activite = Inscription ;
                break ;
        }
    }
}
