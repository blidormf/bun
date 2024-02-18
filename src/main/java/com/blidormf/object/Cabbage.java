package com.blidormf.object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Cabbage extends SuperObject{
    public Cabbage() {
        this.name = "Cabbage";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cabbage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.solidAreaDefaultX = 18;
        this.solidAreaDefaultY = 20;
        this.solidArea = new Rectangle(18, 20, 93, 87);
        this.collisionOn = true;
    }
}
