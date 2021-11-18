package com.caugiay.bomberman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class BombGame extends Game {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		setScreen(new Play());
	}

	@Override
	public void render () {
		super.render();
	}
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
