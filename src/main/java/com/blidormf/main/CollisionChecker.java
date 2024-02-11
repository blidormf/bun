package com.blidormf.main;

import com.blidormf.entity.Entity;

import static com.blidormf.main.GamePanel.TILE_SIZE;

public class CollisionChecker {
    private GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        // Calculate the world coordinates of the entity's boundaries
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entityLeftWorldX + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entityTopWorldY + entity.solidArea.height;

        // Calculate the tile coordinates of the entity's boundaries
        int entityLeftCol = entityLeftWorldX / TILE_SIZE;
        int entityRightCol = entityRightWorldX / TILE_SIZE;
        int entityTopRow = entityTopWorldY / TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / TILE_SIZE;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case UP -> {
                entityTopRow = (entityTopWorldY - entity.speed) / TILE_SIZE;
                tileNum1 = gamePanel.tileManager.map[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.map[entityTopRow][entityRightCol];
                if (gamePanel.tileManager.tiles[tileNum1].collides() ||
                        gamePanel.tileManager.tiles[tileNum2].collides())
                    entity.collisionOn = true;
            }
            case LEFT -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / TILE_SIZE;
                tileNum1 = gamePanel.tileManager.map[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.map[entityBottomRow][entityLeftCol];
                if (gamePanel.tileManager.tiles[tileNum1].collides() ||
                        gamePanel.tileManager.tiles[tileNum2].collides())
                    entity.collisionOn = true;
            }
            case DOWN -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / TILE_SIZE;
                tileNum1 = gamePanel.tileManager.map[entityBottomRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.map[entityBottomRow][entityRightCol];
                if (gamePanel.tileManager.tiles[tileNum1].collides() ||
                        gamePanel.tileManager.tiles[tileNum2].collides())
                    entity.collisionOn = true;
            }
            case RIGHT -> {
                entityRightCol = (entityRightWorldX + entity.speed) / TILE_SIZE;
                tileNum1 = gamePanel.tileManager.map[entityTopRow][entityRightCol];
                tileNum2 = gamePanel.tileManager.map[entityBottomRow][entityRightCol];
                if (gamePanel.tileManager.tiles[tileNum1].collides() ||
                        gamePanel.tileManager.tiles[tileNum2].collides())
                    entity.collisionOn = true;
            }
        }
    }
}