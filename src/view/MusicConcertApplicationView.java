package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.AdminActionHandler;
import controller.AdminSystem;
import controller.BookingManager;
import controller.RegistrationSystem;
import model.Concert;
import model.ConcertTicketBookingSystem;

public class MusicConcertApplicationView {
	 private ConcertTicketBookingSystem ticketSystem;
	    private Scanner scanner;
		private AdminSystem adminSystem;
	    

	    public MusicConcertApplicationView(ConcertTicketBookingSystem ticketSystem, Scanner scanner) {
	        this.ticketSystem = ticketSystem;
	        this.scanner = scanner;
	        this.adminSystem=new AdminSystem();
	    }

		public void run() {
			// TODO Auto-generated method stub
			 System.out.println("----- Music Concert Ticket Booking System -----");
			 System.out.println("1. Admin Login");
		        System.out.println("2. User/New User Login");
		        System.out.print("Enter your choice: ");
		        int loginChoice = scanner.nextInt();
		        scanner.nextLine(); // Consume the newline character

		        if (loginChoice == 1) {
		            // Admin login
		        	 System.out.print("Enter admin username: ");
		             String adminUsername = scanner.nextLine();
		             System.out.print("Enter admin password: ");
		             String adminPassword = scanner.nextLine();

		             if (adminSystem.authenticateAdmin(adminUsername, adminPassword)) {
		            	 AdminActionHandler adminActionHandler = new AdminActionHandler(ticketSystem, scanner);
		            	 adminActionHandler.handleAdminActions();
		             } else {
		                 System.out.println("Invalid admin credentials.");
		             }
		        } else if (loginChoice == 2) {
		            // User/New User login
		            userLogin();
		        } else {
		            System.out.println("Invalid choice.");
		        }
		    }
		
		private void userLogin() {
			 System.out.println("Available Concerts:");
		        for (Concert concert : ticketSystem.getConcerts().values()) {
		            System.out.println("Concert: " + concert.getConcertName());
		            System.out.println("Artist: " + concert.getArtist());
		            System.out.println("Venue: " + concert.getVenue());
		            System.out.println("Date: " + concert.getDate());
		            System.out.println("Timing: " + concert.getTiming());
		            System.out.println();
		        }

		        // Search concerts by name or artist
		        System.out.println("Search Concerts:");
		        System.out.println("1. Search by Concert Name");
		        System.out.println("2. Search by Artist");
		        System.out.print("Enter your choice: ");
		        int searchChoice = scanner.nextInt();
		        scanner.nextLine(); // Consume the newline character

		        List<Concert> matchingConcerts = new ArrayList<>();
		        if (searchChoice == 1) {
		            System.out.print("Enter concert name: ");
		            String keyword = scanner.nextLine();
		            matchingConcerts = ticketSystem.searchByConcertName(keyword);
		        } else if (searchChoice == 2) {
		            System.out.print("Enter artist name: ");
		            String keyword = scanner.nextLine();
		            matchingConcerts = ticketSystem.searchByArtist(keyword);
		        }

		        if (!matchingConcerts.isEmpty()) {
		            System.out.println("Matching Concerts:");
		            for (Concert concert : matchingConcerts) {
		                System.out.println("Concert: " + concert.getConcertName());
		                System.out.println("Artist: " + concert.getArtist());
		                System.out.println("Venue: " + concert.getVenue());
		                System.out.println("Date: " + concert.getDate());
		                System.out.println("Timing: " + concert.getTiming());
		                System.out.println();
		            }

		            System.out.print("Enter concert name to book tickets: ");
		            String concertName = scanner.nextLine();
		            ticketSystem.displayAvailableTickets(concertName);

		            if (ticketSystem.getCurrentUser() == null) {
		                RegistrationSystem registrationSystem = new RegistrationSystem();
		                registrationSystem.register(ticketSystem, scanner);
		            } 
		                BookingManager bookingManager = new BookingManager(ticketSystem);
		                bookingManager.bookConcertTickets();
		            
		        } else {
		            System.out.println("No matching concerts found.");
		        }

		}
}
