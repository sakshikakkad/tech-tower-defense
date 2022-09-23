package com.example.demotest.data;


import android.graphics.Path;

import java.util.ArrayList;

public class Map {

    private Path path;
    private Path healthPath;
    private int map;
    private Tower[] towerList;
    private int selTowerIndex;

    public Map() {
        this.map = 1;
        this.path = new Path();
        this.healthPath = new Path();
        this.towerList = new Tower[9];
        this.selTowerIndex = -1;

        if (map == 1) {
            path.moveTo(200, 285);
            path.lineTo(907, 285);
            path.lineTo(907, 735);
            path.lineTo(1175, 735);

            healthPath.moveTo(200, 275);
            healthPath.lineTo(907, 275);
            healthPath.lineTo(907, 725);
            healthPath.lineTo(1175, 725);
        }
    }
    public Path getPath() {
        return path;
    }

    public Path getHealthPath() {
        return healthPath;
    }

    public Tower getTower(int index) {
        return towerList[index];
    }

    public void addTower(int index, Tower tower) {
        towerList[index] = tower;
    }

    public int getTowerIndex() {
        return selTowerIndex;
    }

    public void setTowerIndex(int index) {
        this.selTowerIndex = index;
    }
}
