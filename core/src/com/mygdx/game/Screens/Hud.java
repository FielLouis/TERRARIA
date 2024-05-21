package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Helper.BoxActor;
import com.mygdx.game.Helper.Pair;
import com.mygdx.game.Inventory.Draggable;
import com.mygdx.game.Inventory.InventoryBox;
import com.mygdx.game.Inventory.ItemBox;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Terraria;

import java.util.ArrayList;

public class Hud {
    private Stage stage;
    private Viewport viewport;
    private ArrayList<InventoryBox> inventoryBoxDisplay = new ArrayList<>(8);
    private ArrayList<Pair<Item, Integer>> player_items;

    public Hud(SpriteBatch batch, ArrayList<Pair<Item, Integer>> player_items){
        this.player_items = player_items;
        this.viewport = new FitViewport(Terraria.V_WIDTH / Terraria.PPM, Terraria.V_HEIGHT / Terraria.PPM, new OrthographicCamera());
        stage = new Stage(this.viewport, batch);

        int prev = 12;
        for(int i = 0; i < 8; i++){
            Item item = player_items.get(i).getFirst();
            ItemBox itembox;

            if(item == null){
                itembox = new ItemBox();
            } else {
                itembox = new ItemBox(item);
            }
            InventoryBox box = new InventoryBox(itembox);

            box.setSize(40,40);
            box.itembox.setSize(25,25);

            box.setPosition(prev, Terraria.V_HEIGHT - 55);
            box.itembox.setPosition(prev + 7, Terraria.V_HEIGHT - 48);
            box.itembox.initLabel();
            prev += 55;

            inventoryBoxDisplay.add(box);

            stage.addActor(box);
            stage.addActor(box.itembox);
            stage.addActor(box.itembox.countLabel);

        }

        Gdx.input.setInputProcessor(stage);
    }

    public Stage getStage(){
        return stage;
    }

    public void update(float dt, ArrayList<Pair<Item, Integer>> player_items, int current_item_hold){
        for(int i = 0; i < 8; i++){
            Pair<Item,Integer> player_item = player_items.get(i);
            inventoryBoxDisplay.get(i).itembox.setItem(player_item.getFirst(), player_item.getSecond());
            if(i == current_item_hold){
                inventoryBoxDisplay.get(i).setOnHoldTexture();
            } else {
                inventoryBoxDisplay.get(i).setDefaultTexture();
            }
        }
    }

    public void render(float dt){
        for(InventoryBox box : inventoryBoxDisplay){
            box.itembox.render();
        }

        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

}
