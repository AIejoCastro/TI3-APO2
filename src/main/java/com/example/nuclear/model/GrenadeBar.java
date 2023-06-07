package com.example.nuclear.model;

import com.example.nuclear.HelloApplication;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GrenadeBar extends  Drawing{
    private Image image;
    private int posX;
    private int posY;

    public GrenadeBar(int posX, int posY) {
        this.posX=posX;
        this.posY=posY;
    }
    
    @Override
    public void draw(GraphicsContext gc) {

        Image image = new Image("file:" + HelloApplication.class.getResource("Guns/bombita.png").getPath());
        double width = 50;  // Ancho deseado
        double height = 50; // Alto deseado
        gc.drawImage(image, posX,posY,width,height);
    }
}
