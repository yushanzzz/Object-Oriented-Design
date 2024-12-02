package application;
	
import java.util.ArrayList;
import java.util.Scanner;

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
			Parent root = FXMLLoader.load(getClass().getResource("User_home.fxml"));
			Scene scene = new Scene(root,600,400);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//Configure configure = Configure.getInstance();
//				Scanner scanner = new Scanner(System.in);
//		        System.out.print("Enter username: ");
//		        String username = scanner.nextLine();
//		        System.out.print("Enter password: ");
//		        String password = scanner.nextLine();
//		        Configure configure = Configure.getInstance();
//				
//		        // test login
//		        if (configure.isAdmin(username, password)) {
//		        	role = "admin";
//		            System.out.println("Welcome, Admin!");
//		        } else if(configure.isUser(username, password)) {
//		        	role = "user";
//		        	System.out.println("Welcome, User!");
//		        } else {
//		        	role = "invalid";
//		        	System.out.println("Invalid credentials. Access denied.");
//		        }
		        
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
		        
		        System.out.println("\nSearching recipes with 'butter': ");
		        user.searchRecipe("butter");
		        
		        // test Admin view
		        Admin admin = new Admin("Admin1", "admin123");
		        System.out.println("Viewing All Users:");
		        admin.viewAllUsers();
		        System.out.println("\nViewing All Recipes:");
		        admin.viewAllRecipes();
		
		launch(args);
	}
}
