package com.caugiay.bomberman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {

    private Vector2 velocity = new Vector2();
    private float speed = 60 * 2, gravity = 60 * 1.8f;
    private TiledMapTileLayer collisionLayer;

    public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite);
        this.collisionLayer = collisionLayer;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta) {
        velocity.y -= gravity * delta;

        if(velocity.y > speed) {
            velocity.y = speed;
        }
        else if (velocity.y < speed) {
            velocity.y -= speed;
        }

        float oldX = getX(), oldY = getY(), tileWidth = collisionLayer.getTileWidth(), tileHeidht = collisionLayer.getTileHeight();
        boolean collisionX = false, collisionY = false;

        setX(getX() + velocity.x * delta);

        if(velocity.x < 0) {
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
            }
        }
        else if (velocity.x > 0) {
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
            }
        }

        if(collisionX) {
            setX(oldX);
            velocity.x = 0;
        }

        setY(getY() + velocity.y * delta);

        if(velocity.y < 0) {
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
            }
        }
        else if (velocity.y > 0) {
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
            }
        }

        if(collisionY) {
            setY(oldY);
            velocity.y = 0;
        }
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
