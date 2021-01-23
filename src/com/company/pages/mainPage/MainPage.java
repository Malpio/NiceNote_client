package com.company.pages.mainPage;

import com.company.pages.Page;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPage extends Page {
    public MainPage(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPageDocument.fxml"));
        scene = new Scene(root);
        this.stage = stage;
    }
}
