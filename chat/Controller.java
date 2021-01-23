package chat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    //private final Map<String, List<Message>> chats = new LinkedHashMap<>();
    //private final String[] chatNames = {"Chat 1", "Chat 2"};
    //private final String activeChat = chatNames[0];
    private final List<Message> msgList = new ArrayList<>();

    private static final double textWrapWidthCoeff1 = 0.7;
    private static final double textWrapWidthCoeff2 = 1.3;
    private static final double textXCoeff = 0.3;
    private static final String boxColor = "#5a5685";
    private static final int indent = 5;

    @FXML
    public ListView chatList;
    @FXML
    private VBox mainChat;
    @FXML
    private TextField msgText;

//    public void addChats() {
//        for (String chatName : chatNames) {
//            chats.put(chatName, new ArrayList<>());
//
//            List<String> oList = Arrays.asList(chatNames);
//            chatList.getItems().setAll(oList);
//        }
//    }

    public VBox getMainChat() {
        return mainChat;
    }

    @FXML
    public void sendMsg(ActionEvent actionEvent) {
        String name, text;
        name = "Алексей";
        text = msgText.getText();
        if (text.length() == 0)
            return;

        msgText.clear();

        Message newMsg = new Message(name, text);

//        List<Message> messageList = chats.get(activeChat);
//        messageList.add((newMsg));
//
//        chats.put(activeChat, messageList);

        msgList.add(newMsg);

        printMsg();
    }

    public void printMsg() {
//        List<Message> msgList = chats.get(activeChat);
//
//        if (msgList.isEmpty())
//            return;
        if (msgList.isEmpty()) {
            System.out.println(111);
            return;
        }
        mainChat.getChildren().clear();


        for (Message msg : msgList) {
            createMsg(msg);
        }
    }

    public void createMsg(Message msg) {
        String name, text;

        name = msg.getName();
        text = msg.getText();

        double mainChatWidth = mainChat.getWidth();

        // Nickname
        Label lblName = new Label(name);
        lblName.setPrefSize(mainChatWidth, 50);
        lblName.setAlignment(Pos.BOTTOM_RIGHT);
        lblName.setTextAlignment(TextAlignment.RIGHT);

        // message
        Text txtText = new Text(text);
        txtText.setTextAlignment(TextAlignment.JUSTIFY);
        txtText.getStyleClass().add("textStyle");

        // box
        Rectangle msgBox = new Rectangle();
        msgBox.setFill(Color.web(boxColor));
        msgBox.setArcHeight(10);
        msgBox.setArcWidth(10);
        //msgBox.heightProperty().bind(txtText.getLayoutBounds().getHeight());

        setSize(txtText, msgBox);

        Group group = new Group();
        group.getChildren().addAll(msgBox, txtText);

        mainChat.getChildren().add(lblName);
        mainChat.getChildren().add(group);
    }

    public void setSize(Text txtText, Rectangle msgBox) {
        double mainChatWidth = mainChat.getWidth();

        if (txtText.getLayoutBounds().getWidth() >= mainChatWidth * textWrapWidthCoeff1) {
            txtText.setWrappingWidth(mainChatWidth * textWrapWidthCoeff1);
            txtText.prefWidth(mainChatWidth * textWrapWidthCoeff1);
        } else {
            txtText.setWrappingWidth(txtText.getLayoutBounds().getWidth() * textWrapWidthCoeff2);
            txtText.prefWidth(txtText.getLayoutBounds().getWidth() * textWrapWidthCoeff2);

        }
        txtText.setX(mainChatWidth - txtText.getLayoutBounds().getWidth());

        double width = txtText.getLayoutBounds().getWidth();
        double height = txtText.getLayoutBounds().getHeight();
        double posX = txtText.getLayoutBounds().getMinX();
        double posY = txtText.getLayoutBounds().getMinY();

        txtText.setX(posX - indent);
        txtText.setY(posY + indent * 2);

        msgBox.setX(posX - indent * 2);
        msgBox.setY(posY - indent);
        msgBox.setWidth(width + indent * 2);
        msgBox.setHeight(height + indent * 2);
    }

    public void resizeMsg(VBox mainChat) {
        for (Node group : mainChat.getChildren()) {
            //System.out.println(group.set);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
