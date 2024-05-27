package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyInputProcessorFactory;
import com.mygdx.game.Sprites.GameMode;
import com.mygdx.game.Terraria;
import com.mygdx.game.Utilities.DatabaseManager;
import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class LoginScreen extends GameScreen {
    private final Stage stage;
    private final Skin skin;
    private final TextField usernameField;
    private final TextField passwordField;
    public static YearOneScreen one;
    public static MiningScreen miningworld;
    public static GameMode gameMode = GameMode.MINING_MODE;

    public LoginScreen(final Terraria game) {
        super(game, "UI/LoginRegisterScreen.png");

        // Initialize the stage and set it as the input processor
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        // Load the skin
        skin = new Skin(Gdx.files.internal("UI/uiskin_login.json"));

        // Create the table and add it to the stage
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextField txtLogin = new TextField("LOG IN", skin);
        txtLogin.setPosition(((Gdx.graphics.getWidth() - txtLogin.getWidth()) / 2), Gdx.graphics.getHeight() - 300);

        Label usernameLabel = new Label("Username:", skin);
        usernameField = new TextField("", skin);

        Label passwordLabel = new Label("Password:", skin);
        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        TextButton loginButton = new TextButton("Log In", skin);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loggingIn();
            }
        });
        loginButton.setPosition(((Gdx.graphics.getWidth() - loginButton.getWidth()) / 2) + 20, Gdx.graphics.getHeight() - 905);

        TextButton backButton = new TextButton("X", skin, "red");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        backButton.setPosition(((float) Gdx.graphics.getWidth() / 2) + 783, Gdx.graphics.getHeight() - 120);

        stage.addActor(txtLogin);

        table.add(usernameLabel).pad(10);
        table.add(usernameField).width(200).pad(10);
        table.row();
        table.add(passwordLabel).pad(10);
        table.add(passwordField).width(200).pad(10);
        table.row();

        stage.addActor(loginButton);
        stage.addActor(backButton);
    }

    private void loggingIn() {
        if (isValidLogin(usernameField.getText(), String.valueOf(passwordField.getText().hashCode()))) {
            miningworld = new MiningScreen();
            game.setScreen(miningworld);
            one = new YearOneScreen(miningworld.getWorld());

            InputProcessor ip1 = miningworld.getWorld().getHudStage();
            InputProcessor ip2 = one.getWorld().getHudStage();

            MyInputProcessorFactory.MyInputListenerB scrollmine = miningworld.getWorld().getPlayerListenerScroll();
            MyInputProcessorFactory.MyInputListenerB scrollyearone = one.getWorld().getPlayerListenerScroll();

            System.out.println("Mine: " + scrollmine.debugg() + "\nOne: " + scrollyearone.debugg());
            Gdx.input.setInputProcessor(new InputMultiplexer(ip1, ip2, miningworld.getWorld().getMerchantboard().getStage(), miningworld.getWorld().getPlayerListenerMine(),scrollyearone, scrollmine));
        }
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

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT_BRACKET) ){
            game.setScreen(miningworld);
            gameMode = GameMode.MINING_MODE;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT_BRACKET)){
            game.setScreen(one);
            gameMode = GameMode.YEAR_ONE_MODE;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            loggingIn();
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}