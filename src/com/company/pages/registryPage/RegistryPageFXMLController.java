package com.company.pages.registryPage;

import com.company.Main;
import com.company.pages.Page;
import com.company.pages.loginPage.LoginPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RegistryPageFXMLController  implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private TextField name;

    @FXML
    private TextField last_name;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password2;

    @FXML
    private Label registryAlert;

    @FXML
    public void loginPageNavigate() {
        try {
            Page loginPage = new LoginPage(Main.page.getStage());
            Main.changeScene(loginPage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void handleRegistry() {
        String name = this.name.getText();
        String lastName = this.last_name.getText();
        String email = this.email.getText();
        String password = this.password.getText();
        String password2 = this.password2.getText();

        if(!isValid(name, lastName, email, password, password2)) {
            showAlert("Wszystkie pola są wymagane!");
            return;
        }

        if(!passwordValidate(password, password2)) {
            showAlert("Hasła nie są takie same!");
            return;
        }

        try {
            boolean register = Main.niceNoteServer.registry(name, lastName, email, password);
            if(register) {
                this.loginPageNavigate();
            } else {
                showAlert("Taki email już istnieje");
            }

        } catch (RemoteException ex) {
            ex.printStackTrace();
            showAlert("Wystąpił nieoczekiwany błąd");
        }
    }

    public void showAlert(String alert) {
        registryAlert.setText(alert);
        changeAlertVisible(true);
        setInputsColor("#f00");
    }

    @FXML
    public void onInputPress() {
        changeAlertVisible(false);
        setInputsColor("#bdbdbd");
    }

    public void setInputsColor(String color) {
        this.name.setStyle(String.format("-fx-border-color: %s", color));
        this.last_name.setStyle(String.format("-fx-border-color: %s", color));
        this.email.setStyle(String.format("-fx-border-color: %s", color));
        this.password.setStyle(String.format("-fx-border-color: %s", color));
        this.password2.setStyle(String.format("-fx-border-color: %s", color));
    }

    public void changeAlertVisible(boolean value) {
        registryAlert.setVisible(value);
    }

    public boolean passwordValidate(String password, String password2) {
        return password.equals(password2);
    }

    public boolean isValid(String name, String lastName, String email, String password, String password2) {
        return !(name.isEmpty() ||
                lastName.isEmpty() ||
                email.isEmpty() ||
                password.isEmpty() ||
                password2.isEmpty());
    }
}
