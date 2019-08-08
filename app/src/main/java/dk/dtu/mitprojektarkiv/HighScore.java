package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HighScore extends AppCompatActivity implements View.OnClickListener {

    // Forskellige instanser
    Button homePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        // Knapper

        homePage = (Button) findViewById(R.id.homePage);

        // Knap Lytter

        homePage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if ( view == homePage){

            // Lukker nuværende Activity
            finish();

        }
        else{
            System.out.println("High Score Page activity intent went Wrong");
        }

    }
}
