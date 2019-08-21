package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PopUpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Pop Up Activity ";

    // Properties
    Button playGame;
    SqlLiteDataBase sqlLiteDataBase;
    EditText editPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        // Settings for the Pop Up Function
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int popUpWidth = displayMetrics.widthPixels;
        int popUpHeigth = displayMetrics.heightPixels;

        getWindow().setLayout((int) (popUpWidth * .8), (int) (popUpHeigth * .7));
        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        // Buttons
        playGame = (Button) findViewById(R.id.addPlayerBtn);
        // Button Listener
        playGame.setOnClickListener(this);

        // Edit Text and add
        editPlayer = (EditText) findViewById(R.id.PlayerName);

        // Sql Data base instance
        sqlLiteDataBase = new SqlLiteDataBase(this);


    }

    @Override
    public void onClick(View view) {
        if (view != null) {

            if (view == playGame) {
                boolean isInserted = sqlLiteDataBase.insertPlayerName(editPlayer.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(PopUpActivity.this, "Player Name Saved", Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(this, Game.class);
                startActivity(i);
            } else
                Toast.makeText(PopUpActivity.this, "Player Name could not be inserted", Toast.LENGTH_LONG).show();
        } else
            Log.e(TAG, "There was Something wrong changing from Pop Up Activity");
    }


    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
