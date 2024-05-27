package com.mygdx.game.Bodies.WorldWeapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Bullet extends Sprite {
    private Body body;
    private float speed;

    public Bullet(World world, float startX, float startY, float targetX, float targetY, float speed) {
        this.speed = speed;

        Vector2 direction = new Vector2(targetX - startX, targetY - startY).nor();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(startX, startY);
        setPosition(startX, startY);
        bodyDef.bullet = true;

        this.body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(10f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;
        this.body.setGravityScale(0f);

        this.body.createFixture(fixtureDef).setUserData(this);
        shape.dispose();
        this.body.setLinearVelocity(direction.scl(speed));
    }

    public void update(float dt) {

    }

    public void render(SpriteBatch batch) {

    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Body getBody() {
        return body;
    }

    public float getDamage() {
        return 100f;
    }


}
