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

import java.util.ArrayList;

public class GameView extends SurfaceView implements SensorEventListener {

    private static final String TAG = "Game";

    // Measurement Properties
    private static int canvasHeight = Game.canvasHeight;
    private static int canvasWidth = Game.canvasWidth;
    private static int playerHeigth = 0;
    private static int playerWidth = 0;

    // Bitmap Properties
    private Bitmap dino, background, backgroundScale, currentBitmap;
    SurfaceHolder holder;
    private GameThread gameThread;
    private ArrayList<Bitmap> dinoWalkRight = new ArrayList<>();
    private ArrayList<Bitmap> dinoWalkLeft = new ArrayList<>();

    // Ball Properties
    Paint paint = new Paint();

    // Position of the objects
    private float xPosition = 0;
    private float yPosition = 700;
    private float xVel = 10;
    private float yVel = 2;
    private int counter, walkNumber,direction = 0;
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
                Log.i(TAG,"Surface Created");
                /*
                // Define Images to be processes as bitmaps
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkrigth_2));
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkrigth_3));
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkrigth_4));
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkrigth_5));
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkrigth_6));
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkrigth_7));
                dinoWalkRight.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkright_8));
                dinoWalkLeft.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkleft_1));
                dinoWalkLeft.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkleft_2));
                dinoWalkLeft.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkleft_3));
                dinoWalkLeft.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkleft_4));
                dinoWalkLeft.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkleft_5));
                dinoWalkLeft.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkleft_6));
                dinoWalkLeft.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkleft_7));
                dinoWalkLeft.add(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalkleft_8));
                */

                // Get Information from the different bitmaps
                playerWidth = dinoWalkRight.get(0).getWidth();
                playerHeigth = dinoWalkRight.get(0).getHeight();

                // Creating background for the game
                //background = BitmapFactory.decodeResource(getResources(), R.drawable.tetris);
                //backgroundScale = Bitmap.createScaledBitmap(background, canvasWidth, canvasHeight, false);

                // Get the sizes of the different images
                gameThread.setRunning(true);
                gameThread.start();
                accelerometerInit(context);
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                Log.i(TAG,"Surface Changed");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                Log.i(TAG,"Surface Destroyed");
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
        playerMovement(canvas);
    }

    // The draw function on the canvas
    @Override
    public void draw(Canvas canvas) {
        if ( canvas != null) {
            // For Background at things which are always there
            super.draw(canvas);
            //canvas.drawBitmap(backgroundScale, 0, 0, null);
            canvas.drawColor(Color.BLUE);
            canvas.drawBitmap(currentBitmap, xPosition, yPosition, null);
            canvas.drawBitmap(dinoWalkRight.get(0), xPosition, yPosition, null);
        }
        else{
            Log.e(TAG,"There is no canvas to draw on! ");
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


    public void playerMovement(Canvas canvas) {
        int walkNumber = 0;
        xVel += 0.5;
        if (Game.rigthBtnAction == 2) {
            direction = 0;
            xPosition += xVel;
            this.counter++;
            xVel = 0;
        }
        if (Game.leftBtnAction == 2) {
            direction = 1;
            xPosition -= xVel;
            xVel = 0;
            this.counter++;
            System.out.println(xPosition);
        }
        if (xPosition >= Game.canvasWidth) {
            xPosition = canvas.getWidth();
        }
        if (xPosition <= 0) {
            xPosition = 0;
        }
        if ((Game.rigthBtnAction) == 2) {
            if (counter == 5) {
                this.walkNumber++;
                if (this.walkNumber == 6) {
                    this.walkNumber = 0;
                }
                if (this.counter == 5) {
                    this.counter = 0;
                }
            }
        }

        if (( Game.leftBtnAction) == 2) {
            if (counter == 5) {
                this.walkNumber++;
                if (this.walkNumber == 6) {
                    this.walkNumber = 0;
                }
                if (this.counter == 5) {
                    this.counter = 0;
                }
            }
        }

        if (direction == 0 ){
            this.currentBitmap = this.dinoWalkRight.get(this.walkNumber);
            System.out.println("Walk number direction 0 = " + this.walkNumber);
        }
        if (direction == 1){
            System.out.println("Walk number direction 1 = " + this.walkNumber);
            this.currentBitmap = this.dinoWalkLeft.get(this.walkNumber);
        }
        else this.currentBitmap = this.dinoWalkRight.get(0);
    }
}


// Update Class for the Points

