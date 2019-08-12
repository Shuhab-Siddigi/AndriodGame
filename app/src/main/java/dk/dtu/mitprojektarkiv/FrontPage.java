package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FrontPage extends AppCompatActivity implements View.OnClickListener {

    // Forskellige instanteringer

    Button startGameBtn, highScoreBtn, creditsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);

        // Knapper
        startGameBtn = (Button) findViewById(R.id.startGame);
        highScoreBtn = (Button) findViewById(R.id.highScore);
        creditsBtn = (Button) findViewById(R.id.credits);

        // Knap Lytter
        startGameBtn.setOnClickListener(this);
        highScoreBtn.setOnClickListener(this);
        creditsBtn.setOnClickListener(this);

    }

    @Override // Handlinger for hvis man trykker på knapperne
    public void onClick(View view) {

        if ( view == startGameBtn){

           startGameBtn.setText("Lets GO!");
            Intent i = new Intent(this, Game.class);
            startActivity(i);

        }
        else if ( view == highScoreBtn){


            Intent i = new Intent(this, HighScore.class);
            startActivity(i);

        }
        else if (view == creditsBtn){

            Intent i = new Intent(this, Credits.class);
            startActivity(i);

        }
        else{
            System.out.println("Front Page activity intent went Wrong");
        }
    }

    // Advarsels box til hvis man kommer til at trykke på tilbage knappen
    @Override
    public void onBackPressed(){
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
