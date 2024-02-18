package com.blidormf.object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Cucumber extends SuperObject{
    public Cucumber() {
        this.name = "Cucumber";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cucumber.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.solidAreaDefaultX = 11;
        this.solidAreaDefaultY = 48;
        this.solidArea = new Rectangle(11, 49, 102, 43);
        this.collisionOn = true;
    }
}
