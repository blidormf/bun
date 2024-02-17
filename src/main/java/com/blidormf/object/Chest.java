package com.blidormf.object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Chest extends SuperObject {
    public Chest() {
        this.name = "Chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.solidArea = new Rectangle(0, 0, 128, 128);
        this.collision = true;
    }
}
