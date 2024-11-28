package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Configure {
	
	ArrayList<String> i1 = new ArrayList<>();
	
	public Configure() {
		i1.add("String");
	}
	
	public ArrayList<Recipe> CreateRecipe() {
		ArrayList<Recipe> recipes = new ArrayList<>();
		Recipe r1 = new Recipe("1", "Main", i1);
		recipes.add(r1);
		
		return recipes;
	}
	
	public Set<User> CreateUser() {
		Set<User> users = new HashSet<>();
		
		User u1 = new User("u1", "123");
		users.add(u1);
		
		return users;
	}
	
	public Set<Admin> CreateAdmin() {
		Set<Admin> admins = new HashSet<>();
		
		Admin a1 = new Admin("a1", "123");
		admins.add(a1);
		
		return admins;
	}
}
