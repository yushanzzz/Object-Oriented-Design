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
    private TableView<Person> accountTableView;

    @FXML
    private TableColumn<Person, String> columnName;

    @FXML
    private TableColumn<Person, String> columnPassword;

    @FXML
    public void initialize() {
        // 配置列绑定
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        // 加载用户数据
        Set<User> users = Configure.getInstance().getUsers();
        ObservableList<Person> userData = FXCollections.observableArrayList(users);
        accountTableView.setItems(userData);
    }

    // 返回管理员主页
    @FXML
    public void MngAcctoAdmin(ActionEvent event) {
        try {
            MainController.switchSceneStatic("Admin_home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
