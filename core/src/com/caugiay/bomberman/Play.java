package com.caugiay.bomberman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Play implements Screen {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;


    public OrthographicCamera camera;
    public ExtendViewport viewport;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    @Override
    public void show() {

        //load the map
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/firstmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.position.set(WIDTH/2,HEIGHT/2,0);
        viewport = new ExtendViewport(WIDTH,HEIGHT,camera);
        viewport.apply();






    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();
    }


    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        camera.update();
        viewport.update(WIDTH,HEIGHT);
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

    }
}
