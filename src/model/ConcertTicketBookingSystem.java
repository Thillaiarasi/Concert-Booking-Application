package model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ConcertTicketBookingSystem {
	private Map<String, Concert> concerts;
    private User currentUser;
    private Map<String, User> users;

    public ConcertTicketBookingSystem() {
    	 this.users = new HashMap<>(); // Initialize the users map
         this.concerts = new HashMap<>();
         this.currentUser = null;

         // Initialize default user
//         initializeDefaultUser();
    }
   
    public Map<String, Concert> getConcerts() {
        return concerts;
    }

    public User getCurrentUser() {
        return currentUser;
    }

//    public void initializeDefaultUser() {
//        // Add predefined user(s) here
//        User user = new User("ThillaiV", "Vanii@123", "1234567890", "user1@example.com");
//        users.put("ThillaiV", user);
//    }

    public void registerUser(String username, String password, String mobileNumber, String email) {
        // Check if the username is already taken
        if (isUsernameTaken(username)) {
            System.out.println("Username already taken. Please choose a different username.");
            return;
        }

        currentUser = new User(username, password, mobileNumber, email);
        users.put(username, currentUser);
        System.out.println("Registration successful!");
    }

    private boolean isUsernameTaken(String username) {
        return users.containsKey(username);
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Login successful! Welcome, " + currentUser.getUsername() + "!");
            return true;
        } else {
            return false;
        }
    }

    public void addConcert(Concert concert) {
        concerts.put(concert.getConcertName(), concert);
    }
    
      
    public List<Concert> searchByConcertName(String concertName) {
        List<Concert> matchingConcerts = new ArrayList<>();
        for (Concert concert : concerts.values()) {
            if (concert.getConcertName().equalsIgnoreCase(concertName)) {
                matchingConcerts.add(concert);
            }
        }
        return matchingConcerts;
    }

    public List<Concert> searchByArtist(String artist) {
        List<Concert> matchingConcerts = new ArrayList<>();
        for (Concert concert : concerts.values()) {
            if (concert.getArtist().equalsIgnoreCase(artist)) {
                matchingConcerts.add(concert);
            }
        }
        return matchingConcerts;
    }

    public void displayAvailableTickets(String concertName) {
        Concert concert = concerts.get(concertName);
        if (concert != null) {
            System.out.println("----- Available Tickets for " + concertName + " -----");
            concert.displayAvailableSeats();
        } else {
            System.out.println("Concert not found.");
        }
    }

    public boolean bookTickets(String concertName, SeatType seatType, int quantity, int numChildren) {
        Concert concert = concerts.get(concertName);
        if (concert != null) {
            return concert.bookTickets(seatType, quantity, numChildren);
        }
        return false;
    }

	public void addUser(User user) {
		
		 users.put(user.getUsername(), user);
	}

	public void initializeDefaultUser(String username, String password, String mobileNumber, String email) {
		// TODO Auto-generated method stub
		User defaultUser = new User(username, password, mobileNumber, email);
        users.put(username, defaultUser);
//        System.out.println("Default user initialized: " + defaultUser);
    
		
	}
       }


