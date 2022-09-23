package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

public class Buzz extends Tower {

    private static int price;

    public Buzz() {

        damage = 5;
        range = 150;
        type = 1;

        switch (player.getDifficulty()) {
            case "Easy":
                price = 20;
                break;
            case "Normal":
                price = 30;
                break;
            case "Hard":
                price = 40;
                break;
        }
    }

    public static int getPrice() {
        return price;
    }
}
