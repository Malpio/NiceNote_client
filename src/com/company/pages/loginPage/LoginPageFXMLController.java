package com.company.pages.loginPage;

import com.company.Main;
import com.company.pages.Page;
import com.company.pages.mainPage.MainPage;
import com.company.pages.registryPage.RegistryPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TextField password;

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
            Integer id = Main.niceNoteServer.login(email, password);
            if (id != null) {
                Main.setUserId(id);
                Page mainPage = new MainPage(Main.page.getStage());
                Main.changeScene(mainPage);
            } else {
                System.out.println("Nie ma takiego konta!");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
