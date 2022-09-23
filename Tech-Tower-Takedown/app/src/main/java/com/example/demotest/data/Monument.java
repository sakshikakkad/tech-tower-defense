package com.example.demotest.data;

//access Player objects across Activities
import static com.example.demotest.ConfigurationScreen.player;

import java.io.Serializable;

// hides compiler warnings
@SuppressWarnings("serial")
public class Monument implements Serializable {

    private int health;
    private int maxHealth;

    public Monument() {
        if (player.getDifficulty().equals("Easy")) {
            this.maxHealth = 200;
        } else if (player.getDifficulty().equals("Normal")) {
            this.maxHealth = 150;
        } else if (player.getDifficulty().equals("Hard")) {
            this.maxHealth = 100;
        }
        this.health = maxHealth;
    }

    public int getHealth() {
        return health;
    }
    
    // enemy damages monument
    public void damageMonument(int damage) {
        if ((this.health - damage) < 0) {
            this.health = 0;
        } else {
            this.health -= damage;
        }
    }
    
    // heal monument when enemies are killed
    public void healMonument(int amount) {
        if ((this.health + amount) <= maxHealth) {
            this.health += amount;
        } else {
            this.health = maxHealth;
        }

    }

    public boolean isDefeated() {

        if (health <= 0) {
            player.setWon(false);
            return true;
        }
        return false;
    }
}
