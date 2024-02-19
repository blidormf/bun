package com.blidormf.tile;

import com.blidormf.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.blidormf.main.GamePanel.*;

public class TileManager {
    private final GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] map;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tiles = new Tile[10];
        this.map = new int[NUMBER_OF_WORLD_ROWS][NUMBER_OF_WORLD_COLUMNS];

        getTileImages();
        loadMap("world-01");
    }

    public void getTileImages() {
        try {
            tiles[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")),false);
            tiles[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/bush.png")), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapName) {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream("maps/" + mapName + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = bufferedReader.readLine();

            int row = 0;

            while (line != null) {
                String[] tiles = line.split(" "); // assuming space-separated integers
                for (int i = 0; i < tiles.length; i++) {
                    map[row][i] = Integer.parseInt(tiles[i]);
                }
                row++;
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldRow = 0, worldCol = 0;

        while (worldRow < NUMBER_OF_WORLD_ROWS) {
            int tileType = map[worldRow][worldCol];

            // Calculate the tile's position in the world coordinates
            int worldX = worldCol * TILE_SIZE;
            int worldY = worldRow * TILE_SIZE;

            // Convert world coordinates to screen coordinates relative to the player's position
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            // Draw the tile only if it's within the visible window area
            if (worldX + TILE_SIZE > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - TILE_SIZE < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + TILE_SIZE > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - TILE_SIZE < gamePanel.player.worldY + gamePanel.player.screenY) {
                g2.drawImage(tiles[tileType].getImage(), screenX, screenY, TILE_SIZE, TILE_SIZE, null);
            }

            worldCol++;

            if (worldCol == NUMBER_OF_WORLD_COLUMNS) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
