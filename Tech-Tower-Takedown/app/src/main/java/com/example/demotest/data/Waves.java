package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

import java.util.ArrayList;

public class Waves {

    private Wave currentWave;
    private int wavesLeft;
    private int waveCount;

    public Waves() {

        setCurrentWave();
        this.waveCount = 0;

        if (player.getDifficulty().equals("Easy")) {
            this.wavesLeft = 2;
        } else if (player.getDifficulty().equals("Normal")) {
            this.wavesLeft = 3;
        } else if (player.getDifficulty().equals("Hard")) {
            this.wavesLeft = 4;
        }
    }

    public Wave getCurrentWave() {
        return currentWave;
    }

    public int getWavesLeft() {
        return wavesLeft;
    }

    public void decrementWavesLeft() {
        this.wavesLeft--;
    }

    public void setCurrentWave() {

        currentWave = new Wave();

        int enemies = 0;

        if (player.getDifficulty().equals("Easy")) {
            enemies = 1;
        } else if (player.getDifficulty().equals("Normal")) {
            enemies = 2;
        } else if (player.getDifficulty().equals("Hard")) {
            enemies = 3;
        }

        enemies *= waveCount;
        currentWave.addEnemies(enemies, enemies + 1, enemies + 2);
        waveCount++;
    }

    public boolean wavesEnded() {
        if (wavesLeft < 0 && currentWave.waveEnded()) {
            player.setWon(true);
            return true;
        }
        return false;
    }

    public void createBossWave() {
        FinalBoss boss = new FinalBoss();
        currentWave = new Wave();
        currentWave.setWave(new ArrayList<Enemy>());
        currentWave.getWave().add(boss);
    }
}
