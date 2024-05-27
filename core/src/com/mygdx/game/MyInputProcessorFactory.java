package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Sprites.GameMode;
import com.mygdx.game.Sprites.Player;

public class MyInputProcessorFactory {
    GameWorld world;


    public InputProcessor processInput (GameWorld world, String listenerType, Player player){
        this.world = world;
        switch (listenerType) {
            case "A":
                return new MyInputListenerA(world);
            case "B":
                return new MyInputListenerB(world, player);
            default:
                return new MyInputListenerA(world);
        }

    }

    public static class MyInputListenerA implements InputProcessor {
        private boolean isLeftHold = false;
        private final Vector2 current_tile = new Vector2(0,0);
        private final MiningWorld world;

        public MyInputListenerA(GameWorld world) {
            this.world = (MiningWorld) world;
        }
        public void updateListenerA(){
            if(isLeftHold){
                world.getPlayer().deleteBlock((int) current_tile.x,(int) current_tile.y);
            } else {
                world.getPlayer().resetBlock((int)current_tile.x, (int)current_tile.y);
            }
        }

        @Override
        public boolean keyDown(int i) {
            return false;
        }

        @Override
        public boolean keyUp(int i) {
            return false;
        }

        @Override
        public boolean keyTyped(char c) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            Vector3 worldCoordinates = world.converToWorldCoordinates(screenX,screenY);
            int tileX = (int) (worldCoordinates.x / 32);
            int tileY = (int) (worldCoordinates.y / 32);

            if(button == Input.Buttons.LEFT){
                isLeftHold = true;
                current_tile.set(tileX,tileY);
                world.getPlayer().deleteBlock(tileX, tileY);
            } else {
                world.getPlayer().placeBlock(tileX,tileY);
            }

            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            if (button == Input.Buttons.LEFT) {
                isLeftHold = false;
            }
            return true;
        }

        @Override
        public boolean touchCancelled(int i, int i1, int i2, int i3) {
            return false;
        }

        @Override
        public boolean touchDragged(int i, int i1, int i2) {
            return false;
        }

        @Override
        public boolean mouseMoved(int i, int i1) {
            return false;
        }

        @Override
        public boolean scrolled(float v, float v1) {
            return false;
        }
    }

    public class MyInputListenerB implements InputProcessor {
        Player player;
        GameWorld world;


        public MyInputListenerB(GameWorld world, Player player) {
            this.world = world;
            this.player = player;

        }

        public String debugg(){
            if(world instanceof MiningWorld){
                return "Mine";
            } else {
                return "One";
            }
        }

        @Override
        public boolean keyDown(int i) {
            return false;
        }

        @Override
        public boolean keyUp(int i) {
            return false;
        }

        @Override
        public boolean keyTyped(char c) {
            return false;
        }

        @Override
        public boolean touchDown(int i, int i1, int i2, int i3) {
            return false;
        }

        @Override
        public boolean touchUp(int i, int i1, int i2, int i3) {
            return false;
        }

        @Override
        public boolean touchCancelled(int i, int i1, int i2, int i3) {
            return false;
        }

        @Override
        public boolean touchDragged(int i, int i1, int i2) {
            return false;
        }

        @Override
        public boolean mouseMoved(int i, int i1) {
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {

            if(Terraria.gameMode == GameMode.MINING_MODE && world instanceof YearOneWorld){
                return false;
            }


            if (amountY < 0) {
                player.setCurrentItem( (player.getCurrentItem() + 1) % 8);
            } else {

                if(player.getCurrentItem() - 1 < 0){
                    player.setCurrentItem(7);
                } else {
                    player.setCurrentItem(player.getCurrentItem() - 1);
                }
            }

            player.syncHudInventory();
            return true;
        }
    }




}
