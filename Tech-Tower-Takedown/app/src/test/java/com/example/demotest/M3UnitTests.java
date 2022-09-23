package com.example.demotest;

import static org.junit.Assert.assertEquals;

import com.example.demotest.data.Buzz;
import com.example.demotest.data.Inventory;
import com.example.demotest.data.Player;
import com.example.demotest.data.RamblinWreck;
import com.example.demotest.data.Shop;
import com.example.demotest.data.SteamWhistle;
import com.example.demotest.data.Tower;

import org.junit.Test;

public class M3UnitTests {

    /**
     * Tests when player tries to buy towers and has insufficient funds.
     * Ensures that the inventory does not update if player cannot afford tower.
     * Testing M3 implementation
     *
     * @author Grace Chun
     */
    @Test
    public void checkInventory() {
        // initialize everything
        String name = "Grace";
        String difficulty = "Normal";
        ConfigurationScreen.player = new Player(name, difficulty);
        Buzz buzzTower = new Buzz();
        SteamWhistle whistleTower = new SteamWhistle();
        RamblinWreck wreckTower = new RamblinWreck();
        Inventory inventory = new Inventory();
        Shop shop = new Shop();

        // buy 2 buzzTowers
        shop.setSelectedTower(buzzTower);
        shop.buyTower(inventory);

        // number of buzzTowers should be 2 (starts with 1 initially)
        assertEquals(2, inventory.getBuzzAmount());

        // buy 1 whistleTower
        shop.setSelectedTower(whistleTower);
        shop.buyTower(inventory);

        // number of whistleTowers should be 1 (starts with 1 initially)
        assertEquals(1, inventory.getWhistleAmount());

        // buy 3 wreckTowers
        shop.setSelectedTower(wreckTower);
        shop.buyTower(inventory);
        shop.buyTower(inventory);
        shop.buyTower(inventory);

        // number of wreckTowers should be 0 because we do not have enough funds
        assertEquals(0, inventory.getWreckAmount());
    }

    /**
     * Tests when player tries to buy towers and has insufficient funds.
     * Ensures that BuzzFund balance does not decrease if insufficient funds.
     * Testing M3 implementation
     *
     * @author Grace Chun
     */
    @Test
    public void checkBalance() {
        // initialize everything
        String name = "Grace";
        String difficulty = "Easy";
        ConfigurationScreen.player = new Player(name, difficulty);
        Buzz buzzTower = new Buzz();
        SteamWhistle whistleTower = new SteamWhistle();
        RamblinWreck wreckTower = new RamblinWreck();
        Inventory inventory = new Inventory();
        Shop shop = new Shop();

        // buy 1 buzzTowers
        shop.setSelectedTower(buzzTower);
        shop.buyTower(inventory);

        // buzzFund balance should be 50 after buying 1 buzzTower
        assertEquals(50, ConfigurationScreen.player.getBuzzFunds());

        // buy 1 whistleTowers
        shop.setSelectedTower(whistleTower);
        shop.buyTower(inventory);

        // buzzFund balance should be 10 after buying 3 whistleTowers
        assertEquals(10, ConfigurationScreen.player.getBuzzFunds());

        // buy 1 wreckTower
        shop.setSelectedTower(wreckTower);
        shop.buyTower(inventory);

        // cannot buy wreckTower because insufficient funds
        // buzzFund balance should remain 10 and not dip below 0
        // 10 - 50 = -40 (cannot afford)
        assertEquals(10, ConfigurationScreen.player.getBuzzFunds());

        // buy 2 buzzTowers
        shop.setSelectedTower(buzzTower);
        shop.buyTower(inventory);
        shop.setSelectedTower(buzzTower);
        shop.buyTower(inventory);

        // buzzFund balance should remain 10 and not dip below 0
        // insufficient balance
        assertEquals(10, ConfigurationScreen.player.getBuzzFunds());

        // buy 3 whistleTowers
        shop.setSelectedTower(whistleTower);
        shop.buyTower(inventory);
        shop.setSelectedTower(whistleTower);
        shop.buyTower(inventory);
        shop.setSelectedTower(whistleTower);
        shop.buyTower(inventory);

        // buzzFund balance should remain 10 and not dip below 0
        // insufficient balance
        assertEquals(10, ConfigurationScreen.player.getBuzzFunds());
    }

    /**
     * Tests that a tower must be selected in order to buy from the shop.
     * Ensures that inventory remains the same if player buys without selecting tower.
     * Testing M3 implementation
     *
     * @author Sakshi Kakkad
     */
    @Test
    public void testBuyInventory() {
        // initialize everything
        ConfigurationScreen.player = new Player("Sakshi", "Easy");
        Inventory inventory = new Inventory();
        Shop shop = new Shop();

        // ensure that no tower is selected initially
        assertEquals(null, shop.getSelectedTower());

        // player attempts to buy tower without selecting
        shop.buyTower(inventory);
        shop.buyTower(inventory);
        shop.buyTower(inventory);

        // ensure inventory remains the same
        assertEquals(1, inventory.getBuzzAmount());
        assertEquals(0, inventory.getWhistleAmount());
        assertEquals(0, inventory.getWreckAmount());
    }

    /**
     * Tests that a tower must be selected in order to buy from the shop.
     * Ensures that balance remains the same if player buys without selecting tower.
     * Testing M3 implementation
     *
     * @author Sakshi Kakkad
     */
    @Test
    public void testBuyBalance() {
        // initialize everything
        ConfigurationScreen.player = new Player("Sakshi", "Easy");
        Inventory inventory = new Inventory();
        Shop shop = new Shop();

        // get initial player balance
        int balance = ConfigurationScreen.player.getBuzzFunds();

        // ensure that no tower is selected initially
        assertEquals(null, shop.getSelectedTower());

        // player attempts to buy tower without selecting
        shop.buyTower(inventory);
        shop.buyTower(inventory);
        shop.buyTower(inventory);

        // ensure balance remains the same
        assertEquals(balance, ConfigurationScreen.player.getBuzzFunds());
    }

    /**
     * A method to test when the player places a tower.
     * Ensures that the number of towers in the inventory decreases when a tower is placed.
     * Testing M3 implemenation
     *
     * @author Connor Jordan
     */
    @Test
    public void testNumTowersReduced() {

        ConfigurationScreen.player = new Player("Player", "Easy");

        //create game inventory
        Inventory inventory = new Inventory();
        //create shop
        Shop shop = new Shop();
        //create towers
        Tower buzzTower = new Buzz();
        Tower whistleTower = new SteamWhistle();
        Tower wreckTower = new RamblinWreck();
        //buy each tower
        shop.setSelectedTower(buzzTower);
        shop.buyTower(inventory);
        shop.setSelectedTower(whistleTower);
        shop.buyTower(inventory);
        shop.setSelectedTower(wreckTower);
        shop.buyTower(inventory);
        //make sure amount of each tower increased by 1
        assertEquals(2, inventory.getBuzzAmount());
        assertEquals(1, inventory.getWhistleAmount());
        assertEquals(0, inventory.getWreckAmount());
        //select buzzTower in inventory
        inventory.setSelectedTower(buzzTower);
        //simulate placing the selectedTower
        inventory.place();
        //make sure the amount of the selectedTower decreased by 1
        assertEquals(1, inventory.getBuzzAmount());
        assertEquals(1, inventory.getWhistleAmount());
        assertEquals(0, inventory.getWreckAmount());
    }

    /**
     * A method to test when the player attempts to place a tower.
     * Ensures that the number of towers in the inventory stays the same when no tower is selected.
     * Testing M3 implementation
     *
     * @author Connor Jordan
     */
    @Test
    public void testNumTowersUnchanged() {
        ConfigurationScreen.player = new Player("Connor", "Easy");
        //create game inventory
        Inventory inventory = new Inventory();
        //create shop
        Shop shop = new Shop();
        //create towers
        Tower buzzTower = new Buzz();
        Tower whistleTower = new SteamWhistle();
        Tower wreckTower = new RamblinWreck();
        //buy each tower
        shop.setSelectedTower(buzzTower);
        shop.buyTower(inventory);
        shop.setSelectedTower(whistleTower);
        shop.buyTower(inventory);
        shop.setSelectedTower(wreckTower);
        shop.buyTower(inventory);
        //make sure amount of each tower increased by 1
        assertEquals(2, inventory.getBuzzAmount());
        assertEquals(1, inventory.getWhistleAmount());
        assertEquals(0, inventory.getWreckAmount());
        //note that our selectedTower is null
        //simulate placing the selectedTower (null in this case)
        inventory.place();
        //make sure none of the amounts changed
        assertEquals(2, inventory.getBuzzAmount());
        assertEquals(1, inventory.getWhistleAmount());
        assertEquals(0, inventory.getWreckAmount());

    }

    /**
     * @author Sophie
     *
     * Tests that the inventory does not decrement when the number of towers in inventory is zero
     * Testing M3 implementation
     */
    @Test
    public void inventoryDecrementZero() {
        ConfigurationScreen.player = new Player("Player", "Easy");
        Inventory testInventory = new Inventory();
        Buzz testBuzz = new Buzz();
        SteamWhistle testWhistle = new SteamWhistle();
        RamblinWreck testWreck = new RamblinWreck();
        //Inventory has: 1 buzz, 0 whistles, and 0 wrecks

        assertEquals(1, testInventory.getBuzzAmount());
        assertEquals(0, testInventory.getWhistleAmount());
        assertEquals(0, testInventory.getWreckAmount());

        testInventory.setSelectedTower(testBuzz);
        testInventory.place();
        assertEquals(0, testInventory.getBuzzAmount());
        testInventory.setSelectedTower(testWhistle);
        testInventory.place();
        assertEquals(0, testInventory.getWhistleAmount());
        testInventory.setSelectedTower(testWreck);
        testInventory.place();
        assertEquals(0, testInventory.getWreckAmount());
    }

    /**
     * @author Sophie
     *
     * Tests that the inventory class only allows one tower type to be selected at a time
     * Testing M3 implementation
     */
    @Test
    public void inventoryTowerSelection() {
        ConfigurationScreen.player = new Player("Player", "Easy");
        Inventory testInventory = new Inventory();
        Buzz testBuzz = new Buzz();
        SteamWhistle testWhistle = new SteamWhistle();
        RamblinWreck testWreck = new RamblinWreck();

        testInventory.setSelectedTower(testBuzz);
        assertEquals(testInventory.getSelectedTower().getType(), testBuzz.getType());
        testInventory.setSelectedTower(testWhistle);
        assertEquals(testInventory.getSelectedTower().getType(), testWhistle.getType());
        testInventory.setSelectedTower(testWreck);
        assertEquals(testInventory.getSelectedTower().getType(), testWreck.getType());
    }
}
