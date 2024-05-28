package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Bodies.*;
import com.mygdx.game.Bodies.WorldWeapons.Bullet;

public class WorldContactListener implements ContactListener {
    GameWorld world;
    public WorldContactListener(GameWorld world){
        this.world = world;
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(fixtureA.getUserData() == null || fixtureB.getUserData() == null) return;

        if(PlayerAndDrop(fixtureA, fixtureB)){
            Player player = (Player) fixtureA.getUserData();
            Drop drop = (Drop) fixtureB.getUserData();

            player.getDrop(drop, 1);
        }

        if(PlayerAndDrop(fixtureB, fixtureA)){
            Player player = (Player) fixtureB.getUserData();
            Drop drop = (Drop) fixtureA.getUserData();

            player.getDrop(drop, 1);
        }

        if(fixtureA.getUserData() instanceof WeaponObject && fixtureB.getUserData() instanceof YearOneBoss){
            YearOneBoss boss = (YearOneBoss) fixtureB.getUserData();
            WeaponObject weapon = (WeaponObject) fixtureA.getUserData();
            boss.life -= weapon.getDamage();
        }

        if(fixtureB.getUserData() instanceof WeaponObject && fixtureA.getUserData() instanceof YearOneBoss){
            YearOneBoss boss = (YearOneBoss) fixtureA.getUserData();
            WeaponObject weapon = (WeaponObject) fixtureB.getUserData();
            System.out.println(boss.life);
            boss.life -= weapon.getDamage();
        }

        if(fixtureA.getUserData() instanceof Bullet && fixtureB.getUserData() instanceof YearOneBoss){
            YearOneBoss boss = (YearOneBoss) fixtureB.getUserData();
            Bullet b = (Bullet) fixtureA.getUserData();

//            b.dispose();
            b.setAlpha(0);
            YearOneWorld.bodiesToremove.add(b.getBody());
            boss.life -= b.getDamage();

        }

        if(fixtureB.getUserData() instanceof Bullet && fixtureA.getUserData() instanceof YearOneBoss){
            YearOneBoss boss = (YearOneBoss) fixtureA.getUserData();
            Bullet b = (Bullet) fixtureB.getUserData();
//            b.dispose();
            b.setAlpha(0);
            YearOneWorld.bodiesToremove.add(b.getBody());
            boss.life -= b.getDamage();
        }

        if(fixtureA.getUserData() instanceof Projectile && fixtureB.getUserData() instanceof Player){
            Player player = (Player) fixtureB.getUserData();


            Projectile projectile = (Projectile) fixtureA.getUserData();
            player.setLife(player.getLife() - projectile.getDamage());

            projectile.setAlpha(0);
            YearOneWorld.bodiesToremove.add(projectile.getBody());
        }

        if(fixtureB.getUserData() instanceof Projectile && fixtureA.getUserData() instanceof Player){
            Player player = (Player) fixtureA.getUserData();

            Projectile projectile = (Projectile) fixtureB.getUserData();
            player.setLife(player.getLife() - projectile.getDamage());

            projectile.setAlpha(0);
            YearOneWorld.bodiesToremove.add(projectile.getBody());
        }

        if(fixtureB.getUserData() instanceof Projectile && !(fixtureA.getUserData() instanceof Projectile) && !(fixtureA.getUserData() instanceof YearOneBoss)){
            Projectile projectile = (Projectile) fixtureB.getUserData();
            projectile.setAlpha(0);
            YearOneWorld.bodiesToremove.add(projectile.getBody());
        }

        if(fixtureA.getUserData() instanceof Projectile && !(fixtureB.getUserData() instanceof Projectile) && !(fixtureB.getUserData() instanceof YearOneBoss)){
            Projectile projectile = (Projectile) fixtureA.getUserData();
            projectile.setAlpha(0);
            YearOneWorld.bodiesToremove.add(projectile.getBody());
        }

        if(fixtureA.getUserData() instanceof Bullet){
            Bullet b = (Bullet) fixtureA.getUserData();
//            b.dispose();

            b.setAlpha(0);
            YearOneWorld.bodiesToremove.add(b.getBody());
        }

        if(fixtureB.getUserData() instanceof Bullet){
            Bullet b = (Bullet) fixtureB.getUserData();
//            b.dispose();

            b.setAlpha(0);
            YearOneWorld.bodiesToremove.add(b.getBody());
        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }

    private boolean PlayerAndDrop(Fixture a, Fixture b){
        return (a.getUserData() instanceof Player && b.getUserData()
        instanceof Drop);
    }
}
