package com.company.pages.mainPage;

import com.company.pages.common.TextFile;
import com.company.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageFXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> list = null;
        try {
            list = Main.niceNoteServer.filesList(Main.userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }

    @FXML
    private TextField fileName;

    @FXML
    private ListView<TextFile> filesList;

    public void addListItem(TextFile file) {
        filesList.getItems().add(file);
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
