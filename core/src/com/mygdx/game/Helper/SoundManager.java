package com.mygdx.game.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundManager {
    private static Music boss = Gdx.audio.newMusic(Gdx.files.internal("MUSIC/TerrariaBoss.mp3"));
    private static Music nujabes = Gdx.audio.newMusic(Gdx.files.internal("MUSIC/nujabes.mp3"));
    private static Music bamyamgeng = Gdx.audio.newMusic(Gdx.files.internal("MUSIC/bamyamgeng.mp3"));
    private static Music buySomething = Gdx.audio.newMusic(Gdx.files.internal("MUSIC/buySomething.wav"));
    private static Music goodLookingWeapon = Gdx.audio.newMusic(Gdx.files.internal("MUSIC/goodLookingWeapon.wav"));
    private static Music notEnoughMoney = Gdx.audio.newMusic(Gdx.files.internal("MUSIC/notEnoughMoney.wav"));
    private static Music breakBlock = Gdx.audio.newMusic(Gdx.files.internal("MUSIC/breakBlock.mp3"));

    public static void playBackgroundMusic() {
        nujabes.setLooping(true);
        nujabes.play();
        nujabes.setVolume(0.3f);
    }

    public static void playBossMusic() {
        boss.setLooping(true);
        boss.play();
    }
    public static void playBuySomehthing() {
        if(buySomething.isPlaying()) return;
        buySomething.setLooping(false);
        buySomething.play();
    }

    public void playPlaceDirt() {
        Music placeDirt = Gdx.audio.newMusic(Gdx.files.internal("MUSIC/placeDirt.mp3"));
        placeDirt.setLooping(false);
        placeDirt.play();
    }

    public void playGetDrop() {
        Music getDrop = Gdx.audio.newMusic(Gdx.files.internal("MUSIC/getDrop.mp3"));
        getDrop.setLooping(false);
        getDrop.play();
    }

    public static void playBreakBlock() {
        breakBlock.setLooping(false);
        breakBlock.play();
    }

    public static void playNotEnoughMoney() {
        if(notEnoughMoney.isPlaying()) return;
        notEnoughMoney.setLooping(false);
        notEnoughMoney.play();
    }

    public static void playGoodLookingWeapon() {
        if(goodLookingWeapon.isPlaying()) return;
        goodLookingWeapon.setLooping(false);
        goodLookingWeapon.play();
    }


    public static void playBamyamgengMusic() {
        bamyamgeng.setLooping(true);
        bamyamgeng.play();
    }

    public static void stopAllMusic() {
        if (nujabes.isPlaying()) {
            nujabes.stop();
        }
        if (boss.isPlaying()) {
            boss.stop();
        }
        if (bamyamgeng.isPlaying()) {
            bamyamgeng.stop();
        }
    }
}
