package chat;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class MainChat {
    private static double textWrapWidthCoeff1 = 0.7;
    private static double textWrapWidthCoeff2 = 1.1;
    private static double textXCoeff = 0.3;
    private static double boxYCoeff = 0.9;
    private static double textHeightCoeff = 1.3;

    @FXML
    public static VBox mainChat;
    @FXML
    public static TextField msgText;

    static void addMsg() {
        String name = "Алексей";
        double mainChatWidth = mainChat.getWidth();

        String msg = msgText.getText();
        msgText.clear();

        Label lblName = new Label(name);
        lblName.setPrefSize(mainChatWidth,50);
        lblName.setAlignment(Pos.TOP_RIGHT);
        lblName.setTextAlignment(TextAlignment.RIGHT);

        Text text = new Text(msg);
        text.setY(40);
        if (text.getBoundsInLocal().getWidth() > mainChatWidth * 0.7) {
            text.setWrappingWidth(mainChatWidth * 0.7);
            text.setX(mainChatWidth * 0.3);
        } else {
            text.setWrappingWidth(text.getBoundsInLocal().getWidth() * 1.1);
            text.setX(mainChatWidth - text.getBoundsInLocal().getWidth());
        }
        text.setTextAlignment(TextAlignment.LEFT);
        text.getStyleClass().add("textStyle");

        Rectangle msgBox = new Rectangle();
        Bounds bounds = text.getBoundsInLocal();
        double textWidth = bounds.getWidth();
        double textHeight = bounds.getHeight();
        double textPosX = bounds.getMinX();
        double textPosY = bounds.getMinY();
        msgBox.setX(textPosX);
        msgBox.setY(textPosY * 0.9);
        msgBox.setWidth(text.getWrappingWidth());
        msgBox.setHeight(textHeight * 1.3);
        msgBox.setFill(Color.web("#454261"));
        msgBox.setArcWidth(10);
        msgBox.setArcHeight(10);

        Group newMsg = new Group();
        newMsg.getChildren().addAll(lblName, msgBox, text);
        newMsg.prefWidth(mainChatWidth);
        newMsg.prefHeight(200);

        mainChat.getChildren().add(newMsg);
    }

    static void resizeMsg() {
        System.out.println(mainChat.getWidth());
    }
}
