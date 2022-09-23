package com.example.demotest.objects;

public class Trolley extends Enemy {
    public Trolley(String difficulty) {
        this.type = 1;
        this.speed = 10;
        if (difficulty.equals("Easy")) {
            this.damage = 1;
            this.health = 50;
            this.reward = 3;
        } else if (difficulty.equals("Normal")) {
            this.damage = 2;
            this.health = 75;
            this.reward = 4;
        } else if (difficulty.equals("Hard")) {
            this.damage = 3;
            this.health = 100;
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
