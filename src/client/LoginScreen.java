package client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.Pocket;

import java.io.IOException;

public class LoginScreen extends Application {
    public Button btn_ok;
    public PasswordField password_field;
    public TextField text_field;
    private Stage primaryStage;
    private Client client = new Client("localhost", 7777);

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sign in");
        this.primaryStage.setResizable(false);



        client.start();


        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginScreen.class.getResource("LoginScreen.fxml"));
            Pane pane = (Pane) loader.load();
            Scene scene = new Scene(pane);

            this.primaryStage.setScene(scene);
            this.primaryStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void btnOkClick(ActionEvent actionEvent) {
        String str = text_field.getText();
        str += password_field.getText();
        Pocket pocket = new Pocket();
        pocket.type = str;
        client.sendToServerObject(pocket);
        System.out.println(str);
    }
}
