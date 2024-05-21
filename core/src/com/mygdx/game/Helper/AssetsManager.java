package com.mygdx.game.Helper;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetsManager extends AssetManager {
	public final String guiTitle = "FONTS/title.png";
	public final String font03_64 = "FONTS/font03_64.fnt";
	public AssetsManager() {
		loadFonts();
	}

	public void loadFonts() {
		final FileHandleResolver resolver = new InternalFileHandleResolver();
		setLoader(BitmapFont.class, new BitmapFontLoader(resolver)); // Tile atlas should be in same folder.

		load(font03_64, BitmapFont.class);
	}
}
