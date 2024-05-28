package com.mygdx.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Helper.AnimationHelper;
import com.mygdx.game.Helper.Pair;
import com.mygdx.game.Items.Coin;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.Sellable;
import com.mygdx.game.Screens.BlackSmithBoard;
import com.mygdx.game.Terraria;

import java.util.ArrayList;

public class Blacksmith extends Sprite {
    private static final TextureAtlas ATLAS = new TextureAtlas("ANIMATION/steve.txt");
    private final World world;
    private Body b2body;
    private Animation<TextureRegion> idleAnimation;
    private float stateTime = 0f;
    private final ArrayList<Pair<Item, Integer>> inventory;
    public static BlackSmithBoard blackSmithBoard;
    public Blacksmith(World world, SpriteBatch batch, Player player){
        super(ATLAS.findRegion("steve"));
        this.world = world;
        blackSmithBoard = new BlackSmithBoard(batch, player);

        inventory = new ArrayList<>(8);

        for(int i = 0; i < 8; i++){
            inventory.add(new Pair<>(null,0));
        }

        definePlayer();

        TextureRegion playerStand = new TextureRegion(getTexture(), 0, 20, 65, 44);
        setBounds(0,0,48 / Terraria.PPM  , 44 / Terraria.PPM );
        setRegion(playerStand);

        initAnimations();
    }

    private void initAnimations() {
        Texture idleSheet = new Texture(Gdx.files.internal("RAW/blacksmith_idle.png"));
        idleAnimation = AnimationHelper.getAnimation(1,24,idleSheet,0.25f);
    }

    private void definePlayer() {

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(32 / Terraria.PPM * 40,384 / Terraria.PPM * 3);

        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10 / Terraria.PPM , 22 / Terraria.PPM);
        fdef.shape = shape;
        fdef.friction = 20f;

        b2body.createFixture(fdef).setUserData(this);
    }

    public void update(float dt){
        stateTime += dt;

    }

    public void render(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        TextureRegion currentFrame;
        currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        setRegion(currentFrame);
    }

    public Pair<Coin, Integer> buyResources(Pair<Item,Integer> resource){
        int value_each = 0;
        if(resource.getFirst() instanceof Sellable){
            value_each = ((Sellable) resource.getFirst()).getValueinCoin();
        }
        return new Pair<>(new Coin(), value_each * resource.getSecond());
    }

    public Vector2 getPosition(){
        return b2body.getPosition();
    }
}
