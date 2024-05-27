package com.mygdx.game.Helper;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Block.Block;
import com.mygdx.game.Block.BlockType.Diamond;
import com.mygdx.game.Block.BlockType.Dirt;
import com.mygdx.game.Block.BlockType.Gold;
import com.mygdx.game.Block.BlockType.Iron;
import com.mygdx.game.Terraria;

import java.util.HashMap;
import java.util.Map;

public class WorldCreator {
    public static HashMap<Vector2, Block> syncWorldAndTiledMap(World world, TiledMap map){

        HashMap<Vector2, Block> tiles = new HashMap<>();

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(2);

        for (int y = 0; y < layer.getHeight(); y++) {
            for (int x = 0; x < layer.getWidth(); x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = new Rectangle();
                    rect.set( x * layer.getTileWidth() * Terraria.ZOOM_FACTOR, y * layer.getTileHeight() * Terraria.ZOOM_FACTOR, layer.getTileWidth() * Terraria.ZOOM_FACTOR, layer.getTileHeight() * Terraria.ZOOM_FACTOR );


                    tiles.put(new Vector2(x,y), new Dirt(world, rect));
                }
            }
        }

//        layer = (TiledMapTileLayer) map.getLayers().get(3);
//
//        for (int y = 0; y < layer.getHeight(); y++) {
//            for (int x = 0; x < layer.getWidth(); x++) {
//                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
//                if (cell != null) {
//                    Rectangle rect = new Rectangle();
//                    rect.set( x * layer.getTileWidth() * Terraria.ZOOM_FACTOR, y * layer.getTileHeight() * Terraria.ZOOM_FACTOR, layer.getTileWidth() * Terraria.ZOOM_FACTOR, layer.getTileHeight() * Terraria.ZOOM_FACTOR );
//
//
//                    tiles.put(new Vector2(x,y), new Diamond(world, rect));
//                    System.out.println("Block: " + tiles.get(new Vector2(x,y)));
//                }
//            }
//        }

        layer = (TiledMapTileLayer) map.getLayers().get(3);

        for (int y = 0; y < layer.getHeight(); y++) {
            for (int x = 0; x < layer.getWidth(); x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = new Rectangle();
                    rect.set( x * layer.getTileWidth() * Terraria.ZOOM_FACTOR, y * layer.getTileHeight() * Terraria.ZOOM_FACTOR, layer.getTileWidth() * Terraria.ZOOM_FACTOR, layer.getTileHeight() * Terraria.ZOOM_FACTOR );


                    tiles.put(new Vector2(x,y), new Iron(world, rect));
                }
            }
        }

        layer = (TiledMapTileLayer) map.getLayers().get(4);

        for (int y = 0; y < layer.getHeight(); y++) {
            for (int x = 0; x < layer.getWidth(); x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = new Rectangle();
                    rect.set( x * layer.getTileWidth() * Terraria.ZOOM_FACTOR, y * layer.getTileHeight() * Terraria.ZOOM_FACTOR, layer.getTileWidth() * Terraria.ZOOM_FACTOR, layer.getTileHeight() * Terraria.ZOOM_FACTOR );


                    tiles.put(new Vector2(x,y), new Gold(world, rect));
                }
            }
        }

        layer = (TiledMapTileLayer) map.getLayers().get(5);

        for (int y = 0; y < layer.getHeight(); y++) {
            for (int x = 0; x < layer.getWidth(); x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = new Rectangle();
                    rect.set( x * layer.getTileWidth() * Terraria.ZOOM_FACTOR, y * layer.getTileHeight() * Terraria.ZOOM_FACTOR, layer.getTileWidth() * Terraria.ZOOM_FACTOR, layer.getTileHeight() * Terraria.ZOOM_FACTOR );


                    tiles.put(new Vector2(x,y), new Diamond(world, rect));
                }
            }
        }

        for (Map.Entry<Vector2, Block> entry : tiles.entrySet()) {
            Vector2 position = entry.getKey();
            Block block = entry.getValue();

            Vector2 pos_above = new Vector2(position.x, position.y + 1);
            if (!tiles.containsKey(pos_above) && block instanceof Dirt){
                ((Dirt) block).changeRightTexture(1,2);
            }

        }




        return tiles;
    }

    public static void syncWorldAndTiledMapYearOne(World world, TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2), (rect.getY() + rect.getHeight() / 2) );

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 , rect.getHeight() / 2);
            fdef.shape = shape;
            body.createFixture(fdef).setUserData("PLATFORM");
        }

    }
}
