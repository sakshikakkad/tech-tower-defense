package com.example.demotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.demotest.data.DamageUpgrade;
import com.example.demotest.data.HealthBar;
import com.example.demotest.data.Player;
import com.example.demotest.data.Monument;
import com.example.demotest.data.Shop;
import com.example.demotest.data.Inventory;
import com.example.demotest.data.Buzz;
import com.example.demotest.data.RamblinWreck;
import com.example.demotest.data.SteamWhistle;
import com.example.demotest.data.Tower;
import com.example.demotest.data.TowerUpgrade;
import com.example.demotest.data.Trolley;
import com.example.demotest.data.Uga;
import com.example.demotest.data.Corona;
import com.example.demotest.data.Waves;
import com.example.demotest.data.FinalBoss;
import com.example.demotest.data.Wave;

import org.junit.Test;

public class M6UnitTests {
    /**
     * Unit Test 1
     * Ensures that profit does not dip below zero.
     * Ensures that profit == 0 when currentBalance == startingBalance.
     * Testing M6 implementation
     *
     * @author Grace Chun
     */
    @Test
    public void unitTest1() {
        // initialize everything
        ConfigurationScreen.player = new Player("Grace", "Easy");
        Buzz buzzTower = new Buzz();
        SteamWhistle whistleTower = new SteamWhistle();
        RamblinWreck wreckTower = new RamblinWreck();
        Inventory inventory = new Inventory();
        Shop shop = new Shop();

        // profit = currentBalance - startingBalance
        // profit == 0
        assertEquals(0, ConfigurationScreen.player.getProfit());

        // buy 1 buzzTower, 1 whistleTower
        shop.setSelectedTower(buzzTower);
        shop.buyTower(inventory);
        shop.setSelectedTower(whistleTower);
        shop.buyTower(inventory);

        // set profit == 0 if negative values
        assertEquals(0, ConfigurationScreen.player.getProfit());
    }

    /**
     * Unit Test 2
     * Ensures that finalBoss health values match what was originally assigned.
     * Testing M6 implementation
     *
     * @author Grace Chun
     */
    @Test
    public void unitTest2() {
        // initialize everything
        String name = "Grace";

        // easyLevel
        ConfigurationScreen.player = new Player(name, "Easy");
        FinalBoss easyFinalBoss = new FinalBoss();

        // easyLevel == 1000
        assertEquals(easyFinalBoss.getHealth(), 1000);

        // normalLevel
        ConfigurationScreen.player = new Player(name, "Normal");
        FinalBoss normalFinalBoss = new FinalBoss();

        // normalLevel == 2000
        assertEquals(normalFinalBoss.getHealth(), 2000);

        // hardLevel
        ConfigurationScreen.player = new Player(name, "Hard");
        FinalBoss hardFinalBoss = new FinalBoss();

        // hard level == 3000
        assertEquals(hardFinalBoss.getHealth(), 3000);
    }

    /**
     * Unit Test 3
     * Ensures that the healthbar correctly calculates the percentage of the enemy's health
     * Testing M6 implementation
     *
     * @author Sophie Myers
     */
    @Test
    public void healthBarCalPercentage() {
        Trolley enemy1 = new Trolley();
        Uga enemy2 = new Uga();
        Corona enemy3 = new Corona();
        FinalBoss enemy4 = new FinalBoss();

        HealthBar bar1 = new HealthBar(enemy1);
        HealthBar bar2 = new HealthBar(enemy2);
        HealthBar bar3 = new HealthBar(enemy3);
        HealthBar bar4 = new HealthBar(enemy4);

        assertEquals((int)((enemy1.getHealth() / enemy1.getMaxHealth()) * 10), bar1.calcHealthPerc());
        assertEquals((int)((enemy2.getHealth() / enemy2.getMaxHealth()) * 10), bar2.calcHealthPerc());
        assertEquals((int)((enemy3.getHealth() / enemy3.getMaxHealth()) * 10), bar3.calcHealthPerc());
        assertEquals((int)((enemy4.getHealth() / enemy4.getMaxHealth()) * 10), bar4.calcHealthPerc());

    }

    /**
     * Unit Test 4
     * Ensures that both the healthbar and the enemy can always access each other
     * Testing M6 implementation
     *
     * @author Sophie Myers
     */
    @Test
    public void enemyHealthBarAssociation() {
        Trolley enemy1 = new Trolley();
        Uga enemy2 = new Uga();
        Corona enemy3 = new Corona();
        FinalBoss enemy4 = new FinalBoss();

        HealthBar bar1 = new HealthBar(enemy1);
        HealthBar bar2 = new HealthBar(enemy2);
        HealthBar bar3 = new HealthBar(enemy3);
        HealthBar bar4 = new HealthBar(enemy4);

        assertEquals(bar1, enemy1.getHealthBar());
        assertEquals(bar2, enemy2.getHealthBar());
        assertEquals(bar3, enemy3.getHealthBar());
        assertEquals(bar4, enemy4.getHealthBar());

        assertEquals(enemy1, bar1.getEnemy());
        assertEquals(enemy2, bar2.getEnemy());
        assertEquals(enemy3, bar3.getEnemy());
        assertEquals(enemy4, bar4.getEnemy());
    }

    /**
     * Unit Test 5
     * Tests that towers cannot be upgraded beyond a certain point (3 levels)
     * Testing M6 implementation
     *
     * @author Sakshi Kakkad
     */
    @Test
    public void upgradeMaxLevel() {

        // Initialize player and towers
        ConfigurationScreen.player = new Player ("Sakshi", "Easy");
        ConfigurationScreen.player.setBuzzFunds(10000);
        Tower buzz = new Buzz();
        Tower whistle = new SteamWhistle();
        Tower wreck = new RamblinWreck();

        // get initial stats
        int buzzDamage = buzz.getDamage();
        int whistleDamage = whistle.getDamage();
        int wreckDamage = wreck.getDamage();

        // upgrade towers to level 1 (testing damage)
        TowerUpgrade buzz1 = new DamageUpgrade(buzz);
        TowerUpgrade whistle1 = new DamageUpgrade(whistle);
        TowerUpgrade wreck1 = new DamageUpgrade(wreck);

        assertEquals(true, buzz.getDamage() > buzzDamage);
        assertEquals(true, whistle.getDamage() > whistleDamage);
        assertEquals(true, wreck.getDamage() > wreckDamage);

        buzzDamage = buzz.getDamage();
        whistleDamage = whistle.getDamage();
        wreckDamage = wreck.getDamage();

        // upgrade towers to level 2
        TowerUpgrade buzz2 = new DamageUpgrade(buzz);
        TowerUpgrade whistle2 = new DamageUpgrade(whistle);
        TowerUpgrade wreck2 = new DamageUpgrade(wreck);

        assertEquals(true, buzz.getDamage() > buzzDamage);
        assertEquals(true, whistle.getDamage() > whistleDamage);
        assertEquals(true, wreck.getDamage() > wreckDamage);

        buzzDamage = buzz.getDamage();
        whistleDamage = whistle.getDamage();
        wreckDamage = wreck.getDamage();

        // upgrade towers to level 3
        TowerUpgrade buzz3 = new DamageUpgrade(buzz);
        TowerUpgrade whistle3 = new DamageUpgrade(whistle);
        TowerUpgrade wreck3 = new DamageUpgrade(wreck);

        assertEquals(true, buzz.getDamage() > buzzDamage);
        assertEquals(true, whistle.getDamage() > whistleDamage);
        assertEquals(true, wreck.getDamage() > wreckDamage);

        buzzDamage = buzz.getDamage();
        whistleDamage = whistle.getDamage();
        wreckDamage = wreck.getDamage();

        int balance = ConfigurationScreen.player.getBuzzFunds();

        // check that max level is reached and try to upgrade
        assertEquals(3, buzz.getLevel());
        TowerUpgrade buzz4 = new DamageUpgrade(buzz);
        TowerUpgrade whistle4 = new DamageUpgrade(whistle);
        TowerUpgrade wreck4 = new DamageUpgrade(wreck);

        assertEquals(true, buzz.getDamage() == buzzDamage);
        assertEquals(true, whistle.getDamage() == whistleDamage);
        assertEquals(true, wreck.getDamage() == wreckDamage);
        assertEquals(true, ConfigurationScreen.player.getBuzzFunds() == balance);

    }

    /**
     * Unit Test 6
     * Tests that players cannot upgrade towers without sufficient funds
     * Testing M6 implementation
     *
     * @author Sakshi Kakkad
     */
    @Test
    public void upgradeInsufficientBalance() {

        // Initialize player and towers
        ConfigurationScreen.player = new Player ("Sakshi", "Easy");
        ConfigurationScreen.player.setBuzzFunds(0);
        Tower buzz = new Buzz();
        Tower whistle = new SteamWhistle();
        Tower wreck = new RamblinWreck();

        // get initial stats
        int buzzDamage = buzz.getDamage();
        int whistleDamage = whistle.getDamage();
        int wreckDamage = wreck.getDamage();
        int balance = ConfigurationScreen.player.getBuzzFunds();

        // try to upgrade towers
        TowerUpgrade buzz1 = new DamageUpgrade(buzz);
        TowerUpgrade whistle1 = new DamageUpgrade(whistle);
        TowerUpgrade wreck1 = new DamageUpgrade(wreck);

        assertEquals(true, buzz.getDamage() == buzzDamage);
        assertEquals(true, whistle.getDamage() == whistleDamage);
        assertEquals(true, wreck.getDamage() == wreckDamage);
        assertEquals(true, ConfigurationScreen.player.getBuzzFunds() == balance);

    }

    /**
     * Unit Test 7
     * Tests that FinalBoss has varying damage depending on game difficulty
     * Testing M6 implementation
     *
     * @author Jenny Liu
     */
    @Test
    public void unitTest7() {
        ConfigurationScreen.player = new Player("player", "Easy");
        FinalBoss finalBossE = new FinalBoss();
        ConfigurationScreen.player = new Player("player", "Normal");
        FinalBoss finalBossN = new FinalBoss();
        ConfigurationScreen.player = new Player("player", "Hard");
        FinalBoss finalBossH = new FinalBoss();
        assertNotEquals(finalBossE.getDamage(), finalBossN.getDamage());
        assertNotEquals(finalBossN.getDamage(), finalBossH.getDamage());
        assertNotEquals(finalBossH.getDamage(), finalBossE.getDamage());
    }

    /**
     * Unit Test 8
     * Tests that FinalBoss has varying reward depending on game difficulty
     * Testing M6 implementation
     *
     * @author Jenny Liu
     */
    @Test
    public void unitTest8() {
        ConfigurationScreen.player = new Player("player", "Easy");
        FinalBoss finalBossE = new FinalBoss();
        ConfigurationScreen.player = new Player("player", "Normal");
        FinalBoss finalBossN = new FinalBoss();
        ConfigurationScreen.player = new Player("player", "Hard");
        FinalBoss finalBossH = new FinalBoss();
        assertNotEquals(finalBossE.getReward(), finalBossN.getReward());
        assertNotEquals(finalBossN.getReward(), finalBossH.getReward());
        assertNotEquals(finalBossH.getReward(), finalBossE.getReward());
    }

    /**
     * Unit Test 9
     * Testing that createBossWave method at different difficulties changes boss health accordingly
     * Testing M6 implementation
     *
     * @author Connor Jordan
     */
    @Test
    public void bossWaveHealthVariation() {

        // create easy boss wave
        ConfigurationScreen.player = new Player("player", "Easy");
        Waves waves = new Waves();
        waves.createBossWave();

        //create normal boss wave
        ConfigurationScreen.player = new Player("player", "Normal");
        Waves waves2 = new Waves();
        waves2.createBossWave();

        int boss1health = waves.getCurrentWave().getWave().get(0).getHealth();
        int boss2health = waves2.getCurrentWave().getWave().get(0).getHealth();

        //compare boss healths
        assertNotEquals(boss1health, boss2health);
    }

    /**
     * Unit Test 10
     * Testing that a wave of difficulty "Boss" only actually creates a enemy list of size 1 when createBossWave is called
     * Testing M6 implementation
     *
     * @author Connor Jordan
     */
    @Test
    public void emptyListWhenMethodNotCalled() {

        ConfigurationScreen.player = new Player("player", "Easy");

        //create boss wave without calling createBossWave method
        Wave fakeBossWave = new Wave();
        int fakeSize = fakeBossWave.getWave().size();

        //check that size != 1
        assertNotEquals(1, fakeSize);

        //create boss wave with calling createBossWave method
        Waves waves = new Waves();
        waves.createBossWave();
        int realSize = waves.getCurrentWave().getWave().size();

        //check that size = 1
        assertEquals(1, realSize);

        //check sizes aren't equal
        assertNotEquals(fakeSize, realSize);
    }
}
