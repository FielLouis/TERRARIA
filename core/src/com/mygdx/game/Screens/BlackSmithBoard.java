package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Helper.SoundManager;
import com.mygdx.game.Inventory.InventoryBox;
import com.mygdx.game.Inventory.ItemBox;
import com.mygdx.game.Items.Weapons.PaladinItem;
import com.mygdx.game.Items.Weapons.PistolItem;
import com.mygdx.game.Sprites.Drop;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.Terraria;

import java.util.ArrayList;

public class BlackSmithBoard {
    private final Stage stage;
    private final Viewport viewport;

    private final ArrayList<InventoryBox> products;
    private InventoryBox coinBox;


    public boolean isHidden = true;

    private final Player player;

    public BlackSmithBoard(SpriteBatch batch, Player player){
        this.player = player;
        this.viewport = new FitViewport(Terraria.V_WIDTH, Terraria.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(this.viewport, batch);
        products  = new ArrayList<>();


        for(int i = 0; i < 6; i++){
            ItemBox itembox = new ItemBox(237 + (i * 60) , (int) (Terraria.V_HEIGHT / 2.0) + 58);
            itembox.addDragListener();
            if(i == 1){
                itembox.setItem(new PistolItem());
            } else {
                itembox.setItem(new PaladinItem());
            }

            itembox.setCount(1);

            itembox.setSize(25,25);
            InventoryBox product = new InventoryBox(itembox);
            product.setSize(40,40);
            product.setPosition(230 + (i * 60), (float) (Terraria.V_HEIGHT / 2.0) + 50);

            stage.addActor(product);
            stage.addActor(product.itembox);
            stage.addActor(product.itembox.countLabel);

            products.add(product);

            itembox.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    player.getDrop(new Drop(player.getB2body().getWorld(), player.getX(),player.getY(),itembox.getItem()),1);
                    System.out.println("Ittteemm");
                    return false;
                }
            });
        }





    }
    public Stage getStage(){
        return stage;
    }

    public void update(Vector2 playerPos, Vector2 blacksmithPos){
        if(areBodiesClose(playerPos,blacksmithPos, 100) ){
            show();
            isHidden = false;
            SoundManager.playBuySomehthing();
        } else {
            hide();
            isHidden = true;
        }

    }

    public void render(float dt){
        for(int i = 0; i < 6; i++){
            products.get(i).itembox.render();
        }

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
