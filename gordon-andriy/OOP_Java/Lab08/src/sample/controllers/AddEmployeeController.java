package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.CadreAgency;
import sample.model.DateUtil;
import sample.model.Person;

public class AddEmployeeController {

    @FXML
    private TextField specialtyTextField;

    @FXML
    private TextField experienceTextField;

    @FXML
    private TextField educationTextField;

    @FXML
    private TextField releaseTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    private Stage addEmployeeStage;

    private CadreAgency cadreAgency;


    @FXML
    void initialize() {
    }

    public void setAddEmployeeStage(Stage addEmployeeStage) { this.addEmployeeStage = addEmployeeStage; }

    public void setPerson(CadreAgency person) { this.cadreAgency = person; }

    @FXML
    private void handleAdd(){
        if(isInputValid()){
            cadreAgency.getPeople().add(new Person(
                    specialtyTextField.getText(),
                    experienceTextField.getText(),
                    educationTextField.getText(),
                    DateUtil.parse(releaseTextField.getText())));
            addEmployeeStage.close();
        }
    }

    @FXML
    private void handleCancel(){
        addEmployeeStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (specialtyTextField.getText() == null || specialtyTextField.getText().length() == 0) {
            errorMessage += "No valid specialty!\n";
        }
        if (experienceTextField.getText() == null || experienceTextField.getText().length() == 0) {
            errorMessage += "No valid experience!\n";
        }
        if (educationTextField.getText() == null || educationTextField.getText().length() == 0) {
            errorMessage += "No valid education!\n";
        }

        if (releaseTextField.getText() == null || releaseTextField.getText().length() == 0) {
            errorMessage += "No valid date of release!\n";
        } else {
            if (!DateUtil.validDate(releaseTextField.getText())) {
                errorMessage += "No valid date of release. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(addEmployeeStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
