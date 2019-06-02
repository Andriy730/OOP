package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.model.CadreAgency;
import sample.model.DateUtil;
import sample.model.Person;

public class ShowEmployeesController extends Window
{

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, String> registrationNumberColumn;

    @FXML
    private TableColumn<Person, String> specialty;

    @FXML
    private Label specialtyLabel;

    @FXML
    private Label experienceLabel;

    @FXML
    private Label educationLabel;

    @FXML
    private Label releaseLabel;

    @FXML
    private Button hireEmployeeButton;

    @FXML
    private Button addEmployeeButton;

    @FXML
    private Button backToTheMainWindowButton;

    private CadreAgency cadreAgency = new CadreAgency();

    private Stage showEmployeesStage;

    @FXML
    void initialize() {
        registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
        specialty.setCellValueFactory(new PropertyValueFactory<>("work"));
        cadreAgency.test();
        personTable.setItems(cadreAgency.getPeople());
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue)
        );

        backToTheMainWindowButton.setOnAction(event -> {
            showEmployeesStage.hide();
        });

        addEmployeeButton.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/addEmployeeWindow.fxml"));

            try {
                loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(showEmployeesStage);
            stage.setTitle("Cadre Agency");
            AddEmployeeController addEmployeeController = loader.getController();
            addEmployeeController.setAddEmployeeStage(stage);
            addEmployeeController.setPerson(cadreAgency);
            stage.showAndWait();
        });
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            specialtyLabel.setText(person.getWork());
            experienceLabel.setText(person.getExpereince().get());
            educationLabel.setText(person.getEducation());
            releaseLabel.setText(DateUtil.format(person.getReleaseDate().get()));
        } else {
            specialtyLabel.setText("");
            experienceLabel.setText("");
            educationLabel.setText("");
            releaseLabel.setText("");
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0)
            personTable.getItems().remove(selectedIndex);
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(showEmployeesStage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    public void setShowEmployeesStage(Stage showEmployeesStage) { this.showEmployeesStage = showEmployeesStage; }
}

