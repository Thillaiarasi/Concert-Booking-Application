package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AdminSystem {
	 private String adminUsername;
	    private String adminPassword;

	    public AdminSystem() {
	        loadAdminCredentials();
	    }

	    private void loadAdminCredentials() {
	        Properties properties = new Properties();
	        try (InputStream inputStream = getClass().getResourceAsStream("config.properties")) {
	            properties.load(inputStream);
	            adminUsername = properties.getProperty("admin.username");
	            adminPassword = properties.getProperty("admin.password");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public boolean authenticateAdmin(String username, String password) {
	        return username.equals(adminUsername) && password.equals(adminPassword);
	    }

}
