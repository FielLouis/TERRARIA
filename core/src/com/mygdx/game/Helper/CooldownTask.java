package com.mygdx.game.Helper;

public class CooldownTask implements Runnable {
    private final int cooldownTime;
    private boolean isCooldownActive;
    private Thread cooldownThread;

    public CooldownTask(int cooldownTime) {
        this.cooldownTime = cooldownTime;
        this.isCooldownActive = false;
    }

    @Override
    public void run() {
        try {

            isCooldownActive = true;
            Thread.sleep(cooldownTime * 1000);
            isCooldownActive = false;

        } catch (InterruptedException e) {
            System.out.println("Cooldown was interrupted.");
            Thread.currentThread().interrupt();
        }
    }

    public synchronized boolean isCooldownActive() {
        return isCooldownActive;
    }

    public void startCooldown() {
        cooldownThread = new Thread(this);
        cooldownThread.start();
    }
}
