package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginViewController
{
    private static String usernameConnected = null;

    //Login attributs
    @FXML private TextField userNameFieldLogin;
    @FXML private PasswordField passwordFieldLogin;

    //Sign up attributs
    @FXML private TextField userNameFieldSignUp;
    @FXML private PasswordField passwordField1SignUp;
    @FXML private PasswordField passwordField2SignUp;


    /**
     * Fonction executée lorsque l'utilisateur clique sur bouton "Login"
     */
    public void onConfirmLoginButtonPressed(ActionEvent event) throws IOException
    {
        if(ControleurGestion.connect(userNameFieldLogin.getText(), passwordFieldLogin.getText()))
        {
            usernameConnected = userNameFieldLogin.getText();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../gui/homeView.fxml"));

            Parent homeViewParent = loader.load();

            Scene homeViewScene = new Scene(homeViewParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homeViewScene);
            window.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login failed");
            alert.setHeaderText(null);
            alert.setResizable(false);
            alert.setContentText("Unable to login, either username or password is incorrect!");
            alert.showAndWait();
        }
    }


    /**
     * Fonction appelée lorsque l'utilisateur clique sur "SignUp"
     */
    public void onConfirmSignUpButtonPressed(ActionEvent event)
    {
        if(passwordField1SignUp.getText().equals(passwordField2SignUp.getText()))
        {
            if(ControleurGestion.creerUtilisateur(userNameFieldSignUp.getText(), passwordField1SignUp.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sign up Successful");
                alert.setHeaderText(null);
                alert.setResizable(false);
                alert.setContentText("Thanks! your account has been successfully created");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sign up failed");
                alert.setHeaderText(null);
                alert.setResizable(false);
                alert.setContentText("Sorry your registration has failed!");
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign up failed");
            alert.setHeaderText(null);
            alert.setResizable(false);
            alert.setContentText("Your password doesnt match!");
            alert.showAndWait();
        }

    }

    /**
     * Fonction qui retourne le username de l'utilisateur connecté
     * @return le username de l'utilsateur connecté sous forme de String
     */
    static String getUsernameConnected()
    {
        return usernameConnected;
    }
}
