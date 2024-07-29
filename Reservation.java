
/**
 * The Reservation class represents a reservation made by a guest for a specific room
 * in a hotel, including details about the stay and the total cost of the reservation.
 */
public class Reservation {
    private String guestName;
    private int checkInDate;
    private int checkOutDate;
    private Room room;
    private double reservationTotal;

    
    /**
     * Constructs a Reservation with the specified details.
     * 
     * @param guestName The name of the guest making the reservation.
     * @param checkInDate The check-in date for the reservation (1-31).
     * @param checkOutDate The check-out date for the reservation (1-31).
     * @param room The room that is reserved.
     * @param reservationTotal The total cost of the reservation.
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room, double reservationTotal) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.reservationTotal = reservationTotal;
    }

    /**
     * Returns the name of the guest who made the reservation.
     * 
     * @return The guest's name.
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * Returns the check-in date of the reservation.
     * 
     * @return The check-in date (1-31).
     */
    public int getCheckInDate() {
        return checkInDate;
    }

    /**
     * Returns the check-out date of the reservation.
     * 
     * @return The check-out date (1-31).
     */
    public int getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Returns the room that is reserved.
     * 
     * @return The reserved room.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets the room for this reservation.
     * 
     * @param room The room to set for this reservation.
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Returns the total cost of the reservation.
     * 
     * @return The reservation total.
     */
    public double getReservationTotal() {
        return this.reservationTotal;
    }

    /**
     * Sets the total cost of the reservation.
     * 
     * @param reservationTotal The total cost to set for this reservation.
     */
    public void setReservationTotal(double reservationTotal) {
        this.reservationTotal = reservationTotal;
    }
}
