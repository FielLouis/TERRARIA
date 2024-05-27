package com.mygdx.game.Items;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Bodies.Player;
import com.mygdx.game.Bodies.WeaponObject;

public abstract class Weapon extends Item{
    protected Weapon(String name, String description) {
        super(name, description);
    }

    public abstract WeaponObject getWeaponObject(World world, Player player);


}