package com.company.pages.loginPage;

import com.company.pages.Page;
import com.company.server.INiceNoteServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LoginPage extends Page {
    public LoginPage(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginPageDocument.fxml"));
        scene = new Scene(root);
        this.stage = stage;
    }
}
