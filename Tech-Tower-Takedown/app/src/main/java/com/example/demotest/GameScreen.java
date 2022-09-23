package com.example.demotest;

import static com.example.demotest.ConfigurationScreen.player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.widget.ImageView;

import com.example.demotest.data.Buzz;
import com.example.demotest.data.DamageUpgrade;
import com.example.demotest.data.Enemy;
import com.example.demotest.data.HealthBar;
import com.example.demotest.data.Inventory;
import com.example.demotest.data.Map;
import com.example.demotest.data.Monument;
import com.example.demotest.data.RamblinWreck;
import com.example.demotest.data.RangeUpgrade;
import com.example.demotest.data.Shop;
import com.example.demotest.data.SteamWhistle;
import com.example.demotest.data.Tower;
import com.example.demotest.data.TowerUpgrade;
import com.example.demotest.data.Wave;
import com.example.demotest.data.Waves;

public class GameScreen extends AppCompatActivity {

    // initialize elements
    private Map map = new Map();
    private Inventory gameInv = new Inventory();
    private Shop shop = new Shop();
    private Monument techTower = new Monument();
    private Waves waves = new Waves();

    // UI elements
    private ConstraintLayout screen;
    private Button startCombat;
    private TextView balance;
    private TextView health;
    private ImageButton buzzShop;
    private ImageButton whistleShop;
    private ImageButton wreckShop;
    private PopupWindow window;

    // timer
    private int elapsedTime;
    private Timer timer;
    private TimerTask timerTask;
    private TextView timerText;
    private boolean start;

    private ArrayList<Animator> enemyAnims = new ArrayList<>();
    private ArrayList<Animator> enemyHealthAnims = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Button buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(l -> {
            Intent quit = new Intent(this, WelcomeScreen.class);
            startActivity(quit);
            finish();
        });

        // initialize buttons and UI views
        init();
        ImageButton buzzInv = findViewById(R.id.buzzTower3);
        ImageButton whistleInv = findViewById(R.id.whistleTower3);
        ImageButton wreckInv = findViewById(R.id.wreckTower3);

        // initialize each tower and set cost information
        Buzz buzz = new Buzz();
        SteamWhistle whistle = new SteamWhistle();
        RamblinWreck wreck = new RamblinWreck();
        setShopInfo();
        setInvInfo();

        // display which tower in shop is selected
        ArrayList<ImageButton> shopButtons = new ArrayList<>();
        shopButtons.add(buzzShop);
        shopButtons.add(whistleShop);
        shopButtons.add(wreckShop);
        buzzShop.setOnClickListener(l -> {
            shop.setSelectedTower(buzz);
            removeClicked(shopButtons, true);
            setClicked(buzzShop, true);
        });
        whistleShop.setOnClickListener(l -> {
            shop.setSelectedTower(whistle);
            removeClicked(shopButtons, true);
            setClicked(whistleShop, true);
        });
        wreckShop.setOnClickListener(l -> {
            shop.setSelectedTower(wreck);
            removeClicked(shopButtons, true);
            setClicked(wreckShop, true);
        });

        // set buy button
        Button buyButton = findViewById(R.id.buyButton);
        buyButton.setOnClickListener(l -> {
            shop.buyTower(gameInv);
            balance.setText("" + player.getBuzzFunds());
            setInvInfo();
            removeClicked(shopButtons, true);
        });

        // display which tower in inventory is selected
        ArrayList<ImageButton> invButtons = new ArrayList<>();
        invButtons.add(buzzInv);
        invButtons.add(whistleInv);
        invButtons.add(wreckInv);
        buzzInv.setOnClickListener(l -> {
            removeClicked(invButtons, true);
            setClicked(buzzInv, true);
            gameInv.setSelectedTower(buzz);
        });
        whistleInv.setOnClickListener(l -> {
            removeClicked(invButtons, true);
            setClicked(whistleInv, true);
            gameInv.setSelectedTower(whistle);
        });
        wreckInv.setOnClickListener(l -> {
            removeClicked(invButtons, true);
            setClicked(wreckInv, true);
            gameInv.setSelectedTower(wreck);
        });

        // sell towers in inventory
        Button sellButton = findViewById(R.id.sellButton);
        sellButton.setOnClickListener(l -> {
            gameInv.sellTower();
            balance.setText("" + player.getBuzzFunds());
            setInvInfo();
            removeClicked(invButtons, true);
        });

        // initialize place buttons on map and loop for tower placement/upgrades
        ArrayList<ImageButton> placeButtons = setButtons();
        for (int j = 0; j < placeButtons.size(); j++) {
            ImageButton button = placeButtons.get(j);
            int index = j;
            button.setOnClickListener(l -> {

                if (gameInv.getSelectedTower() == null && (button.getTag().equals("empty"))) {
                    return;
                }

                if (button.getTag().equals("empty")) {
                    placeTower(button, index);
                    setInvInfo();
                } else if (window != null) {
                    window.dismiss();
                    window = null;
                    removeClicked(placeButtons, false);
                } else {
                    removeClicked(placeButtons, false);
                    setClicked(button, false);
                    map.setTowerIndex(index);
                    towerStatsPopup();
                }
            });
        }

        Button upgradeButton = findViewById(R.id.upgradeButton);
        upgradeButton.setOnClickListener(l -> {
            upgradeTower();
            balance.setText("" + player.getBuzzFunds());
        });


        // set start combat button
        startCombat.setOnClickListener(l -> {
            if (waves.getWavesLeft() > 0) {
                waves.setCurrentWave();
                startWave(waves.getCurrentWave());
                updateWaveText();
            } else if (waves.getWavesLeft() == 0) {
                waves.createBossWave();
                startWave(waves.getCurrentWave());
                updateWaveText();
            } else {
                startCombat.setClickable(false);
            }
            if(!start) {
                startTimer();
                start = true;
            }
        });

        // towers attack enemies
        Handler attackHandler = new Handler();
        Runnable attackRunnable = new Runnable() {
            @Override
            public void run() {
                if (waves.getCurrentWave().getStarted()) {
                    for (int j = 0; j < placeButtons.size(); j++) {
                        attackEnemies(placeButtons.get(j), map.getTower(j), waves.getCurrentWave());
                    }
                }
                attackHandler.postDelayed(this, 500);
            }
        };
        attackHandler.postDelayed(attackRunnable, 500);

        checkStatus();

    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        if (window != null) {
            window.dismiss();
        }
    }


    //-------------------------------END OF ONCREATE & ONDESTROY METHOD---------------------------------------

    //---------------- UI UPDATE METHODS -----------------------

    private void init() {

        // set screen and buttons
        this.screen = findViewById(R.id.constraintLayout);
        this.startCombat = findViewById(R.id.startCombat);
        this.buzzShop = findViewById(R.id.buzzTower);
        this.whistleShop = findViewById(R.id.whistleTower);
        this.wreckShop = findViewById(R.id.wreckTower);

        // text views
        this.balance = findViewById(R.id.balance);
        this.health = findViewById(R.id.health);

        // set text views
        updateWaveText();
        balance.setText("" + player.getBuzzFunds());
        health.setText("" + techTower.getHealth());

        // timer
        timerText = findViewById(R.id.timerText);
        timer = new Timer();
        elapsedTime = 0;
        start = false;
    }

    // set cost information in shop
    private void setShopInfo() {
        TextView buzzInfo = findViewById(R.id.BuzzInfo);
        TextView whistleInfo = findViewById(R.id.WhistleInfo);
        TextView wreckInfo = findViewById(R.id.WreckInfo);
        buzzInfo.setText("Buzz" + "\n" + "$" + Buzz.getPrice());
        whistleInfo.setText("Whistle" + "\n" + "$" + SteamWhistle.getPrice());
        wreckInfo.setText("Wreck" + "\n" + "$" + RamblinWreck.getPrice());
    }

    // updates and displays num towers owned in inventory
    private void setInvInfo() {
        TextView buzzNumber = findViewById(R.id.buzzNumber);
        TextView whistleNumber = findViewById(R.id.whistleNumber);
        TextView wreckNumber = findViewById(R.id.wreckNumber);

        buzzNumber.setText(String.valueOf(gameInv.getBuzzAmount()));
        whistleNumber.setText(String.valueOf(gameInv.getWhistleAmount()));
        wreckNumber.setText(String.valueOf(gameInv.getWreckAmount()));
    }

    // sets the border for selected tower in shop
    private void setClicked(ImageButton clicked, boolean hasBackground) {

        Drawable background;

        if (hasBackground) {
            background = getResources().getDrawable(R.drawable.blue_button_border);
        } else {
            background = getResources().getDrawable(R.drawable.transparent_button_border);
        }

        clicked.setBackground(background);
    }

    // remove white border
    private void removeClicked(ArrayList<ImageButton> buttonList, boolean hasBackground) {

        Drawable background;

        if (hasBackground) {
            background = getResources().getDrawable(R.drawable.blue_background);
        } else {
            background = getResources().getDrawable(R.drawable.transparent_background);
        }

        for (ImageButton button : buttonList) {
            button.setBackground(background);
        }
    }

    // greys out towers if true, removes grey if false
    private void setGrey() {

        ImageView greyBox1 = findViewById(R.id.greyOut1);
        ImageView greyBox2 = findViewById(R.id.greyOut2);
        ImageView greyBox3 = findViewById(R.id.greyOut3);

        if (player.getBuzzFunds() < Buzz.getPrice()) {
            greyBox1.bringToFront();
            buzzShop.setClickable(false);
            greyBox2.bringToFront();
            whistleShop.setClickable(false);
            greyBox3.bringToFront();
            wreckShop.setClickable(false);
        } else if (player.getBuzzFunds() < SteamWhistle.getPrice()) {
            buzzShop.bringToFront();
            buzzShop.setClickable(true);
            greyBox2.bringToFront();
            whistleShop.setClickable(false);
            greyBox3.bringToFront();
            wreckShop.setClickable(false);
        } else if (player.getBuzzFunds() < RamblinWreck.getPrice()) {
            buzzShop.bringToFront();
            buzzShop.setClickable(true);
            whistleShop.bringToFront();
            whistleShop.setClickable(true);
            greyBox3.bringToFront();
            wreckShop.setClickable(false);
        } else {
            buzzShop.bringToFront();
            buzzShop.setClickable(true);
            whistleShop.bringToFront();
            whistleShop.setClickable(true);
            wreckShop.bringToFront();
            wreckShop.setClickable(true);
        }
    }

    // initializes place buttons
    private ArrayList<ImageButton> setButtons() {
        ArrayList<ImageButton> placeButtons = new ArrayList<>();

        placeButtons.add(findViewById(R.id.place1));
        placeButtons.add(findViewById(R.id.place2));
        placeButtons.add(findViewById(R.id.place3));
        placeButtons.add(findViewById(R.id.place4));
        placeButtons.add(findViewById(R.id.place5));
        placeButtons.add(findViewById(R.id.place6));
        placeButtons.add(findViewById(R.id.place7));
        placeButtons.add(findViewById(R.id.place8));
        placeButtons.add(findViewById(R.id.place9));

        return placeButtons;
    }

    // checks status of game and ends game if necessary, updates UI
    private void checkStatus() {

        Handler statusHandler = new Handler();
        Runnable monRunnable = new Runnable() {
            @Override
            public void run() {
                if (techTower.isDefeated() || waves.wavesEnded()) {
                    statusHandler.removeCallbacks(this);
                    player.setTime(formatTime());
                    endGame();
                } else if (waves.getCurrentWave().waveEnded()) {
                    updateWaveText();
                    startCombat.setClickable(true);
                    statusHandler.postDelayed(this, 1500);
                } else {
                    statusHandler.postDelayed(this, 1500);
                }
                setGrey();
            }
        };
        statusHandler.postDelayed(monRunnable, 1500);
    }

    //---------------- TOWER HELPER METHODS -----------------------

    // places tower from inventory onto place button
    private void placeTower(ImageButton placeButton, int index) {

        Tower selected = gameInv.getSelectedTower();

        if (selected == null) {
            return;
        }

        if (selected.getType() == 1) {
            if (gameInv.getBuzzAmount() == 0) {
                return;
            } else {
                placeButton.setImageResource(R.drawable.buzztower);
                placeButton.setBackgroundColor(0);

                Tower tower = new Buzz();
                map.addTower(index, tower);
            }
        } else if (selected.getType() == 2) {
            if (gameInv.getWhistleAmount() == 0) {
                return;
            } else {
                placeButton.setImageResource(R.drawable.whistletower);
                placeButton.setBackgroundColor(0);

                Tower tower = new SteamWhistle();
                map.addTower(index, tower);
            }
        } else if (selected.getType() == 3) {
            if (gameInv.getWreckAmount() == 0) {
                return;
            } else {
                placeButton.setImageResource(R.drawable.wrecktower);
                placeButton.setBackgroundColor(0);

                Tower tower = new RamblinWreck();
                map.addTower(index, tower);
            }
        }

        placeButton.setTag("filled");
        gameInv.place();
    }

    // creates a popup with tower statistics
    private void towerStatsPopup() {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.tower_stats, null);

        // create the popup window
        window = new PopupWindow(popupView, 400, 200);
        updateStats();

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        window.showAtLocation(screen, Gravity.BOTTOM, -800,50);
    }

    // sets UI for tower stats
    private void updateStats() {

        if (window == null) {
            return;
        }

        View popupView = window.getContentView();

        TextView damageText = popupView.findViewById(R.id.damage);
        TextView rangeText = popupView.findViewById(R.id.range);
        TextView levelText = popupView.findViewById(R.id.level);

        Tower tower = map.getTower(map.getTowerIndex());

        damageText.setText("Damage: " + tower.getDamage());
        rangeText.setText("Range: " + (tower.getRange() / 10));
        levelText.setText("Level: " + tower.getLevel());
    }

    // upgrades tower based on popup radio buttons
    private void upgradeTower() {

        int index = map.getTowerIndex();

        if ((index == -1) || (window == null)) {
            return;
        }

        View popupView = window.getContentView();

        RadioButton damage = popupView.findViewById(R.id.damageButton);
        RadioButton range = popupView.findViewById(R.id.rangeButton);
        Tower tower;

        if (damage.isChecked()) {
            tower = new DamageUpgrade(map.getTower(index));
        } else if (range.isChecked()) {
            tower = new RangeUpgrade(map.getTower(index));
        } else {
            return;
        }

        TowerUpgrade upgraded = (TowerUpgrade) tower;
        map.addTower(index, upgraded.getUpgradedTower());
        updateStats();
    }

    // attacks enemies in wave if in range of tower
    private void attackEnemies(ImageButton towerUI, Tower tower, Wave wave) {

        if ((tower == null) || !wave.getStarted()) {
            return;
        }

        ArrayList<Integer> toRemove = new ArrayList<>();

        // loop through enemies
        for (int i = 0; i < enemyAnims.size(); i++) {
            if (inRange(towerUI, enemyAnims.get(i), tower.getRange())) {

                attackAnimation(towerUI, enemyAnims.get(i));

                // tower attacks enemy[i], executes if enemy was defeated by tower
                if (tower.attack(wave.getWave().get(i), techTower)) {

                    toRemove.add(i);
                    // update mon health and player balance
                    health.setText("" + techTower.getHealth());
                    balance.setText("" + player.getBuzzFunds());
                } else {
                    Enemy enemy = wave.getWave().get(i);
                    float healthPercent = (float) enemy.getHealth() / (float) enemy.getMaxHealth();
                    wave.getWave().get(i).getHealthBar().draw((int) (healthPercent * 10));
                }
            }
        }

        // remove enemy from enemy UI and wave lists
        for (int j = 0; j < toRemove.size(); j++) {
            int index = toRemove.get(j) - j;
            enemyAnims.get(index).cancel();
            enemyAnims.get(index).removeAllListeners();
            enemyAnims.remove(index);
            wave.getWave().remove(index);
        }
    }

    // create an animation of enemy getting attacked by a tower
    private void attackAnimation(ImageButton tower, Animator enemy) {

        // create a path from tower to enemy
        ObjectAnimator enemyAnim = (ObjectAnimator) enemy;
        ImageView enemyUI = (ImageView) enemyAnim.getTarget();
        Path attackLine = new Path();

        attackLine.moveTo(tower.getX(), tower.getY());
        attackLine.lineTo(enemyUI.getX(), enemyUI.getY());

        // create a stinger on screen
        ImageView stinger = new ImageView(GameScreen.this);
        stinger.setBackgroundResource(R.drawable.stinger);
        screen.addView(stinger, 100, 100);

        // play animation
        ObjectAnimator stingerAttack = ObjectAnimator.ofFloat(stinger, "x", "y", attackLine);
        stingerAttack.setDuration(350);
        stingerAttack.start();

        stingerAttack.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                screen.removeView(stinger);
                stinger.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    // checks if an enemy is within range of a tower
    private boolean inRange(ImageButton towerUI, Animator enemy, int range) {

        ObjectAnimator enemyAnim = (ObjectAnimator) enemy;
        ImageView enemyUI = (ImageView) enemyAnim.getTarget();

        double x1 = towerUI.getX();
        double y1 = towerUI.getY();

        double x2 = enemyUI.getX();
        double y2 = enemyUI.getY();

        double distSquared = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
        int dist = (int) Math.sqrt(distSquared);

        return dist < range;

    }

    //---------------- ENEMY/WAVE HELPER METHODS -----------------------

    // starts wave when start combat button is clicked
    private void startWave(Wave wave) {

        wave.setStarted(true);
        startCombat.setClickable(false);
        waves.decrementWavesLeft();

        // initialize animator lists
        ArrayList<Enemy> waveList = wave.getWave();

        // loop through and initialize enemies in waveList
        for (int j = 0; j < waveList.size(); j++) {

            // create enemyUI dynamically
            ImageView enemyUI = new ImageView(GameScreen.this);
            Enemy enemy = waveList.get(j);

            HealthBar healthBar = new HealthBar(enemy, GameScreen.this);
            enemy.setHealthBar(healthBar);

            // add enemy to map, set starting pos off screen
            screen.addView(enemyUI, 100, 100);
            enemyUI.setX(-500);
            enemyUI.setY(-500);

            screen.addView(healthBar.getHealthBar(), 70, 10);
            healthBar.getHealthBar().setX(-500);
            healthBar.getHealthBar().setY(-500);

            // get animator
            ObjectAnimator animator = enemyAnimator(enemyUI, enemy);
            ObjectAnimator healthAnimator = enemyHealthAnimator(enemy);

            // set background of enemy depending on type and add to animator lists
            if (enemy.getType() == 1) {
                enemyUI.setBackgroundResource(R.drawable.trolley);
            } else if (enemy.getType() == 2) {
                enemyUI.setBackgroundResource(R.drawable.uga);
            } else if (enemy.getType() == 3){
                enemyUI.setBackgroundResource(R.drawable.corona);
            } else {
                enemyUI.setBackgroundResource(R.drawable.finalboss);
            }
            enemyAnims.add(animator);
            enemyHealthAnims.add(healthAnimator);
        }

        AnimatorSet enemySet = new AnimatorSet();
        enemySet.playTogether(enemyAnims);
        AnimatorSet healthSet = new AnimatorSet();
        healthSet.playTogether(enemyHealthAnims);
        enemySet.start();
        healthSet.start();
    }

    // creates an animator for an enemy
    private ObjectAnimator enemyAnimator(ImageView enemyUI, Enemy enemy) {

        // create animator for enemy and set speed
        ObjectAnimator animator = ObjectAnimator.ofFloat(enemyUI, "x", "y", map.getPath());

        // set delay so enemies don't appear on top of each other
        Random rand = new Random();
        int delay = 500 * (rand.nextInt(8) + 2);
        enemy.setDelay(delay);
        animator.setDuration(1000 * enemy.getSpeed() + delay);

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            // nothing needs to be added here
            public void onAnimationStart(Animator animator) {}

            @Override
            // animation ends when enemy reaches monument
            public void onAnimationEnd(Animator animator) {
                Handler enemyHandler = new Handler();
                Runnable enemyRunnable = new Runnable() {
                    @Override
                    public void run() {
                        if (enemy.getHealth() <= 0) {
                            screen.removeView(enemyUI);
                            enemyUI.setVisibility(View.GONE);
                            screen.removeView(enemy.getHealthBar().getHealthBar());
                            enemy.getHealthBar().getHealthBar().setVisibility(View.GONE);
                        }
                        if (techTower.getHealth() > 0 && enemyUI.getVisibility() != View.GONE) {
                            techTower.damageMonument(enemy.getDamage());
                            health.setText("" + techTower.getHealth());
                            enemyHandler.postDelayed(this, 1500);
                        } else {
                            enemyHandler.removeCallbacks(this);
                        }
                    }
                };
                enemyHandler.postDelayed(enemyRunnable, 1500);
            }

            @Override
            // cancel animation if enemy health = 0
            public void onAnimationCancel(Animator animator) {
                screen.removeView(enemyUI);
                enemyUI.setVisibility(View.GONE);
                screen.removeView(enemy.getHealthBar().getHealthBar());
                enemy.getHealthBar().getHealthBar().setVisibility(View.GONE);
            }

            // nothing needs to be added here
            @Override
            public void onAnimationRepeat(Animator animator) {
            }

        });

        return animator;
    }

    private void updateWaveText() {

        TextView wavesText = findViewById(R.id.wavesText);

        if (waves.getCurrentWave().waveEnded()) {
            wavesText.setText("Wave Complete!");
        } else if (waves.getWavesLeft() == -1){
            wavesText.setText("Last Wave!");
        } else {
            wavesText.setText("Waves Left: " + (waves.getWavesLeft() + 1));
        }
    }
    
    // starts the timer
    private void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        elapsedTime++;
                        timerText.setText(formatTime());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0 ,1000);
    }

    // converts time into a String
    private String formatTime() {
        int minutes = (elapsedTime % 3600) / 60;
        int seconds = elapsedTime % 60;
        String timeString = String.format(String.format("%02d",minutes) + ":" + String.format("%02d",seconds));
        return timeString;
    }

    // creates an animator for an enemy
    private ObjectAnimator enemyHealthAnimator(Enemy enemy) {

        // create animator for enemy and set speed
        ObjectAnimator animator = ObjectAnimator.ofFloat(enemy.getHealthBar().getHealthBar(), "x", "y", map.getHealthPath());
        animator.setDuration(1000 * enemy.getSpeed() + enemy.getDelay());
        return animator;
    }

    // ends game
    private void endGame() {
        Intent over = new Intent(this, OverScreen.class);
        over.putExtra("player", player);
        startActivity(over);
        finish();
    }
}
