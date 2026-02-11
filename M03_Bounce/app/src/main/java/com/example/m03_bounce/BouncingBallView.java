package com.example.m03_bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Russ on 08/04/2014.
 */
public class BouncingBallView extends View {

    private ArrayList<Ball> balls = new ArrayList<Ball>();
    private Ball ball_1;
    private Box box;

    private float previousX;
    private float previousY;

    Random rand = new Random();

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.v("BouncingBallView", "Constructor BouncingBallView");

        // background color change (Challenge 1)
        box = new Box(Color.BLUE);

        balls.add(new Ball(Color.GREEN));
        ball_1 = balls.get(0);
        Log.w("BouncingBallLog", "Just added a bouncing ball");

        balls.add(new Ball(Color.CYAN));
        Log.w("BouncingBallLog", "Just added another bouncing ball");

        this.setFocusable(true);
        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Log.v("BouncingBallView", "onDraw");

        box.draw(canvas);

        for (Ball b : balls) {
            b.draw(canvas);
            b.moveWithCollisionDetection(box);
        }

        // keeps animation running
        this.invalidate();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        box.set(0, 0, w, h);
        Log.w("BouncingBallLog", "onSizeChanged w=" + w + " h=" + h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();
        float deltaX, deltaY;
        float scalingFactor = 5.0f / ((box.xMax > box.yMax) ? box.yMax : box.xMax);

        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:

                deltaX = currentX - previousX;
                deltaY = currentY - previousY;

                ball_1.speedX += deltaX * scalingFactor;
                ball_1.speedY += deltaY * scalingFactor;

                Log.w("BouncingBallLog",
                        "x,y= " + previousX + " ," + previousY +
                                "  Xdiff=" + deltaX + " Ydiff=" + deltaY);

                // random color
                int r = rand.nextInt(256);
                int g = rand.nextInt(256);
                int b = rand.nextInt(256);
                int randomColor = Color.rgb(r, g, b);

                // SUPER-FAST / SUPER-SLOW BALLS
                float speedMultiplier;

                if (Math.abs(deltaX) + Math.abs(deltaY) > 50) {
                    speedMultiplier = 3.0f;   // fast swipe
                    Log.d("BALL_SPEED", "FAST BALL");
                } else {
                    speedMultiplier = 0.5f;   // slow swipe
                    Log.d("BALL_SPEED", "SLOW BALL");
                }

                balls.add(new Ball(
                        randomColor,
                        previousX,
                        previousY,
                        deltaX * speedMultiplier,
                        deltaY * speedMultiplier
                ));

                if (balls.size() > 20) {
                    Log.v("BouncingBallLog", "too many balls, clear back to 1");
                    balls.clear();
                    balls.add(new Ball(Color.RED));
                    ball_1 = balls.get(0);
                }
        }

        previousX = currentX;
        previousY = currentY;

        return true;
    }


    public void RussButtonPressed() {
        Log.d("BouncingBallView BUTTON", "User tapped the button ... VIEW");

        int viewWidth = this.getMeasuredWidth();
        int viewHeight = this.getMeasuredHeight();

        int x = rand.nextInt(viewWidth);
        int y = rand.nextInt(viewHeight);
        int dx = rand.nextInt(50);
        int dy = rand.nextInt(20);

        // also random color for button-created balls
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        int randomColor = Color.rgb(r, g, b);

        balls.add(new Ball(randomColor, x, y, dx, dy));
    }
}
