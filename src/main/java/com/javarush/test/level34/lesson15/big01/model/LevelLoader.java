package com.javarush.test.level34.lesson15.big01.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    public LevelLoader(Path levels) {
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();

        walls.add(new Wall(20, 20));
        walls.add(new Wall(30, 30));
        walls.add(new Wall(40, 40));
        boxes.add(new Box(50, 50));
        homes.add(new Home(60, 60));
        Player player = new Player(70, 70);

        return new GameObjects(walls, boxes, homes, player);
    }
}
