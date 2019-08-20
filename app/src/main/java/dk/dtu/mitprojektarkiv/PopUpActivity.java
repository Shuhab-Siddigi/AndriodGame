package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class PopUpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Pop Up Activity ";

    // Properties
    Button playGame;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int popUpWidth = displayMetrics.widthPixels;
        int popUpHeigth = displayMetrics.heightPixels;

        getWindow().setLayout((int)(popUpWidth*.8),(int)(popUpHeigth*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        playGame = (Button) findViewById(R.id.addPlayerBtn);

        // Button Listener
        playGame.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view != null){
            if ( view == playGame){
                Intent i = new Intent(this, Game.class);
                startActivity(i);
            }
        }
        else {
            Log.e(TAG,"There was Something wrong changing from Pop Up Activity");
        }
    }
}
