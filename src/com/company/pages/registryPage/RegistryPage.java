package com.company.pages.registryPage;

import com.company.pages.Page;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistryPage extends Page {
    public RegistryPage(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegistryPageDocument.fxml"));
        scene = new Scene(root);
        this.stage = stage;
    }
}
