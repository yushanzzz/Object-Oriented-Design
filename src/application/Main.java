package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	static String role;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
			 
			Scene scene = new Scene(root,600,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {	        
		
        // test admin view
        Admin admin = new Admin("Admin1", "admin123");
        System.out.println("Viewing All Users:");
        admin.viewAllUsers();
        System.out.println("\nViewing All Recipes:");
        admin.viewAllRecipes();
        
		// test find recipe
		User user = new User("John", "password123");
		user.searchRecipe("chicken"); // 找到 "Chicken Soup"
        user.searchRecipe("sugar");   // 找到 "Cake"
        user.searchRecipe("butter");  // 未找到
        
        // test add new recipe
        ArrayList<String> newIngredients = new ArrayList<>();
        newIngredients.add("butter");
        newIngredients.add("sugar");
        user.addRecipe("Butter Cake", "Dessert", newIngredients, "image/butter_cake.png");
		
		launch(args);
	}
}
