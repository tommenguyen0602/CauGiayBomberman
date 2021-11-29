package com.caugiay.bomberman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Random;

public class Runner extends Player{
    private final Vector2 velocity = new Vector2();
    private final float speed = 120;
    private TiledMapTileLayer collisionLayer;
    private Animation left, right, up, down;
    private List<Bomb> bombs;

    public Runner(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite, collisionLayer);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    public void update(float delta) {

        //current runner position
        float oldX = getX();
        float oldY = getY();



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

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                velocity.x = 0;
                break;
            case Input.Keys.D:
                velocity.x = 0;
                break;
            case Input.Keys.W :
                velocity.y = 0;
                break;
            case Input.Keys.S:
                velocity.y = 0;
                break;
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W :
                velocity.y = speed;
                break;
            case Input.Keys.S:
                velocity.y = -speed;
                break;
            case Input.Keys.A:
                velocity.x = -speed;
                break;
            case Input.Keys.D:
                velocity.x = speed;
                break;
            case Input.Keys.B:
                //plant a bomb
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character){
        Random random = new Random();
        int xC = random.nextInt(60);
        int yC = random.nextInt(60);
        return false;
    }

}
