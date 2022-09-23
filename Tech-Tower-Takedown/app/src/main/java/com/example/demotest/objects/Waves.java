package com.example.demotest.objects;

public class Waves {

    private Wave currentWave;
    private int wavesLeft;
    private int waveCount;

    public Waves(String difficulty) {

        setCurrentWave(difficulty);
        this.waveCount = 0;

        if (difficulty.equals("Easy")) {
            this.wavesLeft = 2;
        } else if (difficulty.equals("Normal")) {
            this.wavesLeft = 3;
        } else if (difficulty.equals("Hard")) {
            this.wavesLeft = 4;
        }
    }

    public Wave getCurrentWave() {
        return currentWave;
    }

    public int getWavesLeft() {
        return wavesLeft;
    }

    public void setWavesLeft(int wavesLeft) {
        this.wavesLeft = wavesLeft;
    }

    public void setCurrentWave(String difficulty) {

        this.currentWave = new Wave(difficulty);
        int enemies = 0;

        if (difficulty.equals("Easy")) {
            enemies = 1;
        } else if (difficulty.equals("Normal")) {
            enemies = 2;
        } else if (difficulty.equals("Hard")) {
            enemies = 3;
        }

        enemies *= waveCount;
        currentWave.addEnemies(enemies, enemies + 1, enemies + 2, difficulty);

        this.waveCount++;
        this.wavesLeft--;
    }
}
