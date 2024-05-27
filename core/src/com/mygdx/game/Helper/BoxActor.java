package com.mygdx.game.Helper;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public class BoxActor extends Group {
    private Texture backgroundTexture;

    public BoxActor(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
        setWidth(backgroundTexture.getWidth());
        setHeight(backgroundTexture.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(backgroundTexture, getX(), getY(), getWidth(), getHeight());
    }

    public void setTexture(Texture texture){
        this.backgroundTexture = texture;

    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }
}
