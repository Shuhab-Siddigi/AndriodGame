package dk.dtu.mitprojektarkiv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.ImageView;

public class GameView extends SurfaceView implements SensorEventListener {

    // Accelormeter
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private int[] sensorVals = new int[3];

    // Properties
    private Bitmap ball,background,backgroundScale;
    SurfaceHolder holder;
    private GameThread gameThread;

    // Ball Properties
    Paint paint = new Paint();

    // Position of the objects
    private float xPosition = 0;
    private float yPosition = 700;

    private float xVel = 10;
    private float yVel = 2;

    // Data for the Images ---
    private int canvasHeight = 0;
    private int canvasWidth = 0;
    // Data for ball

    private int xBallPosition = 0;
    private int yBallPosition = 0;
    private static final int BALL_SIZE = 32;
    private int ballWidth = 0;
    private int ballHeight = 0;


    public GameView(final Context context) {
        super(context);
        // Create a new thread for the game
        gameThread = new GameThread(this);


        // Create a holder to take care of tasks and clean up
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {



            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                // Define Images to be processes as bitmaps
                ball = BitmapFactory.decodeResource(getResources(), R.drawable.dinowalk_1);
                background = BitmapFactory.decodeResource(getResources(),R.drawable.tetris);
                backgroundScale = Bitmap.createScaledBitmap(background,1920,1080,false);

                // Get the sizes of the different images
                ballWidth = ball.getWidth();
                ballHeight = ball.getHeight();


                gameThread.setRunning(true);
                gameThread.start();
                accelerometerInit(context);


            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                boolean retry = true;
                gameThread.setRunning(false);
                while (retry) {
                    try {
                        // join method waits for this thread to die
                        gameThread.join();
                        retry = false;
                    } catch (InterruptedException e) {

                    }
                }
            }
        });

    }

    @Override
    public void draw(Canvas canvas) {
        // For Background at things which are always there
        super.draw(canvas);

        canvas.drawBitmap(backgroundScale,0,0,null);
        xPosition+=10;

    }

    // For volatile things that can be removed again
    protected void OnDraw(Canvas canvas) {
        canvas.drawBitmap(ball, xPosition, yPosition, null);
        //canvas.drawCircle(600,400,80,this.paint);
        if(xPosition == 1920){
            xPosition *=-1;
        }
        if (xPosition == 0){
            xPosition = 0;
        }


    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorVals[0] = (int)event.values[0];
        sensorVals[1] = (int)event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public int[] accelerometerInit(Context context){
        sensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);

        return sensorVals;
    }
}