package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ChallengeViewController implements Initializable
{
    @FXML private Label operationLabel;
    @FXML private TextField reponseTextField;
    @FXML private Label nbErreursLabel;
    @FXML private ProgressBar progressBar;


    //Filter
    private UnaryOperator<TextFormatter.Change> integerFilter = change -> {
        String input = change.getText();
        if (input.matches("[0-9]*"))
        {
            return change;
        }
        return null;
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.reponseTextField.setTextFormatter(new TextFormatter<String>(integerFilter));
        this.operationLabel.setText(ControleurJeu.suivant());
        this.nbErreursLabel.setText(Integer.toString(ControleurJeu.getNbErreurs()));
    }

    public void OnValiderReponseButtonPressed(ActionEvent event) throws IOException
    {
        if(ControleurJeu.repondre(Integer.parseInt(reponseTextField.getText())))
        {
            progressBar.setProgress(ControleurJeu.getProgress());
            String nextOperation = ControleurJeu.suivant();
            if(nextOperation == null) //il ne y a plus d'opérations dans le défi
            {
                Parent finishedChallengeViewParent = FXMLLoader.load(getClass().getResource("../gui/finishedChallengeView.fxml"));
                Scene finishedChallengeScene = new Scene(finishedChallengeViewParent);

                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

                window.setScene(finishedChallengeScene);
                window.show();
            }
            else
            {
                this.operationLabel.setText(nextOperation);
                this.operationLabel.setTextFill(Color.web("#000000"));
            }
        }
        else
        {
            this.operationLabel.setTextFill(Color.web("#ff1a1a"));
            this.nbErreursLabel.setText(Integer.toString(ControleurJeu.getNbErreurs()));
        }
    }
}
