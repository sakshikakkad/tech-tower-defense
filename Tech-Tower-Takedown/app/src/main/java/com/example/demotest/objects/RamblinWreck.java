package com.example.demotest.objects;

public class RamblinWreck extends Tower {

    private int damage;
    private int range;
    private int cost;
    private int type = 3;

    public RamblinWreck(String difficulty) {

        super(10, 300, 3);

        if (difficulty.equals("Easy")) {
            this.cost = 20;
        } else if (difficulty.equals("Normal")) {
            this.cost = 25;
        } else if (difficulty.equals("Hard")) {
            this.cost = 30;
        }
    }

    public int getCost() {
        return this.cost;
    }

    public int getType() {
        return type;
    }
}
