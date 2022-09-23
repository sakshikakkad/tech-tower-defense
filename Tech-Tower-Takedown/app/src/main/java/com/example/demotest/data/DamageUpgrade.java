package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

public class DamageUpgrade extends TowerUpgrade {

    public DamageUpgrade(Tower tower) {

        this.upgradedTower = tower;

        if (player.getDifficulty().equals("Easy")) {
            this.upgradeCost = 10;
        } else if (player.getDifficulty().equals("Normal")) {
            this.upgradeCost = 20;
        } else {
            this.upgradeCost = 30;
        }

        int amount = 0;
        if ((upgradedTower.level < 3) && (player.getBuzzFunds() >= upgradeCost)) {
            amount = 20 * upgradedTower.getType();
            upgradedTower.level++;
            player.setBuzzFunds(player.getBuzzFunds() - upgradeCost);
        }
        upgradedTower.increaseDamage(amount);
    }
}
