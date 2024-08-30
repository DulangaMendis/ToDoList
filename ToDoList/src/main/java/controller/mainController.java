package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class mainController {

    public VBox vboxtasklist;
    public JFXTextField txtaddtask;
    public VBox vboxcomplete;

    public void btnaddonaction(ActionEvent actionEvent) {
        JFXCheckBox box1 = new JFXCheckBox(txtaddtask.getText());
        vboxtasklist.getChildren().addAll(box1);
        vboxtasklist.setSpacing(10);
        txtaddtask.setText(" ");

        box1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Label completetask = new Label(box1.getText());
                vboxcomplete.getChildren().add(completetask);
            }
        });

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = "INSERT INTO History (task_name) VALUES (?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, box1.getText());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void btnviewhistoryonaction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/view_history.fxml"))));
            stage.show();
            stage.setTitle("View History");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}


