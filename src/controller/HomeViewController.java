package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pojo.Defi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class HomeViewController implements Initializable
{

    private String connectedUsername;

    //Connected User label
    @FXML private Label usernameLabel;

    //Liste des défis TableView
    @FXML private TableView<Defi> listeDefis;
    @FXML private TableColumn<Defi, String> nom;
    @FXML private TableColumn<Defi, Integer> nbOperations;
    @FXML private TableColumn<Defi, Double> bestScore;

    //Nouveau Defis Fields
    @FXML private TextField nomTextField;
    @FXML private TextField nbOperationsTextField;
    @FXML private TextField domaine1MinTextField;
    @FXML private TextField domaine1MaxTextField;
    @FXML private TextField domaine2MinTextField;
    @FXML private TextField domaine2MaxTextField;


    //Filter
    private UnaryOperator<TextFormatter.Change> integerFilter = change -> {
        String input = change.getText();
        if (input.matches("[0-9]*"))
        {
            return change;
        }
        return null;
    };


    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        nom.setCellValueFactory(new PropertyValueFactory<Defi, String>("nom"));
        nbOperations.setCellValueFactory(new PropertyValueFactory<Defi, Integer>("nbOperations"));
        bestScore.setCellValueFactory(new PropertyValueFactory<Defi, Double>("bestScore"));

        this.nbOperationsTextField.setTextFormatter(new TextFormatter<String>(integerFilter));
        this.domaine1MinTextField.setTextFormatter(new TextFormatter<String>(integerFilter));
        this.domaine1MaxTextField.setTextFormatter(new TextFormatter<String>(integerFilter));
        this.domaine2MinTextField.setTextFormatter(new TextFormatter<String>(integerFilter));
        this.domaine2MaxTextField.setTextFormatter(new TextFormatter<String>(integerFilter));

        connectedUsername = LoginViewController.getUsernameConnected();
        this.usernameLabel.setText(connectedUsername);

        listeDefis.getItems().addAll(ControleurGestion.chercherListesDefis(connectedUsername));
    }

    public void onNewDefiButtonPressed()
    {
        ControleurGestion.creerQuiz(
                nomTextField.getText(),
                Integer.parseInt(nbOperationsTextField.getText()),
                Integer.parseInt(domaine1MinTextField.getText()),
                Integer.parseInt(domaine1MaxTextField.getText()),
                Integer.parseInt(domaine2MinTextField.getText()),
                Integer.parseInt(domaine2MaxTextField.getText()),
                this.connectedUsername
        );

        listeDefis.getItems().setAll(ControleurGestion.chercherListesDefis(this.connectedUsername));
    }

    //Méthode pour changer la scene + lancer le défi selectionné
    public void onPlayButtonPushed(ActionEvent event) throws IOException
    {
        ControleurJeu.lancer(listeDefis.getSelectionModel().getSelectedItem().getNom(), connectedUsername);

        Parent challengeViewParent = FXMLLoader.load(getClass().getResource("../gui/challengeView.fxml"));

        Scene challengeViewScene = new Scene(challengeViewParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(challengeViewScene);
        window.show();
    }
}

