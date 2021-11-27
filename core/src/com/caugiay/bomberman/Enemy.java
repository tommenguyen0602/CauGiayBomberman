package com.caugiay.bomberman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Player{
    private final Vector2 velocity = new Vector2();
    private final float speed = 120;
    private TiledMapTileLayer collisionLayer;
    private Animation left, right, up, down;

    public Enemy(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite, collisionLayer);
    }

    public void draw(Batch batch, Runner runner) {
        update(Gdx.graphics.getDeltaTime(), runner);
        super.draw(batch);
    }

    public void update(float delta, Runner runner) {
        float oldX = getX(), oldY = getY();
        boolean collisionX = false, collisionY = false;
        setX(getX() + velocity.x * delta);
        if(velocity.x < 0) {
            collisionX = collidesLeft();
        }
        else if (velocity.x > 0) {
            collisionX = collidesRight();
        }
        if(collisionX) {
            setX(oldX);
            velocity.x = 0;
        }

        setY(getY() + velocity.y * delta);
        if(velocity.y < 0) {
            collisionY = collidesBottom();
        }
        else if (velocity.y > 0) {
            collisionY = collidesTop();
        }
        if(collisionY) {
            setY(oldY);
            velocity.y = 0;
        }

        if(!collisionX) {
            movingX(runner);
        }
        if(!collisionY) {
            movingY(runner);
        }
    }

    public boolean collidesRight() {
        return super.collidesRight();
    }

    public boolean collidesLeft() {
        return super.collidesLeft();
    }

    public boolean collidesTop() {
        return super.collidesTop();
    }

    public boolean collidesBottom() {
        return super.collidesBottom();
    }

    public void movingX(Runner runner) {
        if (this.getX() > runner.getX() + 1) {
            velocity.x = -speed/5;
        }
        if (this.getX() < runner.getX() - 1) {
            velocity.x = speed/5;
        }
    }
    public void movingY(Runner runner) {
        if (this.getY() > runner.getY() + 1) {
            velocity.y = -speed/5;
        }
        if (this.getY() < runner.getY() - 1) {
            velocity.y = speed/5;
        }
    }
}
