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
import com.badlogic.gdx.scenes.scene2d.ui.*;
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
//    private Dialog quitDialog;

    public MainMenuScreen(Terraria game) {
        super(game,"UI/MainMenuScreen.png");

        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new SpriteBatch();

        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("UI/uiskin_mainmenu.atlas"));

        skin = new Skin(Gdx.files.internal("UI/uiskin_mainmenu.json"), atlas);

        // Load the title image texture
        Texture titleTexture = new Texture(Gdx.files.internal("UI/logo_yes_tree.png")); // replace with your image path
        Image titleImage = new Image(titleTexture);
        titleImage.setScale(5);
        titleImage.setPosition((viewport.getWorldWidth() - titleImage.getWidth() * titleImage.getScaleX()) / 2, viewport.getWorldHeight() - titleImage.getHeight() * titleImage.getScaleY() - 100);

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

        stage.addActor(titleImage);
        stage.addActor(btnLogin);
        stage.addActor(btnRegister);

    }

    //failed attempt
//    private void createQuitDialog() {
//        quitDialog = new Dialog("Confirm Quit", skin) {
//            @Override
//            protected void result(Object object) {
//                if (object.equals(true)) {
//                    Gdx.app.exit();
//                } else {
//                    this.hide();
//                }
//            }
//        };
//        quitDialog.text("Are you sure you want to quit the game?");
//        quitDialog.button("Yes", true);
//        quitDialog.button("No", false);
//        quitDialog.setMovable(false);
//        quitDialog.setModal(true);
//    }

    @Override
    public void handleInput(final float delta) {
        super.handleInput(delta);

//        if(Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_RIGHT)) {
//            quitDialog.show(stage);
//        }
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
