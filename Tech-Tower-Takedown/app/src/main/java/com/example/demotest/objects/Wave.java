package com.example.demotest.objects;

import java.util.ArrayList;

public class Wave {

    //private ArrayList<ImageView> waveUI = new ArrayList<ImageView>();
    private ArrayList<Enemy> wave = new ArrayList<Enemy>();
    private Boolean isStarted;
    private Boolean isEnded;
    private int trolleyCount = 0;
    private int ugaCount = 0;
    private int coronaCount = 0;

    public Wave(String difficulty) {
        this.wave = wave;
        this.isStarted = false;
        this.isEnded = false;

        if (difficulty.equals("Easy")) {
            addEnemies(3, 2, 1, difficulty);
        } else if (difficulty.equals("Normal")) {
            addEnemies(5, 3, 2, difficulty);
        } else if (difficulty.equals("Hard")) {
            addEnemies(7, 4, 3, difficulty);
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

    public Boolean getEnded() {
        return isEnded;
    }

    public void setEnded(Boolean ended) {
        isEnded = ended;
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

    public void addEnemies(int trolleyCount, int ugaCount, int coronaCount, String difficulty) {

        for (int i = 0; i < trolleyCount; i++) {
            Trolley trolley = new Trolley(difficulty);
            wave.add(trolley);
        }

        for (int i = 0; i < ugaCount; i++) {
            Uga uga = new Uga(difficulty);
            wave.add(uga);
        }

        for (int i = 0; i < coronaCount; i++) {
            Corona corona = new Corona(difficulty);
            wave.add(corona);
        }

        this.trolleyCount += trolleyCount;
        this.ugaCount += ugaCount;
        this.coronaCount += coronaCount;
    }

}
