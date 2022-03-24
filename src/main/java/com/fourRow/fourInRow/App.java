package com.fourRow.fourInRow;
import control.Control;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;




public class App extends Application {
@Override
public void start(Stage stage) {
Control controller =new Control();
Scene scene = new Scene(controller.intialiserMaquette(), 640, 480);
stage.setScene(scene);
stage.show();

}
public static void main(String[] args) {
launch();
}





}