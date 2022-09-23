package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

public class RangeUpgrade extends TowerUpgrade {

    public RangeUpgrade(Tower tower) {

        this.upgradedTower = tower;

        if (player.getDifficulty().equals("Easy")) {
            this.upgradeCost = 5;
        } else if (player.getDifficulty().equals("Normal")) {
            this.upgradeCost = 10;
        } else {
            this.upgradeCost = 15;
        }

        int amount = 0;
        if ((upgradedTower.level < 3) && (player.getBuzzFunds() >= upgradeCost)) {
            amount = 25 * upgradedTower.getType();
            upgradedTower.level++;
            player.setBuzzFunds(player.getBuzzFunds() - upgradeCost);
        }
        upgradedTower.increaseRange(amount);
    }
}
