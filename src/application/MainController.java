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
}
