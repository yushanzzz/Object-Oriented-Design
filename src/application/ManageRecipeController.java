package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

public class ManageRecipeController {

    @FXML
    private TableView<Recipe> recipeTableView;

    @FXML
    private TableColumn<Recipe, String> columnDishName;

    @FXML
    private TableColumn<Recipe, String> columnType;

    @FXML
    private TableColumn<Recipe, String> columnIngredients;

    @FXML
    public void initialize() {
        // 配置列绑定
    	columnDishName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
    	columnIngredients.setCellValueFactory(new PropertyValueFactory<>("ingredient"));

        // 加载配方数据
        ObservableList<Recipe> recipes = FXCollections.observableArrayList(Configure.getInstance().getRecipes());
        recipeTableView.setItems(recipes);
    }

    // 返回管理员主页
    @FXML
    public void MngRecipetoAdmin(ActionEvent event) {
        try {
            MainController.switchSceneStatic("Admin_home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
