package com.mygdx.game.Block.BlockType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Block.Block;
import com.mygdx.game.Items.BlockItems.DirtItem;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Bodies.Drop;

public class Stone extends Block {

    public Stone(World world, Rectangle rect) {
        super(world,rect, new Texture(Gdx.files.internal("RAW/stone.png")));
    }

    @Override
    public Drop blocktodrop() {
        Item item = new DirtItem(this);
        Drop drop = new Drop(this.world, cell.row * 32, cell.column * 32, item);
        destroyed();
        return drop;
    }
}
