package com.example.test.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;

public class DashboardController {


    ImageView logout;

    @FXML
    AnchorPane rightAnchor;
    private String connectedUsername;
    @FXML
    private Label connectedUser;

    public void medicine(ActionEvent event) {
        Node node = null;

        try {
            node = (Node) FXMLLoader.load(this.getClass().getResource("/hello-view.fxml"));
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        this.rightAnchor.getChildren().setAll(new Node[]{node});
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Pharmacy management system : Medicine");
    }
    public void pay(ActionEvent event) {
        Node node = null;


        try {
            node = (Node) FXMLLoader.load(this.getClass().getResource("/paypage.fxml"));
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        this.rightAnchor.getChildren().setAll(new Node[]{node});
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Pharmacy management system : Pay");
    }
    public void statistics(ActionEvent event) {
        Node node = null;

        try {
//            URL url = new File("src/main/resources/Statistics.fxml").toURI().toURL();
//            Parent root = FXMLLoader.load(url);


            node = (Node) FXMLLoader.load(this.getClass().getResource("/Statistics.fxml"));
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        this.rightAnchor.getChildren().setAll(new Node[]{node});
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Pharmacy management system : Statistics");
    }
    public void profil(ActionEvent event) {
        Node node = null;



        try {
            node = (Node)FXMLLoader.load(this.getClass().getResource("/Facture.fxml"));
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        this.rightAnchor.getChildren().setAll(new Node[]{node});
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("GRH - Dashboard : Employees");
    }

    public void getUsername(String username){
        connectedUsername = username;
        connectedUser.setText(username);
    }

    public void signout(ActionEvent event) {

        ((Node)event.getSource()).getScene().getWindow().hide();

        try {
            TrayNotification notif = new TrayNotification();
            notif.setTray("Notification", "Logout Successful", NotificationType.SUCCESS);
            notif.setAnimationType(AnimationType.POPUP);
            notif.setRectangleFill(Paint.valueOf("#CEE7E6"));
            notif.showAndDismiss(Duration.seconds(2.0));
            Stage stage = new Stage();
            Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("/Login.fxml"));
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(this.getClass().getResource("DashbordDesign.css").toExternalForm());
            stage.setScene(scene);
//            stage.getIcons().add(new Image(""));
            stage.setTitle("Pharmacy system managment");
            stage.setResizable(false);
            stage.sizeToScene();
            stage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
        }



    }


    public void settingsBtn(ActionEvent event) throws IOException {
       /* Stage stage = new Stage();
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("settings.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            public Object call(Class<?> param) {
                SettingsController controller = new SettingsController();
                controller.setUser(DashboardController.this.connectedUsername);
                return controller;
            }
        });
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("GRH - Settings");
        stage.getIcons().add(new Image("/assets/icon.png"));
        stage.show();*/
    }

}
