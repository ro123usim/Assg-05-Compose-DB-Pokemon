package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;


/**
 * Created by Russ on 08/04/2014.
 */
public class Ball {

    float radius = 50;      // Ball's radius
    float x;                // Ball's center (x,y)
    float y;
    float speedX;           // Ball's speed
    float speedY;
    private RectF bounds;   // Needed for Canvas.drawOval
    private Paint paint;    // The paint style, color used for drawing

    // Add accelerometer
    // Add ... implements SensorEventListener
    private double ax, ay, az = 0; // acceration from different axis

    public void setAcc(double ax, double ay, double az){
        this.ax = ax;
        this.ay = ay;
        this.az = az;
    }

    // reference for random num gen:
    // https://www.geeksforgeeks.org/java/generating-random-numbers-in-java/
    // TASK 2: change newly created balls to random color.
    // pass these random color values into the paint set color
    // in the constructors
    Random r = new Random();  // seed random number generator
    int red = r.nextInt(256);
    int green = r.nextInt(256);
    int blue = r.nextInt(256);

    // TASK 3: make balls go super fast or super slow.
    int fastOrSlow = r.nextInt(2);

    // Constructor
    public Ball(int color) {
        bounds = new RectF();
        paint = new Paint();
        // use random color values as argb parameter
        paint.setColor(Color.argb(255, red, green, blue));

        // random position and speed
        x = radius + r.nextInt(800);
        y = radius + r.nextInt(800);
        speedX = r.nextInt(10) - 5;
        speedY = r.nextInt(10) - 5;

        // either speed up or slow down the ball speeds 50/50
        if (fastOrSlow == 1) {
            speedX *= 2;
            speedY *= 2;
        } else {
            speedX *= 0.5f;
            speedY *= 0.5f;
        }
    }

    // Constructor
    public Ball(int color, float x, float y, float speedX, float speedY) {
        bounds = new RectF();
        paint = new Paint();
        // use random color values as argb parameter
        paint.setColor(Color.argb(255, red, green, blue));

        // use parameter position and speed
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;

        // either speed up or slow down the ball speeds 50/50
        if (fastOrSlow == 1) {
            this.speedX *= 2;
            this.speedY *= 2;
        } else {
            this.speedX *= 0.5f;
            this.speedY *= 0.5f;
        }
    }

    public void moveWithCollisionDetection(Box box) {
        // Get new (x,y) position
        x += speedX;
        y += speedY;

        // Add acceleration to speed
        speedX += ax;
        speedY += ay;

        // Detect collision and react
        if (x + radius > box.xMax) {
            speedX = -speedX;
            x = box.xMax - radius;
        } else if (x - radius < box.xMin) {
            speedX = -speedX;
            x = box.xMin + radius;
        }
        if (y + radius > box.yMax) {
            speedY = -speedY;
            y = box.yMax - radius;
        } else if (y - radius < box.yMin) {
            speedY = -speedY;
            y = box.yMin + radius;
        }
    }

    public void draw(Canvas canvas) {
        bounds.set(x - radius, y - radius, x + radius, y + radius);
        canvas.drawOval(bounds, paint);
    }

}
