package com.blidormf.entity;

import com.blidormf.util.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    protected int speed;
    protected BufferedImage upIdle, up1, up2, rightIdle, right1, right2,
            downIdle, down1, down2, leftIdle, left1, left2;
    protected Direction direction;
    protected boolean isMoving;
    protected int spriteCounter = 0;
    protected int spriteNumber = 1;
}
