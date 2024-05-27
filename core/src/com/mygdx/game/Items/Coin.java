package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;

public class Coin extends Item {
    private static final Texture t = new Texture("RAW/coin.png");

    public Coin() {
        super("Coin", "Buy Weapons and Food with gold coins");
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public Texture getTexture() {
        return t;
    }
}
