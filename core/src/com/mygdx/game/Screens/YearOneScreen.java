package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MiningWorld;
import com.mygdx.game.Terraria;
import com.mygdx.game.YearOneWorld;

public class YearOneScreen implements Screen {
    private final Terraria game;
    private final YearOneWorld world;

    public YearOneScreen(final Terraria game, MiningWorld miningWorld){
        this.game = game;
        world = new YearOneWorld(miningWorld);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);
        world.render(delta);
        world.handleInput(delta);
    }

    @Override
    public void resize(int width, int height) {
        world.updateGameport(width, height);
        world.getHudStage().getViewport().update(width, height, true);
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
        world.dispose();
    }

    public YearOneWorld getWorld() {
        return world;
    }
}
