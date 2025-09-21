package org.lwjglb.engine.scene;

import org.joml.Vector3f;

public class MovingEntity extends Entity {

    Float max = 5.0f;
    Vector3f velocity = new Vector3f();

    public MovingEntity(String id, String modelId) {
        super(id, modelId);
        velocity.x = 0.02f;
        velocity.y = 0.03f;
        velocity.z = 0.01f;
    }

    public void MoveEntity(float x, float y, float z) {
        velocity.x = x;
        velocity.y = y;
        velocity.z = z;
        this.MoveEntity();
    }

    public void MoveEntity(){
        Vector3f current = this.getPosition();
        current = current.add(velocity);
        if (Math.abs(current.x) > Math.abs(max)) {
            velocity.x = -velocity.x;
            current.x = max;
        }
        if (Math.abs(current.y) > Math.abs(max)) {
            velocity.y = -velocity.y;
            current.y = (max);
        }
        if (Math.abs(current.z) > Math.abs(max)) {
            velocity.z = -velocity.z;
            current.z = (max);
        }
        this.setPosition(current);
    }

    public boolean collides(MovingEntity that) {
        if (this.getPosition().distance(that.getPosition()) < 0.5f) {
            return true;
        } else {
            return false;
        }
    }

}
