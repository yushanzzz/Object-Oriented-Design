package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import javafx.stage.Window;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;


public class MainController {    
	  @FXML
	  private ImageView lblImage;
	
	  public void AddImageToRecipe(ActionEvent event) {

	      FileChooser fileChooser = new FileChooser();
	        
	      FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");
	      fileChooser.getExtensionFilters().add(filter);

	      File selectedFile = fileChooser.showOpenDialog(new Stage());

	      if (selectedFile != null) {

	         Image image = new Image(selectedFile.toURI().toString());
	         lblImage.setImage(image);
	      }
	   }
	  
	  public void RemoveImage(ActionEvent event) {
		  lblImage.setImage(null);
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
	        switchScene(event, "Search.fxml");
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
}
