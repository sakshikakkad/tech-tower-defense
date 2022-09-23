package com.example.demotest.objects;

public class Corona extends Enemy {
    public Corona(String difficulty) {
        this.type = 3;
        this.speed = 20;
        if (difficulty.equals("Easy")) {
            this.damage = 5;
            this.health = 150;
            this.reward = 15;
        } else if (difficulty.equals("Normal")) {
            this.damage = 6;
            this.health = 175;
            this.reward = 20;
        } else if (difficulty.equals("Hard")) {
            this.damage = 7;
            this.health = 200;
            this.reward = 25;
        }
    }

    public int getDamage() {
        return damage;
    }
    public int getSpeed() {
        return speed;
    }
}
