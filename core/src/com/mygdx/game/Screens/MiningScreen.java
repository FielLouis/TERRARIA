package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.mygdx.game.MiningWorld;
import com.mygdx.game.Terraria;

public class MiningScreen implements Screen {
    private final Terraria game;
    private final MiningWorld world;

    public MiningScreen(final Terraria game){
        this.game = game;
        world = new MiningWorld(this.game);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        world.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        world.updateGameport(width, height);
        world.getHudStage().getViewport().update(width, height, true);
        world.getMerchantboard().getViewport().update(width, height, true);
        world.getBlacksmithBoard().getViewport().update(width, height, true);
        world.getGuardBoard().getViewport().update(width, height, true);
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

    public MiningWorld getWorld(){
        return world;
    }

}
