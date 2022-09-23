package com.example.demotest.objects;

//access Player objects across Activities
import java.io.Serializable;

// hides compiler warnings
@SuppressWarnings("serial")
public class Player implements Serializable {

    private String name;
    private String difficulty;
    private int buzzFunds;
    private boolean won;

    public Player(String name, String difficulty) {
        this.name = name;
        this.difficulty = difficulty;
        this.won = false;

        if (difficulty.equals("Easy")) {
            this.buzzFunds = 100;
        } else if (difficulty.equals("Normal")) {
            this.buzzFunds = 50;
        } else if (difficulty.equals("Hard")) {
            this.buzzFunds = 30;
        }
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getBuzzFunds() {
        return buzzFunds;
    }

    public void setBuzzFunds(int buzzFunds) {
        this.buzzFunds = buzzFunds;
    }

    public boolean getWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public void increaseBuzzFunds(int amount) {
        this.buzzFunds += amount;
    }
}
