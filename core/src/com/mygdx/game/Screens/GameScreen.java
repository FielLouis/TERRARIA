package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void render(float delta) {
        handleInput(delta);

        // Clear the screen
        Gdx.gl.glClearColor(0.2f, 0.3f, 0.3f, 1); //background for the Login Screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
