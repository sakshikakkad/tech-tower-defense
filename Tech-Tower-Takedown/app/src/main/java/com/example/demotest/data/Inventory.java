package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

public class Inventory {
    private Tower selectedTower;
    private int buzzAmount;
    private int whistleAmount;
    private int wreckAmount;

    public Inventory() {
        this.selectedTower = null;

        if (player.getDifficulty() == "Easy") {
            this.buzzAmount = 1;
            this.whistleAmount = this.wreckAmount = 0;
        } else if (player.getDifficulty() == "Normal") {
            this.buzzAmount = this.whistleAmount = 1;
            this.wreckAmount = 0;
        } else {
            this.buzzAmount = this.whistleAmount = 1;
            this.wreckAmount = 0;
        }
    }

    public void place() {
        if (selectedTower != null) {
            // 1 - Buzz, 2 - Steam Whistle, 3 - Ramblin Wreck
            if (selectedTower.getType() == 1 && this.buzzAmount >= 1) {
                this.buzzAmount -= 1;
            } else if (selectedTower.getType() == 2 && this.whistleAmount >= 1) {
                this.whistleAmount -= 1;
            } else if (selectedTower.getType() == 3 && this.wreckAmount >= 1) {
                this.wreckAmount -= 1;
            }
        } else {
            return;
        }
    }

    public void addTower(Tower tower) {
        if (tower.getType() == 1) {
            this.buzzAmount += 1;
        } else if (tower.getType() == 2) {
            this.whistleAmount += 1;
        } else if (tower.getType() == 3) {
            this.wreckAmount += 1;
        }
    }

    public void sellTower() {
        if (selectedTower == null) {
            return;
        } else if (selectedTower.getType() == 1 && this.buzzAmount > 0) {
            this.buzzAmount--;
            player.increaseBuzzFunds(Buzz.getPrice());
        } else if (selectedTower.getType() == 2 && this.whistleAmount > 0) {
            this.whistleAmount--;
            player.increaseBuzzFunds(SteamWhistle.getPrice());
        } else if (selectedTower.getType() == 3 && this.wreckAmount > 0){
            this.wreckAmount--;
            player.increaseBuzzFunds(RamblinWreck.getPrice());
        // necessary to ensure wreck doesn't dip into negative values
        } else {
            return;
        }
    }

    public Tower getSelectedTower() {
        return selectedTower;
    }

    public void setSelectedTower(Tower t) {
        this.selectedTower = t;
    }

    public int getBuzzAmount() {
        return this.buzzAmount;
    }

    public int getWhistleAmount() {
        return this.whistleAmount;
    }

    public int getWreckAmount() {
        return this.wreckAmount;
    }
}
