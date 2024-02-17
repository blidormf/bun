package com.blidormf.entity;

import com.blidormf.main.GamePanel;
import com.blidormf.main.KeyHandler;
import com.blidormf.util.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.blidormf.main.GamePanel.*;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX, screenY;
    public int numberOfKeys;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this. keyHandler = keyHandler;

        // Set the player's position to the center of screen
        this.screenX = WINDOW_WIDTH / 2 - TILE_SIZE / 2;
        this.screenY = WINDOW_HEIGHT / 2 - TILE_SIZE / 2;

        setDefaultValues();
        getPlayerSprite();
    }

    public void setDefaultValues() {
        this.worldX = TILE_SIZE * 23;
        this.worldY = TILE_SIZE * 21;
        this.speed = 5;
        this.direction = Direction.DOWN;
        this.collisionOn = false;
        this.solidArea = new Rectangle(44, 85, 40, 35);
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
        this.numberOfKeys = 0;
    }

    public void getPlayerSprite() {
        try {
            upIdle = ImageIO.read(getClass().getResourceAsStream("/player/bunny-up-idle.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/bunny-up-1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/bunny-up-2.png"));

            rightIdle = ImageIO.read(getClass().getResourceAsStream("/player/bunny-right-idle.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/bunny-right-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/bunny-right-2.png"));

            downIdle = ImageIO.read(getClass().getResourceAsStream("/player/bunny_down_idle.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/bunny_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/bunny_down_2.png"));

            leftIdle = ImageIO.read(getClass().getResourceAsStream("/player/bunny-left-idle.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/bunny-left-1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/bunny-left-2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.isUpPressed() || keyHandler.isRightPressed() ||
                keyHandler.isDownPressed() || keyHandler.isLeftPressed()) {
            this.isMoving = true;

            if (keyHandler.isUpPressed()) {
                this.direction = Direction.UP;
            }

            if (keyHandler.isRightPressed()) {
                this.direction = Direction.RIGHT;
            }

            if (keyHandler.isDownPressed()) {
                this.direction = Direction.DOWN;
            }

            if (keyHandler.isLeftPressed()) {
                this.direction = Direction.LEFT;
            }

            // Check tile collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            // Check object collision
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // If collision is false, player can move.
            if (!collisionOn) {
                switch (this.direction) {
                    case UP -> this.worldY -= this.speed;
                    case RIGHT -> this.worldX += this.speed;
                    case DOWN -> this.worldY += this.speed;
                    case LEFT -> this.worldX -= this.speed;
                }
            }

            // Change the sprite every 10 frames
            spriteCounter++;
            if (spriteCounter > 10) {
                switch (spriteNumber) {
                    case 1 -> spriteNumber = 2;
                    case 2 -> spriteNumber = 3;
                    case 3 -> spriteNumber = 4;
                    case 4 -> spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        } else {
            this.isMoving = false;
        }
    }

    public void pickUpObject(int i) {
        if (i > -1) {
            String objectName = gamePanel.objects[i].name;

            switch (objectName) {
                case "Key" -> {
                    this.numberOfKeys++;
                    this.gamePanel.ui.displayNotification("You found a key!");
                    gamePanel.objects[i] = null;
                }
                case "Door" -> {
                    if (numberOfKeys > 0) {
                        gamePanel.objects[i] = null;
                        this.numberOfKeys--;
                    }
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage bufferedImage = null;

        if (this.isMoving) {
            bufferedImage = switch (direction) {
                case UP -> {
                    if (this.spriteNumber == 1) yield this.up1;
                    else if (this.spriteNumber == 2) yield this.upIdle;
                    else if (this.spriteNumber == 3) yield this.up2;
                    yield this.upIdle;
                }
                case RIGHT -> {
                    if (this.spriteNumber == 1) yield this.right1;
                    else if (this.spriteNumber == 2) yield this.rightIdle;
                    else if (this.spriteNumber == 3) yield this.right2;
                    yield this.rightIdle;
                }
                case DOWN -> {
                    if (this.spriteNumber == 1) yield this.down1;
                    else if (this.spriteNumber == 2) yield this.downIdle;
                    else if (this.spriteNumber == 3) yield this.down2;
                    yield this.downIdle;
                }
                case LEFT -> {
                    if (this.spriteNumber == 1) yield this.left1;
                    else if (this.spriteNumber == 2) yield this.leftIdle;
                    else if (this.spriteNumber == 3) yield this.left2;
                    yield this.leftIdle;
                }
            };
        } else {
            bufferedImage = switch (direction) {
                case UP -> this.upIdle;
                case RIGHT -> this.rightIdle;
                case DOWN -> this.downIdle;
                case LEFT -> this.leftIdle;
            };
        }

        g2.drawImage(bufferedImage, this.screenX, this.screenY, TILE_SIZE, TILE_SIZE, null);
    }
}
