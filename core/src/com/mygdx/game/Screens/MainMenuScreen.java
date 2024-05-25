package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Terraria;
import org.w3c.dom.Text;

public class MainMenuScreen extends GameScreen {
    private final String optionLogin = "LOG IN";
    private final String optionRegister = "REGISTER";
    private final SpriteBatch batch;
    private final Stage stage;
    private final Skin skin;
    public MainMenuScreen(Terraria game) {
        super(game);

        viewport = new StretchViewport(640,480);

        batch = new SpriteBatch();

        // Initialize the stage and skin
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin_mainmenu.json")); // Use the appropriate skin file

        TextButton btnLogin = new TextButton(optionLogin, skin);
        btnLogin.setPosition((viewport.getWorldWidth() - btnLogin.getWidth()) / 2, ((viewport.getWorldHeight() - btnLogin.getHeight()) / 2) - 50);

        btnLogin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoginScreen(game));
            }
        });

        TextButton btnRegister = new TextButton(optionRegister, skin);
        btnRegister.setPosition((viewport.getWorldWidth() - btnLogin.getWidth()) / 2, ((viewport.getWorldHeight() - btnLogin.getHeight()) / 2) + 50);

        btnRegister.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new RegisterScreen(game));
            }
        });

        // Add the button to the stage
        stage.addActor(btnLogin);
        stage.addActor(btnRegister);
    }

    @Override
    public void handleInput(final float delta) {
        super.handleInput(delta);
    }

    @Override
    public void render(final float delta) {
        super.render(delta);

        stage.act(delta);
        stage.draw();
    }


    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        stage.dispose();
        skin.dispose();
    }
}
