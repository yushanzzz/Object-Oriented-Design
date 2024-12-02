package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Set;

public class ManageAccountController {

	@FXML
    private TableView<User> accountTableView;
    @FXML
    private TableColumn<User, String> columnName;
    @FXML
    private TableColumn<User, String> columnPassword;

    
    @FXML
    public void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        Set<User> users = Configure.getInstance().getUsers();
        ObservableList<User> userData = FXCollections.observableArrayList(users);
        accountTableView.setItems(userData);
    }
    
    @FXML
    public void MngAcctoAdmin(ActionEvent event) {
        try {
        	MainController.switchScene(event, "Admin_home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleDeleteUser(ActionEvent event) {
        User selectedUser = accountTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            Configure.getInstance().getUsers().remove(selectedUser); 
            accountTableView.getItems().remove(selectedUser); 
            System.out.println("User deleted: " + selectedUser.getName());
        } else {
            System.out.println("No user selected!");
        }
    }

}
