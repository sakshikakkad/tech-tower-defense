package com.example.demotest.objects;

public class Inventory {
    private Tower selectedTower;
    private int buzzAmount;
    private int whistleAmount;
    private int wreckAmount;

    public Inventory() {
        this.selectedTower = null;
        this.buzzAmount = 0;
        this.whistleAmount = 0;
        this.wreckAmount = 0;
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

    public int sellTower() {
        if (selectedTower == null) {
            return 0;
        } else if (selectedTower.getType() == 1 && this.buzzAmount > 0) {
            this.buzzAmount--;
            return selectedTower.getCost();
        } else if (selectedTower.getType() == 2 && this.whistleAmount > 0) {
            this.whistleAmount--;
            return selectedTower.getCost();
        } else if (selectedTower.getType() == 3 && this.wreckAmount > 0) {
            this.wreckAmount--;
            return selectedTower.getCost();
        } else {
            return 0;
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
