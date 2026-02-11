package com.example.assignment5;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity implements SensorEventListener {

    private TextView directionTextView;
    private TextView scoreTextView;
    private SensorManager sensorManager;
    private Sensor linearAcceleration;

    private int score = 0;
    private int currentDirection;
    private Random random;

    private static final int LEFT_RIGHT = 0;
    private static final int TOWARDS_AWAY = 1;
    private static final int UP_DOWN = 2;

    private static final float THRESHOLD_HIGH = 1.5f;  // Balanced sensitivity
    private static final float THRESHOLD_LOW = 0.8f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        directionTextView = findViewById(R.id.directionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        linearAcceleration = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        random = new Random();
        pickNewDirection();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, linearAcceleration, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    private void pickNewDirection() {
        currentDirection = random.nextInt(3);
        switch (currentDirection) {
            case LEFT_RIGHT:
                directionTextView.setText("Move device left and right");
                break;
            case TOWARDS_AWAY:
                directionTextView.setText("Move device towards and away");
                break;
            case UP_DOWN:
                directionTextView.setText("Move device up and down");
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        switch (currentDirection) {
            case LEFT_RIGHT:
                if (Math.abs(y) > THRESHOLD_HIGH && Math.abs(x) < THRESHOLD_LOW && Math.abs(z) < THRESHOLD_LOW) {
                    increaseScore();
                }
                break;
            case TOWARDS_AWAY:
                if (Math.abs(z) > THRESHOLD_HIGH && Math.abs(x) < THRESHOLD_LOW && Math.abs(y) < THRESHOLD_LOW) {
                    increaseScore();
                }
                break;
            case UP_DOWN:
                if (Math.abs(x) > THRESHOLD_HIGH && Math.abs(y) < THRESHOLD_LOW && Math.abs(z) < THRESHOLD_LOW) {
                    increaseScore();
                }
                break;
        }
    }

    private void increaseScore() {
        score++;
        scoreTextView.setText("Score: " + score);
        pickNewDirection();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
