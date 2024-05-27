package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Helper.CooldownTask;
import com.mygdx.game.Sprites.BossAttacks.Missile;
import com.mygdx.game.YearOneWorld;

import java.util.Random;

public class YearOneBoss extends Sprite {
    private final World world;
    private Body b2body;

    private GameMode mode;

    private final float cooldown = 10f;
    private final float current_cooldown = 0;
    private float timeSinceLastAttack = 0f;
    private final float attackInterval = 0.2f;
    private static final Texture t1 = new Texture("RAW/attack_serato.png");
    private static final Texture t2 = new Texture("RAW/break_serato.png");
    private static final float width = 100, height = 100;
    private final CooldownTask cooldownBossStateHandler;
    public float life;

    public YearOneBoss(World world, float WorldX, float WorldY){
        super(t1);
        this.world = world;
        life = 1000;
        defineBody(WorldX, WorldY);
        cooldownBossStateHandler = new CooldownTask(10);
        mode = GameMode.COMBAT_MODE;
    }

    private void defineBody(float WorldX, float WorldY) {
        setPosition(WorldX, WorldY);
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(WorldX, WorldY);

        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(width, height);
        fdef.shape = shape;
        fdef.friction = 30f;

        b2body.setGravityScale(0f);
        b2body.createFixture(fdef).setUserData(this);
    }

    public void update(float delta, float x, float y) {

        if(mode == GameMode.DEAD_MODE) return;

        if(mode == GameMode.COMBAT_MODE){
                attack(delta);
                if(x >= b2body.getPosition().x){
                    b2body.applyLinearImpulse(new Vector2(80f, 0), b2body.getWorldCenter(), true);
                } else {
                    b2body.applyLinearImpulse(new Vector2(-80f, 0), b2body.getWorldCenter(), true);
                }
        }

        if(!cooldownBossStateHandler.isCooldownActive()){
            if(mode == GameMode.COMBAT_MODE){
                mode = GameMode.VULNERABLE_MODE;
                setTexture(t2);
                b2body.setGravityScale(1f);
            } else {
                mode = GameMode.COMBAT_MODE;
                b2body.setGravityScale(0f);
                b2body.setTransform(new Vector2(520,520), 0);
                setTexture(t1);
            }

            cooldownBossStateHandler.startCooldown();
        }

        if(life == 0){
            world.destroyBody(b2body);
            mode = GameMode.DEAD_MODE;
        }
    }

    public Body getBody(){
        return b2body;
    }


    public void render(float delta) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public Missile attack(float delta){

        if(mode == GameMode.VULNERABLE_MODE){
            return null;
        }

        int random = new Random().nextInt((int) width + 80);

        if(timeSinceLastAttack >= attackInterval){
            Missile m = new Missile(world, b2body.getPosition().x - width / 2 + random - 30, b2body.getPosition().y - height - 70);
            //new Missile(world, b2body.getPosition().x - width / 2, b2body.getPosition().y - height - 20);
            YearOneWorld.missiles.add(m);
            timeSinceLastAttack = 0f;
            return m;
        }

        timeSinceLastAttack += delta;
        return null;
    }

}
