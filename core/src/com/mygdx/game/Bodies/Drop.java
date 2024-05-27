package com.mygdx.game.Bodies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Items.Item;

public class Drop extends Sprite {
    private World world;
    private Body b2body;
    private Item item;

    public Drop(World world, float WorldX, float WorldY, Item item){
        super(item.getTexture());
        this.world = world;
        this.item = item;
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

    public Item getItem() {
        return item;
    }
}
