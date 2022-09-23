package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

public class Shop {

    private Tower selectedTower;
    private int numTowersBought;

    public Shop() {
        this.selectedTower = null;
        this.numTowersBought = 0;
    }

    public Tower getSelectedTower() {
        return selectedTower;
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    public void buyTower(Inventory inventory) {
        if (selectedTower == null) {
            return;
        }

        int towerCost;
        if (selectedTower.getType() == 1) {
            towerCost = Buzz.getPrice();
        } else if (selectedTower.getType() == 2) {
            towerCost = SteamWhistle.getPrice();
        } else {
            towerCost = RamblinWreck.getPrice();
        }

        int currentTotal = player.getBuzzFunds() - towerCost;

        if (currentTotal >= 0) {
            player.setBuzzFunds(currentTotal);
            this.numTowersBought++;
            player.setTowersBought(numTowersBought);
            inventory.addTower(selectedTower);
            setSelectedTower(null);
        }
    }
}
