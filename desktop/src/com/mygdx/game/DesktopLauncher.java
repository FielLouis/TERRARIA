package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Terraria;

public class DesktopLauncher {

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("CITerra");

		//Login interface ui first
		//TODO then splashscreen (Company Name: WonderPetz, GameName: CITerra)
		// splashscreen for cutscene

		//TODO ideas
		// boss fight no.1 bomb drop (done)
		// boss fight no.2 platformer parkour
		// boss fight no.3 spin the wheel
		// boss fight no.4 (suggestions: TicTacToe, Exam-essay(sir will grade, manual input) )

		config.setInitialBackgroundColor(new Color(66 / 256f, 33 / 256f, 54 / 256f, 1f));

		//sets app into fullscreen as launched
		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());

		new Lwjgl3Application(new Terraria(), config);

	}
}
