package com.example.nuclear;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        openWindow("hello-view.fxml");

    }
    public static void openWindow(String fxml){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            ModuleLayer.Controller controller = fxmlLoader.getController();
            Stage stage = new Stage();
            GameSceneOne gameSceneOne= new GameSceneOne();
            Scene scene = new Scene(fxmlLoader.load());
            String uri = "file:"+HelloApplication.class.getResource("Misc/Aim.png").getPath();
            Cursor cursorPersonalizado = Cursor.cursor(uri);
            scene.setCursor(cursorPersonalizado);
            stage.setTitle("NuclearThrone.exe");
            stage.setScene(scene);
            stage.show();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}