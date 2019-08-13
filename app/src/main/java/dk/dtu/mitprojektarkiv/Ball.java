
package dk.dtu.mitprojektarkiv;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


public class Ball {

    // Dimensions for the ball
    private static final int ballWidth = 80;
    private static final int ballHeight = 80;

    // The positions of the ball
    float xPosition;
    float yPosition;
    /*
    int posZ; // For further use later
    */
    private RectF ballBounds;

    // Creating a Constructor for the ball to be passed on
    public Ball(float posX, float yPosition) {
        this.xPosition = posX;
        this.yPosition = yPosition;
        this.ballBounds = new RectF();
    }

    // Draw Method for the ball with bounds
    public void drawBall(Canvas canvas, Paint paint) {

        ballBounds.set(this.xPosition, this.yPosition, this.ballWidth + this.xPosition, this.ballHeight + this.yPosition);
        paint.setColor(Color.BLACK);
        canvas.drawOval(this.ballBounds, paint);
    }


}

