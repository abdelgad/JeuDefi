package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pojo.Score;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FinishedChallengeViewController implements Initializable
{
    @FXML private Label nbErreursLabel;
    @FXML private Label fullTimeLabel;
    @FXML private TableView<Score> listeScores;
    @FXML private TableColumn<Score, Double> tempsColumn;
    @FXML private TableColumn<Score, Integer> nbErreursColumn;
    @FXML private TableColumn<Score, LocalDate> dateColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.nbErreursLabel.setText(Integer.toString(ControleurJeu.getNbErreurs()));
        this.fullTimeLabel.setText(Double.toString(ControleurJeu.getFullTime()));

        //table
        tempsColumn.setCellValueFactory(new PropertyValueFactory<Score, Double>("temps"));
        nbErreursColumn.setCellValueFactory(new PropertyValueFactory<Score, Integer>("nbErreurs"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Score, LocalDate>("date"));

        listeScores.setVisible(false);
    }


    public void onShowScoresButtonPressed(ActionEvent event)
    {
        listeScores.getItems().setAll(ControleurJeu.demanderMeilleursScores());
        listeScores.setVisible(true);
    }

    public void onBackButtonPressed(ActionEvent event) throws IOException
    {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("../gui/homeView.fxml"));
        Scene homeViewScene = new Scene(homeViewParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(homeViewScene);
        window.show();
    }

}
