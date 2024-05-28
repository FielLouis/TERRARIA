package com.mygdx.game.Bodies.WorldWeapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Bullet extends Sprite {
    private Body body;
    private float speed;
    private Texture texture;

    public Bullet(World world, float startX, float startY, float targetX, float targetY, float speed) {
        this(new Texture("RAW/bullet.png"), world, startX, startY, targetX, targetY, speed);
    }

    public Bullet(Texture texture, World world, float startX, float startY, float targetX, float targetY, float speed) {
        super(texture);
        this.texture = texture;
        this.speed = speed;
        this.setSize(20, 20);

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
        this.setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
    }

    public void render(SpriteBatch batch) {
        this.draw(batch);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Body getBody() {
        return body;
    }

    public float getDamage() {
        return 80f;
    }

}
