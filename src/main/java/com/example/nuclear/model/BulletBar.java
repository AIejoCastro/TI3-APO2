package com.example.nuclear.model;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BulletBar extends  Drawing{
    private Image[] lifeImages;
    private int currentState;

    private int posX;

    private int posY;

    public BulletBar(Image[] lifeImages, int posX, int posY) {
        this.lifeImages = lifeImages;
        this.currentState = lifeImages.length - 1; // Establecer el estado inicial al máximo
        this.posX=posX;
        this.posY=posY;
    }



    public void decreaseBullet() {
        if (currentState > 0) {
            currentState--;  // Disminuir el estado actual

        }
    }


    public void restart(){

        currentState= lifeImages.length-1;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public void draw(GraphicsContext gc, double x, double y) {

    }

    @Override
    public void draw(GraphicsContext gc) {
        Image currentImage = lifeImages[currentState];



        double width = 180;  // Ancho deseado
        double height = 50; // Alto deseado
        gc.drawImage(currentImage, posX,posY,width,height);





    }
}

