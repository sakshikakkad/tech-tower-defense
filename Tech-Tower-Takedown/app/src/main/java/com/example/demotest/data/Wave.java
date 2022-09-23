package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;

public class Wave {

    //private ArrayList<ImageView> waveUI = new ArrayList<ImageView>();
    private ArrayList<Enemy> wave = new ArrayList<Enemy>();
    private Boolean isStarted;
    private int trolleyCount = 0;
    private int ugaCount = 0;
    private int coronaCount = 0;
    private int bossCount = 0;

    public Wave() {
        this.isStarted = false;

        if (player.getDifficulty().equals("Easy")) {
            addEnemies(3, 2, 1);
        } else if (player.getDifficulty().equals("Normal")) {
            addEnemies(5, 3, 2);
        } else {
            addEnemies(7, 4, 3);
        }
    }

    public ArrayList<Enemy> getWave() {
        return wave;
    }

    public Boolean getStarted() {
        return isStarted;
    }

    public void setStarted(Boolean started) {
        isStarted = started;
    }

    public void setWave(ArrayList<Enemy> arr) {
        wave = arr;
    }

    public int getTrolleyCount() {
        return trolleyCount;
    }

    public int getUgaCount() {
        return ugaCount;
    }

    public int getCoronaCount() {
        return coronaCount;
    }

    public void addEnemies(int trolleyCount, int ugaCount, int coronaCount) {

        for (int i = 0; i < trolleyCount; i++) {
            Trolley trolley = new Trolley();
            wave.add(trolley);
        }

        for (int i = 0; i < ugaCount; i++) {
            Uga uga = new Uga();
            wave.add(uga);
        }

        for (int i = 0; i < coronaCount; i++) {
            Corona corona = new Corona();
            wave.add(corona);
        }

        this.trolleyCount += trolleyCount;
        this.ugaCount += ugaCount;
        this.coronaCount += coronaCount;
    }

    public boolean waveEnded() {

        if (wave.size() == 0) {
            return true;
        }
        return false;
    }
}
