package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
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
        super(game, "UI/MainMenuScreen.png");

        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new SpriteBatch();

        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("UI/uiskin_mainmenu.atlas"));

        skin = new Skin(Gdx.files.internal("UI/uiskin_mainmenu.json"), atlas);

        // Create title label
        Label.LabelStyle labelStyle = new Label.LabelStyle(skin.getFont("default-font"), Color.WHITE);
        Label titleLabel = new Label("Your Game Title", labelStyle);
        titleLabel.setAlignment(Align.center);
        titleLabel.setPosition((viewport.getWorldWidth() - titleLabel.getWidth()) / 2, viewport.getWorldHeight() - 100);

        TextButton btnLogin = new TextButton(optionLogin, skin);
        btnLogin.setPosition((viewport.getWorldWidth() - btnLogin.getWidth()) / 2, ((viewport.getWorldHeight() - btnLogin.getHeight()) / 2) - 150);

        btnLogin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoginScreen(game));
            }
        });

        TextButton btnRegister = new TextButton(optionRegister, skin);
        btnRegister.setPosition(((viewport.getWorldWidth() - btnLogin.getWidth()) / 2) + ((viewport.getWorldWidth() - btnLogin.getWidth()) / 4) + 25, ((viewport.getWorldHeight() - btnLogin.getHeight()) / 2) - 150);

        btnRegister.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new RegisterScreen(game));
            }
        });

        stage.addActor(titleLabel);
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
