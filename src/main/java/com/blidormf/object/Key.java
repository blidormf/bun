package com.blidormf.object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Key extends SuperObject {
    public Key() {
        this.name = "Key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.solidArea = new Rectangle(0, 0, 128, 128);
        this.collision = true;
    }
}
