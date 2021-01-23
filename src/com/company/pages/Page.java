package com.company.pages;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Page {
    protected Scene scene;
    protected Stage stage;

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }
}
