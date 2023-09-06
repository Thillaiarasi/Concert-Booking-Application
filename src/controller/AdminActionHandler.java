package controller;

import java.util.Scanner;

import model.ConcertTicketBookingSystem;

public class AdminActionHandler {

		 private ConcertTicketBookingSystem ticketSystem;
		    private Scanner scanner;

		    public AdminActionHandler(ConcertTicketBookingSystem ticketSystem, Scanner scanner) {
		        this.ticketSystem = ticketSystem;
		        this.scanner = scanner;
		    }

		    public void handleAdminActions() {
		        while (true) {
		            System.out.println("Admin Menu:");
		            System.out.println("1. Add Concert");
		            System.out.println("2. Update Concert Details");
		            System.out.println("3. Delete Concert");
		            System.out.println("4. View All Users");
		            System.out.println("5. View Booking History");
		            System.out.println("6. User Management");
		            System.out.println("7. Logout");

		            System.out.print("Enter your choice: ");
		            int adminChoice = scanner.nextInt();
		            scanner.nextLine(); // Consume the newline character

		            switch (adminChoice) {
		                case 1:
		                    addConcert();
		                    break;
		                case 2:
		                    updateConcertDetails();
		                    break;
		                case 3:
		                    deleteConcert();
		                    break;
		                case 4:
		                    viewAllUsers();
		                    break;
		                case 5:
		                    viewBookingHistory();
		                    break;
		                case 6:
		                    userManagement();
		                    break;
		                case 7:
		                    System.out.println("Logging out...");
		                    return;
		                default:
		                    System.out.println("Invalid choice. Please select a valid option.");
		            }
		        }
		    
	

		    }

}
