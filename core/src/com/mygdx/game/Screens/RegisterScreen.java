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
import com.mygdx.game.Utilities.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterScreen extends GameScreen {
    private final Stage stage;
    private final Skin skin;
    private final TextField usernameField;
    private final TextField passwordField;

    public RegisterScreen(final Terraria game) {
        super(game, "UI/LoginRegisterScreen.png");

        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("UI/uiskin_login.json"));

        Table table = new Table();
        table.setFillParent(true);

        TextField txtRegister = new TextField("REGISTRATION", skin);
        txtRegister.setPosition(((Gdx.graphics.getWidth() - txtRegister.getWidth()) / 2), Gdx.graphics.getHeight() - 300);

        Label usernameLabel = new Label("Username:", skin);
        usernameField = new TextField("", skin);

        Label passwordLabel = new Label("Password:", skin);
        passwordField = new TextField("", skin);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);

        TextButton registerButton = new TextButton("Register", skin);
        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isValidRegister(usernameField.getText())) {
                    //insert data
                    try (Connection c = DatabaseManager.getConnection();
                         PreparedStatement statement = c.prepareStatement(
                                 "INSERT INTO tblusers (uname, upassword) VALUES (?, ?)"
                         )) {

                        String username = usernameField.getText();
                        String userpassword = String.valueOf(passwordField.getText().hashCode());

                        statement.setString(1, username);
                        statement.setString(2, userpassword);

                        statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    game.setScreen(new LoginScreen(game));
                }
            }
        });
        registerButton.setPosition(((Gdx.graphics.getWidth() - registerButton.getWidth()) / 2) + 20, Gdx.graphics.getHeight() - 905);


        TextButton backButton = new TextButton("X", skin, "red");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        backButton.setPosition(((float) Gdx.graphics.getWidth() / 2) + 783, Gdx.graphics.getHeight() - 120);

        stage.addActor(txtRegister);
        stage.addActor(table);

        table.add(usernameLabel).pad(10);
        table.add(usernameField).width(200).pad(10);
        table.row();
        table.add(passwordLabel).pad(10);
        table.add(passwordField).width(200).pad(10);
        table.row();

        stage.addActor(registerButton);
        stage.addActor(backButton);
    }


    private boolean isValidRegister(String username) {

        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO tblusers (uname, upassword) VALUES (?, ?)"
             )) {

            String query = "SELECT * FROM tblusers";
            ResultSet res = statement.executeQuery(query);

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("uname");

                //checks if inputted username already exists or not
                if (name.equals(username)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Update and draw the stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void handleInput(final float delta) {
        super.handleInput(delta);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}