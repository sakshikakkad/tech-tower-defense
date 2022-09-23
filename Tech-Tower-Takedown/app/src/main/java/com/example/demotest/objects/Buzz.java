package com.example.demotest.objects;

public class Buzz extends Tower {
    private int damage;
    private int range;
    private int cost;
    private int type = 1;

    public Buzz(String difficulty) {

        super(5, 200, 1);

        if (difficulty.equals("Easy")) {
            this.cost = 10;
        } else if (difficulty.equals("Normal")) {
            this.cost = 15;
        } else if (difficulty.equals("Hard")) {
            this.cost = 20;
        }
    }

    public void sting() {
        return;
    }
    public int getCost() {
        return this.cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public int getType() {
        return type;
    }

}
