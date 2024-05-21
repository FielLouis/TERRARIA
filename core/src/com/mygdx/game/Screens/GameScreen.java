package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Terraria;

import static java.time.InstantSource.tick;

public class GameScreen implements Screen {
    public final Terraria game;
    protected Viewport viewport;

    public GameScreen(final Terraria game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    public void handleInput(float delta) {

    }

    @Override
    public void render(float delta) {
        handleInput(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
