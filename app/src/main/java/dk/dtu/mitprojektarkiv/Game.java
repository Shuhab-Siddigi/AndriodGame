package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Game extends AppCompatActivity implements SensorEventListener {

    // Accelormeter Properties

    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager mySensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // ---------------- Ball --------------- //
        View Ball = new Ball(this);
        setContentView(Ball);

        // ---------------- Accelormeter ------------ //
        // Create Sensor Manger
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        // Accelormeter Sensor
        mySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // Register sensor Listener
        mySensorManager.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_GAME);
        // Assign TextView


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
       System.out.println("X Cordinate is = " + sensorEvent.values[0]);
       System.out.println("Y Cordinate is = " + sensorEvent.values[1]);
       System.out.println("Z Cordinate is = " + sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
