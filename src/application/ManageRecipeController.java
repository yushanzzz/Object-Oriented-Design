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
    private TableColumn<Recipe, String> columnFavorite;

    
    @FXML
    public void initialize() {
    	columnDishName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
    	columnIngredients.setCellValueFactory(new PropertyValueFactory<>("ingredient"));
    	columnFavorite.setCellValueFactory(new PropertyValueFactory<>("favorite"));
    	

        ObservableList<Recipe> recipes = FXCollections.observableArrayList(Configure.getInstance().getRecipes());
        recipeTableView.setItems(recipes);
    }
    
    @FXML
    private void handleDeleteRecipe(ActionEvent event) {
        Recipe selectedRecipe = recipeTableView.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            Configure.getInstance().getRecipes().remove(selectedRecipe);
            recipeTableView.getItems().remove(selectedRecipe);
            System.out.println("Recipe deleted: " + selectedRecipe.getName());
        } else {
            System.out.println("No recipe selected!");
        }
    }
    
    @FXML
    public void MngRecipetoAdmin(ActionEvent event) {
        try {
        	MainController.switchScene(event, "Admin_home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
