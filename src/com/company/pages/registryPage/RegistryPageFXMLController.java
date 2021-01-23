package com.company.pages.registryPage;

import com.company.Main;
import com.company.pages.Page;
import com.company.pages.loginPage.LoginPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
    private TextField password;


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
        String last_name = this.last_name.getText();
        String email = this.email.getText();
        String password = this.password.getText();

        try {
            Main.niceNoteServer.registry(name, last_name, email, password);
            this.loginPageNavigate();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
