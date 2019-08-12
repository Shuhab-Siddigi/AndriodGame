
package dk.dtu.mitprojektarkiv;

import android.app.Activity;
import android.app.Application;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;

public class GameSensor extends Application implements SensorEventListener {

    private Sensor mySensor;
    private SensorManager mySensorManager;
    static Activity mActtivity;
    AsyncTask asyncTask;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                System.out.println("The Sensor is initialized");

            }

            @Override
            public void onActivityStarted(Activity activity) {
                mActtivity = activity;
                System.out.println("Activity is Started");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                mActtivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                mActtivity = null;
                System.out.println("Activity is paused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                System.out.println("Activity is stopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });

        // ---------------- Accelormeter ------------ //
        // Create Sensor Manger
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if (mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            System.out.println("Accelerometer link was a success!");
        }
        else{
            System.out.println("There is either no accelerometer or it cannot be accessed");
        }

        // Accelormeter Sensor
           mySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // Register sensor Listener
           mySensorManager.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_GAME);
        // Assign TextView

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
       // System.out.println("X Coordinate is = " + sensorEvent.values[0]);
       // System.out.println("Y Coordinate is = " + sensorEvent.values[1]);
       // System.out.println("Z Coordinate is = " + sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
