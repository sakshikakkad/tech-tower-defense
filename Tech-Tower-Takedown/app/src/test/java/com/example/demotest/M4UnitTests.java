package com.example.demotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.demotest.data.Corona;
import com.example.demotest.data.Player;
import com.example.demotest.data.Trolley;
import com.example.demotest.data.Uga;
import com.example.demotest.data.Wave;
import com.example.demotest.data.Waves;
import com.example.demotest.data.Monument;

import org.junit.Test;

public class M4UnitTests {
    /**
     * Unit Test 1
     * Evaluating whether different enemies deal different damage.
     * Three Enemies: FootballPlayer, Corona, Trolley
     * Testing M4 implementation
     *
     * @author Grace Chun
     */
    @Test
    public void checkDamageByEnemy() {

        // EASY LEVEL

        ConfigurationScreen.player = new Player("Player", "Easy");
        // initialize all the enemies
        Uga footballPlayer = new Uga();
        Corona corona = new Corona();
        Trolley trolley = new Trolley();

        // ensure that damage varies by enemy
        assertNotEquals(footballPlayer.getDamage(), corona.getDamage());
        assertNotEquals(footballPlayer.getDamage(), trolley.getDamage());
        assertNotEquals(corona.getDamage(), trolley.getDamage());

        // NORMAL LEVEL

        ConfigurationScreen.player = new Player("Player", "Normal");
        // initialize all the enemies
        footballPlayer = new Uga();
        corona = new Corona();
        trolley = new Trolley();

        // ensure that damage varies by enemy
        assertNotEquals(footballPlayer.getDamage(), corona.getDamage());
        assertNotEquals(footballPlayer.getDamage(), trolley.getDamage());
        assertNotEquals(corona.getDamage(), trolley.getDamage());

        // HARD LEVEL

        ConfigurationScreen.player = new Player("Player", "Hard");
        // initialize all the enemies
        footballPlayer = new Uga();
        corona = new Corona();
        trolley = new Trolley();

        // ensure that damage varies by enemy
        assertNotEquals(footballPlayer.getDamage(), corona.getDamage());
        assertNotEquals(footballPlayer.getDamage(), trolley.getDamage());
        assertNotEquals(corona.getDamage(), trolley.getDamage());
    }

    /**
     * Unit Test 2
     * Evaluating whether the damage for each enemy differs by level.
     * Three Enemies: FootballPlayer, Corona, Trolley
     * Testing M4 implementation
     *
     * @author Grace Chun
     */
    @Test
    public void checkDamageByLevel() {

        // FOOTBALL PLAYERS
        // initialize Football enemies
        ConfigurationScreen.player = new Player("Player", "Easy");
        Uga footballPlayer1 = new Uga();

        ConfigurationScreen.player = new Player("Player", "Normal");
        Uga footballPlayer2 = new Uga();

        ConfigurationScreen.player = new Player("Player", "Hard");
        Uga footballPlayer3 = new Uga();

        // ensure that damage varies by difficulty for FootballPlayer enemies
        assertNotEquals(footballPlayer1.getDamage(), footballPlayer2.getDamage());
        assertNotEquals(footballPlayer2.getDamage(), footballPlayer3.getDamage());
        assertNotEquals(footballPlayer1.getDamage(), footballPlayer3.getDamage());

        // CORONA
        // initialize Corona enemies
        ConfigurationScreen.player = new Player("Player", "Easy");
        Corona corona1 = new Corona();

        ConfigurationScreen.player = new Player("Player", "Normal");
        Corona corona2 = new Corona();

        ConfigurationScreen.player = new Player("Player", "Hard");
        Corona corona3 = new Corona();

        // ensure that damage varies by difficulty for Corona enemies
        assertNotEquals(corona1.getDamage(), corona2.getDamage());
        assertNotEquals(corona2.getDamage(), corona3.getDamage());
        assertNotEquals(corona1.getDamage(), corona3.getDamage());

        // TECH BUSES
        // initialize Trolley enemies
        ConfigurationScreen.player = new Player("Player", "Easy");
        Trolley trolley1 = new Trolley();

        ConfigurationScreen.player = new Player("Player", "Normal");
        Trolley trolley2 = new Trolley();

        ConfigurationScreen.player = new Player("Player", "Hard");
        Trolley trolley3 = new Trolley();

        // ensure that damage varies by difficulty for Trolley enemies
        assertNotEquals(trolley1.getDamage(), trolley2.getDamage());
        assertNotEquals(trolley2.getDamage(), trolley3.getDamage());
        assertNotEquals(trolley1.getDamage(), trolley3.getDamage());
    }

    /**
     * Unit Test 3
     * Verifies that enemies types are unique in health amount
     * Testing M4 implementation
     *
     * @author Sophie Myers
     */
    @Test
    public void checkHealthByEnemyType() {
        // initialize complete enemy type possibilities
        ConfigurationScreen.player = new Player("Player", "Easy");
        Trolley trolleyEasy = new Trolley();
        ConfigurationScreen.player = new Player("Player", "Normal");
        Trolley trolleyNormal = new Trolley();
        ConfigurationScreen.player = new Player("Player", "Hard");
        Trolley trolleyHard = new Trolley();

        ConfigurationScreen.player = new Player("Player", "Easy");
        Uga ugaEasy = new Uga();
        ConfigurationScreen.player = new Player("Player", "Normal");
        Uga ugaNormal = new Uga();
        ConfigurationScreen.player = new Player("Player", "Hard");
        Uga ugaHard = new Uga();

        ConfigurationScreen.player = new Player("Player", "Easy");
        Corona coronaEasy = new Corona();
        ConfigurationScreen.player = new Player("Player", "Normal");
        Corona coronaNormal = new Corona();
        ConfigurationScreen.player = new Player("Player", "Hard");
        Corona coronaHard = new Corona();

        // tests all possible comparisons per difficulty
        assertNotEquals(trolleyEasy.getHealth(), ugaEasy.getHealth());
        assertNotEquals(trolleyEasy.getHealth(), coronaEasy.getHealth());
        assertNotEquals(coronaEasy.getHealth(), ugaEasy.getHealth());

        assertNotEquals(trolleyNormal.getHealth(), ugaNormal.getHealth());
        assertNotEquals(trolleyNormal.getHealth(), coronaNormal.getHealth());
        assertNotEquals(coronaNormal.getHealth(), ugaNormal.getHealth());

        assertNotEquals(trolleyHard.getHealth(), ugaHard.getHealth());
        assertNotEquals(trolleyHard.getHealth(), coronaHard.getHealth());
        assertNotEquals(coronaHard.getHealth(), ugaHard.getHealth());
    }

    /**
     * Unit Test 4
     * Verifies that game ends if waves are over
     * Testing M4 implementation
     *
     * @author Sophie Myers
     */
    @Test
    public void checkNumWaves() {

        ConfigurationScreen.player = new Player("Player", "Easy");
        Waves testWaves1 = new Waves();
        ConfigurationScreen.player = new Player("Player", "Normal");
        Waves testWaves2 = new Waves();
        ConfigurationScreen.player = new Player("Player", "Hard");
        Waves testWaves3 = new Waves();

        // test that num waves are initially > 0
        assertEquals(true, testWaves1.getWavesLeft() > 0);
        assertEquals(true, testWaves2.getWavesLeft() > 0);
        assertEquals(true, testWaves3.getWavesLeft() > 0);

        // test that num waves differs by difficulty
        assertNotEquals(testWaves1.getWavesLeft(), testWaves2.getWavesLeft());
        assertNotEquals(testWaves2.getWavesLeft(), testWaves3.getWavesLeft());
    }

    /**
     * Unit Test 5
     * Verifying that all enemies' speeds change with difficulty -> test no longer applicable
     * Testing M4 implementation
     *
     * @author Connor Jordan
     */
    public void testEnemySpeeds() {
        ConfigurationScreen.player = new Player("Player", "Easy");
        Corona easyP = new Corona();
        Trolley easyB = new Trolley();
        Uga easyF = new Uga();

        ConfigurationScreen.player = new Player("Player", "Normal");
        Corona mediumP = new Corona();
        Trolley mediumB = new Trolley();
        Uga mediumF = new Uga();;

        assertNotEquals(easyP.getSpeed(), mediumP.getSpeed());
        assertNotEquals(easyB.getSpeed(), mediumB.getSpeed());
        assertNotEquals(easyF.getSpeed(), mediumF.getSpeed());
    }

    /**
     * Unit Test 6
     * Verifying that all enemies' health changes with difficulty
     * Testing M4 implementation
     *
     * @author Connor Jordan
     */
    @Test
    public void testEnemyHealth() {
        ConfigurationScreen.player = new Player("Player", "Easy");
        Corona easyP = new Corona();
        Trolley easyB = new Trolley();
        Uga easyF = new Uga();

        ConfigurationScreen.player = new Player("Player", "Normal");
        Corona mediumP = new Corona();
        Trolley mediumB = new Trolley();
        Uga mediumF = new Uga();

        assertNotEquals(easyP.getHealth(), mediumP.getHealth());
        assertNotEquals(easyB.getHealth(), mediumB.getHealth());
        assertNotEquals(easyF.getHealth(), mediumF.getHealth());
    }

    /**
     * Unit Test 7
     * Checks that the number of enemies in a wave vary per difficulty
     * Testing M4 implementation
     *
     * @author Sakshi Kakkad
     */
    @Test
    public void testNumEnemies() {

        ConfigurationScreen.player = new Player("Player", "Easy");
        Wave easyWave = new Wave();

        ConfigurationScreen.player = new Player("Player", "Normal");
        Wave normalWave = new Wave();

        ConfigurationScreen.player = new Player("Player", "Hard");
        Wave hardWave = new Wave();

        // check tech trolley count varies
        assertNotEquals(easyWave.getTrolleyCount(), normalWave.getTrolleyCount());
        assertNotEquals(normalWave.getTrolleyCount(), hardWave.getTrolleyCount());
        assertNotEquals(hardWave.getTrolleyCount(), easyWave.getTrolleyCount());

        // check uga count varies
        assertNotEquals(easyWave.getUgaCount(), normalWave.getUgaCount());
        assertNotEquals(normalWave.getUgaCount(), hardWave.getUgaCount());
        assertNotEquals(hardWave.getUgaCount(), easyWave.getUgaCount());

        // check corona count varies
        assertNotEquals(easyWave.getCoronaCount(), normalWave.getCoronaCount());
        assertNotEquals(normalWave.getCoronaCount(), hardWave.getCoronaCount());
        assertNotEquals(hardWave.getCoronaCount(), easyWave.getCoronaCount());
    }

    /**
     * Unit Test 8
     * Verifies functionality of adding enemies to a wave
     * Testing M4 implementation
     *
     * @author Sakshi Kakkad
     */
    @Test
    public void testAddEnemies() {

        ConfigurationScreen.player = new Player("Player", "Easy");

        Wave wave = new Wave();

        int trolleyCount = wave.getTrolleyCount();
        int ugaCount = wave.getUgaCount();
        int coronaCount = wave.getCoronaCount();

        wave.addEnemies(5, 10, 15);

        // check that original and modified wave have diff enemy counts
        assertNotEquals(trolleyCount, wave.getTrolleyCount());
        assertNotEquals(ugaCount, wave.getUgaCount());
        assertNotEquals(coronaCount, wave.getCoronaCount());

        // check that the wave was modified correctly
        assertEquals(trolleyCount, wave.getTrolleyCount() - 5);
        assertEquals(ugaCount, wave.getUgaCount() - 10);
        assertEquals(coronaCount, wave.getCoronaCount() - 15);
    }
    
    /**
     * Unit Test 9
     * Tests that monument health specifically decreases when enemies attack
     * Testing M4 implementation
     *
     * @author Jenny Liu
     */
    @Test
    public void testMonumentHealthDecreases() {

        ConfigurationScreen.player = new Player("Player", "Easy");
        Corona corona = new Corona();
        Uga uga = new Uga();
        Trolley trolley = new Trolley();
        Monument monument = new Monument();

        // CORONA enemy
        // attacks monument, track monument health before & after
        int cOriginalHealth = monument.getHealth();
        monument.damageMonument(corona.getDamage());
        int cNewHealth = monument.getHealth();
        // check monument health after attack is less than health before
        assertTrue(cNewHealth < cOriginalHealth);

        // UGA enemy
        // attacks monument, track monument health before & after
        int uOriginalHealth = monument.getHealth();
        monument.damageMonument(uga.getDamage());
        int uNewHealth = monument.getHealth();
        // check monument health after attack is less than health before
        assertTrue(uNewHealth < uOriginalHealth);

        // TROLLEY enemy
        // attacks monument, track monument health before & after
        int tOriginalHealth = monument.getHealth();
        monument.damageMonument(trolley.getDamage());
        int tNewHealth = monument.getHealth();
        // check monument health after attack is less than health before
        assertTrue(tNewHealth < tOriginalHealth);
    }

    /**
     * Unit Test 10
     * Tests that there's varying numbers of each type of enemy in a given wave
     * Testing M4 implementation
     *
     * @author Jenny Liu
     */
    @Test
    public void testNumEnemiesByWave() {

        ConfigurationScreen.player = new Player("Player", "Easy");
        Wave waveE = new Wave();

        ConfigurationScreen.player = new Player("Player", "Normal");
        Wave waveN = new Wave();

        ConfigurationScreen.player = new Player("Player", "Hard");
        Wave waveH = new Wave();

        // Easy level
        assertNotEquals(waveE.getTrolleyCount(), waveE.getUgaCount());
        assertNotEquals(waveE.getUgaCount(), waveE.getCoronaCount());
        assertNotEquals(waveE.getCoronaCount(), waveE.getTrolleyCount());

        // Normal level
        assertNotEquals(waveN.getTrolleyCount(), waveN.getUgaCount());
        assertNotEquals(waveN.getUgaCount(), waveN.getCoronaCount());
        assertNotEquals(waveN.getCoronaCount(), waveN.getTrolleyCount());

        // Hard level
        assertNotEquals(waveH.getTrolleyCount(), waveH.getUgaCount());
        assertNotEquals(waveH.getUgaCount(), waveH.getCoronaCount());
        assertNotEquals(waveH.getCoronaCount(), waveH.getTrolleyCount());
    }
}
