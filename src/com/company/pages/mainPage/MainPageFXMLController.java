package com.company.pages.mainPage;

import com.company.common.TextFile;
import com.company.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageFXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<TextFile> list = null;
        try {
            list = Main.niceNoteServer.filesList(Main.userId);
            setFilesList(list);
            fileContent.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    Main.niceNoteServer.writeFile(Main.userId, getCurrentFileName(), newValue);
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

    public void setFileContent(String content) {
        fileContent.setText(content);
    }

    public void setFilesList(List<TextFile> filesList) {
        this.filesList.getItems().addAll(filesList);
        setCellFactory();
    }

    public void addListItem(TextFile file) {
        filesList.getItems().add(file);
        setCellFactory();
    }

    public String getCurrentFileName() {
        return filesList.getSelectionModel().getSelectedItem().getFileName();
    }

    @FXML
    public void changeFile(MouseEvent mouseEvent) {
        try {
            String fileName = getCurrentFileName();
            String content = Main.niceNoteServer.readFile(Main.userId, fileName);
            setFileContent(content);
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
            Main.niceNoteServer.writeFile(Main.userId, fileName, content);
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
            Integer fileId = Main.niceNoteServer.createFile(Main.userId, fileName);
            TextFile file = new TextFile(fileId, fileName);
            addListItem(file);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
