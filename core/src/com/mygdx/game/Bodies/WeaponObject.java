package com.mygdx.game.Bodies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class WeaponObject extends Sprite {

    protected float damage;
    public float width, height, angle;
    public float speed;

    public World world;
    public Body b2body;
    protected Player player;

    public WeaponObject(Texture texture) {
        super(texture);
    }


    public abstract void update(float delta);


    public abstract void render(float delta);

    public abstract void useWeapon(float delta);
    public abstract void useWeapon(float delta, float x, float y);

    public abstract float getDamage();
}
