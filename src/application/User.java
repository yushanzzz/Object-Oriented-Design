package application;

import java.util.ArrayList;

public class User extends Person{
	
	public User(String name, String pwd) {
		super(name, pwd);
	}

	public void addRecipe(String name, String type, ArrayList<String> ingredient) {
		ArrayList<Recipe> recipeArray = Configure.getInstance().getRecipes();
		Recipe myRecipe = new Recipe(name, type, ingredient);
		recipeArray.add(myRecipe);
		System.out.println("Recipe added: " + name);
	}
	
	public void searchRecipe(String ingredient) {
		ArrayList<Recipe> recipeArray = Configure.getInstance().getRecipes();
		boolean found = false;
	    for (Recipe recipe : recipeArray) {
	        if (recipe.getIngredient().contains(ingredient)) {
	            System.out.println("Found Recipe: " + recipe.getName());
	            found = true;
	        }
	    }
	    if (!found) {
	        System.out.println("No recipes found containing the ingredient: " + ingredient);
	    }
	}
}
