package com.mygdx.game.Sprites.BossAttacks;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Sprites.Projectile;

public class Missile extends Projectile {

    public Missile(World world, float WorldX, float WorldY){
        this.world = world;
        damage = 5f;
        width = 20;
        height = 60;
        speed = 5;
        defineBody(world, WorldX, WorldY);
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

        b2body.createFixture(fdef).setUserData(this);
    }


    @Override
    public float getDamage() {
        return damage;
    }

    public Body getBody(){
        return b2body;
    }

    public void render(float delta) {

    }

}
