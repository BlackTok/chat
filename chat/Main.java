package chat;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double screenHeight = screenSize.height;
    int sceneSize = (int) (screenHeight * 0.8);
    static Scene scene;
    protected FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, sceneSize, sceneSize));

        primaryStage.setMinWidth(sceneSize);
        primaryStage.setMinHeight(sceneSize);

        Scene scene = primaryStage.getScene();
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Controller controller = new Controller();
                controller.printMsg();
            }
        });

        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
