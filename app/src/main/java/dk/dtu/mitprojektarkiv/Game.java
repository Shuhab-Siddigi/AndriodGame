
package dk.dtu.mitprojektarkiv;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


/*
This Activity is set to landscape in the AndroidManifest.xml file
if changes are needed that is the place to edit the orientation.
 */

public class Game extends Activity implements View.OnClickListener {

    private static final String TAG = "Game";

    Button exitBtn, leftBtn, rightBtn;
    public static int canvasHeight, canvasWidth;
    public static int leftBtnAction, rigthBtnAction = 0;
    private int exitBtnWidth = 300;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Must be placed here !!
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        canvasHeight = displayMetrics.heightPixels;
        canvasWidth = displayMetrics.widthPixels;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Frame layout for the visible and invisible Controls
        FrameLayout game = new FrameLayout(this);

        // Layer 1
        GameView gameView = new GameView(this);
        // Layer 2
        LinearLayout gameWidgets = new LinearLayout(this);
        // Layer 3

        //LinearLayout gameControls = new LinearLayout(this);

        // Buttons
        exitBtn = new Button(this);
        leftBtn = new Button(this);
        rightBtn = new Button(this);
        Log.i(TAG, "Created Buttons");

        // Visible Controls
        exitBtn.setWidth(exitBtnWidth);
        exitBtn.setText("Exit Game");
        exitBtn.setX(canvasWidth-exitBtnWidth);
        gameWidgets.addView(exitBtn);

        // Invisible Controls
        leftBtn.setWidth(canvasWidth / 2);
        leftBtn.setHeight(canvasHeight);
        leftBtn.getBackground().setAlpha(20);  // 25% transparent


        rightBtn.setWidth(canvasWidth / 2);
        rightBtn.setHeight(canvasHeight);
        rightBtn.getBackground().setAlpha(20);  // 25% transparent

        Log.i(TAG, "Canvas Heigth + Width = " + canvasHeight + " and " + canvasWidth);

      //  gameControls.addView(leftBtn);
       // gameControls.addView(rightBtn);

        Log.i(TAG, "Left And Right button added to the screen");

        // Creating layers for the objects
        // Layer 1
        game.addView(gameView);
        Log.i(TAG, "Adding first layer to view");
        // Layer 2
      //  game.addView(gameControls);
        Log.i(TAG, "Adding second layer to view");
        // Layer 3
        game.addView(gameWidgets);
        Log.i(TAG, "Adding third layer to view");

        setContentView(game);
        Log.i(TAG, "Setting Content View to Layer 1/2/3");
        exitBtn.setOnClickListener(this);

        // Have supressed acceseorbility warining !!

        leftBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == motionEvent.getAction()) {
                    int tempValue = leftBtnAction;
                    leftBtnAction = motionEvent.getAction();
                    if (tempValue != leftBtnAction) {
                        Log.i(TAG, "Left Button now has the value = " + motionEvent.getAction());
                    }
                }
                return false;
            }
        });

        rightBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == motionEvent.getAction()) {
                    int tempValue = rigthBtnAction;
                    rigthBtnAction = motionEvent.getAction();
                    if(tempValue != rigthBtnAction) {
                        System.out.println("Button Left Pressed = " + motionEvent.getAction());
                    }
                }
                return false;
            }
        });
    }

    public void onClick(View view) {
        if (view == exitBtn) {
            Intent intent = new Intent(this, FrontPage.class);
            startActivity(intent);
            System.out.println("Hello");
        }
        /*
        if (view == leftBtn){
            System.out.println("Left Button Pressed");
        }
        if (view == rightBtn){
            System.out.println("Right Button Pressed");
        }
        */
        // re-starts this activity from game-view. add this.finish(); to remove from stack
    }


}
