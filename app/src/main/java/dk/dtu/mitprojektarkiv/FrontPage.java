package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class FrontPage extends AppCompatActivity implements View.OnClickListener {

    // Instantations

    Button startGameBtn, highScoreBtn, creditsBtn,extraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);

        // Knapper
        startGameBtn = (Button) findViewById(R.id.startGame);
        highScoreBtn = (Button) findViewById(R.id.highScore);
        creditsBtn = (Button) findViewById(R.id.credits);
        extraBtn = (Button) findViewById(R.id.extraBtn);

        // Knap Lytter
        startGameBtn.setOnClickListener(this);
        highScoreBtn.setOnClickListener(this);
        creditsBtn.setOnClickListener(this);
        extraBtn.setOnClickListener(this);
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
