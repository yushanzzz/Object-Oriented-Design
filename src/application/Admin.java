package application;

import java.util.ArrayList;
import java.util.Set;

public class Admin extends Person{

	public Admin(String name, String pwd) {
		super(name, pwd);
	}
	
	public void viewAllUsers() {
        Set<User> users = Configure.getInstance().getUsers(); 
        System.out.println("All Users:");
        for (User user : users) {
            System.out.println("Name: " + user.getName());
        }
    }
	
	public void viewAllRecipes() {
        ArrayList<Recipe> recipes = Configure.getInstance().getRecipes(); // 从 Configure 获取食谱列表
        System.out.println("All Recipes:");
        for (Recipe recipe : recipes) {
            System.out.println("Name: " + recipe.getName());
            System.out.println("Type: " + recipe.getType());
            System.out.println("Ingredients: " + String.join(", ", recipe.getIngredient()));
            System.out.println("---");
        }
    }

}
