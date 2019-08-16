/*
package dk.dtu.mitprojektarkiv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.View;

public class GameCanvas extends View {

    // Properties
    private Paint paint;

    // Instances
    GameSensor gameSensor = new GameSensor();
    ImageDraw drawObjects;

    // -------- Debug tool or Keyboard / key input ----------------- //
    int xPosition;
    int yPosition;
    KeyInput keyInput = new KeyInput(this.xPosition, this.yPosition);

    // KeyUp Event Handler
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        keyInput.Keys(keyCode);
        return true;
    }
    // ------------------------------------------------------------- //

    // Creating Canvas
    public GameCanvas(Context context) {

        super(context);
        paint = new Paint();
        this.setFocusable(true);
        this.requestFocus();

    }

    // Drawing on Canvas
    @Override
    protected void onDraw(Canvas canvas) {
        //Draw ImageDraw

        // Move ball by Accelerometer
        // ImageDraw bold = new ImageDraw(gameSensor.xPosition,gameSensor.yPosition);

        // Move ball by Keyboard / Key input
        ImageDraw bold = new ImageDraw(keyInput.xPosition,keyInput.yPosition,50,50);
        ImageDraw firkart = new ImageDraw(keyInput.xPosition,keyInput.yPosition,50,40);
        // Draw Objects on Canvas
        bold.drawBall(canvas, paint);
        drawObjects.drawSquare(canvas,paint);

        // Create a small delay so it can be updated correctly
      try {
            Thread.sleep(30);
        } catch (
                InterruptedException exception) {
        }

        // UI Thread that updates the Canvas / View
        invalidate(); // Updates Canvas

    }

}
*/