package model;

public class Seat {
	private int totalSeats;
    private int availableSeats;
    private double price;
    private double childPrice;

    public Seat(int totalSeats, double price, double childPrice) {
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.price = price;
        this.childPrice = childPrice;
    }

    public int getTotalSeats() {
        return totalSeats;
    }
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
    
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    

    public double getChildPrice() {
        return childPrice;
    }
    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public boolean bookSeats(int quantity, int numChildren) {
    	int totalSeatsRequired = quantity + numChildren;
        if (availableSeats >= quantity) {
            availableSeats -=totalSeatsRequired ;
            return true;
        }
        return false;
    }
}
