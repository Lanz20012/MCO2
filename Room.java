
/**
 * The Room class represents an abstract room in a hotel, including its name, type, price,
 * and availability for each day of the month.
 */
public abstract class Room {
	protected String roomName;
	protected int type;
	protected double price;
	protected boolean[] availability; // Availability for each day of the month

	/**
     * Constructs a Room with the specified name, type, and price.
     * 
     * @param roomName The name of the room.
     * @param type The type of the room (e.g., Standard, Deluxe, Executive).
     * @param price The price of the room per night.
     */
    public Room(String roomName, int type, double price) {
        this.roomName = roomName;
        this.type = type;
        this.price = price;
        this.availability = new boolean[31]; // 31 days in a month
        for (int i = 0; i < 31; i++) {
            this.availability[i] = true; // All days are available by default
        }
    }

    /**
     * Returns the name of the room.
     * 
     * @return The room's name.
     */
    public String getRoomName() {
        return this.roomName;
    }

    /**
     * Returns the price of the room per night.
     * 
     * @return The room's price.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the room per night.
     * 
     * @param price The new price to set.
     */
    public void setPrice(double price) {
        this.price = price; 
    }

    /**
     * Returns the availability of the room for a specific day.
     * 
     * @param day The day of the month (1-31).
     * @return True if the room is available on the specified day; false otherwise.
     */
    public boolean getAvailabilityDay(int day) {
        return this.availability[day - 1];
    }

    /**
     * Sets the availability of the room for a specific day.
     * 
     * @param day The day of the month (1-31).
     * @param available True to set the room as available; false to set it as unavailable.
     */
    public void setAvailabilityDay(int day, boolean available) {
        this.availability[day - 1] = available;
    }

    /**
     * Returns the type of the room.
     * 
     * @return The room type.
     */
    public int getRoomType() {
        return type;
    }

    /**
     * Sets the type of the room.
     * 
     * @param type The new room type to set.
     */
    public void setRoomType(int type) {
        this.type = type;
    }
}