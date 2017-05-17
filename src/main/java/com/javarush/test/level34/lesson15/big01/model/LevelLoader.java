package com.javarush.test.level34.lesson15.big01.model;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        level = level > 60 ? level - 60 : level;
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            while (true) {
                String line = reader.readLine();
                if (line.startsWith("Maze")) {
                    if (getNumber(line) == level) {
                        int w = 0;
                        int h = 0;
                        for (int i = 0; i < 6; i++) {
                            line = reader.readLine();
                            if (i == 1) {
                                w = getNumber(line);
                            }
                            if (i == 2) {
                                h = getNumber(line);
                            }
                        }
                         return createObjects(w, h, reader);
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public GameObjects createObjects(int sizeX, int sizeY, BufferedReader r) throws IOException {
        Set<Box> boxes = new HashSet<>();
        Set<Wall> walls = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        for (int y = 0; y < sizeY; y++) {
            char[] chars = r.readLine().toCharArray();
            for (int x = 0; x < sizeX; x++) {
                int newX = Model.FIELD_SELL_SIZE / 2 + x * Model.FIELD_SELL_SIZE;
                int newY = Model.FIELD_SELL_SIZE / 2 + y * Model.FIELD_SELL_SIZE;
                switch (chars[x]) {
                    case 'X':
                        walls.add(new Wall(newX, newY));
                        break;
                    case '*':
                        boxes.add(new Box(newX, newY));
                        break;
                    case '.':
                        homes.add(new Home(newX, newY));
                        break;
                    case '&':
                        boxes.add(new Box(newX, newY));
                        homes.add(new Home(newX, newY));
                        break;
                    case '@':
                        player = new Player(newX, newY);
                        break;
                }
            }
        }
        return new GameObjects(walls, boxes, homes, player);
    }

    public int getNumber(String s) {
        String[] split = s.split(":");
        return Integer.parseInt(split[1].trim());
    }
}
