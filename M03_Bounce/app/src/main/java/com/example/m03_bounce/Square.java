package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Paint;



public class Square {

    float x, y;
    float speedX, speedY;
    float size;
    int color;
    Paint paint = new Paint();

    public Square(int color, float x, float y, float dx, float dy, float size) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.speedX = dx;
        this.speedY = dy;
        this.size = size;
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(x - size, y - size, x + size, y + size, paint);
    }

    public void moveWithCollisionDetection(Box box) {
        x += speedX;
        y += speedY;
        if (x < box.xMin || x > box.xMax) speedX = -speedX;
        if (y < box.yMin || y > box.yMax) speedY = -speedY;
    }
}


