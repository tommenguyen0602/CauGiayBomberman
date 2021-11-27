package com.caugiay.bomberman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.List;

public class Play implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Player player;
    private Runner runner;
    private Enemy enemy;
    private List<Bomb> bombs;
    private Music soundtrack;

    public Play() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //center the player with the camera
        camera.position.set(runner.getX() + runner.getWidth() / 2, runner.getY() + runner.getHeight() / 2, 0);
        camera.update();

        renderer.setView(camera);
        renderer.render();

        renderer.getBatch().begin();
        runner.draw(renderer.getBatch());
        enemy.draw(renderer.getBatch(), runner);
        renderer.getBatch().end();
    }

    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/secondmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        //camera.zoom = 1 / 2f;

        soundtrack = Gdx.audio.newMusic(Gdx.files.internal("soundtrack.mp3"));
        soundtrack.setLooping(true);
        soundtrack.play();

        runner = new Runner(new Sprite(new Texture("Player sprites/boy_down_1.png")), (TiledMapTileLayer) map.getLayers().get(2));
        runner.setX(40);
        runner.setY(400);
        Gdx.input.setInputProcessor(runner);

        enemy = new Enemy(new Sprite(new Texture("Player sprites/boy_down_1.png")), (TiledMapTileLayer) map.getLayers().get(2));
        enemy.setX(40);
        enemy.setY(500);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        //camera.update();
    }


    @Override
    public void pause() {

    }


    @Override
    public void resume() {

    }


    @Override
    public void hide() {
        dispose();

    }


    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        soundtrack.dispose();
    }
}
