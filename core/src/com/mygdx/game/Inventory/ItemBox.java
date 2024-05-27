package com.mygdx.game.Inventory;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import com.mygdx.game.Helper.BoxActor;
import com.mygdx.game.Items.Item;

import com.mygdx.game.Bodies.Merchant;

public class ItemBox extends BoxActor implements Draggable {
    private static final int MAX_NUMBER = 64;
    public static final Texture defaultTexture = new Texture("empty.png");
    private Item item;
    public Label countLabel;
    private int count = 0;

    private final float originalX;
    private final float originalY;

    public ItemBox(Item item, int x, int y) {
        super(item.getTexture());
        setPosition(x, y);
        this.item = item;
        originalX = getX();
        originalY = getY();

        initLabel();
        addDragListener();
    }


    public ItemBox(){
        super(defaultTexture);
        originalX = getX();
        originalY = getY();
        initLabel();
        addDragListener();

    }

    public ItemBox(int x, int y) {
        super(defaultTexture);
        setPosition(x, y);
        originalX = getX();
        originalY = getY();
        initLabel();
        addDragListener();
    }


    public void initLabel(){
        BitmapFont font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        countLabel = new Label(String.format("%02d", this.count), style);
        countLabel.setSize(5,5);
        countLabel.setPosition(getX()  - 5, getY() - 5);
        addActor(countLabel);
    }

    public void render(){
        if(count != -1){
            countLabel.setText(Integer.toString(count));
        }

        if(item == null || item.getTexture() == null){
            setTexture(defaultTexture);
        } else {
            setTexture(item.getTexture());
        }


    }

    public void setItem(Item item, int count) {
        this.item = item;
        this.count = count;
    }


    public Item getItem() {
        return item;
    }

    public int getCount(){
        return count;
    }

    public void setItem(Item item) {
        this.item = item;
        if(item != null){
            this.setTexture(item.getTexture());
        }

    }

    public void setCount(int count) {
        this.count = count;
    }

    private ItemBox getMyself(){
        return this;
    }

    @Override
    public void addDragListener() {


        this.addListener(new DragListener() {
            private boolean dragging;

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                dragging = true;
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                if (dragging) {

                    moveBy(x - getWidth() / 2, y - getHeight() / 2);
                    countLabel.setPosition(getX() - 5, getY() - 5);
                }
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                if (dragging) {
                    Actor actor1 = Merchant.merchantboard.getResourceBox();


                    Rectangle bounds1 = new Rectangle(actor1.getX(), actor1.getY(), actor1.getWidth(), actor1.getHeight());

                    Rectangle bounds2 = new Rectangle(getX(), getY(), getWidth(), getHeight());

                    if(bounds1.overlaps(bounds2) && !Merchant.merchantboard.isHidden){
                        Merchant.merchantboard.resourceBoxSwap(getMyself());
                        Merchant.merchantboard.showTrade();
                    }

                    setPosition(originalX, originalY);
                    countLabel.setPosition(originalX - 5, originalY - 5);
                    dragging = false;
                }


            }
        });
    }

    public void setLabelToText(String text){
        countLabel.setText(text);
    }

}
