package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Inventory.InventoryBox;
import com.mygdx.game.Inventory.ItemBox;
import com.mygdx.game.Items.Coin;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.Sellable;
import com.mygdx.game.Sprites.Drop;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.Terraria;

public class MerchantBoard {
    private Stage stage;
    private Viewport viewport;

    private InventoryBox resourceBox;
    private InventoryBox coinBox;
    public boolean isHidden = true;

    private Player player;

    public MerchantBoard(SpriteBatch batch, Player player){
        this.player = player;
        this.viewport = new FitViewport(Terraria.V_WIDTH, Terraria.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(this.viewport, batch);

        ItemBox itembox1 = new ItemBox((int) (Terraria.V_WIDTH / 2.0 -50 + 5), (int) (Terraria.V_HEIGHT / 2.0) + 50 + 5);
        itembox1.setItem(null);
        itembox1.setSize(25,25);

        resourceBox = new InventoryBox(itembox1);
        ItemBox itembox2 = new ItemBox((int) (Terraria.V_WIDTH / 2.0 + 10 + 5), (int) (Terraria.V_HEIGHT / 2.0) + 50 + 5);
        itembox2.setItem(null);

        itembox2.setSize(25,25);
        coinBox = new InventoryBox(itembox2);

        resourceBox.setSize(40,40);
        coinBox.setSize(40,40);


        coinBox.setPosition((float) (Terraria.V_WIDTH / 2.0 + 10), (float) (Terraria.V_HEIGHT / 2.0) + 50);
        resourceBox.setPosition((float) (Terraria.V_WIDTH / 2.0 - 50), (float) (Terraria.V_HEIGHT / 2.0) + 50);
        coinBox.setOnHoldTexture();

        stage.addActor(resourceBox);
        stage.addActor(coinBox);
        stage.addActor(resourceBox.itembox);
        stage.addActor(resourceBox.itembox.countLabel);
        stage.addActor(coinBox.itembox);
        stage.addActor(coinBox.itembox.countLabel);

        coinBox.itembox.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int count = coinBox.itembox.getCount();
                if(count != 0){
                    player.getDrop(new Drop(player.getB2body().getWorld(), 1,1, coinBox.itembox.getItem()), count);
                    coinBox.itembox.setCount(0);
                    resourceBox.itembox.setCount(0);
                    resourceBox.itembox.setItem(null);

                }
                return false;
            }
        });

    }
    public Stage getStage(){
        return stage;
    }

    public void update(Vector2 playerPos, Vector2 merchantPos){
        if(areBodiesClose(playerPos,merchantPos, 100) ){
            show();
            isHidden = false;
        } else {
            hide();
            isHidden = true;
        }

    }

    public void render(float dt){
        resourceBox.itembox.render();
        coinBox.itembox.render();

        stage.act(dt);
        stage.draw();

    }

    public void show() {
        stage.getRoot().setVisible(true);
    }

    public void hide() {
        stage.getRoot().setVisible(false);
    }

    public Viewport getViewport(){
        return viewport;
    }

    public void showTrade(){
        player.syncInventoryHud();
        if(resourceBox.itembox != null && resourceBox.itembox.getItem() instanceof Sellable){
            coinBox.itembox.setItem(new Coin());
            coinBox.itembox.setCount(resourceBox.getItemCount() * ((Sellable) resourceBox.getItem()).getValueinCoin());
        } else {
            coinBox.itembox.setCount(0);
        }
    }

    private static boolean areBodiesClose(Vector2 body1Position, Vector2 body2Position, float range) {
        float distanceSquared = body1Position.dst2(body2Position);
        float rangeSquared = range * range;

        return distanceSquared <= rangeSquared;
    }

    public InventoryBox getResourceBox() {
        return resourceBox;
    }

    public void resourceBoxSwap(ItemBox itembox1){
        ItemBox itembox2 = resourceBox.itembox;
        Item itemTemp = itembox1.getItem();
        int countTemp = itembox1.getCount();

        itembox1.setItem(itembox2.getItem());
        itembox1.setCount(itembox2.getCount());

        itembox2.setItem(itemTemp);
        itembox2.setCount(countTemp);
    }

    public void coinBoxSwap(ItemBox itembox1){
        ItemBox itembox2 = coinBox.itembox;
        Item itemTemp = itembox1.getItem();
        int countTemp = itembox1.getCount();

        itembox1.setItem(itembox2.getItem());
        itembox1.setCount(itembox2.getCount());

        itembox2.setItem(itemTemp);
        itembox2.setCount(countTemp);
    }

    public void setResourceBox(InventoryBox resourceBox) {
        this.resourceBox = resourceBox;
    }

    public InventoryBox getCoinBox() {
        return coinBox;
    }

    public void setCoinBox(InventoryBox coinBox) {
        this.coinBox = coinBox;
    }
}
