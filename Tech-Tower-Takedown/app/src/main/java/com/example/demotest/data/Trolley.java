package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

public class Trolley extends Enemy {
    public Trolley() {
        this.type = 1;
        this.speed = 10;
        if (player.getDifficulty().equals("Easy")) {
            this.damage = 1;
            this.health = 50;
            this.maxHealth = 50;
            this.reward = 5;
        } else if (player.getDifficulty().equals("Normal")) {
            this.damage = 2;
            this.health = 75;
            this.maxHealth = 75;
            this.reward = 4;
        } else if (player.getDifficulty().equals("Hard")) {
            this.damage = 3;
            this.health = 100;
            this.maxHealth = 100;
            this.reward = 3;
        }
    }

    public int getDamage() {
        return damage;
    }
    public int getSpeed() {
        return speed;
    }
}
