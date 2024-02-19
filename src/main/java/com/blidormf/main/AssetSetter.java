package com.blidormf.main;

import com.blidormf.object.Cabbage;
import com.blidormf.object.Carrot;
import com.blidormf.object.Cucumber;
import com.blidormf.object.House;

import static com.blidormf.main.GamePanel.TILE_SIZE;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.objects[0] = new Cabbage();
        gamePanel.objects[0].worldX = 23 * TILE_SIZE;
        gamePanel.objects[0].worldY = 7 * TILE_SIZE;

        gamePanel.objects[1] = new Carrot();
        gamePanel.objects[1].worldX = 23 * TILE_SIZE;
        gamePanel.objects[1].worldY = 40 * TILE_SIZE;

        gamePanel.objects[2] = new Cucumber();
        gamePanel.objects[2].worldX = 38 * TILE_SIZE;
        gamePanel.objects[2].worldY = 8 * TILE_SIZE;

        gamePanel.objects[3] = new House();
        gamePanel.objects[3].worldX = 9 * TILE_SIZE;
        gamePanel.objects[3].worldY = 7 * TILE_SIZE;
    }
}
