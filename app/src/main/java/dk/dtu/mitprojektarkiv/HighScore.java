package dk.dtu.mitprojektarkiv;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HighScore extends Activity implements View.OnClickListener {

    // Log
    private static final String TAG = "High score";
    // Button Properties
    Button homePageBtn, highscoreBtn, selfieBtn, removeBtn, editBtn;

    // SQlite Database
    SqlLiteDataBase sqlLiteDataBase;

    // For the Camera
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        // Creating new instance of database;
        sqlLiteDataBase = new SqlLiteDataBase(this);

        // Camera

        // Knapper
        homePageBtn = (Button) findViewById(R.id.homePage);
        highscoreBtn = (Button) findViewById(R.id.seHighscoresBtn);
        selfieBtn = (Button) findViewById(R.id.cameraBtn);
        removeBtn = (Button) findViewById(R.id.deletePlayerBtn);
        editBtn = (Button) findViewById(R.id.mistakeBtn);

        // Knap Lytter
        homePageBtn.setOnClickListener(this);
        highscoreBtn.setOnClickListener(this);
        selfieBtn.setOnClickListener(this);
        removeBtn.setOnClickListener(this);
        editBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == homePageBtn) {
            finish();
        }

        if (view == highscoreBtn) {

        }

        if (view == selfieBtn) {
            // @wip
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (view != null) {
                startActivityForResult(intent, 0);
            }
        }

        if (view == removeBtn) {

        }

        if (view == editBtn) {

        } else {
            Log.e(TAG, "Something went wrong when pressing btn");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }
}
