package com.caugiay.bomberman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player extends Sprite implements InputProcessor {
    private Vector2 velocity = new Vector2();
    private float speed = 120 , gravity = 60 * 1.8f;
    private TiledMapTileLayer collisionLayer;
    private Animation left, right, up, down;;

    public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite);
        this.collisionLayer = collisionLayer;
    }

    public Player(Animation left, Animation right, Animation up, Animation down, TiledMapTileLayer collisionLayer) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.collisionLayer = collisionLayer;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta) {

        float oldX = getX(), oldY = getY(), tileWidth = collisionLayer.getTileWidth(), tileHeidht = collisionLayer.getTileHeight();
        boolean collisionX = false, collisionY = false;

        setX(getX() + velocity.x * delta);

        if(velocity.x < 0) {
            collisionX = collidesLeft();
            /*
            // top left
            collisionX = collisionLayer.getCell( (int) (getX() / tileWidth), (int) ((getY() + getHeight()) / tileHeidht))
                    .getTile().getProperties().containsKey("blocked");
            // middle left
            if(!collisionX) {
                collisionX = collisionLayer.getCell( (int) (getX() / tileWidth), (int) ((getY() + getHeight() / 2) / tileHeidht))
                        .getTile().getProperties().containsKey("blocked");
            }
            // bottom left
            if(!collisionX) {
                collisionX = collisionLayer.getCell( (int) (getX() / tileWidth), (int) ((getY() / tileHeidht)))
                        .getTile().getProperties().containsKey("blocked");
            }*/
        }
        else if (velocity.x > 0) {
            collisionX = collidesRight();
            /*
            // top right
            collisionX = collisionLayer.getCell( (int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight() / tileHeidht) / tileHeidht))
                    .getTile().getProperties().containsKey("blocked");
            // middle right
            if(!collisionX) {
                collisionX = collisionLayer.getCell( (int) (getX() / tileWidth), (int) ((getY() + getHeight() / 2) / tileHeidht))
                        .getTile().getProperties().containsKey("blocked");
            }
            // bottom right
            if(!collisionX) {
                collisionX = collisionLayer.getCell( (int) (getX() / tileWidth), (int) (getY() / tileHeidht))
                        .getTile().getProperties().containsKey("blocked");
            }*/
        }

        if(collisionX) {
            setX(oldX);
            velocity.x = 0;
        }

        setY(getY() + velocity.y * delta);

        if(velocity.y < 0) {
            collisionY = collidesBottom();
            /*
            // bot left
            collisionY = collisionLayer.getCell( (int) (getX() / tileWidth), (int) (getY() / tileHeidht))
                    .getTile().getProperties().containsKey("blocked");
            // bot middle
            if(!collisionY) {
                collisionY = collisionLayer.getCell( (int) ((getX() + getWidth() / 2 ) / tileWidth), (int) (getY() / tileHeidht))
                        .getTile().getProperties().containsKey("blocked");
            }
            // bot right
            if(!collisionY) {
                collisionY = collisionLayer.getCell( (int) ((getX() + getWidth()) / tileWidth), (int) (getY() / tileHeidht))
                        .getTile().getProperties().containsKey("blocked");
            }*/
        }
        else if (velocity.y > 0) {
            collisionY = collidesTop();
            /*
            // top left
            collisionY = collisionLayer.getCell( (int) (getX() / tileWidth), (int) ((getY() + getHeight()) / tileHeidht))
                    .getTile().getProperties().containsKey("blocked");
            // top mid
            if(!collisionY) {
                collisionY = collisionLayer.getCell( (int) ((getX() + getWidth() / 2) / tileWidth), (int) ((getY() + getHeight()) / tileHeidht))
                        .getTile().getProperties().containsKey("blocked");
            }
            // bot left
            if(!collisionY) {
                collisionY = collisionLayer.getCell( (int) ((getX() + getWidth()) / tileWidth), (int) ((getY() + getHeight()) / tileHeidht))
                        .getTile().getProperties().containsKey("blocked");;
            }*/
        }

        if(collisionY) {
            setY(oldY);
            velocity.y = 0;
        }
    }

    private boolean isCellBlocked(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell!=null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

    public boolean collidesRight() {
        for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(getX() + getWidth(), getY() + step))
                return true;
        return false;
    }

    public boolean collidesLeft() {
        for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(getX(), getY() + step))
                return true;
        return false;
    }

    public boolean collidesTop() {
        for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(getX() + step, getY() + getHeight()))
                return true;
        return false;
    }

    public boolean collidesBottom() {
        for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(getX() + step, getY()))
                return true;
        return false;
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
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public float getGravity() {
        return gravity;
    }

    public float getSpeed() {
        return speed;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

}
