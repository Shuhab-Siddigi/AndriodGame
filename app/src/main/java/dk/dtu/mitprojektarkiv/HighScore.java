package dk.dtu.mitprojektarkiv;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HighScore extends Activity implements View.OnClickListener {

    // Log
    private static final String TAG = "High score";
    // Button Properties
    Button homePageBtn, highscoreBtn, selfieBtn, removeBtn, editBtn;

    // SQlite Database
    SqlLiteDataBase sqlLiteDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        // Creating new instance of database;
        sqlLiteDataBase = new SqlLiteDataBase(this);

        // Knapper
        homePageBtn = (Button) findViewById(R.id.homePage);
        highscoreBtn = (Button) findViewById(R.id.seHighscoresBtn);
        removeBtn = (Button) findViewById(R.id.deletePlayerBtn);
        editBtn = (Button) findViewById(R.id.mistakeBtn);

        // Knap Lytter
        homePageBtn.setOnClickListener(this);
        highscoreBtn.setOnClickListener(this);
        removeBtn.setOnClickListener(this);
        editBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            if (view == homePageBtn) {
                finish();
            }




            else {
                Log.e(TAG, "Something went wrong when pressing btn in Highscore Class");
            }




        }
    }





}
