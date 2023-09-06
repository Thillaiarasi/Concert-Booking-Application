package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;



public class Concert {
	  private String concertName;
	    private String artist;
	    private String venue;
	    private String date;
	    private String timing;
	    private Seat platinumSeat;
	    private Seat silverSeat;
	    private Seat generalSeat;

	    private Map<SeatType, Seat> seats;

	    public Concert() {
	        this.concertName = concertName;
	        this.artist = artist;
	        this.venue = venue;
	        this.date = date;
	        this.timing = timing;
	        this.platinumSeat = platinumSeat;
	        this.silverSeat = silverSeat;
	        this.generalSeat = generalSeat;
	        this.seats = new HashMap<>();
	    }

	    public String getConcertName() {
	        return concertName;
	    }
	    public void setConcertName(String concertName) {
	        this.concertName = concertName;
	    }

	    public String getArtist() {
	        return artist;
	    }
	    public void setArtist(String artist) {
	        this.artist = artist;
	    }
	    
	    public String getVenue() {
	        return venue;
	    }
	    public void setVenue(String venue) {
	        this.venue = venue;
	    }

	    public String getDate() {
	        return date;
	    }
	    public void setDate(String date) {
	        this.date = date;
	    }
	    
	    public String getTiming() {
	        return timing;
	    }
	    public void setTiming(String timing) {
	        this.timing = timing;
	    }
	    
	    public Seat getPlatinumSeat() {
	        return platinumSeat;
	    }

	    public void setPlatinumSeat(Seat platinumSeat) {
	        this.platinumSeat = platinumSeat;
	    }

	    public Seat getSilverSeat() {
	        return silverSeat;
	    }

	    public void setSilverSeat(Seat silverSeat) {
	        this.silverSeat = silverSeat;
	    }

	    public Seat getGeneralSeat() {
	        return generalSeat;
	    }

	    public void setGeneralSeat(Seat generalSeat) {
	        this.generalSeat = generalSeat;
	    }

	    public void addSeat(SeatType seatType, Seat seat) {
	        seats.put(seatType, seat);
	    }

	    public Map<SeatType, Seat> getSeats() {
	        return seats;
	    }

	    public void displayAvailableSeats() {
	        System.out.println("Available Seats for " + concertName + ":");
	        for (Map.Entry<SeatType, Seat> entry : seats.entrySet()) {
	            SeatType seatType = entry.getKey();
	            Seat seat = entry.getValue();
	            System.out.println("Seat Type: " + seatType);
	            System.out.println("Total Seats: " + seat.getTotalSeats());
	            System.out.println("Price: " + seat.getPrice());
	            System.out.println("Child Price: " + seat.getChildPrice());
	            System.out.println();
	        }
	    }

	    public boolean bookTickets(SeatType seatType, int quantity, int numChildren) {
	        Seat seat = seats.get(seatType);
	        if (seat != null) {
	            return seat.bookSeats(quantity, numChildren);
	        }
	        return false;
	    }
}
