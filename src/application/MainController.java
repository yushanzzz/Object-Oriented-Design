package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MainController {

	
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

	    public void MaintoAdmin(ActionEvent event) {
	        switchScene(event, "main.fxml");
	    }
	    

}
