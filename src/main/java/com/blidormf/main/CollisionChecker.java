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

    public int checkObject(Entity entity, boolean player) {
        int index = -1;

        for (int i = 0; i < gamePanel.objects.length; i++) {
            if (gamePanel.objects[i] != null) {
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the object's solid area position
                gamePanel.objects[i].solidArea.x = gamePanel.objects[i].worldX + gamePanel.objects[i].solidArea.x;
                gamePanel.objects[i].solidArea.y = gamePanel.objects[i].worldY + gamePanel.objects[i].solidArea.y;

                switch (entity.direction) {
                    case UP -> entity.solidArea.y -= entity.speed;
                    case RIGHT -> entity.solidArea.x += entity.speed;
                    case DOWN -> entity.solidArea.y += entity.speed;
                    case LEFT -> entity.solidArea.x -= entity.speed;
                }

                if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                    entity.collisionOn = gamePanel.objects[i].collisionOn;

                    if (player)
                        index = i;
                }

                // Reset entity's solid area values
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                // Reset object's solid area values
                gamePanel.objects[i].solidArea.x = gamePanel.objects[i].solidAreaDefaultX;
                gamePanel.objects[i].solidArea.y = gamePanel.objects[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}