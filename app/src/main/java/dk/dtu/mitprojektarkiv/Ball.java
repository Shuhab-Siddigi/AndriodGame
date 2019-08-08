
package dk.dtu.mitprojektarkiv;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.View;

public class Ball extends View {

    // Properties

    private int xMin = 0;
    private int xMax;
    private int yMin = 0;
    private int yMax;
    private float ballRadius = 30;
    private float ballX = ballRadius + 20;
    private float ballY = ballRadius + 40;
    private float ballSpeedX = 8;
    private float ballSpeedY = 0;
    private RectF ballBounds;
    private Paint paint;


    // Constructor for the Ball
    public Ball(Context context) {
        super(context);
        ballBounds = new RectF();
        paint = new Paint();

        this.setFocusable(true);
        this.requestFocus();
    }


    // KeyUp Event Handler
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BUTTON_6: // Increase speed right
                ballSpeedY = 0;
                if
                (ballSpeedX > 0) ;
                else {
                    ballSpeedX = -10;
                }
                break;
            case KeyEvent.KEYCODE_BUTTON_4: // Increase speed left
                ballSpeedY = 0;
                if
                (ballSpeedX < 0) ;
                else {
                    ballSpeedX = -10;
                }
                break;
            case KeyEvent.KEYCODE_BUTTON_2: // Increase speed up
                ballSpeedX = 0;
                if
                (ballSpeedY < 0) ;
                else {
                    ballSpeedY = -10;
                }
                break;
            case KeyEvent.KEYCODE_BUTTON_8: // Increase speed down
                ballSpeedX = 0;
                if
                (ballSpeedY > 0) ;
                else {
                    ballSpeedY = -10;
                }
                break;
            case KeyEvent.KEYCODE_BUTTON_5: // Stop ball
                ballSpeedX = 0;
                ballSpeedY = 0;
                break;
        }
        return true; // Event håndteret
    }



    @Override
    protected void onDraw(Canvas canvas) {
        // Draw Ball

        ballBounds.set(ballX - ballRadius, ballY - ballRadius, ballX + ballRadius, ballSpeedY + ballRadius);
        paint.setColor(Color.BLACK);
        canvas.drawOval(ballBounds, paint);


        // Update the position of the ball, including collision detection and reaction
        update();


        try {
            Thread.sleep(30);
        } catch (
                InterruptedException exception) {
        }

        invalidate();

    }



    private void update() {
        // Get new (x,y) position
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        if (ballX + ballRadius > xMax) {
            ballSpeedX = -ballSpeedX;
            ballX = xMax - ballRadius;
        } else if (ballX - ballRadius < xMin) {
            ballSpeedX = -ballSpeedX;
            ballX = xMin + ballRadius;
        }
        if (ballY + ballRadius > yMax) {
            ballSpeedY = -ballSpeedY;
            ballY = yMax - ballRadius;
        } else if (ballY - ballRadius < yMin) {
            ballSpeedY = -ballSpeedX;
            ballY = yMin + ballRadius;
        }
    }

}
