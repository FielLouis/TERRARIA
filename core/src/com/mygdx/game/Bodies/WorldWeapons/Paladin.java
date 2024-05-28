package com.mygdx.game.Bodies.WorldWeapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Bodies.Player;
import com.mygdx.game.Bodies.WeaponObject;
import com.mygdx.game.YearOneWorld;

public class Paladin extends WeaponObject {


    public Paladin(World world, Player player, float WorldX, float WorldY){
        super(new Texture("RAW/sword.png"));
        this.player = player;
        damage = 30f;
        width = 10;
        height = 40;
        speed = 10;
        angle = 0;
        defineBody(world, -1, -1);
    }

    public void defineBody(World world, float WorldX, float WorldY) {
        setPosition(WorldX, WorldY);
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(WorldX, WorldY);

        b2body = world.createBody(bdef);


        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(width / 2, height / 2);
        fdef.shape = shape;
        fdef.friction = 30f;

        b2body.setGravityScale(0f);
        b2body.createFixture(fdef).setUserData(this);

        YearOneWorld.spritesToDraw.add(this);
    }

    @Override
    public void useWeapon(float delta) {

        if(angle > 360){
            angle = 0;
        }

        angle += speed * delta;

        float newX = player.getPosition().x + 40 * MathUtils.cos(angle);
        float newY = player.getPosition().y + 40 * MathUtils.sin(angle);
        getBody().setTransform(newX, newY, 135);
    }

    @Override
    public void useWeapon(float delta, float x, float y) {
        useWeapon(delta);

    }

    @Override
    public float getDamage() {
        return damage;
    }


    public Body getBody(){
        return b2body;
    }

    @Override
    public void update(float delta) {
        setPosition(b2body.getPosition().x - 35, b2body.getPosition().y - 18);
    }

    public void render(float delta) {

    }


}
