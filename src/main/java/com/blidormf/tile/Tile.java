package com.blidormf.tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private boolean collides;

    public Tile(BufferedImage image, boolean collides) {
        this.image = image;
        this.collides = collides;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean collides() {
        return collides;
    }

    public void setCollides(boolean collides) {
        this.collides = collides;
    }
}
