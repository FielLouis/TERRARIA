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
import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class LoginScreen extends GameScreen {
    private Stage stage;
    private Skin skin;
    private TextField usernameField;
    private TextField passwordField;

    public LoginScreen(final Terraria game) {
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

        TextField txtLogin = new TextField("LOG IN", skin);

        // Create the username label and text field
        Label usernameLabel = new Label("Username:", skin);
        usernameField = new TextField("", skin);

        // Create the password label and text field
        Label passwordLabel = new Label("Password:", skin);
        passwordField = new TextField("", skin);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);

        // Create the login button
        TextButton loginButton = new TextButton("Log In", skin);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isValidLogin(usernameField.getText(), String.valueOf(passwordField.getText().hashCode()))) {
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
        table.add(txtLogin).colspan(2).pad(10);
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
        if("admin".equals(username) && String.valueOf("123".hashCode()).equals(password)) {
            return true;
        }

        try (Connection c = DatabaseManager.getConnection();
             Statement statement = c.createStatement()) {
            String query = "SELECT * FROM tblusers";
            ResultSet res = statement.executeQuery(query);

            while(res.next()) {
                String uname = res.getString("uname");
                String upass = res.getString("upassword");

                //checks if username exists
                if(uname.equals(username) && upass.equals(password)) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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