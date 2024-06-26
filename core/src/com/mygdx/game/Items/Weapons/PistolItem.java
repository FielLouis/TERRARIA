package com.mygdx.game.Items.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Bodies.Player;
import com.mygdx.game.Bodies.WeaponObject;
import com.mygdx.game.Bodies.WorldWeapons.Pistol;
import com.mygdx.game.Items.Weapon;

public class PistolItem extends Weapon {
    private static Pistol pistol;

    private static final Texture t = new Texture("item_demo.png");

    public PistolItem(String name, String description) {
        super(name, description);
    }

    public PistolItem() {
        super("Pistol", "Shoots 1 bullet at a time");
    }

    @Override
    public String getType() {
        return "Pistol";
    }

    @Override
    public Texture getTexture() {
        return t;
    }

    @Override
    public WeaponObject getWeaponObject(World world, Player player) {
        if(pistol == null){
            pistol = new Pistol(world, player, -1, -1);
        }
        return pistol;
    }
}
