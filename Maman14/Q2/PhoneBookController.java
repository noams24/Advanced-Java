import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

// the controller for the phonebook GUI
public class PhoneBookController {
    
    @FXML private TableView<Map.Entry<String, String>> table;
    @FXML private TableColumn<Map.Entry<String, String>, String> nameCol;
    @FXML private TableColumn<Map.Entry<String, String>, String>  phoneCol;
    @FXML private TextField addName;
    @FXML private TextField addPhone;
    @FXML private TextField deleteName;
    @FXML private TextField updateName;
    @FXML private TextField updatePhone;
    @FXML private TextField searchName;
    @FXML private TextField fileNameLoad;
    @FXML private TextField fileNameSave;
    private Alert error;
    private Alert info;
    private PhoneBook phoneBook;

    public PhoneBookController() {
        phoneBook = new PhoneBook();
        error = new Alert(AlertType.ERROR);
        info = new Alert(AlertType.INFORMATION);
    }

    @FXML
    private void initialize() {
        table.setPlaceholder(new Label("There are no contacts to view"));
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        phoneCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));
    }
    
    @FXML
    private void add() {
        try {
            phoneBook.add(addName.getText(), addPhone.getText());
        } catch (DuplicateContactException | InvalidPhoneNumException e) {
            error.setContentText(e.getMessage());
            error.show();
        }
        viewPhoneBook();
        addName.setText("");
        addPhone.setText("");
    }
    
    @FXML
    private void delete() {
        try {
            phoneBook.delete(deleteName.getText());
        } catch (ContactNotFoundException e) {
            error.setContentText(e.getMessage());
            error.show();
        }
        viewPhoneBook();
        deleteName.setText("");
    }
    
    @FXML 
    private void update() {
        try {
            phoneBook.update(updateName.getText(), updatePhone.getText());
        } catch (ContactNotFoundException | InvalidPhoneNumException e) {
            error.setContentText(e.getMessage());
            error.show();
        }
        viewPhoneBook();
        updateName.setText("");
        updatePhone.setText("");
    }

    @FXML
    private void search() {
        table.getItems().clear();
        table.getItems().addAll(phoneBook.search(searchName.getText()).entrySet());
    }

    @FXML
    private void clear() {
        searchName.setText("");
        viewPhoneBook();
    }

    // show the updated phone book in the table view
    @FXML 
    private void viewPhoneBook() {
        table.getItems().clear();
        table.getItems().addAll(phoneBook.getPhoneBook().entrySet());
    }

    @FXML
    private void saveToFile() {
        File f = new File(fileNameSave.getText());
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(f));
            objOut.writeObject(phoneBook);
            objOut.close();
            info.setContentText("Saved phone book under the name " + fileNameSave.getText() + " in the project's directory");
            info.show();
        } catch (IOException e) {
            error.setContentText(e.getMessage());
            error.show();
        }
        fileNameSave.setText("");
    }

    @FXML 
    private void loadFromFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileNameLoad.getText()));
            phoneBook.setPhoneBook((PhoneBook) in.readObject());
            in.close();
        } catch (IOException e) {
            error.setContentText(e.getMessage());
            error.show();
        } catch (ClassNotFoundException e) {
            error.setContentText(e.getMessage());
            error.show();
        }
        viewPhoneBook();
        fileNameLoad.setText("");
    }
}