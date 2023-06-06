package com.example.nuclear.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Grenade extends  Drawing {


    private String name;
    private int blastRradius;

    private int posX;

    private  int posY;


    public Grenade(String name, int posX, int posY) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.blastRradius=50;
    }

    public int getBlastRradius() {
        return blastRradius;
    }

    public void setBlastRradius(int blastRradius) {
        this.blastRradius = blastRradius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void explotion(){};

    @Override
    public void draw(GraphicsContext gc) {



        Image image = new Image("c:\\Users\\Admin\\Desktop\\Tercer semestre\\Integradora3\\NuclearThrone\\Nuclear\\src\\main\\resources\\com\\example\\nuclear\\bombita.png");

        // Especificar el ancho y alto deseados
        double width = 30;  // Ancho deseado
        double height = 30; // Alto deseado

        // Dibujar la imagen en el GraphicsContext con el tamaño especificado
        gc.drawImage(image, posX, posY, width, height);





    }

}
