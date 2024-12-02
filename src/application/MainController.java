package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.stage.Window;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MainController {    
	  @FXML
	    private ImageView lblImage;
	  @FXML
	    private TextField txtName; 
	    
	    @FXML
	    private TextField txtType; 
	    
	    @FXML
	    private TextField txtSearch;
	    
	    @FXML
	    private TextArea txtIngredient;
	    
	    @FXML
	    private HBox recipeButtonsContainer; 
	    
	    @FXML
	    private void handleSaveButton(ActionEvent event) {
	        try {
	            System.out.println("Save button clicked!");
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }
	    }
	    
	    
	    private File selectedImageFile; 
	  
	  private Configure configure = Configure.getInstance();  // 獲取 Configure 類的單例
	  
	  @FXML
	  private void initialize() {
		  if (recipeButtonsContainer == null) {
		        System.out.println("recipeButtonsContainer is null");
		    } else {
		        loadRecipeButtons();
		    }
		  //loadRecipeButtons();
		  
	  }
	  
	  private void loadRecipeButtons() {
	        recipeButtonsContainer.getChildren().clear();  
	        ArrayList<Recipe> recipes = configure.getRecipes();  

	        for (Recipe recipe : recipes) {
	            Button recipeButton = new Button(recipe.getName());
	            recipeButton.setMinWidth(125);  
	            recipeButton.setMaxWidth(125);  
	            recipeButton.setPrefWidth(125); 
	            recipeButton.setStyle("-fx-padding: 10; -fx-alignment: center; -fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 2;");
	            recipeButton.setOnAction(event -> showRecipeDetails(recipe));  
	            recipeButtonsContainer.getChildren().add(recipeButton);  
	        }
	    }

	
	  public void AddImageToRecipe(ActionEvent event) {

	      FileChooser fileChooser = new FileChooser();
	        
	      FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");
	      fileChooser.getExtensionFilters().add(filter);

	      File selectedFile = fileChooser.showOpenDialog(new Stage());

	      if (selectedFile != null) {

	         Image image = new Image(selectedFile.toURI().toString());
	         lblImage.setImage(image);
	         this.selectedImageFile = selectedFile;
	      }
	   }
	  
	  public void RemoveImage(ActionEvent event) {
		  lblImage.setImage(null);
		  selectedImageFile = null;
	  }
	  
	  
	  public void handleSaveRecipe(ActionEvent event) {
	        String dishName = txtName.getText();
	        String type = txtType.getText();
	        String ingredientsText = txtIngredient.getText();

	        if (dishName.isEmpty() || type.isEmpty() || ingredientsText.isEmpty()) {
	            showAlert("Error", "Please fill in all fields!");
	            return;
	        }
	        
	        ArrayList<String> ingredients = new ArrayList<>();
	        for (String ingredient : ingredientsText.split(",")) {
	            ingredients.add(ingredient.trim());
	        }
	        String imagePath = selectedImageFile != null ? selectedImageFile.toURI().toString() : null;
	        Recipe newRecipe = new Recipe(dishName, type, ingredients, imagePath);
	        
//	        if (selectedImageFile != null) {
//	            Image image = new Image(selectedImageFile.toURI().toString());
//	            newRecipe.setImage(image);
//	        }
	        configure.getRecipes().add(newRecipe);
	        txtName.clear();
	        txtType.clear();
	        txtIngredient.clear();
	        lblImage.setImage(null);
	        selectedImageFile = null;
	        
	        addRecipeButton(newRecipe);
	        showAlert("Success", "Recipe added successfully!");
	        
	        
	        
	/*        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AnotherScene.fxml"));
	            Parent root = loader.load();
	            MainController controller = loader.getController();

	            Stage stage = (Stage) recipeButtonsContainer.getScene().getWindow();
	            stage.setScene(new Scene(root));
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }                          */
	    }
	  
	  private void addRecipeButton(Recipe recipe) {
		  if (recipeButtonsContainer == null) {
		        System.out.println("recipeButtonsContainer is null");
		        return;
		    }
		  
	        Button recipeButton = new Button(recipe.getName());
	        recipeButton.setOnAction(event -> showRecipeDetails(recipe)); 
	        recipeButtonsContainer.getChildren().add(recipeButton); 
	    }
	        
	   private void showAlert(String title, String message) {
	            Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle(title);
	            alert.setHeaderText(null);
	            alert.setContentText(message);
	            alert.showAndWait();
	    }
	  
	  
	    private void showRecipeDetails(Recipe recipe) {
	            try {
	                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/RecipeShow.fxml"));
	           //     loader.setController(this);
	                Parent root = loader.load();

	                RecipeShowController controller = loader.getController();
	                controller.showRecipeDetails(recipe);

	                Stage stage = (Stage) recipeButtonsContainer.getScene().getWindow(); 
	                stage.setScene(new Scene(root));
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	    }
	
	  public void switchScene(ActionEvent event, String fxmlFile) {
	        try {
	            Parent newPage = FXMLLoader.load(getClass().getResource(fxmlFile));
	            Scene newScene = new Scene(newPage);
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            stage.setScene(newScene);
	            stage.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	  // conditional determine (Main to Admin/ User)
	    public void MaintoAdmin(ActionEvent event) {
	        switchScene(event, "Admin_home.fxml");
	    }
	    
	    public void MaintoUser(ActionEvent event) {
	        switchScene(event, "User_home.fxml");
	    }

	    public void AdmintoMngAcc(ActionEvent event) {
	        switchScene(event, "ManageAccount.fxml");
	    }
	    
	    public void MngAcctoAdmin(ActionEvent event) {
	        switchScene(event, "Admin_home.fxml");
	    }
	    
	    public void AdmintoMngRecipe(ActionEvent event) {
	        switchScene(event, "ManageRecipe.fxml");
	    }
	    
	    public void MngRecipetoAdmin(ActionEvent event) {
	        switchScene(event, "Admin_home.fxml");
	    }
	    
	    public void UsertoShowRecipe(ActionEvent event) {
	        switchScene(event, "RecipeShow.fxml");
	    }
	    
	    public void ShowRecipetoUser(ActionEvent event) {
	        switchScene(event, "User_home.fxml");
	    }
	    
	    public void UsertoCreateRecipe(ActionEvent event) {
	        switchScene(event, "CreateRecipe.fxml");
	    }
	    
	    public void CreateRecipetoUser(ActionEvent event) {
	        switchScene(event, "User_home.fxml");
	    }
	    
	    public void UsertoSearch(ActionEvent event) {
	        //switchScene(event, "Search.fxml");
	    	showSearchTable();
	    }
	    
	    public void SearchtoUser(ActionEvent event) {
	        switchScene(event, "User_home.fxml");
	    }
	    
	    public void UserSignUp(ActionEvent event) {
	        switchScene(event, "SignUpPage.fxml");
	    }
	    
	    public void BacktoLogin(ActionEvent event) {
	    	switchScene(event, "LoginPage.fxml");
	    }
	    
	    public void AdminLogout(ActionEvent event) {
	    	switchScene(event, "LoginPage.fxml");
	    }
	    
	    public void UserLogout(ActionEvent event) {
	    	switchScene(event, "LoginPage.fxml");
	    }
	    
	    public void CreateAcc(ActionEvent event) {
	    	Dialog<String> dialog = new Dialog<>();
	    	dialog.setTitle("Success");
	    	dialog.setContentText("Your account has been successfully created!");
	    	ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
	    	dialog.getDialogPane().getButtonTypes().add(okButton);
	    	dialog.showAndWait();
	    }
	    
	    public static void switchSceneStatic(String fxmlFile) throws Exception {
	        FXMLLoader loader = new FXMLLoader(MainController.class.getResource(fxmlFile));
	        Parent root = loader.load();
	        Stage stage = (Stage) Stage.getWindows().filtered(Window::isShowing).get(0);
	        stage.setScene(new Scene(root));
	        stage.show();
	    }
	    
	    @FXML
	    private TextField usernameField;

	    @FXML
	    private TextField passwordField;

	    @FXML
	    private Button loginButton;

	    public void handleLogin(ActionEvent event) {
	        String username = usernameField.getText();
	        String password = passwordField.getText();
	        System.out.print("username = " + usernameField.getText() + "  password =" + passwordField.getText() );
	        
	        Configure configure = Configure.getInstance();
	        
	        
	        if (configure.isAdmin(username, password)) {
	            
	            switchToScene("Admin_home.fxml");
	        } else if (configure.isUser(username, password)) {
	            
	            switchToScene("User_home.fxml");
	        } else {
	            
	            showErrorDialog("Invalid username or password!");
	        }
	    }
	    
	    public void switchToScene(String fxmlFile) {
	        try {
	            Parent newPage = FXMLLoader.load(getClass().getResource(fxmlFile));
	            Scene newScene = new Scene(newPage);
	            Stage stage = (Stage) usernameField.getScene().getWindow();  
	            stage.setScene(newScene);
	            stage.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void showErrorDialog(String message) {
	        Dialog<String> dialog = new Dialog<>();
	        dialog.setTitle("Login Error");
	        dialog.setContentText(message);

	        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
	        dialog.getDialogPane().getButtonTypes().add(okButton);
	        
	        dialog.showAndWait();
	    }
	    
	    @FXML
	    private TextField signUpUsernameField;

	    @FXML
	    private TextField signUpPasswordField;

	    public void handleSignUp(ActionEvent event) {
	        String name = signUpUsernameField.getText();
	        String password = signUpPasswordField.getText();
	        if (!name.matches("^[a-zA-Z0-9]+$")) {
	            showErrorDialog("Username can only contain letters and numbers!");
	            return;
	        }

	       
	        Configure configure = Configure.getInstance();
	        if (configure.isUsernameTaken(name)) {
	            showErrorDialog("Username is already taken!");
	        } else {
	        User newUser = new User(name, password);
	        configure.addUser(newUser);  

	        
	        switchScene(event, "LoginPage.fxml");

	       
	        showSuccessDialog("Account created successfully!");
	    }
	    }

	    public void showSuccessDialog(String message) {
	        Dialog<String> dialog = new Dialog<>();
	        dialog.setTitle("Success");
	        dialog.setContentText(message);
	        
	        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
	        dialog.getDialogPane().getButtonTypes().add(okButton);
	        dialog.showAndWait();
	    }
	    
	    
	    @FXML
	    public void showRecipe(ActionEvent event) {
	        Button srcbtn = (Button) event.getSource();
	        String btntxt = srcbtn.getText();

	        ArrayList<Recipe> recipes = configure.getRecipes();
	        for (Recipe recipe : recipes) {
	            if (recipe.getName().equals(btntxt)) {
	                // 找到相應的食譜，並進行頁面切換
	                showRecipeDetails(recipe);
	                break;
	            }
	        }
	    }
	    

	 /*   private void showRecipeDetails(Recipe recipe, ActionEvent event) {
	        try {
	            // 加載 RecipeShow.fxml
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/RecipeShow.fxml"));
	            Parent root = loader.load();

	            // 獲取 RecipeShowController
	            RecipeShowController controller = loader.getController();

	            // 傳遞食譜數據到 RecipeShowController
	            controller.showRecipeDetails(recipe);

	            // 顯示新的場景
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // 使用事件來源來獲取 Stage
	            stage.setScene(new Scene(root));
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    */
	    
	    public void showSearchTable() {
	    	String searchIngredient = txtSearch.getText();
	    	if (searchIngredient.isEmpty()) {
	            showAlert("Warning", "Search field cannot be empty!");
	            return;
	        }
	    	ArrayList<Recipe> filteredRecipes = searchRecipes(searchIngredient);
	    	
	    	if (filteredRecipes.isEmpty()) {
	            showAlert("Info", "No recipes found for the given ingredient.");
	            return;
	        }
	    	
	    	recipeButtonsContainer.getChildren().clear();  

	        for (Recipe recipe : filteredRecipes) {
	            Button recipeButton = new Button(recipe.getName());
	            recipeButton.setMinWidth(125);  
	            recipeButton.setMaxWidth(125);  
	            recipeButton.setPrefWidth(125); 
	            recipeButton.setStyle("-fx-padding: 10; -fx-alignment: center; -fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 2;");
	            recipeButton.setOnAction(event -> showRecipeDetails(recipe));  
	            recipeButtonsContainer.getChildren().add(recipeButton);  
	        }
	    }
	    
	    public ArrayList<Recipe> searchRecipes(String ingredient) {
	        System.out.println("Searching for ingredient: " + ingredient);
//	        if (ingredient.isEmpty()) {
//	            //recipeTableView.setItems(FXCollections.observableArrayList());
//	            return;
//	        }

	        ArrayList<Recipe> filteredRecipes = new ArrayList<>();
	        for (Recipe recipe : Configure.getInstance().getRecipes()) {
	        	System.out.println("Checking recipe: " + recipe.getName() + ", Ingredients: " + recipe.getIngredientArray());
	            for (String recipeIngredient : recipe.getIngredientArray()) {
	            	System.out.println("Matching input: " + ingredient + " with ingredient: " + recipeIngredient.toLowerCase());
	                if (recipeIngredient.toLowerCase().contains(ingredient)) {
	                    filteredRecipes.add(recipe);
	                    break;
	                }
	            }
	        }
	        
	        return filteredRecipes;
	    }
	    
}
