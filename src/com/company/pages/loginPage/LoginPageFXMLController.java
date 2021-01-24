package com.company.pages.loginPage;

import com.company.Main;
import com.company.common.User;
import com.company.pages.Page;
import com.company.pages.mainPage.MainPage;
import com.company.pages.registryPage.RegistryPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class LoginPageFXMLController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label loginAlert;

    public void changeAlertVisible(boolean value) {
        loginAlert.setVisible(value);
    }

    public void setInputsColor(String color) {
        this.email.setStyle(String.format("-fx-border-color: %s", color));
        this.password.setStyle(String.format("-fx-border-color: %s", color));
    }

    public void showValidationFail() {
        changeAlertVisible(true);
        setInputsColor("#f00");
    }

    @FXML
    public void registryPageNavigate() {
        try {
            Page registryPage = new RegistryPage(Main.page.getStage());
            Main.changeScene(registryPage);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void handleLogin() {
        try {
            String email = this.email.getText();
            String password = this.password.getText();
            if (!isValid(email, password)) {
                showValidationFail();
                return;
            }

            User user = Main.niceNoteServer.login(email, password);
            if (user != null) {
                Main.setUser(user);
                Page mainPage = new MainPage(Main.page.getStage());
                Main.changeScene(mainPage);
            } else {
                showValidationFail();
            }
        } catch (IOException ex) {
            showValidationFail();
            ex.printStackTrace();
        }
    }

    @FXML
    public void onInputPress() {
        changeAlertVisible(false);
        setInputsColor("#bdbdbd");
    }

    public boolean isValid(String email, String password) {
        return !(email.isEmpty() || password.isEmpty());
    }
}
