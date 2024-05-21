package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Terraria;

public class RegisterScreen extends GameScreen {
    private Stage stage;
    private Skin skin;
    private TextField usernameField;
    private TextField passwordField;

    public RegisterScreen(final Terraria game) {
        super(game);

        // Initialize the stage and set it as the input processor
        stage = new Stage(new StretchViewport(640, 480));
        Gdx.input.setInputProcessor(stage);

        // Load the skin
        skin = new Skin(Gdx.files.internal("uiskin_login.json"));

        // Create the table and add it to the stage
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextField txtRegister = new TextField("REGISTER", skin);

        // Create the username label and text field
        Label usernameLabel = new Label("Username:", skin);
        usernameField = new TextField("", skin);

        // Create the password label and text field
        Label passwordLabel = new Label("Password:", skin);
        passwordField = new TextField("", skin);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);

        // Create the login button
        TextButton loginButton = new TextButton("Login", skin);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isValidLogin(usernameField.getText(), passwordField.getText())) {
                    game.setScreen(new EntityScreen(game));
                }
            }
        });

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        // Add the UI components to the table
        table.add(txtRegister).width(200).colspan(2).pad(10);
        table.row();
        table.add(usernameLabel).pad(10);
        table.add(usernameField).width(200).pad(10);
        table.row();
        table.add(passwordLabel).pad(10);
        table.add(passwordField).width(200).pad(10);
        table.row();
        table.add(loginButton).pad(10);
        table.add(backButton);
    }

    private boolean isValidLogin(String username, String password) {
        // Placeholder for actual login validation logic
        return "admin".equals(username) && "password".equals(password);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Clear the screen
        Gdx.gl.glClearColor(0.2f, 0.3f, 0.3f, 1); //background for the Login Screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update and draw the stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}