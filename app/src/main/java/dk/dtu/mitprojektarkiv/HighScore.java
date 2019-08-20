package dk.dtu.mitprojektarkiv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HighScore extends Activity implements View.OnClickListener {

    // Log
    private static final String TAG = "High score";
    // Button Properties
    Button homePageBtn, viewPlayerBtn;

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
        viewPlayerBtn = (Button) findViewById(R.id.seHighscoresBtn);


        // Knap Lytter
        homePageBtn.setOnClickListener(this);
        viewPlayerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            if (view == homePageBtn) {
                finish();
            }

            if (view == viewPlayerBtn) {

                Cursor res = sqlLiteDataBase.getAllData();
                if (res.getCount() == 0) {
                    Toast.makeText(HighScore.this, "You are the first player friend", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :" + res.getString(0) + "\n");
                    buffer.append("Player :" + res.getString(1) + "\n");
                    buffer.append("Points :" + res.getString(2) + "\n");
                }
                showMessage("Data", buffer.toString());
            }
        } else {
            Log.e(TAG, "Something went wrong when pressing btn in Highscore Class");
        }
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

