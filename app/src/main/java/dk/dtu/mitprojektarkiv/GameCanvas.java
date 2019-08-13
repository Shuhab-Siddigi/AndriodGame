package dk.dtu.mitprojektarkiv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.View;

public class GameCanvas extends View {

    // Properties
    private Paint paint;
    private RectF ballBounds;

    // Instances
    GameSensor gameSensor = new GameSensor();

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
        ballBounds = new RectF();
        this.setFocusable(true);
        this.requestFocus();

    }

    // Drawing on Canvas
    @Override
    protected void onDraw(Canvas canvas) {
        //Draw Ball

        // Move ball by Accelerometer
        // Ball bold = new Ball(gameSensor.xPosition,gameSensor.yPosition);

        // Move ball by Keyboard / Key input
        Ball bold = new Ball(keyInput.xPosition, keyInput.yPosition);

        // Draw Objects on Canvas
        bold.drawBall(canvas, paint);

        // Create a small delay so it can be updated correctly
      /*try {
            Thread.sleep(30);
        } catch (
                InterruptedException exception) {
        }
        */

        // UI Thread that updates the Canvas / View
        invalidate(); // Updates Canvas

    }

}
