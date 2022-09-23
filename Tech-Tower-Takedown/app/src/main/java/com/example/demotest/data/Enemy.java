package com.example.demotest.data;

import java.io.Serializable;

public abstract class Enemy implements Serializable {

    protected int damage;
    protected int speed;
    protected int delay;
    protected int health;
    protected int maxHealth;
    protected int reward; // monetary reward for killing enemies
    protected int type; // 1=trolley, 2=uga, 3=corona, 4=Finals(Boss)
    protected HealthBar healthBar;

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

    public int getMaxHealth() {
        return maxHealth;
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

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
