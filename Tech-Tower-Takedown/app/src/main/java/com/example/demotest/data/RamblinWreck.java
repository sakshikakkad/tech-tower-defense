package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

public class RamblinWreck extends Tower {

    private static int price;
    public RamblinWreck() {

        damage = 10;
        range = 250;
        type = 3;

        switch (player.getDifficulty()) {
            case "Easy":
                price = 50;
                break;
            case "Normal":
                price = 75;
                break;
            case "Hard":
                price = 100;
                break;
        }
    }

    public static int getPrice() {
        return price;
    }
}
