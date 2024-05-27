package com.mygdx.game.Bodies.WorldWeapons;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Bodies.Player;
import com.mygdx.game.Bodies.WeaponObject;
import com.mygdx.game.YearOneWorld;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Pistol extends WeaponObject {


    private AtomicBoolean canShoot;
    private ScheduledExecutorService scheduler;

    public Pistol(World world, Player player, float WorldX, float WorldY) {
        this.world = world;
        this.player = player;
        damage = 0f;
        width = 10;
        height = 40;
        speed = 600;
        angle = 0;
        canShoot = new AtomicBoolean(true);
        scheduler = Executors.newScheduledThreadPool(1);
        defineBody(world, player, WorldX, WorldY);
    }

    public void defineBody(World world, Player player, float WorldX, float WorldY) {
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
    public void update(float delta) {
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void useWeapon(float delta) {
        // This method is intentionally left empty, as we're using the version with target coordinates.
    }

    @Override
    public void useWeapon(float delta, float x, float y) {
        if (canShoot.get()) {
            YearOneWorld.bullets.add(new Bullet(world, player.getX() + 16, player.getY() + 100, x, y, speed));
            canShoot.set(false);
            scheduler.schedule(() -> canShoot.set(true), 2, TimeUnit.SECONDS);
        }
    }

    @Override
    public float getDamage() {
        return damage;
    }


}
