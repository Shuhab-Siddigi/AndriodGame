package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Credits extends AppCompatActivity implements View.OnClickListener {

    // instances
    Button homePage;

    GestureDetectorCompat gestureDetectorCompat;
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        // Gradient Animation

        ConstraintLayout constraintLayout = findViewById(R.id.credits);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        // Lorem ipsum Scroll funcion
        ConstraintLayout marque = (ConstraintLayout) this.findViewById(R.id.credits);
        marque.setSelected(true);

        // Buttons
        homePage = (Button) findViewById(R.id.homePage);
        // Button Listener
        homePage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view == homePage) {
            finish(); // Kills Activity
        } else {
            System.out.println("Front page intent from Credits page went Wrong");
        }

    }

}
