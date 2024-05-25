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
        TextButton registerButton = new TextButton("Register User", skin);
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
        table.add(registerButton).pad(10);
        table.add(backButton);
    }

    private boolean isValidRegister(String username) {

        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement statement = c.prepareStatement(
                    "INSERT INTO tblusers (uname, upassword) VALUES (?, ?)"
             )) {

            String query = "SELECT * FROM tblusers";
            ResultSet res = statement.executeQuery(query);

            while(res.next()) {
                int id = res.getInt("id");
                String name = res.getString("uname");

                //checks if inputted username already exists or not
                if(name.equals(username)) {
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