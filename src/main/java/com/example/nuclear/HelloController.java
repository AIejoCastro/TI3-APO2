package com.example.nuclear;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private BorderPane miAnchorPane;
    @FXML
    private Canvas canvas;
    @FXML
    private ImageView playBT;
    @FXML
    private ImageView quitBT;


    public void initialize() {
        // Establecer la imagen de fondo
        String uri = "file:"+HelloApplication.class.getResource("Misc/FondoGame.gif").getPath();
        Image imagenDeFondo = new Image(uri);
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imagenDeFondo, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImage);
        miAnchorPane.setBackground(background);

        quitBT.setOnMouseClicked(event -> {
            //Cerrar la ventana al hacer clic en la imagen "quitBT"
            Stage stage = (Stage) miAnchorPane.getScene().getWindow();
            stage.close();
        });
        playBT.setOnMouseClicked(event -> {
            //Cerrar la ventana al hacer clic en la imagen "quitBT"
            Stage stage=(Stage)this.playBT.getScene().getWindow();
            stage.close();
            HelloApplication.openWindow("game-scene-one.fxml");
        });
    }

}
