package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class FrontPage extends AppCompatActivity implements View.OnClickListener {

    // Properties

    Button startGameBtn, highScoreBtn, creditsBtn,extraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);

        // Buttons
        startGameBtn = (Button) findViewById(R.id.startGame);
        highScoreBtn = (Button) findViewById(R.id.highScore);
        creditsBtn = (Button) findViewById(R.id.credits);
        extraBtn = (Button) findViewById(R.id.extraBtn);

        // Button Listener
        startGameBtn.setOnClickListener(this);
        highScoreBtn.setOnClickListener(this);
        creditsBtn.setOnClickListener(this);
        extraBtn.setOnClickListener(this);

        // Creating moving text for front Page
        TextView welcomScreen = (TextView) this.findViewById(R.id.welcomeScreen);
        // If the orientation is in portrait mode then show animation
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            welcomScreen.setSelected(true);
        } // if its not in portrait mode then set the text empty
        else {
            welcomScreen.setText("");
        }


    }

    @Override // Small mux for handling what happens if you press the buttons
    public void onClick(View view) {

        if (view == startGameBtn) {
            startGameBtn.setText("Lets GO!");
            Intent i = new Intent(this, Game.class);
            startActivity(i);
        } else if (view == highScoreBtn) {
            Intent i = new Intent(this, HighScore.class);
            startActivity(i);
        } else if (view == creditsBtn) {
            Intent i = new Intent(this, Credits.class);
            startActivity(i);
        }
        else if ( view == extraBtn ){
            Intent i = new Intent(this, GameView.class);
            startActivity(i);
        }

            else {
            System.out.println("Front Page activity intent went Wrong");
        }
    }

    // Warning for if you press the back button on the Front page
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(FrontPage.this);
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(true);
        builder.setNegativeButton("No i want more fun!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
