package com.blidormf.object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Carrot extends SuperObject {
    public Carrot() {
        this.name = "Carrot";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/carrot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.solidAreaDefaultX = 9;
        this.solidAreaDefaultY = 34;
        this.solidArea = new Rectangle(9, 34, 110, 59);
        this.collisionOn = true;
    }
}
