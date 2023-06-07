package com.example.nuclear.model;

import com.example.nuclear.HelloApplication;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import com.example.nuclear.GameSceneOne;

import java.util.Iterator;
import java.util.List;

public class ThrowGrenade extends Drawing {

    private VectorToGrenade pos;
    private VectorToGrenade dir;
    private Image image;
    private double width;
    private double height;
    private double speed;
    private double gravity;
    private double damageRadius;




    public ThrowGrenade(VectorToGrenade pos, VectorToGrenade dir) {
        this.pos = pos;
        this.dir = dir;
        this.width = 30;  // Tamaño deseado de ancho
        this.height = 30;  // Tamaño deseado de alto
        this.speed = 5.0; // Velocidad de la granada
        this.gravity = 0.1; // Gravedad que afecta la caída de la granada
        this.damageRadius = 50.0; // Radio de daño de la granada

    }

    public void drawExplotion(GraphicsContext gc){
        Image explosionImage= new Image("file:"+ HelloApplication.class.getResource("Guns/explosion.png").getPath());
        // Dibujar la imagen de explosión cuando la granada ha explotado
        gc.drawImage(explosionImage, pos.getX() - width / 2, pos.getY() - height / 2, 40, 40);
    }

    @Override
    public void draw(GraphicsContext gc) {
        double angle = Math.toDegrees(Math.atan2(dir.getY(), dir.getX()));

        gc.setFill(Color.DARKGRAY);
        gc.fillOval(pos.getX() - 5, pos.getY() - 5, 10, 10);

        gc.save();  // Guardar el estado gráfico actual
        gc.translate(pos.getX(), pos.getY());  // Establecer el punto de referencia en el centro de la bala
        gc.rotate(angle);  // Rotar el contexto gráfico según el ángulo de dirección
        gc.drawImage(image, -width / 2, -height / 2, width, height);  // Dibujar la imagen de la bala centrada
        gc.restore();  // Restaurar el estado gráfico original

        pos.setX(pos.getX() + dir.getX());
        pos.setY(pos.getY() + dir.getY());

    }



    public void update() {
        // Actualizar la posición de la granada
        pos.setX(pos.getX() + dir.getX() * speed);
        pos.setY(pos.getY() + dir.getY() * speed + gravity);
        gravity += 0.1; // Incrementar la gravedad para simular la caída de la granada
    }

    public boolean isExploded() {
        // Verificar si la granada ha explotado (cuando ha alcanzado el suelo)
        return pos.getY() >= GameSceneOne.getGroundLevel();



    }

    public boolean hasCollidedWithEnemy(Enemy enemy) {
        boolean pass=false;
        // Calcular la distancia entre los puntos posiciones de la granada y el enemigo
        double distance = Math.sqrt(Math.pow(pos.getX() - enemy.getPos().getX(), 2) + Math.pow(pos.getY() - enemy.getPos().getY(), 2));
        if(distance <= damageRadius){
            pass=true;
        }
        return pass;
    }

    public Enemy explode(List<Enemy> enemies) {
        // Realizar acciones después de la explosión, como eliminar a los enemigos dentro del radio de daño

        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (hasCollidedWithEnemy(enemy)) {
                // Eliminar al enemigo
                return  enemy;
            }
        }
        return  null;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getDamageRadius() {
        return damageRadius;
    }

    public void setDamageRadius(double damageRadius) {
        this.damageRadius = damageRadius;
    }

    public VectorToGrenade getPos() {
        return pos;
    }

    public void setPos(VectorToGrenade pos) {
        this.pos = pos;
    }

    public VectorToGrenade getDir() {
        return dir;
    }

    public void setDir(VectorToGrenade dir) {
        this.dir = dir;
    }


    // Resto de los métodos getters y setters
    // ...
}