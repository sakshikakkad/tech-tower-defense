package com.example.demotest;

import static org.junit.Assert.assertEquals;

import com.example.demotest.data.Monument;
import com.example.demotest.data.Player;

import org.junit.Test;

public class M2UnitTests {

    /**
     * tests that initial buzzfunds are different based on difficulty level
     * M2
     * @author Jenny Liu
     */
    @Test
    public void initialBuzzfunds() {

        // tests that balance = 100 on Easy mode
        ConfigurationScreen.player = new Player("Jenny", "Easy");
        assertEquals(100, ConfigurationScreen.player.getBuzzFunds());

        // tests that balance = 50 on Normal mode
        ConfigurationScreen.player = new Player("Jenny", "Normal");
        assertEquals(50, ConfigurationScreen.player.getBuzzFunds());

        // tests that balance = 25 on Hard mode
        ConfigurationScreen.player = new Player("Jenny", "Hard");
        assertEquals(25, ConfigurationScreen.player.getBuzzFunds());

    }

    /**
     * tests that initial health is different based on difficulty level
     * M2
     * @author Jenny Liu
     */
    @Test
    public void initialHealth() {

        // tests that health = 100 on Easy mode
        ConfigurationScreen.player = new Player("Player", "Easy");
        Monument monument1 = new Monument();
        assertEquals(100, monument1.getHealth());

        // tests that health = 75 on Normal mode
        ConfigurationScreen.player = new Player("Player", "Normal");
        Monument monument2 = new Monument();
        assertEquals(75, monument2.getHealth());

        // tests that health = 50 on Hard mode
        ConfigurationScreen.player = new Player("Player", "Hard");
        Monument monument3 = new Monument();
        assertEquals(50, monument3.getHealth());

    }
}
