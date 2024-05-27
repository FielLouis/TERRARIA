package com.mygdx.game.Items;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.Sprites.WeaponObject;

public abstract class Weapon extends Item{


    protected Weapon(String name, String description) {
        super(name, description);
    }

    public abstract WeaponObject getWeaponObject(World world, Player player);


}