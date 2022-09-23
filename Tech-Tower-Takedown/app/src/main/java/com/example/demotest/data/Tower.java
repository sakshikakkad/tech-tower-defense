package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

import java.io.Serializable;

public abstract class Tower implements Serializable {

    protected int damage;
    protected int range;
    protected int level;
    protected int type; // 1=Buzz, 2=SteamWhistle, 3=RamblinWreck

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    // returns true if enemy was defeated
    public boolean attack(Enemy enemy, Monument monument) {

        enemy.reduceHealth(this.damage);

        if (enemy.isDefeated()) {
            if (this.type == 3) {
                monument.healMonument(enemy.getReward() / 2);
            } else if (this.type == 2) {
                player.increaseBuzzFunds(enemy.getReward() * 2);
            } else {
                player.increaseBuzzFunds(enemy.getReward());
            }
            return true;
        }
        return false;
    }

    public void increaseDamage(int damage) {
        this.damage += damage;
    }

    public void increaseRange(int range) {
        this.range += range;
    }
}
