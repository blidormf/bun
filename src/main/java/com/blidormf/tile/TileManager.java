package com.blidormf.tile;

import com.blidormf.main.GamePanel;
import com.blidormf.util.ImageScaler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.blidormf.main.GamePanel.*;

public class TileManager {
    GamePanel gamePanel;
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
            tiles[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")), true);
            tiles[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")), true);
            tiles[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png")), false);
            tiles[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/bush02.png")), true);
            tiles[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png")), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupTile(int index, String imageName, boolean collision) {
        ImageScaler imageScaler = new ImageScaler();

        try {
            BufferedImage tileImage = ImageIO.read(getClass().getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tileImage = imageScaler.scaleImage(tileImage, TILE_SIZE, TILE_SIZE);

            this.tiles[index] = new Tile(tileImage, collision);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapName) {
        try (Scanner fileReader = new Scanner(Paths.get("src/main/resources/maps/" + mapName + ".txt"))) {
            for (int row = 0; row < NUMBER_OF_WORLD_ROWS; row++) {
                for (int col = 0; col < NUMBER_OF_WORLD_COLUMNS; col++) {
                    map[row][col] = fileReader.nextInt();
                }
            }
        } catch (Exception e) {
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
