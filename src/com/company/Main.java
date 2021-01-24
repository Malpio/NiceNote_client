package com.company;

import com.company.common.User;
import com.company.pages.Page;
import com.company.pages.loginPage.LoginPage;
import com.company.server.INiceNoteServer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Main extends Application {
    public static INiceNoteServer niceNoteServer;
    public static Page page;
    public static User user = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        niceNoteServer = (INiceNoteServer) registry.lookup("NiceNoteServer");
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("NiceNote");
        page = new LoginPage(stage);
        stage.setScene(page.getScene());
        stage.setMaxHeight(400);
        stage.setMaxWidth(640);
        stage.setMinHeight(400);
        stage.setMinWidth(640);
        stage.show();
    }

    public static void changeScene(Page page) {
        page.getStage().setScene(page.getScene());
    }

    public static void setUser(User user) {
        Main.user = user;
    }
}
