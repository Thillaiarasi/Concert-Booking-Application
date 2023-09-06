package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import model.Concert;
import model.ConcertTicketBookingSystem;
import model.Seat;
import model.SeatType;
import model.User;

public class ConfigLoader {
	 public static void loadConfigAndAddConcerts(ConcertTicketBookingSystem ticketSystem, Properties config) {
	        for (String key : config.stringPropertyNames()) {
	        	
	            if (key.startsWith("concert.")) {
	                String[] keyParts = key.split("\\.");
	                if (keyParts.length == 3) {
	                    String concertName = keyParts[1];
	                    String property = keyParts[2];

	                    if (property.equals("name")) {
	                        Concert concert = new Concert();
	                        concert.setConcertName(config.getProperty(key));

	                        String artistKey = "concert." + concertName + ".artist";
	                        concert.setArtist(config.getProperty(artistKey));

	                        String venueKey = "concert." + concertName + ".venue";
	                        concert.setVenue(config.getProperty(venueKey));

	                        String dateKey = "concert." + concertName + ".date";
	                        concert.setDate(config.getProperty(dateKey));

	                        String timingKey = "concert." + concertName + ".timing";
	                        concert.setTiming(config.getProperty(timingKey));

	                        // Load seat information for the concert
	                        for (SeatType seatType : SeatType.values()) {
	                            String seatPrefix = "concert." + concertName + ".seat." + seatType.name().toLowerCase();
	                            String totalSeatsKey = seatPrefix + ".totalSeats";
	                            String priceKey = seatPrefix + ".price";
	                            String childPriceKey = seatPrefix + ".childPrice";

	                            int totalSeats = Integer.parseInt(config.getProperty(totalSeatsKey, "0"));
	                            double price = Double.parseDouble(config.getProperty(priceKey, "0.0"));
	                            double childPrice = Double.parseDouble(config.getProperty(childPriceKey, "0.0"));

	                            Seat seat = new Seat(totalSeats, price, childPrice);
	                            concert.addSeat(seatType, seat);
	                        }
	                        System.out.println("Loaded concert: " + concert.getConcertName());

	                        ticketSystem.addConcert(concert);
	                    }
	                }
	            }
	            
	        }
	        String defaultUsername = config.getProperty("user.default.username");
	        String defaultPassword = config.getProperty("user.default.password");
	        String defaultMobileNumber = config.getProperty("user.default.mobileNumber");
	        String defaultEmail = config.getProperty("user.default.email");
	        ticketSystem.initializeDefaultUser(defaultUsername, defaultPassword, defaultMobileNumber, defaultEmail);
	    }
	 

	 		}
