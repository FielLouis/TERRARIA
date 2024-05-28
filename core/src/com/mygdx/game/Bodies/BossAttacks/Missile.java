package com.mygdx.game.Bodies.BossAttacks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Bodies.Projectile;

public class Missile extends Projectile {

    public Missile(World world, float WorldX, float WorldY){
        this(new Texture("RAW/missile.png"), world, WorldX, WorldY);
    }

    public Missile(Texture texture, World world, float WorldX, float WorldY){
        super(texture);
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

    public void render(SpriteBatch batch) {
        this.draw(batch);
    }

    public void update(float dt) {
        this.setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

}
