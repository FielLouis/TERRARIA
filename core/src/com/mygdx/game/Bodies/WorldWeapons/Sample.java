package com.mygdx.game.Bodies.WorldWeapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Sample extends Sprite {
    private World world;
    private Body b2body;

    public Sample(World world, float WorldX, float WorldY){
        super(new Texture("RAW/iron.png"));
        this.world = world;
        defineBody(WorldX, WorldY);
    }


    private void defineBody(float WorldX, float WorldY) {
        setPosition(WorldX, WorldY);
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(WorldX, WorldY);

        b2body = world.createBody(bdef);

        float width = getWidth();
        float height = getHeight();

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(width / 2, height / 2);
        fdef.shape = shape;
        fdef.friction = 30f;

        b2body.createFixture(fdef).setUserData(this);
    }

    public Body getBody(){
        return b2body;
    }


    public void render(float delta) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

}
