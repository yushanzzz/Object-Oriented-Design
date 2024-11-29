package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Configure {
	private static Configure instance = null;

    private ArrayList<String> i1 = new ArrayList<>();
    private ArrayList<String> i2 = new ArrayList<>();
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private Set<User> users = new HashSet<>();
    private Set<Admin> admins = new HashSet<>();
	
	public Configure() {
		initializeData();
	}
	
	public static Configure getInstance() {
        if (instance == null) {
            instance = new Configure();
        }
        return instance;
    }
	
	private void initializeData() {
        i1.add("chicken");
        i1.add("salt");
        i2.add("flour");
        i2.add("sugar");

        recipes.add(new Recipe("Chicken Soup", "Soup", i1));
        recipes.add(new Recipe("Cake", "Dessert", i2));

        users.add(new User("Mark", "123"));

        admins.add(new Admin("Admin", "123"));
    }
	
	public ArrayList<Recipe> getRecipes() {
//		ArrayList<Recipe> recipes = new ArrayList<>();
//		Recipe r1 = new Recipe("Chicken Soup", "Soup", i1);
//		Recipe r2 = new Recipe("Cake", "Dessert", i2);
//		recipes.add(r1);
//		recipes.add(r2);
		
		return recipes;
	}
	
	public Set<User> getUsers() {
//		Set<User> users = new HashSet<>();
//		
//		User u1 = new User("Mark", "123");
//		users.add(u1);
		
		return users;
	}
	
	public Set<Admin> CreateAdmin() {
//		Set<Admin> admins = new HashSet<>();
//		
//		Admin a1 = new Admin("Admin", "123");
//		admins.add(a1);
//		
		return admins;
	}
	
	// check user
	public boolean isUser(String username, String password) {
        for (User user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
	
	// check admin
	public boolean isAdmin(String username, String password) {
        for (Admin admin : admins) {
            if (admin.getName().equals(username) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
