package com.example.m03_bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class BouncingBallView extends View {

    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Square> squares = new ArrayList<>();

    private Ball ball_1;
    private Box box;

    private float previousX;
    private float previousY;

    private RectF targetRect;
    private int score = 0;
    private Paint rectPaint = new Paint();

    Random rand = new Random();

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);

        box = new Box(Color.LTGRAY);

        balls.add(new Ball(Color.GREEN, 100, 100, 5, 5, 30));
        ball_1 = balls.get(0);

        balls.add(new Ball(Color.CYAN, 200, 200, 5, 5, 30));

        targetRect = new RectF(300, 300, 500, 400);
        rectPaint.setColor(Color.BLACK);

        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        box.draw(canvas);

        // draw balls and check collision
        for (Ball b : balls) {
            b.draw(canvas);
            b.moveWithCollisionDetection(box);

            if (targetRect.contains(b.x, b.y)) {
                score++;
                Log.d("SCORE", "Score = " + score);
            }
        }

        // draw squares
        for (Square s : squares) {
            s.draw(canvas);
            s.moveWithCollisionDetection(box);
        }

        canvas.drawRect(targetRect, rectPaint);

        invalidate();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        box.set(0, 0, w, h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float currentX = event.getX();
        float currentY = event.getY();

        float deltaX, deltaY;

        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:

                deltaX = currentX - previousX;
                deltaY = currentY - previousY;

                int r = rand.nextInt(256);
                int g = rand.nextInt(256);
                int b = rand.nextInt(256);
                int randomColor = Color.rgb(r, g, b);

                float swipeSpeed = Math.abs(deltaX) + Math.abs(deltaY);

                // Calculate shape size based on swipe speed
                float size = 20 + swipeSpeed / 5; // bigger for faster swipes

                if (swipeSpeed > 50) {
                    squares.add(new Square(randomColor, previousX, previousY, deltaX * 2, deltaY * 2, size));
                    Log.d("SHAPE", "Square created, size=" + size);
                } else {
                    balls.add(new Ball(randomColor, previousX, previousY, deltaX * 0.5f, deltaY * 0.5f, size));
                    Log.d("SHAPE", "Circle created, size=" + size);
                }

                if (balls.size() > 20) {
                    balls.clear();
                    balls.add(new Ball(Color.RED, 100, 100, 5, 5, 30));
                    ball_1 = balls.get(0);
                }
        }

        previousX = currentX;
        previousY = currentY;

        return true;
    }

    // For your button
    public void RussButtonPressed() {
        int viewWidth = this.getMeasuredWidth();
        int viewHeight = this.getMeasuredHeight();

        int x = rand.nextInt(viewWidth);
        int y = rand.nextInt(viewHeight);

        int dx = rand.nextInt(40) - 20;
        int dy = rand.nextInt(40) - 20;

        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        int randomColor = Color.rgb(r, g, b);

        balls.add(new Ball(randomColor, x, y, dx, dy, 30));
    }
}


