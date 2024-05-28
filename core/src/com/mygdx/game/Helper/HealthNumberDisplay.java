package com.mygdx.game.Helper;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Bodies.YearOneBoss;

public class HealthNumberDisplay {
        YearOneBoss boss;

        Body body;
        private static final float WIDTH = 50;
        BitmapFont font;
        public HealthNumberDisplay(YearOneBoss boss){
            font = new BitmapFont();
            this.body = boss.getBody();
            this.boss = boss;
            font.getData().setScale(2f);
            String t = String.valueOf(boss.life);
        }

        public void render(SpriteBatch batch) {
            font.draw(
                    batch,
                    String.valueOf(boss.life),
                    body.getPosition().x - 50,
                    body.getPosition().y + 150,
                    100,
                    Align.center,
                    false
            );
        }


}
