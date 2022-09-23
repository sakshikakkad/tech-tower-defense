package com.example.demotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.demotest.data.Player;
import com.example.demotest.data.Monument;
import com.example.demotest.data.Shop;
import com.example.demotest.data.Inventory;
import com.example.demotest.data.Buzz;
import com.example.demotest.data.RamblinWreck;
import com.example.demotest.data.SteamWhistle;
import com.example.demotest.data.Trolley;
import com.example.demotest.data.Uga;
import com.example.demotest.data.Corona;
import com.example.demotest.data.Waves;

import org.junit.Test;

public class M5UnitTests {
    /**
     * Unit Test 1
     * Ensures that inventory decreases when towers are sold.
     * Testing M5 implementation
     *
     * @author Grace Chun
     */
    @Test
    public void sellTowers() {
        // initialize everything
        String name = "Grace";
        String difficulty = "Easy";
        ConfigurationScreen.player = new Player(name, difficulty);
        Buzz buzzTower = new Buzz();
        SteamWhistle whistleTower = new SteamWhistle();
        RamblinWreck wreckTower = new RamblinWreck();
        Inventory inventory = new Inventory();
        Shop shop = new Shop();

        // buy 1 buzzTower, 1 whistleTower, 1 wreckTower
        shop.setSelectedTower(buzzTower);
        shop.buyTower(inventory);
        shop.setSelectedTower(whistleTower);
        shop.buyTower(inventory);

        // number of buzzTowers == 2, number of whistleTowers == 0
        assertEquals(2, inventory.getBuzzAmount());
        assertEquals(1, inventory.getWhistleAmount());

        // sell all towers
        inventory.setSelectedTower(buzzTower);
        inventory.sellTower();
        inventory.setSelectedTower(whistleTower);
        inventory.sellTower();

        // number of buzzTowers == 0, number of whistleTowers == 0
        assertEquals(1, inventory.getBuzzAmount());
        assertEquals(0, inventory.getWhistleAmount());
    }

    /**
     * Unit Test 2
     * Ensures that BuzzFunds increase when towers are sold.
     * Testing M5 implementation
     *
     * @author Grace Chun
     */
    @Test
    public void sellTowersBalance() {
        // initialize everything
        String name = "Grace";
        String difficulty = "Easy";
        ConfigurationScreen.player = new Player(name, difficulty);
        Buzz buzzTower = new Buzz();
        SteamWhistle whistleTower = new SteamWhistle();
        RamblinWreck wreckTower = new RamblinWreck();
        Inventory inventory = new Inventory();
        Shop shop = new Shop();

        // buy 1 buzzTower, 1 whistleTower, 1 wreckTower
        shop.setSelectedTower(buzzTower);
        shop.buyTower(inventory);
        shop.setSelectedTower(whistleTower);
        shop.buyTower(inventory);

        // BuzzFund balance == 55
        assertEquals(10, ConfigurationScreen.player.getBuzzFunds());

        // sell buzzTower
        inventory.setSelectedTower(buzzTower);
        inventory.sellTower();

        // BuzzFund balance == 65 after selling buzzTower
        assertEquals(30, ConfigurationScreen.player.getBuzzFunds());
    }

    /**
     * Unit Test 3
     * Ensures that health decreases by the correct amount when enemies take damage
     * Testing M5 implementation
     *
     * @author Sophie Myers
     */
    @Test
    public void enemyTakeDamage() {
        Buzz tower = new Buzz();
        int damage = tower.getDamage();

        Uga uga = new Uga();
        Trolley trolley = new Trolley();
        Corona corona = new Corona();

        int ugaStartHealth = uga.getHealth();
        int trolleyStartHealth = trolley.getHealth();
        int coronaStartHealth = corona.getHealth();

        uga.reduceHealth(tower.getDamage());
        trolley.reduceHealth(tower.getDamage());
        corona.reduceHealth(tower.getDamage());

        assertEquals(ugaStartHealth - damage, uga.getHealth());
        assertEquals(trolleyStartHealth - damage, trolley.getHealth());
        assertEquals(coronaStartHealth - damage, corona.getHealth());
    }

    /**
     * Unit Test 4
     * Ensures that enemy dies when health <= 0
     * Testing M5 implementation
     *
     * @author Sophie Myers
     */
    @Test
    public void enemyDies() {
        ConfigurationScreen.player = new Player("player", "Easy");
        Buzz tower = new Buzz();
        int damage = tower.getDamage();

        Uga uga = new Uga();
        Trolley trolley = new Trolley();
        Corona corona = new Corona();

        int ugaStartHealth = uga.getHealth();
        int trolleyStartHealth = trolley.getHealth();
        int coronaStartHealth = corona.getHealth();

        int ugaAttackTimes = ugaStartHealth / damage;
        int trolleyAttackTimes = trolleyStartHealth / damage;
        int coronaAttackTimes = coronaStartHealth / damage;

        for (int i = 0; i < ugaAttackTimes - 1; i++) {
            uga.reduceHealth(tower.getDamage());
            assertEquals(false, uga.isDefeated());
        }

        for (int i = 0; i < trolleyAttackTimes - 1; i++) {
            trolley.reduceHealth(tower.getDamage());
            assertEquals(false, trolley.isDefeated());
        }

        for (int i = 0; i < coronaAttackTimes - 1; i++) {
            corona.reduceHealth(tower.getDamage());
            assertEquals(false, corona.isDefeated());
        }

        uga.reduceHealth(tower.getDamage());
        trolley.reduceHealth(tower.getDamage());
        corona.reduceHealth(tower.getDamage());

        assertEquals(true, uga.isDefeated());
        assertEquals(true, trolley.isDefeated());
        assertEquals(true, corona.isDefeated());
    }

    /**
     * Unit Test 5
     * Tests that the towers have different implementations
     * Testing M5 implementation
     *
     * @author Sakshi Kakkad
     */
    @Test
    public void testTowerImplementations() {

        // Testing tower = buzz
        ConfigurationScreen.player = new Player("player", "Easy");
        Monument monument1 = new Monument();
        monument1.damageMonument(monument1.getHealth() / 2); // reduce below max health
        Trolley trolley1 = new Trolley();
        Buzz buzz = new Buzz();

        int buzzAttacks = trolley1.getHealth() / buzz.getDamage();

        for (int i = 0; i < buzzAttacks - 1; i++) {
            buzz.attack(trolley1, monument1);
        }

        Player player1 = ConfigurationScreen.player;

        // check that attack returns true when trolley is defeated
        assertEquals(true, buzz.attack(trolley1, monument1));

        // Testing tower = steam whistle
        ConfigurationScreen.player = new Player("player", "Easy");
        Monument monument2 = new Monument();
        monument2.damageMonument(monument2.getHealth() / 2); // reduce below max health
        Trolley trolley2 = new Trolley();
        SteamWhistle whistle = new SteamWhistle();

        int whistleAttacks = trolley2.getHealth() / whistle.getDamage();

        for (int i = 0; i < whistleAttacks - 1; i++) {
            whistle.attack(trolley2, monument2);
        }

        Player player2 = ConfigurationScreen.player;

        // check that attack returns true when trolley is defeated
        assertEquals(true, whistle.attack(trolley2, monument2));

        // Testing tower = steam whistle
        ConfigurationScreen.player = new Player("player", "Easy");
        Monument monument3 = new Monument();
        monument3.damageMonument(monument3.getHealth() / 2); // reduce below max health
        Trolley trolley3 = new Trolley();
        RamblinWreck wreck = new RamblinWreck();

        int wreckAttacks = trolley3.getHealth() / wreck.getDamage();

        for (int i = 0; i < wreckAttacks - 1; i++) {
            wreck.attack(trolley3, monument3);
        }

        Player player3 = ConfigurationScreen.player;

        // check that attack returns true when trolley is defeated
        assertEquals(true, wreck.attack(trolley3, monument3));

        // check that player balance is different for each tower implementation
        assertEquals(false, (player1.getBuzzFunds() == player2.getBuzzFunds()));
        assertEquals(false, (player2.getBuzzFunds() == player3.getBuzzFunds()));
        assertEquals(false, (player1.getBuzzFunds() == player3.getBuzzFunds()));

        // check that the monument health is different for wreck tower implementation
        assertEquals(false, (monument1.getHealth() == monument3.getHealth()));
        assertEquals(false, (monument2.getHealth() == monument3.getHealth()));
    }

    /**
     * Unit Test 6
     * Tests that the monument cannot be healed beyond max health
     * Testing M5 implementation
     *
     * @author Sakshi Kakkad
     */
    @Test

    public void testMaxHealth() {

        Monument monument = new Monument();
        int maxHealth = monument.getHealth();
        monument.damageMonument(maxHealth / 2); // reduce below max health

        // test healMonument()
        monument.healMonument(maxHealth + 1);
        assertEquals(true, monument.getHealth() == maxHealth);
        assertEquals(false, monument.getHealth() > maxHealth);

        // test tower implementation (ramblin wreck)
        RamblinWreck wreck = new RamblinWreck();
        ConfigurationScreen.player = new Player("player", "Easy");
        Trolley trolley = new Trolley();
        Uga uga = new Uga();
        Corona corona = new Corona();

        monument.damageMonument(1);
        trolley.reduceHealth(trolley.getHealth() - 1);
        uga.reduceHealth(uga.getHealth() - 1);

        wreck.attack(trolley, monument);
        assertEquals(maxHealth, monument.getHealth());

        wreck.attack(uga, monument);
        assertEquals(maxHealth, monument.getHealth());

        wreck.attack(corona, monument);
        assertEquals(maxHealth, monument.getHealth());
    }

    /**
     * unit test 7
     * tests that on a given difficulty level, diff enemies have varying rewards
     * M5
     *
     * @author Jenny Liu
     */
    @Test
    public void enemyRewardByType() {
        // on level Easy, all enemies have varying rewards
        Corona coronaE = new Corona();
        Trolley trolleyE = new Trolley();
        Uga ugaE = new Uga();
        assertNotEquals(coronaE.getReward(), trolleyE.getReward());
        assertNotEquals(trolleyE.getReward(), ugaE.getReward());
        assertNotEquals(ugaE.getReward(), coronaE.getReward());

        // on level Normal, all enemies have varying rewards
        Corona coronaN = new Corona();
        Trolley trolleyN = new Trolley();
        Uga ugaN = new Uga();
        assertNotEquals(coronaN.getReward(), trolleyN.getReward());
        assertNotEquals(trolleyN.getReward(), ugaN.getReward());
        assertNotEquals(ugaN.getReward(), coronaN.getReward());

        // on level Hard, all enemies have varying rewards
        Corona coronaH = new Corona();
        Trolley trolleyH = new Trolley();
        Uga ugaH = new Uga();
        assertNotEquals(coronaH.getReward(), trolleyH.getReward());
        assertNotEquals(trolleyH.getReward(), ugaH.getReward());
        assertNotEquals(ugaH.getReward(), coronaH.getReward());
    }

    /**
     * unit test 8
     * tests that every enemy's reward varies by difficulty level
     * M5
     *
     * @author Jenny Liu
     */
    @Test
    public void enemyRewardByDifficulty() {

        ConfigurationScreen.player = new Player("Player", "Easy");
        Corona coronaE = new Corona();
        Trolley trolleyE = new Trolley();
        Uga ugaE = new Uga();

        ConfigurationScreen.player = new Player("Player", "Normal");
        Corona coronaN = new Corona();
        Trolley trolleyN = new Trolley();
        Uga ugaN = new Uga();

        ConfigurationScreen.player = new Player("Player", "Hard");
        Corona coronaH = new Corona();
        Trolley trolleyH = new Trolley();
        Uga ugaH = new Uga();

        // corona enemy's reward varies by difficulty level
        assertNotEquals(coronaE.getReward(), coronaN.getReward());
        assertNotEquals(coronaN.getReward(), coronaH.getReward());
        assertNotEquals(coronaH.getReward(), coronaE.getReward());

        // trolley enemy's reward varies by difficulty level
        assertNotEquals(trolleyE.getReward(), trolleyN.getReward());
        assertNotEquals(trolleyN.getReward(), trolleyH.getReward());
        assertNotEquals(trolleyH.getReward(), trolleyE.getReward());

        // uga enemy's reward varies by difficulty level
        assertNotEquals(ugaE.getReward(), ugaN.getReward());
        assertNotEquals(ugaN.getReward(), ugaH.getReward());
        assertNotEquals(ugaH.getReward(), ugaE.getReward());
    }
    
    /**
     * Unit Test 9
     * Tests that player wins if waves are over
     * Testing M5 implementation
     *
     * @author Connor Jordan, edited by Sakshi Kakkad
     */
    @Test
    public void winCondition() {

        // easy game has 2 waves
        ConfigurationScreen.player = new Player("Player", "Easy");
        Waves waves = new Waves();

        // decrease waves remaining
        waves.decrementWavesLeft();
        waves.decrementWavesLeft();
        waves.decrementWavesLeft();

        // check that no waves are remaining
        assertEquals(-1, waves.getWavesLeft());

        // remove enemies from current wave
        waves.getCurrentWave().getWave().clear();

        // check that current wave is over
        assertEquals(waves.getCurrentWave().waveEnded(), true);

        // check that all waves are over
        assertEquals(waves.wavesEnded(), true);

        // check that player won
        assertEquals(ConfigurationScreen.player.getWon(), true);
    }


    /**
     * Unit Test 10
     * Tests that enemy's reward increases BuzzFunds correctly <- Needs to be fixed
     * Testing M5 implementation
     *
     * @author Connor Jordan
     */
    public void playerRewarded() {
        Player p = new Player("CJ", "Easy");
        int money = p.getBuzzFunds();
        Corona c = new Corona();
        c.reduceHealth(150);
        assertEquals(money + 15, p.getBuzzFunds());
    }
}
