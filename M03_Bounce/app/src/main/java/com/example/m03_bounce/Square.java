package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Square {

    public float x, y;
    public float speedX, speedY;
    private int size = 60;
    private Paint paint = new Paint();
    private Box box;

    public Square(int color, float x, float y, float dx, float dy) {
        paint.setColor(color);
        this.x = x;
        this.y = y;
        this.speedX = dx;
        this.speedY = dy;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + size, y + size, paint);
    }

    public void moveWithCollisionDetection(Box box) {
        this.box = box;

        x += speedX;
        y += speedY;

        if (x < box.xMin || x + size > box.xMax) {
            speedX = -speedX;
        }

        if (y < box.yMin || y + size > box.yMax) {
            speedY = -speedY;
        }
    }
}

