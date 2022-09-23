package com.example.demotest.data;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demotest.GameScreen;
import com.example.demotest.R;

public class HealthBar extends AppCompatActivity {
    ImageView healthBar;
    Enemy enemy;

    public HealthBar(Enemy enemy, GameScreen gameScreen) {
        this.healthBar = new ImageView(gameScreen);
        healthBar.setBackgroundResource(R.drawable.health100);
        this.enemy = enemy;
    }

    public HealthBar(Enemy enemy) {
        this.enemy = enemy;
        this.enemy.setHealthBar(this);
    }

    public void draw(int percent) {
        switch (percent) {
            case 10:
                healthBar.setBackgroundResource(R.drawable.health100);
                break;
            case 9:
                healthBar.setBackgroundResource(R.drawable.health90);
                break;
            case 8:
                healthBar.setBackgroundResource(R.drawable.health80);
                break;
            case 7:
                healthBar.setBackgroundResource(R.drawable.health70);
                break;
            case 6:
                healthBar.setBackgroundResource(R.drawable.health60);
                break;
            case 5:
                healthBar.setBackgroundResource(R.drawable.health50);
                break;
            case 4:
                healthBar.setBackgroundResource(R.drawable.health40);
                break;
            case 3:
                healthBar.setBackgroundResource(R.drawable.health30);
                break;
            case 2:
                healthBar.setBackgroundResource(R.drawable.health20);
                break;
            default:
                healthBar.setBackgroundResource(R.drawable.health10);
                break;

        }
    }
    public int calcHealthPerc() {
        float percent = this.enemy.getHealth() / this.enemy.getMaxHealth();
        return (int)(percent * 10);
    }
    public Enemy getEnemy() {
        return enemy;
    }
    public ImageView getHealthBar() {
        return healthBar;
    }
}
