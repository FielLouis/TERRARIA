package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Screens.MiningScreen;
import com.mygdx.game.Screens.SplashScreen;
import com.mygdx.game.Screens.YearOneScreen;
import com.mygdx.game.Sprites.GameMode;
import com.mygdx.game.Utilities.DatabaseManager;

public class Terraria extends Game {
	public  static final int V_WIDTH = 800;
	public  static final int V_HEIGHT = 400;
	public SpriteBatch batch;
	public static final int ZOOM_FACTOR = 1;
	public static final float PPM = 1;
	public static YearOneScreen one;
	public static MiningScreen miningworld;
	public static GameMode gameMode = GameMode.MINING_MODE;

	@Override
	public void create () {
		batch = new SpriteBatch();
		DatabaseManager.createDatabase();
		DatabaseManager.createTableUser();
		setScreen(new SplashScreen(this));

//		miningworld = new MiningScreen();
//		setScreen(miningworld);
//		one = new YearOneScreen(miningworld.getWorld());
//
//		InputProcessor ip1 = miningworld.getWorld().getHudStage();
//		InputProcessor ip2 = one.getWorld().getHudStage();
//
//		MyInputProcessorFactory.MyInputListenerB scrollmine = miningworld.getWorld().getPlayerListenerScroll();
//		MyInputProcessorFactory.MyInputListenerB scrollyearone = one.getWorld().getPlayerListenerScroll();
//
//		System.out.println("Mine: " + scrollmine.debugg() + "\nOne: " + scrollyearone.debugg());
//		Gdx.input.setInputProcessor(new InputMultiplexer(ip1, ip2, miningworld.getWorld().getMerchantboard().getStage(), miningworld.getWorld().getPlayerListenerMine(),scrollyearone, scrollmine));
	}

	@Override
	public void render () {
		super.render();

//		if(gameMode == GameMode.MINING_MODE){
//			one.getWorld().getPlayer().setLife(100);
//			setScreen(miningworld);
//		}
//
//		if(gameMode == GameMode.YEAR_ONE_MODE){
//			setScreen(one);
//		}

//		if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT_BRACKET) ){
//			setScreen(miningworld);
//			gameMode = GameMode.MINING_MODE;
//		}
//
//		if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT_BRACKET)){
//			setScreen(one);
//			gameMode = GameMode.YEAR_ONE_MODE;
//		}

	}
	
	@Override
	public void dispose () {

	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
