package com.blidormf.entity;

import com.blidormf.util.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public BufferedImage upIdle, up1, up2, rightIdle, right1, right2,
            downIdle, down1, down2, leftIdle, left1, left2;
    public Direction direction;
    public boolean isMoving;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn;
}
