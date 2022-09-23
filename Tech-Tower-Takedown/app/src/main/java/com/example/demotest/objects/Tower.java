package com.example.demotest.objects;

import java.io.Serializable;

public class Tower implements Serializable {

    private int damage;
    private int range;
    private int cost;
    private int type; // 1=Buzz, 2=SteamWhistle, 3=RamblinWreck

    public Tower(int d, int r, int t) {
        this.damage = d;
        this.range = r;
        this.type = t;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getCost() {
        return cost;
    }

    public int getType() {
        return type;
    }

    // returns true if enemy was defeated
    public boolean attack(Enemy enemy, Player player, Monument monument) {

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
}
