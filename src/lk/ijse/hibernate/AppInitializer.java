package lk.ijse.hibernate;/**
 * @author : K.S.P.D De Silva <sanodeemantha@gmail.com>
 * @since : 1/20/21
 **/


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("view/MainForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Customer Form");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
