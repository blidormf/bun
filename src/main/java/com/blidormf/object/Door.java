package com.blidormf.object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Door extends SuperObject {
    public Door() {
        this.name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.solidArea = new Rectangle(0, 0, 128, 128);
        this.collision = true;
    }
}
