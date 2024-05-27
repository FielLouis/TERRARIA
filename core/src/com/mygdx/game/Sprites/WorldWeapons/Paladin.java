package com.mygdx.game.Sprites.WorldWeapons;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.Sprites.WeaponObject;

public class Paladin extends WeaponObject {

    public Paladin(World world, Player player, float WorldX, float WorldY){
        damage = 20f;
        width = 10;
        height = 40;
        speed = 10;
        angle = 0;
        defineBody(world, player, WorldX, WorldY);
    }

    public void defineBody(World world,Player player, float WorldX, float WorldY) {
        this.player = player;
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
    }

    @Override
    public void useWeapon(float delta) {

        if(angle > 360){
            angle = 0;
        }

        angle += speed * delta;

        float newX = player.getPosition().x + 40 * MathUtils.cos(angle);
        float newY = player.getPosition().y + 40 * MathUtils.sin(angle);

        setPosition(newX, newY);
        getBody().setTransform(newX, newY, angle);
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

    }

    public void render(float delta) {

    }


}
