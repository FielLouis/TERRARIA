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

public class CutsceneHelper implements Screen {
    private final Terraria game;
    private Texture backgroundTexture;
    private final SpriteBatch batch;
    String path;
    int counter;

    public CutsceneHelper(Terraria game, String type) {
        this.game = game;
        batch = new SpriteBatch();

        if(type.equals("ending")) {
            path = "end";
        } else {
            path = "";
        }

        counter = 1;

        backgroundTexture = new Texture("STORYLINE/" + path + counter + ".png");
    }

    public void handleInput(float delta) {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            counter++;

            if(counter > 26 || (counter > 3 && path.equals("end"))) {

                //updating database
                try (Connection c = DatabaseManager.getConnection();
                     PreparedStatement statement = c.prepareStatement(
                             "UPDATE tblusers SET cutsceneDone=1 WHERE id=?"
                     );
                     PreparedStatement statement2 = c.prepareStatement(
                             "UPDATE tblusers SET bossCutsceneDone=1 WHERE id=?"
                     )) {
                    c.setAutoCommit(false);

                    if(path.equals("end")) {
                        statement2.setInt(1, CurrentUser.getCurrentUserID());
                        statement2.executeUpdate();
                    } else {
                        statement.setInt(1, CurrentUser.getCurrentUserID());
                        statement.executeUpdate();
                    }

                    c.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //transferring to MiningWorld
                game.setScreen(Terraria.miningworld);

            } else {

                backgroundTexture = new Texture("STORYLINE/" + path + counter + ".png");

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
