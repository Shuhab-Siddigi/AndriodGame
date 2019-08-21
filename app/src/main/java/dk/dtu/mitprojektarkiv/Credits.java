package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Credits extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Credits";

    // instances
    Button homePageBtn;

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

       // Cast Constraint layout so that the animations is set true
        ConstraintLayout marque = (ConstraintLayout) this.findViewById(R.id.credits);
        marque.setSelected(true);

        // Buttons
        homePageBtn = (Button) findViewById(R.id.homePageBtn);
        // Button Listener
        homePageBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        if (view == homePageBtn) {
            finish(); // Kills Activity
        } else {
            System.out.println("Front page intent from Credits page went Wrong");
        }

    }

}
