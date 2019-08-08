package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;

public class Credits extends AppCompatActivity implements View.OnClickListener {

    // Forskellige instanser
    Button homePage;

    GestureDetectorCompat gestureDetectorCompat;
    GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        // Knapper
        homePage = (Button) findViewById(R.id.homePage);
        // Knap Lytter
        homePage.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        if ( view == homePage){

            finish();
          //  Intent i = new Intent(this, FrontPage.class);
          //  startActivity(i);
        }
        else{
            System.out.println("Credits Page activity intent went Wrong");
        }

    }

}
