package com.company.pages.mainPage;

import com.company.common.TextFile;
import com.company.Main;
import com.company.common.User;
import com.company.pages.loginPage.LoginPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageFXMLController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<TextFile> list = null;
        try {
            setMainLabel(Main.user);
            list = Main.niceNoteServer.filesList(Main.user.getId());
            setFilesList(list);
            fileContent.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    Main.niceNoteServer.writeFile(Main.user.getId(), getCurrentFileName(), newValue);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField fileName;

    @FXML
    private ListView<TextFile> filesList;

    @FXML
    private TextArea fileContent;

    @FXML
    private VBox menu;

    @FXML
    private Label nameLabel;

    public void setMainLabel(User user) {
        nameLabel.setText(String.format("Witaj %s %s", user.getName(), user.getLastName()));
    }

    public void setFileContent(String content) {
        fileContent.setText(content);
    }

    public void setFilesList(List<TextFile> filesList) {
        this.filesList.getItems().addAll(filesList);
        setCellFactory();
        if (filesList.isEmpty()) {
            fileContent.setDisable(true);
        } else {
            this.filesList.getSelectionModel().select(0);
            changeFile();
        }
    }

    public void addListItem(TextFile file) {
        filesList.getItems().add(file);
        setCellFactory();
    }

    public void changeMenuVisible(boolean value) {
        menu.setVisible(value);
    }

    public String getCurrentFileName() {
        return filesList.getSelectionModel().getSelectedItem().getFileName();
    }

    @FXML
    public void onHamburgerPress(MouseEvent mouseEvent) {
        changeMenuVisible(!menu.isVisible());
    }

    @FXML
    public void changeFile() {
        try {
            String fileName = getCurrentFileName();
            String content = Main.niceNoteServer.readFile(Main.user.getId(), fileName);
            setFileContent(content);
            fileContent.setDisable(false);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void writeFile() {
        try {
            System.out.println("WYWOLALO Sie");
            String fileName = getCurrentFileName();
            String content = fileContent.getText();
            Main.niceNoteServer.writeFile(Main.user.getId(), fileName, content);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void setCellFactory() {
        filesList.setCellFactory(param -> new ListCell<TextFile>() {
            @Override
            protected void updateItem(TextFile file, boolean empty) {
                super.updateItem(file, empty);
                if (empty || file == null || file.getFileName() == null) {
                    setText(null);
                } else {
                    setText(file.getFileName());
                }
            }
        });
    }

    @FXML
    public void createFile() {
        try {
            String fileName = this.fileName.getText();
            if(fileName.isEmpty()){
                this.fileName.setStyle("-fx-border-color: #f00");
                return;
            }
            Integer fileId = Main.niceNoteServer.createFile(Main.user.getId(), fileName);
            if (fileId == null){
                this.fileName.setStyle("-fx-border-color: #f00");
                return;
            }
            TextFile file = new TextFile(fileId, fileName);
            addListItem(file);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void logout() {
        try {
            Main.setUser(null);
            Main.changeScene(new LoginPage(Main.page.getStage()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void onFileNameInputFocus() {
        this.fileName.setStyle("-fx-border-color: #bdbdbd");
    }
}
