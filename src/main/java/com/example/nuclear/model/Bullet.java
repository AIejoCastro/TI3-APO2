package com.example.nuclear.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bullet extends Drawing {

    private Vector dir;
    private Image image;
    private double width;
    private double height;

    public Bullet(Vector pos, Vector dir) {
        this.pos = pos;
        this.dir = dir;
        this.width = 30;  // Tamaño deseado de ancho
        this.height = 30;  // Tamaño deseado de alto

    }

    @Override
    public void draw(GraphicsContext gc) {

        double angle = Math.toDegrees(Math.atan2(dir.getY(), dir.getX()));

        gc.setFill(Color.DARKGRAY);
        gc.fillOval(pos.getX()-5, pos.getY()-5, 10,10);


        gc.save();  // Guardar el estado gráfico actual
        gc.translate(pos.getX(), pos.getY());  // Establecer el punto de referencia en el centro de la bala
        gc.rotate(angle);  // Rotar el contexto gráfico según el ángulo de dirección
        gc.drawImage(image, -width / 2, -height / 2, width, height);  // Dibujar la imagen de la bala centrada
        gc.restore();  // Restaurar el estado gráfico original

        pos.setX(pos.getX() + dir.getX());
        pos.setY(pos.getY() + dir.getY());
    }

    public void drawGranade(GraphicsContext gc) {


    }


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
