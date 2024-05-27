package com.mygdx.game.Helper;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyInputProcessorFactory;
import com.mygdx.game.Screens.MiningScreen;
import com.mygdx.game.Screens.YearOneScreen;
import com.mygdx.game.Terraria;
import com.mygdx.game.Utilities.CurrentUser;
import com.mygdx.game.Utilities.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.mygdx.game.Screens.LoginScreen.miningworld;
import static com.mygdx.game.Screens.LoginScreen.one;

public class CutsceneHelper implements Screen {
    private final Terraria game;
    private Texture backgroundTexture;
    private final SpriteBatch batch;
    int counter;

    public CutsceneHelper(Terraria game) {
        this.game = game;
        batch = new SpriteBatch();
        counter = 1;

        backgroundTexture = new Texture("STORYLINE/" + counter + ".png");
    }

    public void handleInput(float delta) {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            counter++;

            if(counter > 26) {

                //updating database
                try (Connection c = DatabaseManager.getConnection();
                     PreparedStatement statement = c.prepareStatement(
                             "UPDATE tblusers SET cutsceneDone=1 WHERE id=?"
                     )) {
                    c.setAutoCommit(false);

                    statement.setInt(1, CurrentUser.getCurrentUserID());

                    statement.executeUpdate();
                    c.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //transferring to MiningWorld
                miningworld = new MiningScreen(game);
                game.setScreen(miningworld);
                one = new YearOneScreen(game, miningworld.getWorld());

                InputProcessor ip1 = miningworld.getWorld().getHudStage();
                InputProcessor ip2 = one.getWorld().getHudStage();

                MyInputProcessorFactory.MyInputListenerB scrollmine = miningworld.getWorld().getPlayerListenerScroll();
                MyInputProcessorFactory.MyInputListenerB scrollyearone = one.getWorld().getPlayerListenerScroll();

                System.out.println("Mine: " + scrollmine.debugg() + "\nOne: " + scrollyearone.debugg());
                Gdx.input.setInputProcessor(new InputMultiplexer(ip1, ip2, miningworld.getWorld().getMerchantboard().getStage(), miningworld.getWorld().getPlayerListenerMine(), scrollyearone, scrollmine));

            } else {

                backgroundTexture = new Texture("STORYLINE/" + counter + ".png");

                batch.begin();
                batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                batch.end();

            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void show() {
//        Timer.schedule(new Timer.Task() {
//            @Override
//            public void run() {
//
//            }
//        }, 2);
    }

    @Override
    public void render(float delta) {
        handleInput(delta);

        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
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
        backgroundTexture.dispose();
    }
}
