package com.example.demotest.data;

//access Player objects across Activities
import java.io.Serializable;

// hides compiler warnings
@SuppressWarnings("serial")
public class Player implements Serializable {

    private String name;
    private String difficulty;
    private int buzzFunds;
    private boolean won;
    private String time; // time elapsed during round
    private int startFunds; // store starting funds
    private int towersBought; // number of towers bought

    public Player(String name, String difficulty) {
        this.name = name;
        this.difficulty = difficulty;
        this.won = false;
        this.time = "00:00";
        this.towersBought = 0;

        if (difficulty.equals("Easy")) {
            this.buzzFunds = this.startFunds = 70;
        } else if (difficulty.equals("Normal")) {
            this.buzzFunds = this.startFunds = 60;
        } else if (difficulty.equals("Hard")) {
            this.buzzFunds = this.startFunds = 50;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTowersBought() {
        return towersBought;
    }

    public void setTowersBought(int towersBought) {
        this.towersBought = towersBought;
    }

    public int getProfit() {
        int profit = this.buzzFunds - this.startFunds;
        if (profit > 0) {
            return profit;
        } else {
            return 0;
        }
    }

    public void increaseBuzzFunds(int amount) {
        this.buzzFunds += amount;
    }
}
