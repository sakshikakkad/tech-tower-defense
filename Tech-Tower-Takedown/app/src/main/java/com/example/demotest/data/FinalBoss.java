package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

public class FinalBoss extends Enemy {

    public FinalBoss() {
        this.type = 4;
        this.speed = 35;

        if (player.getDifficulty().equals("Easy")) {
            this.damage = 15;
            this.health = 1000;
            this.maxHealth = 1000;
            this.reward = 200;
        } else if (player.getDifficulty().equals("Normal")) {
            this.damage = 18;
            this.health = 2000;
            this.maxHealth = 625;
            this.reward = 150;
        } else if (player.getDifficulty().equals("Hard")) {
            this.damage = 25;
            this.health = 3000;
            this.maxHealth = 750;
            this.reward = 100;
        }
    }

    public int getDamage() {
        return damage;
    }
    public int getSpeed() {
        return speed;
    }

}
