package com.example.demotest.objects;

public class Uga extends Enemy {
    public Uga(String difficulty) {
        this.type = 2;
        this.speed = 15;
        if (difficulty.equals("Easy")) {
            this.damage = 12;
            this.health = 100;
            this.reward = 5;
        } else if (difficulty.equals("Normal")) {
            this.damage = 3;
            this.health = 125;
            this.reward = 10;
        } else if (difficulty.equals("Hard")) {
            this.damage = 4;
            this.health = 150;
            this.reward = 15;
        }
    }

    public int getDamage() {
        return damage;
    }
    public int getSpeed() {
        return speed;
    }
}
