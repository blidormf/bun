package com.blidormf.main;

import com.blidormf.object.Chest;
import com.blidormf.object.Door;
import com.blidormf.object.Key;

import static com.blidormf.main.GamePanel.TILE_SIZE;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.objects[0] = new Key();
        gamePanel.objects[0].worldX = 23 * TILE_SIZE;
        gamePanel.objects[0].worldY = 7 * TILE_SIZE;

        gamePanel.objects[1] = new Key();
        gamePanel.objects[1].worldX = 23 * TILE_SIZE;
        gamePanel.objects[1].worldY = 40 * TILE_SIZE;

        gamePanel.objects[2] = new Key();
        gamePanel.objects[2].worldX = 38 * TILE_SIZE;
        gamePanel.objects[2].worldY = 8 * TILE_SIZE;

        gamePanel.objects[3] = new Door();
        gamePanel.objects[3].worldX = 10 * TILE_SIZE;
        gamePanel.objects[3].worldY = 11 * TILE_SIZE;

        gamePanel.objects[4] = new Door();
        gamePanel.objects[4].worldX = 8 * TILE_SIZE;
        gamePanel.objects[4].worldY = 28 * TILE_SIZE;

        gamePanel.objects[5] = new Door();
        gamePanel.objects[5].worldX = 12 * TILE_SIZE;
        gamePanel.objects[5].worldY = 22 * TILE_SIZE;

        gamePanel.objects[6] = new Chest();
        gamePanel.objects[6].worldX = 10 * TILE_SIZE;
        gamePanel.objects[6].worldY = 7 * TILE_SIZE;
    }
}
