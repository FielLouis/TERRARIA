package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.MiningScreen;
import com.mygdx.game.Screens.SplashScreen;
import com.mygdx.game.Screens.YearOneScreen;
import com.mygdx.game.Bodies.GameMode;
import com.mygdx.game.Utilities.DatabaseManager;

public class Terraria extends Game {
	public  static final int V_WIDTH = 800;
	public  static final int V_HEIGHT = 400;
	public SpriteBatch batch;
	public static final int ZOOM_FACTOR = 1;
	public static final float PPM = 1;
	public static YearOneScreen one;
	public static MiningScreen miningworld;
	public static InputProcessor ip1;
	public static InputProcessor ip2;
	public static MyInputProcessorFactory.MyInputListenerB scrollmine;
	public static MyInputProcessorFactory.MyInputListenerB scrollyearone;
	public static GameMode gameMode = GameMode.MINING_MODE;

	@Override
	public void create () {
		batch = new SpriteBatch();
		DatabaseManager.createDatabase();
		DatabaseManager.createTableUser();

		miningworld = new MiningScreen(this);
		one = new YearOneScreen(this, miningworld.getWorld());

		ip1 = miningworld.getWorld().getHudStage();
		ip2 = one.getWorld().getHudStage();

		scrollmine = miningworld.getWorld().getPlayerListenerScroll();
		scrollyearone = one.getWorld().getPlayerListenerScroll();

		System.out.println("Mine: " + scrollmine.debugg() + "\nOne: " + scrollyearone.debugg());
//		Gdx.input.setInputProcessor(new InputMultiplexer(ip1, ip2, miningworld.getWorld().getMerchantboard().getStage(), miningworld.getWorld().getBlacksmithBoard().getStage(), miningworld.getWorld().getGuardBoard().getStage(), miningworld.getWorld().getPlayerListenerMine(),scrollyearone, scrollmine));

		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
