package com.mygdx.game.Block.BlockType;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Block.Block;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.Resources.IronItem;
import com.mygdx.game.Sprites.Drop;

public class Iron extends Block {

    private static final Texture defaultTexture = new Texture("RAW/iron_block.png");

    public Iron(World world, Rectangle rect) {
        super(world,rect, defaultTexture);
    }

    @Override
    public Drop blocktodrop() {
        Item item = new IronItem();
        Drop drop = new Drop(this.world, cell.row * 32, cell.column * 32, item);
        destroyed();
        return drop;
    }

}
