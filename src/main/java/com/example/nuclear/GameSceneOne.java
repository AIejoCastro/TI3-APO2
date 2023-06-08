package com.example.nuclear;

import com.example.nuclear.model.*;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.example.nuclear.HelloApplication.openWindow;

public class GameSceneOne {
    @FXML
    private Canvas canvas;
    @FXML
    private ImageView fondoIMGVIEW;
    @FXML
    private AnchorPane fondoNivel;
    private GraphicsContext gc;
    private static final double GROUND_LEVEL = 500.0;
    private ArrayList<Level> levels;
    private int currentLevel = 0;

    Image backgroundImage;

    private int shotsFired=0;
    private GrenadeBar granadeBar;
    private boolean isAlive = true;
    private boolean Apressed = false;
    private boolean Wpressed = false;
    private boolean Spressed = false;
    private boolean Dpressed = false;

    private Avatar avatar;
    private Paredes paredes;

    private ArrayList<Weapon> weapons;
    private ArrayList<Grenade> grenades;
    private LifeBar lifeBar;
    private BulletBar bulletBar;
    private Scene scene;

    public GameSceneOne() {
        canvas = new Canvas();  // Inicialización del objeto canvas
    }
    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(this::handleKeyPressed);
        canvas.setOnKeyReleased(this::handleKeyReleased);
        canvas.setOnKeyReleased(this::onKeyReleased);
        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseMoved(this::onMouseMoved);
        avatar = new Avatar();
        levels = new ArrayList<>();
        weapons = new ArrayList<>();
        grenades =  new ArrayList<>();


        //Barras o indicadores

        //De vida
        Image[] lifeImages = {
                new Image("file:" + HelloApplication.class.getResource("Life Bar/2golpes.png").getPath()),   // Imagen para ultimo golpe
                new Image("file:" + HelloApplication.class.getResource("Life Bar/1golpe.png").getPath()),   // Imagen para un  golpes
                new Image("file:" + HelloApplication.class.getResource("Life Bar/fullVida.png").getPath())  // Imagen full de vida

        };

        lifeBar = new LifeBar(lifeImages,10,10);

        ///Inicializar bullet bar y luego dibujarla


        //De balas
        Image[] bulletBarImages = {
                new Image("file:" + HelloApplication.class.getResource("Gun Bar/indicadorDebala0.png").getPath()),   // Imagen para no tener balas
                new Image("file:" + HelloApplication.class.getResource("Gun Bar/indicadordebala1.png").getPath()),   // 1 bala
                new Image("file:" + HelloApplication.class.getResource("Gun Bar/indicador2debala.png").getPath()),  // 2 balas
                new Image("file:" + HelloApplication.class.getResource("Gun Bar/indicadordebala3.png").getPath()),  // 3 balas
                new Image("file:" + HelloApplication.class.getResource("Gun Bar/indicadorDebala4.png").getPath()),  // 4 balas
                new Image("file:" + HelloApplication.class.getResource("Gun Bar/indicadorDebala5.png").getPath())  // full balas
        };

        bulletBar = new BulletBar(bulletBarImages,10,45);
        granadeBar = new GrenadeBar(10,60);

        // Generate the first map
        Level l1 = new Level(0);
        double randomNumberHeight = Math.random() * (canvas.getHeight() - 1 + 1) + 1;
        double randomNumberWidth = Math.random() * (canvas.getWidth() - 1 + 1) + 1;
        Enemy e = new Enemy(new Vector(randomNumberWidth, randomNumberHeight));
        new Thread(e).start();
        l1.getEnemies().add(e);
        for (int i = 0; i < 9; i++) {
            randomNumberHeight = Math.random() * (canvas.getHeight() - 1 + 1) + 1;
            randomNumberWidth = Math.random() * (canvas.getWidth() - 1 + 1) + 1;
            l1.getEnemies().add(new Enemy(new Vector(randomNumberWidth, randomNumberHeight)));
        }
        levels.add(l1);

        //armas
        // Crear y configurar las armas
        levels.get(0).generarArmaEnSuelo("Ak",400, 700);
        levels.get(0).generarArmaEnSuelo("Ak",1300, 600);
        levels.get(0).generarArmaEnSuelo("Ak",1000, 100);

        weapons.addAll(l1.getWeaponsInTheFloor());

        //Granadas
        levels.get(0).generarGranadasEnElSuelo("Grenade", (int) canvas.getWidth()/2, 800);
        levels.get(0).generarGranadasEnElSuelo("Grenade", 200, 200);
        levels.get(0).generarGranadasEnElSuelo("Grenade", 1200, 300);
        levels.get(0).generarGranadasEnElSuelo("Grenade", (int) canvas.getWidth() / 2, (int) canvas.getHeight() / 2);

        grenades.addAll(l1.getGrenadesInTheFloor());

        // Generate the second map
        Level l2 = new Level(1);
        l2.setColor(Color.GRAY);
        for (int i = 0; i < 15; i++) {
            randomNumberHeight = Math.random() * (canvas.getHeight() - 1 + 1) + 1;
            randomNumberWidth = Math.random() * (canvas.getWidth() - 1 + 1) + 1;
            l2.getEnemies().add(new Enemy(new Vector(randomNumberWidth, randomNumberHeight)));
        }
        levels.add(l2);

        Level l3 = new Level(2);
        for (int i = 0; i < 25; i++) {
            randomNumberHeight = Math.random() * (canvas.getHeight() - 1 + 1) + 1;
            randomNumberWidth = Math.random() * (canvas.getWidth() - 1 + 1) + 1;
            l3.getEnemies().add(new Enemy(new Vector(randomNumberWidth, randomNumberHeight)));
        }
        levels.add(l3);

        // Crear y configurar las armas
        levels.get(2).generarArmaEnSuelo("Ak",400, 700);
        levels.get(2).generarArmaEnSuelo("Ak",1300, 600);
        levels.get(2).generarArmaEnSuelo("Ak",1000, 100);

        weapons.addAll(l1.getWeaponsInTheFloor());

        //Granadas
        levels.get(2).generarGranadasEnElSuelo("Grenade", (int) canvas.getWidth()/2, 800);
        levels.get(2).generarGranadasEnElSuelo("Grenade", 200, 200);
        levels.get(2).generarGranadasEnElSuelo("Grenade", 1200, 300);
        levels.get(2).generarGranadasEnElSuelo("Grenade", (int) canvas.getWidth() / 2, (int) canvas.getHeight() / 2);

        grenades.addAll(l1.getGrenadesInTheFloor());

        drawParedes(0);
        drawParedes(1);
        drawParedes(2);
        draw();
    }

    public void handleKeyPressed(KeyEvent event) {
        System.out.println(event.getCode());
        switch (event.getCode()) {
            case W:
                Wpressed = true;
                avatar.keyPressed("W");
                break;
            case A:
                Apressed = true;
                avatar.keyPressed("A");
                break;
            case S:
                Spressed = true;
                avatar.keyPressed("S");
                break;
            case D:
                Dpressed = true;
                avatar.keyPressed("D");
                break;

        }
    }

    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                Wpressed = false;
                break;
            case A:
                Apressed = false;
                break;
            case S:
                Spressed = false;
                break;
            case D:
                Dpressed = false;
                break;
        }
    }

    public void onKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                Wpressed = false;
                break;
            case A:
                Apressed = false;
                break;
            case S:
                Spressed = false;
                break;
            case D:
                Dpressed = false;
                break;

            case R:
                if(avatar.getWeapon()!=null & shotsFired>=5){
                    avatar.getWeapon().reload();
                    shotsFired = 0;
                    bulletBar.restart();

                    avatar.getWeapon().drawReload(gc);
                }
                break;

            case SPACE:

                    //Tira granada

                break;

        }
    }
    private void onMouseMoved(MouseEvent e) {
        double relativePosition = e.getX()-avatar.pos.getX();
        avatar.setFacingRight(
                relativePosition > 0
        );
    }

    private void onMousePressed(MouseEvent e) {
        System.out.println("X: " + e.getX() + " Y: " + e.getY());

        if (avatar.getWeapon() != null && shotsFired<5) {


            double diffX = e.getX() - avatar.pos.getX();
            double diffY = e.getY() - avatar.pos.getY();
            Vector diff = new Vector(diffX, diffY);
            diff.normalize();
            diff.setMag(7);

            Bullet bullet = new Bullet(new Vector(avatar.pos.getX(), avatar.pos.getY()), diff);
            Image image = new Image("file:" + HelloApplication.class.getResource("Guns/bulletLaser.png").getPath());
            bullet.setImage(image);

            levels.get(currentLevel).getBullets().add(bullet);
            shotsFired++;
            System.out.println("shots fired " + shotsFired);
            avatar.getWeapon().shoot();

            //disminuir del indicador
            bulletBar.decreaseBullet();
        } else if(avatar.getGrenade()!= null){


            double diffX = e.getX() - avatar.pos.getX();
            double diffY = e.getY() - avatar.pos.getY();
            VectorToGrenade diff = new VectorToGrenade(diffX, diffY);
            diff.normalize();
            double speed = 8.0; // Velocidad deseada
            diff.multiply(speed);

            ThrowGrenade grenade = new ThrowGrenade(new VectorToGrenade(avatar.pos.getX(), avatar.pos.getY()),diff);
            Image image = new Image("file:" + HelloApplication.class.getResource("Guns/bombita.png").getPath());
            grenade.setImage(image);

            levels.get(currentLevel).getThrowGranades().add(grenade);




            // avatar.getGrenade().shootGranade();

            //disminuir del indicador
            System.out.println("granada disparada");

            //Desequipar granada
            avatar.unequipGrenade();

        }else {
            System.out.println("No weapon");
        }
    }

    private void draw() {
        Thread ae = new Thread(() -> {
            while (isAlive) {
                // Draw on the canvas
                Level level = levels.get(currentLevel);

                Platform.runLater(() -> {
                    gc.drawImage(level.getImg(), 0, 0, canvas.getWidth(), canvas.getHeight());
                    gc.setFill(Color.BLACK);
                    gc.fillRect(canvas.getWidth() - 10, 0, 10, 10);
                    avatar.draw(gc);
                    avatar.drawGrenade(gc);

                    avatar.setMoving(Wpressed || Spressed || Dpressed || Apressed);
                    if (!level.getBullets().isEmpty()) {
                        for (int i = 0; i < level.getBullets().size(); i++) {
                            collisionParedesBullets(level.getBullets().get(i));
                            if (!level.getBullets().isEmpty()) {
                                level.getBullets().get(i).draw(gc);
                            }
                        }
                    }
                    if (!level.getBullets().isEmpty()) {
                        for (int i = 0; i < level.getBullets().size(); i++) {
                            if (isOutside(level.getBullets().get(i).pos.getX(), level.getBullets().get(i).pos.getY())) {
                                level.getBullets().remove(i);
                            }
                        }
                    }
                    if (!level.getThrowGranades().isEmpty()) {
                        for (int i = 0; i < level.getThrowGranades().size(); i++) {
                            level.getThrowGranades().get(i).draw(gc);
                            if (isOutside(level.getThrowGranades().get(i).getPos().getX(), level.getThrowGranades().get(i).getPos().getY())) {
                                if (!level.getThrowGranades().isEmpty()) {
                                    level.getThrowGranades().remove(i);
                                }
                            }
                        }
                    }
                    if (!level.getEnemies().isEmpty()) {
                        for (int i = 0; i < level.getEnemies().size(); i++) {
                            level.getEnemies().get(i).draw(gc);
                        }
                    }
                    for (int i = 0; i < level.getParedes().size(); i++) {
                        level.getParedes().get(i).draw(gc);
                        collisionParedesAvatar(level.getParedes().get(i));
                    }

                    //Armas en el suelo
                    for (int i = 0; i < level.getWeaponsInTheFloor().size(); i++) {
                        level.getWeaponsInTheFloor().get(i).draw(gc);
                    }

                    for (int i = 0; i < level.getGrenadesInTheFloor().size(); i++) {
                        level.getGrenadesInTheFloor().get(i).draw(gc);
                    }


                    //dibujar barra de vida

                    lifeBar.draw(gc);

                    //barra de armas
                    if(avatar.getWeapon()!=null){
                        bulletBar.draw(gc);
                    }

                    if(avatar.getGrenade()!=null){
                        granadeBar.draw(gc);

                    }

                        ///reload


                });

                //Colision con de enemigos con paredes
                for (Enemy enemies : level.getEnemies()) {
                    for (Paredes paredes : level.getParedes()) {
                        double diffX = enemies.pos.getX() - paredes.getX();
                        double diffY = enemies.pos.getY() - paredes.getY();
                        if ((diffX > (-enemies.getWidth()) && diffX < paredes.getWidth()) && (diffY > (-enemies.getHeight()) && diffY < paredes.getHeight())) {
                            double diffSup = Math.abs(diffY + enemies.getHeight());
                            double diffBottom = Math.abs(diffY - paredes.getHeight());
                            double diffRight = Math.abs(diffX - paredes.getWidth());
                            double diffLeft = Math.abs(diffX + enemies.getWidth());
                            if (diffSup < 9) {
                                enemies.pos.setY(enemies.pos.getY() - 5);
                            }
                            if (diffBottom < 9) {
                                enemies.pos.setY(enemies.pos.getY() + 5);
                            }
                            if (diffRight < 9) {
                                enemies.pos.setX(enemies.pos.getX() + 5);
                            }
                            if (diffLeft < 9) {
                                enemies.pos.setX(enemies.pos.getX() - 5);
                            }
                        }
                    }
                }

                // Paredes
                if (avatar.pos.getX() < 25) {
                    avatar.pos.setX(25);
                }
                if (avatar.pos.getY() > canvas.getHeight() - 25) {
                    avatar.pos.setY(canvas.getHeight() - 25);
                }
                if (avatar.pos.getX() > canvas.getWidth() - 25) {
                    avatar.pos.setX(canvas.getWidth() - 25);
                }
                if (avatar.pos.getY() < 0) {
                    if (level.getEnemies().size() == 0) {
                        if(currentLevel == 0) {
                            currentLevel = 1;
                        } else if (currentLevel == 1) {
                            currentLevel = 2;
                        }
                    } else {
                        avatar.pos.setY(canvas.getHeight());
                    }
                }

                // Dentro del bucle de dibujo en el método draw()
                for (int i = 0; i < level.getWeaponsInTheFloor().size(); i++) {
                    Weapon arma = level.getWeaponsInTheFloor().get(i);

                    double distance = Math.sqrt(
                            Math.pow(avatar.pos.getX() - arma.getPosX(), 2) +
                                    Math.pow(avatar.pos.getY() - arma.getPosY(), 2)
                    );

                    if (distance < 30) {

                        if(avatar.getGrenade()!= null){
                            avatar.unequipGrenade();
                        }
                        // El jugador está cerca del arma, puede recojerla
                        avatar.equipWeapon(arma); // Agrega el arma a la lista de armas del jugador
                        level.getWeaponsInTheFloor().remove(i); // Remueve el arma del suelo
                        break; // Sale del bucle, asumiendo que solo se puede recojer una arma a la vez
                    }
                }

                    /// Dentro del bucle de dibujo en el método draw()
                    for (int k = 0; k < level.getGrenadesInTheFloor().size(); k++) {
                        Grenade grenade = level.getGrenadesInTheFloor().get(k);

                        double distanceGranade = Math.sqrt(
                                Math.pow(avatar.pos.getX() - grenade.getPosX(), 2) +
                                        Math.pow(avatar.pos.getY() - grenade.getPosY(), 2)
                        );

                        if (distanceGranade < 30) {
                            // El jugador está cerca del arma, puede recojerla

                            if(avatar.getWeapon()!=null){
                                avatar.unequipWeapon();
                            }

                            avatar.equipGrenade(grenade); // Agrega el arma a la lista de armas del jugador
                            level.getGrenadesInTheFloor().remove(k); // Remueve el arma del suelo
                            break; // Sale del bucle, asumiendo que solo se puede recojer una arma a la vez
                        }


                }

                // Collisions with enemies
                for (int i = 0; i < level.getBullets().size(); i++) {
                    Bullet bn = level.getBullets().get(i);
                    for (int j = 0; j < level.getEnemies().size(); j++) {
                        Enemy en = level.getEnemies().get(j);

                        double distance = Math.sqrt(
                                Math.pow(en.pos.getX() - bn.pos.getX(), 2) + Math.pow(en.pos.getY() - bn.pos.getY(), 2)
                        );

                        if (distance < 25) {
                            en.setBulletsReceived(en.getBulletsReceived() + 1);
                            if (!level.getBullets().isEmpty()) {
                                level.getBullets().remove(i);
                            }
                            if (en.getBulletsReceived() == 3) {
                                level.getEnemies().remove(j);
                            }
                        }
                    }
                }

                //Colsion de enemigos con enemigos
                for (int i = 0; i < level.getEnemies().size(); i++) {
                    Enemy bn = level.getEnemies().get(i);
                    for (int j = 0; j < level.getEnemies().size(); j++) {
                        if (j == i) {
                            continue;
                        }
                        Enemy en = level.getEnemies().get(j);

                        double distance = Math.sqrt(
                                Math.pow(en.pos.getX() - bn.pos.getX(), 2) + Math.pow(en.pos.getY() - bn.pos.getY(), 2)
                        );

                        if (distance < 25) {
                            double angle = Math.atan2(en.pos.getY() - bn.pos.getY(), en.pos.getX() - bn.pos.getX());
                            double offsetX = Math.cos(angle) * 1;
                            double offsetY = Math.sin(angle) * 1;

                            Vector vector1 = new Vector(bn.pos.getX() - offsetX, bn.pos.getY() - offsetY);
                            Vector vector2 = new Vector(en.pos.getX() + offsetX, en.pos.getY() + offsetY);
                            bn.setPos(vector1);
                            en.setPos(vector2);
                        }
                    }
                }


                //Colision de enemigos con el jugador
                for (int j = 0; j < level.getEnemies().size(); j++) {
                    Enemy en = level.getEnemies().get(j);

                    double distance = Math.sqrt(
                            Math.pow(en.pos.getX() - avatar.pos.getX(), 2) + Math.pow(en.pos.getY() - avatar.pos.getY(), 2)
                    );

                    if (distance < 25) {
                        double angle = Math.atan2(en.pos.getY() - avatar.pos.getY(), en.pos.getX() - avatar.pos.getX());
                        double offsetX = Math.cos(angle) * 40;
                        double offsetY = Math.sin(angle) * 40;
                        avatar.setBitesReceived(avatar.getBitesReceived() + 1);
                        lifeBar.decreaseLife();
                        Vector pos = new Vector(en.pos.getX() + offsetX, en.pos.getY() + offsetY);
                        en.setPos(pos);
                        if (avatar.getBitesReceived() == 3) {
                            System.exit(0);
                      //      HelloApplication.openWindow("death-scene.fxml");
                        }
                    }
                }

                for (int i = 0; i < level.getThrowGranades().size(); i++) {
                    ThrowGrenade bn = level.getThrowGranades().get(i);
                    for (int j = 0; j < level.getEnemies().size(); j++) {
                        Enemy en = level.getEnemies().get(j);

                        double distance = Math.sqrt(
                                Math.pow(en.pos.getX() - bn.getPos().getX(), 2) + Math.pow(en.pos.getY() - bn.getPos().getY(), 2)
                        );

                        if (distance < 25) {
                            if (!level.getThrowGranades().isEmpty()) {
                                level.getThrowGranades().remove(i);
                                bn.drawExplotion(gc);
                                level.getEnemies().remove(j);
                            }
                        }
                    }
                }

                //Enemigos a donde miran
                for (int i = 0; i < levels.get(currentLevel).getEnemies().size(); i++) {
                    double relativePosition = avatar.pos.getX() - levels.get(currentLevel).getEnemies().get(i).pos.getX();
                    levels.get(currentLevel).getEnemies().get(i).setFacingRight(relativePosition > 0);
                }

                //Los enemigos te persiguen
                for (int i = 0; i < level.getEnemies().size(); i++) {
                    double enemyX = level.getEnemies().get(i).pos.getX();
                    double enemyY = level.getEnemies().get(i).pos.getY();
                    double playerX = avatar.pos.getX();
                    double playerY = avatar.pos.getY();

                    double diffX = playerX - enemyX;
                    double diffY = playerY - enemyY;
                    Vector diff = new Vector(diffX, diffY);
                    diff.normalize();
                    diff.setMag(3);

                    level.getEnemies().get(i).changeImage();
                    level.getEnemies().get(i).pos.setX(enemyX + diff.getX());
                    level.getEnemies().get(i).pos.setY(enemyY + diff.getY());
                }

                // Movement
                if (Wpressed) {
                    avatar.pos.setY(avatar.pos.getY() - 4);
                }
                if (Apressed) {
                    avatar.pos.setX(avatar.pos.getX() - 4);
                }
                if (Spressed) {
                    avatar.pos.setY(avatar.pos.getY() + 4);
                }
                if (Dpressed) {
                    avatar.pos.setX(avatar.pos.getX() + 4);
                }

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ae.setDaemon(true); // Establecer como daemon para que se detenga cuando se cierre la aplicación
        ae.start();
    }

    private void collisionParedesAvatar(Paredes paredes) {
        double diffX=avatar.pos.getX() -paredes.getX();
        double diffY=avatar.pos.getY() -paredes.getY();
        if((diffX> (-avatar.getWidth()) && diffX< paredes.getWidth()) && (diffY> (-avatar.getHeight()) && diffY< paredes.getHeight()) ){
            double diffSup= Math.abs(diffY+ avatar.getHeight());
            double diffBottom= Math.abs(diffY - paredes.getHeight());
            double diffRight= Math.abs(diffX - paredes.getWidth());
            double diffLeft= Math.abs(diffX+ avatar.getWidth());
            if (diffSup < 9){
                avatar.pos.setY(avatar.pos.getY()-5);
            }
            if (diffBottom < 9){
                avatar.pos.setY(avatar.pos.getY()+5);
            }
            if (diffRight < 9){
                avatar.pos.setX(avatar.pos.getX()+5);
            }
            if (diffLeft < 9){
                avatar.pos.setX(avatar.pos.getX()-5);
            }


        }
    }

    private void collisionParedesBullets(Bullet bullet) {
        for (Paredes paredes : levels.get(currentLevel).getParedes()) {
            double diffX = bullet.pos.getX() - paredes.getX();
            double diffY = bullet.pos.getY() - paredes.getY();
            if ((diffX > (-bullet.getWidth()) && diffX < paredes.getWidth()) && (diffY > (-bullet.getHeight()) && diffY < paredes.getHeight())) {
                double diffSup = Math.abs(diffY + bullet.getHeight());
                double diffBottom = Math.abs(diffY - paredes.getHeight());
                double diffRight = Math.abs(diffX - paredes.getWidth());
                double diffLeft = Math.abs(diffX + bullet.getWidth());
                if (diffSup < 9) {
                    if (!levels.get(currentLevel).getBullets().isEmpty()) {
                        levels.get(currentLevel).getBullets().remove(bullet);
                    }
                }
                if (diffBottom < 9) {
                    if (!levels.get(currentLevel).getBullets().isEmpty()) {
                        levels.get(currentLevel).getBullets().remove(bullet);
                    }
                }
                if (diffRight < 9) {
                    if (!levels.get(currentLevel).getBullets().isEmpty()) {
                        levels.get(currentLevel).getBullets().remove(bullet);
                    }
                }
                if (diffLeft < 9) {
                    if (!levels.get(currentLevel).getBullets().isEmpty()) {
                        levels.get(currentLevel).getBullets().remove(bullet);
                    }
                }
            }
        }
    }

    public static double getGroundLevel() {
        return GROUND_LEVEL;
    }

    public boolean isOutside(double x, double y) {
        return x < -10 || y < -10 || x > canvas.getWidth() || y > canvas.getHeight();
    }
    private double clampRange(double value,int min, double max){
        if(value<min){
            return min;
        } else if (value>max){
            return max;
        }else {
            return value;
        }
    }
    public void camera(){
        this.scene = canvas.getScene();
        Rectangle camera = new Rectangle();

        camera.widthProperty().bind(scene.widthProperty());
        camera.heightProperty().bind(scene.heightProperty());
        camera.xProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(avatar.pos.getX() - scene.getWidth() / 2, 0, canvas.getWidth() - scene.getWidth()),
                avatar.getxProperty(), scene.widthProperty()));
        camera.yProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(avatar.pos.getY() - scene.getHeight() / 2, 0, canvas.getHeight() - scene.getHeight()),
                avatar.getyProperty(), scene.heightProperty()));
    }


    public void drawParedes(int index) {
        String path = "file:" + HelloApplication.class.getResource("Misc/ParedFill.png").getPath();
        int height = 590;
        int heighty = 930;
        int increment = 30;
        double canvasHeight = canvas.getHeight();
        double canvasWidth = canvas.getWidth();
        Level lvl = levels.get(index);

        switch (index) {
            case 0:
                for (int i = 0; i < 20; i++) {
                    int xPos = (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, xPos, 10));
                }

                for (int i = 0; i < 10; i++) {
                    int yPos = (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, 600, yPos));
                }

                for (int i = 0; i < 5; i++) {
                    int yPos = (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, 800, yPos));
                }

                for (int i = 0; i < 17; i++) {
                    int xPos = heighty + (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, xPos, 350));
                }

                for (int i = 0; i < 20; i++) {
                    int xPos = (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, xPos, 620));
                }

                for (int i = 0; i < 17; i++) {
                    int yPos = height + (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, 800, yPos));
                }
                break;
            case 1:
                for (int i = 0; i < 35; i++) {
                    int xPos = (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, xPos, 10));
                }

                for (int i = 0; i < 20; i++) {
                    int yPos = (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, 600, yPos));
                }

                for (int i = 0; i < 10; i++) {
                    int xPos = (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, xPos, 300));
                }

                for (int i = 0; i < 5; i++) {
                    int yPos = (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, 800, yPos));
                }

                for (int i = 0; i < 22; i++) {
                    int xPos = heighty + (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, xPos, 350));
                }

                for (int i = 0; i < 15; i++) {
                    int xPos = (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, xPos, 620));
                }

                for (int i = 0; i < 17; i++) {
                    int yPos = height + (i * increment);
                    lvl.getParedes().add(new Paredes(canvas, path, 800, yPos));
                }
                break;
            case 2:
                break;
        }
    }
}