package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

public class Corona extends Enemy {
    public Corona() {
        this.type = 3;
        this.speed = 20;
        if (player.getDifficulty().equals("Easy")) {
            this.damage = 5;
            this.health = 150;
            this.maxHealth = 150;
            this.reward = 20;
        } else if (player.getDifficulty().equals("Normal")) {
            this.damage = 6;
            this.health = 200;
            this.maxHealth = 200;
            this.reward = 15;
        } else if (player.getDifficulty().equals("Hard")) {
            this.damage = 7;
            this.health = 250;
            this.maxHealth = 250;
            this.reward = 10;
        }
    }

    public int getDamage() {
        return damage;
    }
    public int getSpeed() {
        return speed;
    }
}
