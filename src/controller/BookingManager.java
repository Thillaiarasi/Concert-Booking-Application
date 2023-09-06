package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Concert;
import model.ConcertTicketBookingSystem;
import model.PersonDetails;
import model.Seat;
import model.SeatType;


public class BookingManager {
	private ConcertTicketBookingSystem ticketSystem;

    public BookingManager(ConcertTicketBookingSystem ticketSystem) {
        this.ticketSystem = ticketSystem;
    }

    public void bookConcertTickets() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter concert name: ");
        String concertName = scanner.nextLine();
        Concert concert = ticketSystem.getConcerts().get(concertName); // Retrieve the concert using the entered name
        if (concert == null) {
            System.out.println("Concert not found.");
            return;
        }

        concert.displayAvailableSeats(); 

        if (ticketSystem.getCurrentUser() == null) {
            RegistrationSystem registrationSystem = new RegistrationSystem();
            registrationSystem.register(ticketSystem, scanner);
        } else {
            System.out.println("Enter the seat type (SILVER, PLATINUM, GENERAL): ");
            String seatTypeStr = scanner.nextLine().toUpperCase(); // Convert to uppercase
            SeatType seatType;
            try {
                seatType = SeatType.valueOf(seatTypeStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid seat type. Please enter a valid seat type.");
                return;
            }

            System.out.print("Enter the quantity of tickets to book (adults): ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            List<PersonDetails> personDetailsList = new ArrayList<>();
            for (int i = 0; i < quantity; i++) {
                System.out.println("Enter details for Person " + (i + 1) + ":");
                PersonDetails personDetails = getPersonDetailsFromUser(scanner);
                personDetailsList.add(personDetails);
            }


            System.out.print("Enter the number of children (below age 10): ");
            int numChildren = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            List<PersonDetails> childrenDetailsList = new ArrayList<>();
            for (int i = 0; i < numChildren; i++) {
                System.out.println("Enter details for Child " + (i + 1) + ":");
                PersonDetails childDetails = getPersonDetailsFromUser(scanner);
                childrenDetailsList.add(childDetails);
            }
            
            boolean bookedSuccessfully = ticketSystem.bookTickets(concertName, seatType, quantity, numChildren);
            if (bookedSuccessfully) {
                double totalAmount = calculateTotalAmount(concertName, quantity, seatType, numChildren);
                System.out.println("Tickets booked successfully!");
                System.out.println("Total Amount: $" + totalAmount);
                payment();
                System.out.println("Payment done.");
                System.out.println("----- Booking Details -----");
                System.out.println("Concert: " + concertName);
                System.out.println("Venue: " + concert.getVenue()); 
                System.out.println("Date: " + concert.getDate()); 
                System.out.println("Timing: " + concert.getTiming());
                System.out.println("Seat Type: " + seatType);
                System.out.println("Number of Persons (adult): " + quantity);
                System.out.println("Number of Children: " + numChildren);
                System.out.println("----- Person Details -----");
                for (int i = 0; i < personDetailsList.size(); i++) {
                    PersonDetails personDetails = personDetailsList.get(i);
                    System.out.println("Person " + (i + 1) + ": Name: " + personDetails.getName() + ", Age: " + personDetails.getAge());
                }

                System.out.println("----- Children Details -----");
                for (int i = 0; i < childrenDetailsList.size(); i++) {
                    PersonDetails childDetails = childrenDetailsList.get(i);
                    System.out.println("Child " + (i + 1) + ": Name: " + childDetails.getName() + ", Age: " + childDetails.getAge());
                   
                } System.out.println("***ThankYou for Booking***");
            } else {
                System.out.println("Booking failed. Not enough available seats.");
            }}
        }
        private PersonDetails getPersonDetailsFromUser(Scanner scanner) {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            return new PersonDetails(name, age);
        
    }

    private double calculateTotalAmount(String concertName, int quantity, SeatType seatType, int numChildren) {
        Concert concert = ticketSystem.getConcerts().get(concertName);
        Seat seat = concert.getSeats().get(seatType);

        double totalPrice = seat.getPrice() * quantity;
        double childPrice = seat.getChildPrice() * numChildren;
        return totalPrice + childPrice;
    }

    private void payment() {
        // Payment process
        Scanner scanner = new Scanner(System.in);
        System.out.println("Booking successful! Proceed to payment.");
        System.out.println("Select a payment mode:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Cash");
        System.out.print("Enter your choice: ");
        int paymentOption = scanner.nextInt();

        switch (paymentOption) {
            case 1:
                System.out.println("Payment using Credit Card selected. Processing payment...");
                // Payment processing logic for Credit Card
                break;
            case 2:
                System.out.println("Payment using PayPal selected. Processing payment...");
                // Payment processing logic for PayPal
                break;
            case 3:
                System.out.println("Payment using Cash selected. Please pay at the venue.");
                break;
            default:
                System.out.println("Invalid payment option. Payment failed.");
                return;
        }
}}
