package controller;

import java.util.Scanner;

import model.ConcertTicketBookingSystem;


public class RegistrationSystem {
	public void register(ConcertTicketBookingSystem ticketSystem, Scanner scanner) {
        System.out.println("You need to register or login first.");

        System.out.println("Enter 1 to register.");
        System.out.println("Enter 2 to login.");
        System.out.println("Enter 3 to exit.");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                boolean isValidRegistration = false;
                while (!isValidRegistration) {
                    System.out.print("Enter a username: ");
                    String registerUsername = scanner.nextLine();
                    if (isValidUsername(registerUsername)) {
                        System.out.print("Enter a password: ");
                        String registerPassword = scanner.nextLine();
                        if (isValidPassword(registerPassword)) {
                            System.out.print("Enter your mobile number: ");
                            String registerMobileNumber = scanner.nextLine();
                            if (isValidMobileNumber(registerMobileNumber)) {
                                System.out.print("Enter your email: ");
                                String registerEmail = scanner.nextLine();
                                if (isValidEmail(registerEmail)) {
                                    ticketSystem.registerUser(registerUsername, registerPassword, registerMobileNumber, registerEmail);
                                    isValidRegistration = true;
                                } else {
                                    System.out.println("Invalid email format. Please enter a valid email.");
                                }
                            } else {
                                System.out.println("Invalid mobile number format. Please enter a valid mobile number.");
                            }
                        } else {
                            System.out.println("Invalid password. Password must contain at least 8 characters and include both letters and numbers.");
                        }
                    } else {
                        System.out.println("Invalid username. Username must contain only alphanumeric characters and be between 3 and 20 characters long.");
                    }
                }
                break;
            case 2:
                System.out.print("Enter your username: ");
                String loginUsername = scanner.nextLine();
                System.out.print("Enter your password: ");
                String loginPassword = scanner.nextLine();
                boolean loginSuccess = ticketSystem.login(loginUsername, loginPassword);
                if (!loginSuccess) {
                    System.out.println("Invalid credentials. Exiting the system.");
                    System.exit(0);
                }
                break;
            case 3:
                System.out.println("Exiting the system.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Exiting the system.");
                System.exit(0);
        }
    }

    private boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9]{3,20}$";
        return username.matches(regex);
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$";
        return password.matches(regex);
    }

    private boolean isValidMobileNumber(String mobileNumber) {
        String regex = "^[0-9]{10}$";
        return mobileNumber.matches(regex);
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }
}
