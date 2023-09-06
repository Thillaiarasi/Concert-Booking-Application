package view;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import controller.ConfigLoader;
import model.ConcertTicketBookingSystem;
import model.SeatType;

public class MusicConcertApplication {

	public static void main(String[] args) {
		ConcertTicketBookingSystem ticketSystem = new ConcertTicketBookingSystem();
        Scanner scanner = new Scanner(System.in);
        MusicConcertApplicationView view = new MusicConcertApplicationView(ticketSystem, scanner);

        // Load configuration from properties file
        Properties config = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Add concerts using configuration
       
        ConfigLoader.loadConfigAndAddConcerts(ticketSystem, config);


        // Run the application
        view.run();

}
	
	}
