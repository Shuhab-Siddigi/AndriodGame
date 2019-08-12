
package dk.dtu.mitprojektarkiv;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;



public class Ball {

    private static final int ballWidth = 80;
    private static final int ballHeight = 80;

    int posX;
    int posY;
    private RectF ballBounds;


    public Ball(int posX, int posY) {
         this.posX = posX;
         this.posY = posY;
        this.ballBounds = new RectF();
    }

    public void drawBall(Canvas canvas, Paint paint){

        ballBounds.set(this.posX,this.posY,this.ballWidth+this.posX,this.ballHeight+this.posY);
        paint.setColor(Color.BLACK);
        canvas.drawOval(this.ballBounds,paint);
    }


}
