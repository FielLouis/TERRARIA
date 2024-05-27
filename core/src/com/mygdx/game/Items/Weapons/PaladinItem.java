package com.mygdx.game.Items.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Bodies.Player;
import com.mygdx.game.Bodies.WeaponObject;
import com.mygdx.game.Bodies.WorldWeapons.Paladin;
import com.mygdx.game.Items.Weapon;

public class PaladinItem extends Weapon {
    private static Paladin paladin;
    private static final Texture t = new Texture("RAW/paladin.png");
    public PaladinItem(String name, String description, Paladin paladin) {
        super(name, description);
    }

    public PaladinItem() {
        super("Paladin", "To damage enemies");
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public Texture getTexture() {
        return t;
    }


    @Override
    public WeaponObject getWeaponObject(World world, Player player) {
        if (paladin == null){
            paladin = new Paladin(world,player, 1,1);
        }

        return paladin;
    }
}
