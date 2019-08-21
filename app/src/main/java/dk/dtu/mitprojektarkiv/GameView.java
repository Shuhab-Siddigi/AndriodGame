package dk.dtu.mitprojektarkiv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameView extends SurfaceView implements SensorEventListener {

    private static final String TAG = "Game";

    // Measurement Properties

    private static int canvasHeight = Game.canvasHeight;
    private static int canvasWidth = Game.canvasWidth;


    // Game View and Thread Properties
    SurfaceHolder holder;
    private GameThread gameThread;

    // Bit map properties

    private Bitmap dino;

    // Ball Properties
    Paint paint = new Paint();
    private float xPosition = 0;
    private float yPosition = 0;
    private int radius = 30;
    private Paint ballColor = new Paint();

    // Dino properties
    private int dinoXposition = 300;
    private int dinoYposition = 400;

    // Game Properties
    private Bitmap backGround;
    private int score = 0;
    // Accelormeter
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private int[] sensorValues = new int[3];

    public GameView(final Context context) {
        super(context);
        // Create a new thread for the game
        gameThread = new GameThread(this);

        // Create a holder to take care of tasks and clean up
        holder = getHolder();


        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                Log.i(TAG, "Surface Created");

                backGround = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tetris), Game.canvasWidth, Game.canvasHeight, false);
                dino = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dino), 64, 64, false);


                gameThread.setRunning(true);
                gameThread.start();
                accelerometerInit(context);

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                Log.i(TAG, "Surface Changed");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                Log.i(TAG, "Surface Destroyed");
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

    // For logical updates
    protected void update(Canvas canvas) {
        ballMovement(canvas);
    }

    // The draw function on the canvas
    @Override
    public void draw(Canvas canvas) {
        if (canvas != null) {
            super.draw(canvas);
            canvas.drawBitmap(backGround, 0, 0, null);
            paint.setColor(Color.CYAN);
            canvas.drawCircle(xPosition, yPosition, radius, paint);
            canvas.drawBitmap(dino, dinoXposition, dinoYposition, null);
            if(hitCheck((int)xPosition,(int)yPosition) == true){
                System.out.println("HIT!");
            }

        } else {
            Log.e(TAG, "There is no canvas to draw on! ");
        }
    }
// Sensor Methods

    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorValues[0] = (int) event.values[0];
        sensorValues[1] = (int) event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public int[] accelerometerInit(Context context) {

        sensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        return sensorValues;
    }


    public void ballMovement(Canvas canvas) {

        xPosition = xPosition - (int) sensorValues[0];
        yPosition = yPosition + (int) sensorValues[1];
        //Make sure we do not draw outside the bounds of the view.
        //So the max values we can draw to are the bounds + the size of the circle
        if (xPosition <= 0 + radius) {
            xPosition = 0 + radius;
        }
        if (xPosition >= Game.canvasWidth - radius) {
            xPosition = Game.canvasWidth - radius;
        }
        if (yPosition <= 0 + radius) {
            yPosition = 0 + radius;
        }
        if (yPosition >= Game.canvasHeight - radius * 3) {
            yPosition = Game.canvasHeight - radius * 3;
        }
    }

    public boolean hitCheck(int x, int y) {
        if (dinoXposition < xPosition && xPosition < (dinoXposition + dino.getWidth()) &&
        dinoYposition < yPosition && yPosition <(dinoYposition + dino.getHeight())){
            return true;
        }
        else return false;
    }


}








