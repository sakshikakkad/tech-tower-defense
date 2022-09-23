package com.example.demotest.objects;

import java.io.Serializable;

public class Enemy implements Serializable {

    protected int damage;
    protected int speed;
    protected int health;
    protected int reward; // monetary reward for killing enemies
    protected int type; // 1=trolley, 2=uga, 3=corona, 4=Finals(Boss)

    public Enemy() {
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public int getReward() {
        return reward;
    }

    public int getType() {
        return type;
    }

    // sets enemy health after being attacked
    public void reduceHealth(int damage) {
        this.health -= damage;
    }

    // returns false if enemy is alive, true if not
    public boolean isDefeated() {
        return this.health <= 0;
    }
}
