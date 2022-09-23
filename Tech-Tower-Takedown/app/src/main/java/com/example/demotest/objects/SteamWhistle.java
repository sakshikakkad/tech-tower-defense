package com.example.demotest.objects;

public class SteamWhistle extends Tower {
    private int damage;
    private int range;
    private int cost;
    private int type = 2;

    public SteamWhistle(String difficulty) {

        super(10, 250, 2);

        if (difficulty.equals("Easy")) {
            this.cost = 15;
        } else if (difficulty.equals("Normal")) {
            this.cost = 20;
        } else if (difficulty.equals("Hard")) {
            this.cost = 25;
        }
    }

    public int getCost() {
        return this.cost;
    }
    public int getType() {
        return type;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public void steam() {
        return;
    }
}
