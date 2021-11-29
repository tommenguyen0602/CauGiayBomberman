package com.caugiay.bomberman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

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
        Random random = new Random();
        int xC = random.nextInt(60);
        int yC = random.nextInt(60);
        setX(getX() + xC * delta);
        setY(getY() + yC * delta);


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


}
