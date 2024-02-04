package com.blidormf.entity;

import com.blidormf.main.GamePanel;
import com.blidormf.main.KeyHandler;
import com.blidormf.util.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.blidormf.main.GamePanel.TILE_SIZE;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this. keyHandler = keyHandler;
        setDefaultValues();
        getPlayerSprite();
    }

    public void setDefaultValues() {
        this.x = 100;
        this.y = 100;
        this.speed = 1;
        this.direction = Direction.DOWN;
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
                this.y -= this.speed;
            }

            if (keyHandler.isRightPressed()) {
                this.direction = Direction.RIGHT;
                this.x += this.speed;
            }

            if (keyHandler.isDownPressed()) {
                this.direction = Direction.DOWN;
                this.y += this.speed;
            }

            if (keyHandler.isLeftPressed()) {
                this.direction = Direction.LEFT;
                this.x -= this.speed;
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

        g2.drawImage(bufferedImage, this.x, this.y, TILE_SIZE, TILE_SIZE, null);
    }
}
