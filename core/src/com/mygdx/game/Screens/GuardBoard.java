package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Bodies.GameMode;
import com.mygdx.game.Bodies.Player;
import com.mygdx.game.Inventory.InventoryBox;
import com.mygdx.game.Inventory.ItemBox;
import com.mygdx.game.Terraria;

public class GuardBoard {

    private Stage stage;
    private Viewport viewport;

    private InventoryBox tuitionBox;
    public boolean isHidden = true;
    private boolean alreadyTalked = false;
    private Player player;

    public GuardBoard(SpriteBatch batch, Player player){
        this.player = player;
        this.viewport = new FitViewport(Terraria.V_WIDTH, Terraria.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(this.viewport, batch);

        ItemBox itembox = new ItemBox(347  , (int) (Terraria.V_HEIGHT / 2.0) + 43);
        itembox.addDragListener();
        itembox.setItem(null);
        itembox.setCount(-1);
        itembox.setLabelToText("PAY TUITION (500$)");
        itembox.setSize(25,25);
        tuitionBox = new InventoryBox(itembox);
        tuitionBox.setSize(40,40);
        tuitionBox.setPosition(340 , (float) (Terraria.V_HEIGHT / 2.0) + 35);
        Texture t = new Texture("RAW/tuition_box.png");
        tuitionBox.setTexture(t);
        tuitionBox.setSize(t.getWidth(), t.getHeight());

        stage.addActor(tuitionBox);
        stage.addActor(tuitionBox.itembox);
        stage.addActor(tuitionBox.itembox.countLabel);


        tuitionBox.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Terraria.gameMode = GameMode.YEAR_ONE_MODE;
                    return false;
                }

        });

    }
    public Stage getStage(){
        return stage;
    }

    public void update(Vector2 playerPos, Vector2 blacksmithPos){
        if(areBodiesClose(playerPos,blacksmithPos, 100) ){
            show();
            isHidden = false;

            if(!alreadyTalked){
                //SoundManager.playBuySomehthing();
                alreadyTalked = true;
            }

        } else {
            hide();
            isHidden = true;
            alreadyTalked = false;
        }

    }

    public void render(float dt){

        tuitionBox.itembox.render();

        stage.act(dt);
        stage.draw();

    }

    public void show() {
        stage.getRoot().setVisible(true);
    }

    public void hide() {
        stage.getRoot().setVisible(false);
    }


    private static boolean areBodiesClose(Vector2 body1Position, Vector2 body2Position, float range) {
        float distanceSquared = body1Position.dst2(body2Position);
        float rangeSquared = range * range;

        return distanceSquared <= rangeSquared;
    }


    public Viewport getViewport() {
        return viewport;
    }
}
