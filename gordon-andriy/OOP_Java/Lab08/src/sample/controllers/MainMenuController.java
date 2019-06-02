package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController
{
    @FXML
    private Button showEmployeesButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {
        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
        });

        showEmployeesButton.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/showEmployeesWindow.fxml"));

            try {
                loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadre Agency");
            ShowEmployeesController showEmployeesController = loader.getController();
            showEmployeesController.setShowEmployeesStage(stage);
            stage.showAndWait();
        });
    }
}