package com.example.demotest.data;

import static com.example.demotest.ConfigurationScreen.player;

public class SteamWhistle extends Tower {

    private static int price;

    public SteamWhistle() {

        damage = 10;
        range = 200;
        type = 2;

        switch (player.getDifficulty()) {
            case "Easy":
                price = 40;
                break;
            case "Normal":
                price = 60;
                break;
            case "Hard":
                price = 80;
                break;
        }
    }

    public static int getPrice() {
        return price;
    }
}
