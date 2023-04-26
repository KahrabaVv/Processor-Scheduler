/*please read this before project
*I know that rest of project working on javafx 19 but scene builder doesn't response to javafx 19 so I worked on javafx 17
*/
package com.nada.sb_test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AlgoScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        Image icon = new Image("D:\\JavaFx_Proj\\SB_test\\src\\main\\java\\com\\nada\\sb_test\\cpu_icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("CPU Scheduler");
        stage.setScene(scene);
        stage.show();
    }
}