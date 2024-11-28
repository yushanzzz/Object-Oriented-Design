package application;

import java.util.ArrayList;

public class Recipe {
	
	ArrayList<Recipe> allrecipe = new ArrayList<>();
	String name;
	String type;
	ArrayList<String> ingredient;
	
	public Recipe(String name, String type, ArrayList<String> ingredient) {
		this.name = name;
		this.type = type;
		this.ingredient = ingredient;
	}

	public ArrayList<Recipe> getAllrecipe() {
		return allrecipe;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public ArrayList<String> getIngredient() {
		return ingredient;
	}

}
