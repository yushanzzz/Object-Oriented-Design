package application;

import java.util.ArrayList;

public class User extends Person{
	
	public User(String name, String pwd) {
		super(name, pwd);
	}

	public void addRecipe(String name, String type, ArrayList<String> ingredient, ArrayList<Recipe> recipeArray) {
//		recipeArray.add(name, type, ingredient);
	}
	
	public void searchRecipe(String ingredient, ArrayList<String> recipeArray) {
		
	}
}
