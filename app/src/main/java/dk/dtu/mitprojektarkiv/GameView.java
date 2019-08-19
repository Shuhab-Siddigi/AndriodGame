package dk.dtu.mitprojektarkiv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements SensorEventListener {

    // Measurement Properties
    private static int canvasHeight = Game.canvasHeight;
    private static int canvasWidth = Game.canvasWidth;
    private static int playerHeigth = 0;
    private static int playerWidth = 0;

    // Bitmap Properties
    private Bitmap dino, background, backgroundScale;
    SurfaceHolder holder;
    private GameThread gameThread;
    private ArrayList<Bitmap> dinoWalkRight = new ArrayList<>();

    // Ball Properties
    Paint paint = new Paint();

    // Position of the objects
    private float xPosition = 0;
    private float yPosition = 700;
    private float xVel = 10;
    private float yVel = 2;

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

                // Define Images to be processes as bitmaps
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkrigth_2));
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkrigth_3));
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkrigth_4));
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkrigth_5));
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkrigth_6));
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkrigth_7));
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkright_8));

                // Creating background for the game
                background = BitmapFactory.decodeResource(getResources(), R.drawable.tetris);
                backgroundScale = Bitmap.createScaledBitmap(background, canvasWidth, canvasHeight, false);

                // Get the sizes of the different images
                gameThread.setRunning(true);
                gameThread.start();
                accelerometerInit(context);
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                // Get Information from the different bitmaps
                playerWidth = dinoWalkRight.get(0).getWidth();
                playerHeigth = dinoWalkRight.get(0).getHeight();
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

    // For logical updates
    protected void update(Canvas canvas) {
        //canvas.drawBitmap(dino, xPosition, yPosition, null);

    }

    // The draw function on the canvas
    @Override
    public void draw(Canvas canvas) {
        // For Background at things which are always there
        super.draw(canvas);
        canvas.drawBitmap(backgroundScale, 0, 0, null);
        canvas.drawBitmap(dinoWalkRight.get(0), canvasWidth/2-dinoWalkRight.get(1).getWidth()/2, canvasHeight/20+600, null);
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

}