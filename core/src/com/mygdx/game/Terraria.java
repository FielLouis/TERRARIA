package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Helper.AssetsManager;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Utilities.DatabaseManager;

public class Terraria extends Game {
	private SpriteBatch batch;
	private AssetsManager assetsManager;
	public static final int V_WIDTH = 1000;
	public static final int V_HEIGHT = 500;

	public static final int ZOOM_FACTOR = 1;
	public static final float PPM = 1;

	public AssetsManager getAssMan() {
		return assetsManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void create () {
		DatabaseManager.createDatabase();
		DatabaseManager.createTableUser();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		getScreen().render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		getScreen().dispose();
	}
}
