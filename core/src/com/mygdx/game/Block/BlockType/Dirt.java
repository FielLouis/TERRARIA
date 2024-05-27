package com.mygdx.game.Block.BlockType;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Block.Block;
import com.mygdx.game.Helper.AnimationHelper;
import com.mygdx.game.Items.BlockItems.DirtItem;
import com.mygdx.game.Items.Item;
import com.mygdx.game.MiningWorld;
import com.mygdx.game.Bodies.Drop;

public class Dirt extends Block {
    private static final TextureRegion[][] dirtTextures = AnimationHelper.getTexturePack(3, 6, new Texture("RAW/dynamic_dirt.png"));
    public Dirt(World world,Rectangle rect) {
        super(world,rect, dirtTextures[0][0].getTexture());
        changeRightTexture(1,1);
    }

    public void changeRightTexture(int r, int c){
        Vector2 top = new Vector2(cell.row - 1, cell.column);
        Vector2 bottom = new Vector2(cell.row + 1, cell.column);
        Vector2 left = new Vector2(cell.row, cell.column -1);
        Vector2 right = new Vector2(cell.row, cell.column + 1);

        int ansX, ansY;

        if(MiningWorld.tilesMap.get(top) == null){
            if(MiningWorld.tilesMap.get(left) == null){
                ansX = 0;
                ansY = 0;
            } else if (MiningWorld.tilesMap.get(right) == null) {
                ansX = 0;
                ansY = 2;
            } else {
                ansX = 0;
                ansY = 1;
            }
        } else if (MiningWorld.tilesMap.get(left) == null){
            if(MiningWorld.tilesMap.get(bottom) == null){
                ansX = 2;
                ansY = 0;
            } else {
                ansX = 1;
                ansY = 0;
            }
        } else if (MiningWorld.tilesMap.get(right) == null){
            if(MiningWorld.tilesMap.get(bottom) == null){
                ansX = 2;
                ansY = 2;
            } else {
                ansX = 1;
                ansY = 2;
            }
        } else if (MiningWorld.tilesMap.get(bottom) == null) {
            ansX = 2;
            ansY = 1;
        } else {
            ansX = 1;
            ansY = 1;
        }

        changeTexture(dirtTextures[r][c]);
    }

    @Override
    public Drop blocktodrop() {
        Item item = new DirtItem(this);
        Drop drop = new Drop(this.world, cell.row * 32, cell.column * 32, item);
        destroyed();
        return drop;
    }
}
