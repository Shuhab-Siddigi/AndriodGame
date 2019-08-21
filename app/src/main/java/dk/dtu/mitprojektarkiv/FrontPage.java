package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FrontPage extends AppCompatActivity implements View.OnClickListener {

    // Properties

    Button startGameBtn, highScoreBtn, creditsBtn, justStuffBtn,deleteDataBtn;

    ImageView imageView;

    SqlLiteDataBase sqlLiteDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);

        // Buttons
        startGameBtn = (Button) findViewById(R.id.startGame);
        highScoreBtn = (Button) findViewById(R.id.highScore);
        creditsBtn = (Button) findViewById(R.id.credits);
        justStuffBtn = (Button) findViewById(R.id.justStuffBtn);
        deleteDataBtn = (Button) findViewById(R.id.deleteBtn);

        // Button Listener
        startGameBtn.setOnClickListener(this);
        highScoreBtn.setOnClickListener(this);
        creditsBtn.setOnClickListener(this);
        justStuffBtn.setOnClickListener(this);
        deleteDataBtn.setOnClickListener(this);

        // Creating moving text for front Page
        TextView welcomScreen = (TextView) this.findViewById(R.id.welcomeScreen);
        // If the orientation is in portrait mode then show animation
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            welcomScreen.setSelected(true);
        } // if its not in portrait mode then set the text empty
        else {
            welcomScreen.setText("");
        }

        // Camera
        imageView = (ImageView) findViewById(R.id.imageView);

        // Sql Data base
        sqlLiteDataBase = new SqlLiteDataBase(this);

    }

    @Override // Small mux for handling what happens if you press the buttons
    public void onClick(View view) {
        if (view != null) {
            if (view == startGameBtn) {

                startGameBtn.setText("Lets GO!");
                Intent i = new Intent(getApplicationContext(), PopUpActivity.class);
                startActivity(i);
            } else if (view == highScoreBtn) {
                Intent i = new Intent(this, HighScore.class);
                startActivity(i);
            } else if (view == creditsBtn) {
                Intent i = new Intent(this, Credits.class);
                startActivity(i);
            } else if (view == justStuffBtn) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }else if (view == deleteDataBtn){
                Intent intent = new Intent(this,DeletePlayer.class);
                startActivityForResult(intent, 0);
            } else {
                System.out.println("Front Page activity intent went Wrong");
            }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if ( data != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
    }


}
