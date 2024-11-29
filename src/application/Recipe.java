package application;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;

public class Recipe {
	
	ArrayList<Recipe> allrecipe = new ArrayList<>();
//	String name;
//	String type;
//	ArrayList<String> ingredient;
	private SimpleStringProperty name;
    private SimpleStringProperty type;
    private SimpleStringProperty ingredient;
	
	public Recipe(String name, String type, ArrayList<String> ingredients) {
		this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.ingredient = new SimpleStringProperty(String.join(", ", ingredients));
	}

	public ArrayList<Recipe> getAllrecipe() {
		return allrecipe;
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
        this.name.set(name);
    }


	public String getType() {
		return type.get();
	}
	
	public void setType(String type) {
        this.type.set(type);
    }

	public String getIngredient() {
		return ingredient.get();
	}
	
	public void setIngredient(String ingredient) {
        this.ingredient.set(ingredient);
    }

}
