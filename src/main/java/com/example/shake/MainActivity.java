package com.example.shake;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AccelerometerInit();
    }


        public int[] AccelerometerInit() {
            SensorManager SM = (SensorManager) getSystemService(SENSOR_SERVICE);
            Sensor S = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            SensorEventListener Sel = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    TextView Xangle = findViewById(R.id.Xangle);
                    TextView Zangle = findViewById(R.id.Zangle);
                    TextView Yangle = findViewById(R.id.Yangle);

                    Xangle.setText("X: " + event.values[0]);
                    Zangle.setText("Z: " + event.values[1]);
                    Yangle.setText("Y: " + event.values[2]);

                    View android = findViewById(R.id.android);
                    android.setRotation(event.values[0]);
                    android.setRotation(event.values[1]);
                    android.setRotation(event.values[2]);
                }


                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };

            SM.registerListener(Sel, S, SensorManager.SENSOR_DELAY_NORMAL);
            findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View V) {
                    View andorid = findViewById(R.id.android);
                    andorid.setRotation(0);
                    andorid.setRotation(0);
                    andorid.setRotation(0);
                    SM.unregisterListener(Sel);

                    Intent intent = getIntent();
                    startActivity(getIntent());
                    finish();
                }
            });


            findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });
            return new int[0];

        }

}