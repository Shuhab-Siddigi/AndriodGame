
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
    float posX;
    float posY;
    /*
    int posZ; // For further use later
    */

    private RectF ballBounds;

    // Creating a Constructor for the ball to be passed on
    public Ball(float posX, float posY) {
         this.posX = posX;
         this.posY = posY;
        this.ballBounds = new RectF();
    }
    // Draw Method for the ball with bounds
    public void drawBall(Canvas canvas, Paint paint){

        ballBounds.set(this.posX,this.posY,this.ballWidth+this.posX,this.ballHeight+this.posY);
        paint.setColor(Color.BLACK);
        canvas.drawOval(this.ballBounds,paint);
    }


}
