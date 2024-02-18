package com.blidormf.object;

import com.blidormf.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static com.blidormf.main.GamePanel.TILE_SIZE;

public class House extends SuperObject {
    public House() {
        this.name = "House";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/house.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.solidArea = new Rectangle(0, 0, 3 * TILE_SIZE, 2 * TILE_SIZE - 20);
        this.collisionOn = true;
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        // Convert world coordinates to screen coordinates relative to the player's position
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        // Draw the object only if it's within the visible window area
        if (worldX + TILE_SIZE * 3 > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - TILE_SIZE * 3 < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + TILE_SIZE * 2 > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - TILE_SIZE * 2 < gamePanel.player.worldY + gamePanel.player.screenY) {
            g2.drawImage(image, screenX, screenY, 3 * TILE_SIZE, 2 * TILE_SIZE, null);
        }
    }
}
