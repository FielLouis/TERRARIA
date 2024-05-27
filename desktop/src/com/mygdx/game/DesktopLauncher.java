package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("CITerraria");

		//TODO first splashscreen for cutscene (auto)

		//TODO ideas
		// boss fight no.1 bomb drop (done)
		// boss fight no.2 platformer parkour
		// boss fight no.3 spin the wheel
		// boss fight no.4 (suggestions: TicTacToe, Exam-essay(sir will grade, manual input)

		//sets app into fullscreen as launched
		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());

		new Lwjgl3Application(new Terraria(), config);

	}
}
