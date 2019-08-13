package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/*
This Activity is set to landscape in the AndroidManifest.xml file
if changes are needed that is the place to edit the orientation.
 */

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // ---------------- Canvas View  --------------- //
        View gameCanvas = new GameCanvas(this);
        setContentView(gameCanvas);


    }

}

