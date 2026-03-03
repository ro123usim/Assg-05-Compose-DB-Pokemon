package com.example.m03_bounce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private BouncingBallView bbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bbView = (BouncingBallView) findViewById(R.id.custView);
    }

    // ADD BALL BUTTON
    public void onRussButtonClick(View v) {

        SeekBar seekbar_color = findViewById(R.id.seekBar_Color);
        SeekBar seekbar_x = findViewById(R.id.seekBar_X);
        SeekBar seekbar_y = findViewById(R.id.seekBar_Y);
        SeekBar seekbar_dx = findViewById(R.id.seekBar_DX);
        SeekBar seekbar_dy = findViewById(R.id.seekBar_DY);

        int color = seekbar_color.getProgress();
        int x = seekbar_x.getProgress();
        int y = seekbar_y.getProgress();
        int dx = seekbar_dx.getProgress();
        int dy = seekbar_dy.getProgress();

        bbView.RussButtonPressed(color, x, y, dx, dy);
    }

    // CLEAR BUTTON
    public void onClearButtonClick(View v) {
        bbView.clearAllBalls();
    }
}