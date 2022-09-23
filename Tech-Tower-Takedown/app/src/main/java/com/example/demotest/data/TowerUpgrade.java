package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

public abstract class TowerUpgrade extends Tower {

    protected Tower upgradedTower;
    protected int upgradeCost;

    public Tower getUpgradedTower() {
        return upgradedTower;
    }
}
