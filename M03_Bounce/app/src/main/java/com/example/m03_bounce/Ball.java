package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Paint;



public class Ball {

    float x, y;
    float speedX, speedY;
    int color;
    float size;
    Paint paint = new Paint();

    public Ball(int color, float x, float y, float dx, float dy, float size) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.speedX = dx;
        this.speedY = dy;
        this.size = size;
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, size, paint);
    }

    public void moveWithCollisionDetection(Box box) {
        x += speedX;
        y += speedY;
        if (x < box.xMin || x > box.xMax) speedX = -speedX;
        if (y < box.yMin || y > box.yMax) speedY = -speedY;
    }
}

