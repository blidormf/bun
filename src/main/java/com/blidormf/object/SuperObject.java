package com.blidormf.object;

import com.blidormf.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.blidormf.main.GamePanel.TILE_SIZE;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision;
    public int worldX, worldY;
    public Rectangle solidArea;
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        // Convert world coordinates to screen coordinates relative to the player's position
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        // Draw the object only if it's within the visible window area
        if (worldX + TILE_SIZE > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - TILE_SIZE < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + TILE_SIZE > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - TILE_SIZE < gamePanel.player.worldY + gamePanel.player.screenY) {
            g2.drawImage(image, screenX, screenY, TILE_SIZE, TILE_SIZE, null);
        }
    }
}
