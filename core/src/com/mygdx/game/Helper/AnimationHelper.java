package com.mygdx.game.Helper;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationHelper {

    public AnimationHelper(){

    }

    public static TextureRegion[][] getTexturePack(int rows, int cols, Texture sheet){
        TextureRegion[][] temp  = TextureRegion.split(sheet, sheet.getWidth() / cols , sheet.getHeight() / rows);
        TextureRegion[][] ans = new TextureRegion[3][6];

        if(rows == 3 && cols == 6) {
            ans[0][0] = temp[2][0];
            ans[0][1] = temp[1][0];
            ans[0][2] = temp[0][0];

            ans[1][0] = temp[2][1];
            ans[1][1] = temp[1][1];
            ans[1][2] = temp[0][1];

            ans[2][0] = temp[2][2];
            ans[2][1] = temp[1][2];
            ans[2][2] = temp[0][2];

            ans[0][3] = temp[2][3];
            ans[0][4] = temp[1][3];
            ans[0][5] = temp[0][3];

            ans[1][3] = temp[2][4];
            ans[1][4] = temp[1][4];
            ans[1][5] = temp[0][4];

            ans[2][3] = temp[2][5];
            ans[2][4] = temp[1][5];
            ans[2][5] = temp[0][5];

            return ans;
        }

        return temp;
    }

    public static Animation<TextureRegion> getAnimation(int rows, int cols, Texture sheet, float duration){

        TextureRegion[][] tmp =getTexturePack(rows,cols, sheet);

        TextureRegion[] Frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                  Frames[index++] = tmp[i][j];
            }
        }

        return new Animation<>(duration, Frames);
    }
}
