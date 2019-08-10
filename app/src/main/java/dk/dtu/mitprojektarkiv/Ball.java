package dk.dtu.mitprojektarkiv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.View;

public class Ball extends View {

    // Ball Properties

    private int ballWidth = 80;
    private int ballHeight = 80;
    private int posX = 0;
    private int posY = 0;
    // Other Propeties
    private Paint paint;
    private RectF ballBounds;

    public Ball(Context context) {

        super(context);
        paint = new Paint();
        ballBounds = new RectF();
        this.setFocusable(true);
        this.requestFocus();

    }

    @Override
    protected void onDraw(Canvas canvas){

        //Draw Ball
        ballBounds.set(posX,posY,ballWidth+posX,ballHeight+posY);
        paint.setColor(Color.BLACK);
        canvas.drawOval(ballBounds,paint);


        move();

        // Updates the View every 30 millis
      /*try {
            Thread.sleep(30);
        } catch (
                InterruptedException exception) {
        }
        */
        invalidate();

    }

    private void move() {
        // @wip
    }


    // KeyUp Event Handler
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_D: // move right
                posX += 5;
                System.out.println("PosX Right = "+ posX);
                break;
            case KeyEvent.KEYCODE_S: // move left
                posY  +=5;
                System.out.println("PosY left = "+ posY);
                break;
            case KeyEvent.KEYCODE_A: // move down
                posX -=5;
                System.out.println("PosX down = "+ posX);
                break;
            case KeyEvent.KEYCODE_W: // move up
                posY -=5;
                System.out.println("PosY up = "+ posY);
                break;
            case KeyEvent.KEYCODE_SPACE: // Stop ball
                posX = 0;
                posY = 0;
                System.out.println(" posY and posX  " + posX + posY);
                break;
        }
        return true; // Event håndteret
    }





}
