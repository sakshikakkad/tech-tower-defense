package com.example.demotest.objects;

public class Shop {

    private Tower selectedTower;

    public Shop() {
        this.selectedTower = null;
    }

    public Tower getSelectedTower() {
        return selectedTower;
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    public void buyTower(Tower tower, Player player, Inventory inventory) {
        if (selectedTower == null) {
            return;
        }

        int startingTotal = player.getBuzzFunds();
        int towerCost = tower.getCost();
        int currentTotal = startingTotal - towerCost;

        if (currentTotal >= 0) {
            player.setBuzzFunds(currentTotal);
            inventory.addTower(tower);
            setSelectedTower(null);
        }
    }
}
