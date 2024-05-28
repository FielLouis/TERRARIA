package com.mygdx.game.Helper;

import java.util.ArrayList;

public class HealthBar {

    private float fullLife;
    public float currentLife;
    public ArrayList<Heart> hearts = new ArrayList<>(10);
    public int heartsToRender;

    public HealthBar(float fullLife){
        this.fullLife = fullLife;
        this.currentLife = fullLife;
        this.heartsToRender = 10;

        for(int i = 0; i < 10; i++){
            Heart heart = new Heart(i * 35, 340);
            hearts.add(heart);
        }
    }

    public void update(float newLife){
        currentLife = newLife;
        heartsToRender = calculateHeartsToRender();
    }

    public void render(float dt){

        try{
            for(int i = heartsToRender; i < 10; i++){
                hearts.get(i).setVisible(false);
            }
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }


    }

    private int calculateHeartsToRender() {
        float percentage = (currentLife / fullLife);
        float segmentSize = 0.1f;


        int segment = (int) Math.ceil(percentage / segmentSize);


        return segment;
    }

}
