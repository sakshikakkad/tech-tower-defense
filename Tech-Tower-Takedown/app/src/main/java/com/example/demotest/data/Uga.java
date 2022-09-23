package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

public class Uga extends Enemy {
    public Uga() {

        this.type = 2;
        this.speed = 15;
        if (player.getDifficulty().equals("Easy")) {
            this.damage = 12;
            this.health = 100;
            this.maxHealth = 100;
            this.reward = 15;
        } else if (player.getDifficulty().equals("Normal")) {
            this.damage = 3;
            this.health = 125;
            this.maxHealth = 125;
            this.reward = 10;
        } else if (player.getDifficulty().equals("Hard")) {
            this.damage = 4;
            this.health = 150;
            this.maxHealth = 150;
            this.reward = 5;
        }
    }

    public int getDamage() {
        return damage;
    }
    public int getSpeed() {
        return speed;
    }
}
