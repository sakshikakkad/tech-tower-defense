package com.example.demotest.objects;


import android.graphics.Path;

public class Map {

    private Path path;
    private int map;

    public Map() {
        this.map = 1;
        this.path = new Path();

        if (map == 1) {
            path.moveTo(200, 285);
            path.lineTo(907, 285);
            path.lineTo(907, 735);
            path.lineTo(1175, 735);
        }
    }

    public Path getPath() {
        return path;
    }
}
