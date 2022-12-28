package com.example.test.Controller;

import com.example.test.Controller.DashboardController;
import com.example.test.utiles.Dbutils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.sql.SQLException;

public class LoginController {

    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Label label ;

    public void buttonPressed(KeyEvent event) throws SQLException
    {
        if(event.getCode().toString().equals("ENTER"))
        {
            ActionEvent actionEvent = new ActionEvent(event.getSource(),event.getTarget());
            login(actionEvent);
        }
    }

    public void login(ActionEvent event) throws SQLException{

        if (!(username.getText().equals("")) && !(password.getText().equals("")))
        {
            boolean isauth = Dbutils.authentification(username.getText(), password.getText());
            System.out.println(isauth);
            if(isauth){
                label.setText("Login Successful");
                try {
                    TrayNotification notif = new TrayNotification();
                    notif.setTray("Notification", "Login Successful", NotificationType.SUCCESS);
                    notif.setAnimationType(AnimationType.POPUP);
                    notif.setRectangleFill(Paint.valueOf("#2A9A84"));
                    notif.showAndDismiss(Duration.seconds(2));

                    //hide main stage
                    ((Node)event.getSource()).getScene().getWindow().hide();
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    Parent root = loader.load(getClass().getResource("/Dashboard.fxml"));


                    //initialize the userController
                    DashboardController dashboardcontroller = loader.getController();
//                    dashboardcontroller.getUsername(username.getText());
                    Scene scene = new Scene(root);


                    // add css file + icon
//                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//                stage.getIcons().add(new Image("assets/medicine.png"));
                    stage.setTitle("Pharmacy managment system ");
                    stage.setResizable(false);
                    stage.sizeToScene();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception e) {
                    e.printStackTrace();
                }


            }
            else{
                // add label
                label.setText("Login Not Successful");
                label.setStyle("-fx-text-fill: red");
            }
        }
        else{
            // add label
            label.setText("Some field is empty");
            label.setStyle("-fx-text-fill: red");
        }

    }
}
