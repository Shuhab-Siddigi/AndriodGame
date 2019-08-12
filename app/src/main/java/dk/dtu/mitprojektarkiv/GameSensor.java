
package dk.dtu.mitprojektarkiv;


import android.app.Application;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class GameSensor extends Application implements SensorEventListener {

    // Sensor instances
    private Sensor mySensor;
    private SensorManager mySensorManager;

    static int posX = 0;
    static int posY = 0;

    @Override
    public void onCreate() {
        super.onCreate();
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

       posX = (int) sensorEvent.values[0];
       posY =  (int) sensorEvent.values[1];
      // System.out.println("Gyro PosX = " + posX);
      // System.out.println("Gyro posY = " + posY);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
