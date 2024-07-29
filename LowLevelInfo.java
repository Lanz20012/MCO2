import java.util.ArrayList;


/**
 * The LowLevelInfo class manages the detailed information of a hotel's rooms and reservations.
 * This includes room availability, reservations, and pricing modifications.
 */
public class LowLevelInfo {
  private ArrayList<Room> rooms;
  private ArrayList<Reservation> reservations;
  // private String hotelName;
  private NormalDates normal;
  private ModifiedDates modified;

  /**
   * Constructs a LowLevelInfo object, initializing the rooms and reservations lists,
   * as well as the modified and normal date information.
   */
  public LowLevelInfo() {
    this.rooms = new ArrayList<Room>();
    this.reservations = new ArrayList<Reservation>();
    this.modified = new ModifiedDates(); // Initialize modified dates
    this.normal = new NormalDates(modified); // Initialize normal dates with modified dates reference
  }

  /**
   * Gets the list of rooms in the hotel.
   * 
   * @return An ArrayList of Room objects representing the hotel's rooms.
   */
  public ArrayList<Room> getRooms() {
    return rooms;
  }

  /**
   * Retrieves a specific room by its room number.
   * 
   * @param roomNumber The number of the room to retrieve.
   * @return The Room object corresponding to the specified room number, or null if not found.
   */
  public Room getRoom(int roomNumber) {
    if (roomNumber > 0 && roomNumber <= rooms.size()) {
      return rooms.get(roomNumber - 1);
    }
    return null;
  }

  /**
   * Adds a new room to the hotel's list of rooms.
   * 
   * @param room The Room object to add.
   */
  public void addRoom(Room room) {
    rooms.add(room);
  }

  /**
   * Gets the list of reservations for the hotel.
   * 
   * @return An ArrayList of Reservation objects representing the hotel's reservations.
   */
  public ArrayList<Reservation> getReservations() {
    return reservations;
  }

  /**
   * Adds a new reservation to the hotel's list of reservations.
   * 
   * @param reservation The Reservation object to add.
   */
  public void addReservation(Reservation reservation) {
    reservations.add(reservation);
  }

  /**
   * Gets the number of available rooms on a specific date.
   * 
   * @param selectedDate The date to check availability.
   * @return The number of available rooms on the specified date.
   */
  public int getAvailableRooms(int selectedDate) {
    int availableRooms = 0;
    for (Room room : rooms) {
      if (room.getAvailabilityDay(selectedDate)) {
        availableRooms++;
      }
    }
    return availableRooms;
  }

  /**
   * Gets the number of booked rooms on a specific date.
   * 
   * @param selectedDate The date to check bookings.
   * @return The number of booked rooms on the specified date.
   */
  public int getBookedRooms(int selectedDate) {
    int bookedRooms = 0;
    for (Room room : rooms) {
      if (!room.getAvailabilityDay(selectedDate)) {
        bookedRooms++;
      }
    }
    return bookedRooms;
  }

  /**
   * Generates room names and types for the hotel based on the number of rooms.
   * 
   * @param numberOfRooms The total number of rooms to generate.
   */
  public void generateRoomNames(int numberOfRooms) {
      double basePrice = 1299.00;
      Room room;

      for (int i = 1, j = 1; i <= numberOfRooms; i++, j++) {
          // Cycle through room types 1, 2, 3
          switch (j) {
              case 1:
                  room = new StandardRoom("Room " + i, j, basePrice);
                  break;
              case 2:
                  room = new DeluxeRoom("Room " + i, j, basePrice);
                  break;
              case 3:
                  room = new ExecutiveRoom("Room " + i, j, basePrice);
                  break;
              default:
                  room = new StandardRoom("Room " + i, j, basePrice);
                  j = 1; // Reset type cycle
                  break;
          }


          rooms.add(room); // Add created room to the list
      }
  }

  /**
   * Initializes room availability for all rooms to true for all days of the month.
   */
  public void createDates() {
    for (Room room : rooms) {
      for (int i = 0; i < 31; i++) {
        room.setAvailabilityDay(i + 1, true);
      }
    }
  }

  /**
   * Initializes the list of reservations for the hotel.
   */
  public void createReservations() {
    this.reservations = new ArrayList<Reservation>();
  }

  /**
   * Checks if room prices can be updated based on the presence of reservations and the new price.
   * 
   * @param newPrice The new price to be set for the rooms.
   * @param lowLevelInfo The LowLevelInfo object containing the current reservations.
   * @return true if the prices can be updated, false otherwise.
   */
  public boolean canUpdateRoomPrice(double newPrice, LowLevelInfo lowLevelInfo) {
	  if (newPrice < 100) {
	        return false; // New price is less than 100, cannot update
	    }

      for (Reservation reservation : lowLevelInfo.getReservations()) {
          if (reservation != null) {
              return false; // There is at least one reservation, cannot update prices
          }
      }
      return true; // All rooms can be updated

  }

  /*
  public void setHotelName(String hotelName) {
      this.hotelName = hotelName;
  }
  */

  /**
   * Changes the rates for a specific day by adding the rate to the modified dates list.
   * 
   * @param day The day of the month for which the rate is being changed.
   * @param rate The new rate to apply for the specified day.
   */
  public void changeRates(int day, double rate){
      modified.addModifiedDates(day, rate);
  }

  /**
   * Gets the NormalDates object, representing days without rate changes.
   * 
   * @return The NormalDates object associated with this LowLevelInfo.
   */
  public NormalDates getNormalDates(){
    return this.normal;
  }

  /**
   * Sets the NormalDates object for this LowLevelInfo.
   * 
   * @param normal The NormalDates object to set.
   */
  public void setNormalDates(NormalDates normal){
    this.normal = normal;
  }

  /**
   * Gets the ModifiedDates object, representing days with rate changes.
   * 
   * @return The ModifiedDates object associated with this LowLevelInfo.
   */
  public ModifiedDates getModifiedDates(){
    return this.modified;
  }

  /**
   * Sets the ModifiedDates object for this LowLevelInfo.
   * 
   * @param modified The ModifiedDates object to set.
   */
  public void setModifiedDates(ModifiedDates modified){
    this.modified = modified;
  }

}