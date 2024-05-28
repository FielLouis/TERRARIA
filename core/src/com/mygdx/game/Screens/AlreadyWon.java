package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Terraria;

import static com.mygdx.game.Terraria.*;
import static com.mygdx.game.Terraria.scrollmine;

public class AlreadyWon implements Screen {
    private final Terraria game;
    private final Texture splashImage;

    public AlreadyWon(final Terraria game) {
        this.game = game;
        splashImage = new Texture("already_won.png");
    }

    @Override
    public void show() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(miningworld);
                Gdx.input.setInputProcessor(new InputMultiplexer(ip1, ip2, miningworld.getWorld().getMerchantboard().getStage(), miningworld.getWorld().getBlacksmithBoard().getStage(), miningworld.getWorld().getGuardBoard().getStage(), miningworld.getWorld().getPlayerListenerMine(),scrollyearone, scrollmine));
            }
        }, 2);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(splashImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.getBatch().end();
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
        splashImage.dispose();
    }
}
