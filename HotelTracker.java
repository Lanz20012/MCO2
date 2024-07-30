import java.util.ArrayList;



/**
 * HotelTracker manages a collection of hotels, providing functionality to create, 
 * view, update, and delete hotels and their associated data such as rooms and reservations.
 * This class acts as a central point for managing hotel-related operations.
 */
public class HotelTracker {
    private ArrayList<Hotel> hotels;
    private ArrayList<String> hotelNames = new ArrayList<>();

    
    /**
     * Constructor for the HotelTracker class.
     * Initializes the list of hotels.
     */
    public HotelTracker() {
        this.hotels = new ArrayList<Hotel>();
    }

    
    
    /**
     * Creates a new hotel if the number of rooms is within the valid range 
     * and the hotel name is unique.
     * 
     * @param hotelName The name of the hotel to be created.
     * @param numberOfRooms The number of rooms in the hotel.
     * @return An integer status code: 
     *         0 - Success, hotel created;
     *         1 - Hotel already exists;
     *         2 - Invalid number of rooms.
     */
    public int createHotel(String hotelName, int numberOfRooms) {


    	if (numberOfRooms >= 1 && numberOfRooms <= 50) {
            for (Hotel hotel : hotels) {
                if (hotel.getHighLevelInfo().getHotelName().equals(hotelName)) {
                   
                    return 1;
                }
            }

            LowLevelInfo lowinfo = new LowLevelInfo();
            HighLevelInfo highinfo = new HighLevelInfo(hotelName, numberOfRooms, lowinfo);

            lowinfo.generateRoomNames(numberOfRooms);
            lowinfo.createDates();
            lowinfo.createReservations();
            
            if (hotelExists(hotelName)) {
                
                return 1;
            }

            Hotel newHotel = new Hotel(highinfo, lowinfo);
            this.hotels.add(newHotel);

            
             return 0;
        } else {
            
        }
    	return 2;


    }
    
    
    /**
     * Displays high-level information of a specific hotel by name.
     * 
     * @param hotelName The name of the hotel.
     * @return The Hotel object with the specified name, or null if not found.
     */
    public Hotel displayHighLevelInfo(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getHighLevelInfo().getHotelName().equals(hotelName)) {
                
                return hotel;
            }
        }
		return null;
    }
    
    
    
    /**
     * Displays low-level information for a specific date in a hotel.
     * 
     * @param hotelName The name of the hotel.
     * @param selectedDate The date for which information is requested.
     * @return The Hotel object containing the low-level information, or null if not found.
     */
    public Hotel displayLowLevelInfoDate(String hotelName, int selectedDate) {
        for (Hotel hotel : hotels) {
            if (hotel.getHighLevelInfo().getHotelName().equals(hotelName)) {
            	return hotel;
            }
        }
        return null;
    }
    
    
    /**
     * Displays room information for a specific room number in a hotel.
     * 
     * @param hotelName The name of the hotel.
     * @param roomNumber The room number for which information is requested.
     * @return The Room object, or null if the room number is invalid or hotel not found.
     */
    public Room displayLowLevelInfoRoom(String hotelName, int roomNumber) {
        for (Hotel hotel : hotels) {
            if (hotel.getHighLevelInfo().getHotelName().equals(hotelName)) {
                LowLevelInfo lowInfo = hotel.getLowLevelInfo();

                if (lowInfo != null) {
                    if (roomNumber < 1 || roomNumber > lowInfo.getRooms().size()) {

                        return null;
                    }

                    Room room = lowInfo.getRoom(roomNumber);
                    return room;

                    
                    
                } else {
                   

                }
                return null;
            }
        }

        return null;
    }

    
    
    /**
     * Displays reservation information for a specific guest in a hotel.
     * 
     * @param hotelName The name of the hotel.
     * @param guestName The name of the guest.
     * @return The Reservation object, or null if no reservation found for the guest.
     */
    public Reservation displayLowLevelInfoGuest(String hotelName, String guestName) {
        for (Hotel hotel : hotels) {
            if (hotel.getHighLevelInfo().getHotelName().equals(hotelName)) {
                LowLevelInfo lowInfo = hotel.getLowLevelInfo();

                if (lowInfo != null) {
                    for (Reservation reservation : lowInfo.getReservations()) {
                        if (guestName.equals(reservation.getGuestName())) {


                            return reservation;
                        }
                    }
                    
                    return null;
                } else {
                   
                }
                return null;
            }
        }
       
        return null;
    }
    
    
    /**
     * Changes the name of a hotel.
     * 
     * @param oldName The current name of the hotel.
     * @param newName The new name to be set for the hotel.
     * @return An integer status code: 
     *         0 - Success, hotel name changed;
     *         1 - A hotel with the new name already exists;
     *         2 - Hotel with the old name not found.
     */
    public int changeHotelName(String oldName, String newName) {
        

        
        for (Hotel hotel : hotels) {
        	
        	if (hotelExists(newName)) {
                
                return 1;
            }
            if (hotel.getHighLevelInfo().getHotelName().equals(oldName)) {
                hotel.getHighLevelInfo().setHotelName(newName);
                
                return 0;
            }
        }
        
        return 2;
       }
    
    
    /**
     * Adds rooms to a hotel.
     * 
     * @param hotelName The name of the hotel.
     * @param newRooms The number of new rooms to be added.
     * @return The number of rooms actually added, or 0 if the hotel is not found or full.
     */
    public int addRoom(String hotelName, int newRooms) {
        double basePrice = 1299.00;
        for (Hotel hotel : hotels) {
            if (hotel.getHighLevelInfo().getHotelName().equals(hotelName)) {
                int currentRoomCount = hotel.getLowLevelInfo().getRooms().size();
                int roomsToAdd = Math.min(newRooms, 50 - currentRoomCount);

                if (roomsToAdd <= 0) {
                    
                    return 0;
                }

                for (int i = 0; i < roomsToAdd; i++) {
                    int roomIndex = currentRoomCount + i;
                    int roomType = (roomIndex % 3) + 1; // This will cycle through 1, 2, 3

                    switch (roomType) {
                        case 1:
                            basePrice = hotel.getLowLevelInfo().getRoom(1).getPrice();
                            StandardRoom standardRoom = new StandardRoom("Room " + (roomIndex + 1), roomType, basePrice);
                            hotel.getLowLevelInfo().addRoom(standardRoom);
                            break;
                        case 2:
                            basePrice = hotel.getLowLevelInfo().getRoom(1).getPrice(); 
                            DeluxeRoom deluxeRoom = new DeluxeRoom("Room " + (roomIndex + 1), roomType, basePrice);
                            hotel.getLowLevelInfo().addRoom(deluxeRoom);
                            break;
                        case 3:
                            basePrice = hotel.getLowLevelInfo().getRoom(1).getPrice();
                            ExecutiveRoom executiveRoom = new ExecutiveRoom("Room " + (roomIndex + 1), roomType, basePrice);
                            hotel.getLowLevelInfo().addRoom(executiveRoom);
                            break;
                    }
                }

                hotel.getHighLevelInfo().setNumberOfRooms(currentRoomCount + roomsToAdd);
                
                return roomsToAdd;
            }
        }
        
        return 0;
    }

    
    
    /**
     * Removes a room from a hotel if it has no reservations.
     * 
     * @param hotelName The name of the hotel.
     * @param roomNumber The room number to be removed.
     * @return An integer status code: 
     *         0 - Success, room removed;
     *         1 - Room has reservations;
     *         2 - Hotel not found;
     *         3 - Hotel name mismatch;
     *         4 - Invalid room number.
     */
    public int removeRoom(String hotelName, int roomNumber) {
        for (Hotel hotel : hotels) {
            if (hotel.getHighLevelInfo().getHotelName().equals(hotelName)) {
                LowLevelInfo lowInfo = hotel.getLowLevelInfo();
                if (lowInfo != null) {
                    if (roomNumber < 1 || roomNumber > lowInfo.getRooms().size()) {
                        
                        return 4;
                    }

                    Room room = lowInfo.getRoom(roomNumber);
                    boolean hasReservations = false;
                    for (Reservation reservation : lowInfo.getReservations()) {
                        if (reservation.getRoom().getRoomName().equals(room.getRoomName())) {
                            hasReservations = true;
                            break;
                        }
                    }

                    if (!hasReservations) {
                        lowInfo.getRooms().remove(roomNumber - 1);
                        hotel.getHighLevelInfo().setNumberOfRooms(hotel.getHighLevelInfo().getNumberOfRooms() - 1);
                        
                        return 0;
                    } else {
                       
                        return 1;
                    }
                } else {
                    
                }
                return 2;
            }
        }
        
        return 3;
    }
    
    
    /**
     * Updates the price of all rooms in a hotel.
     * 
     * @param hotelName The name of the hotel.
     * @param newPrice The new price to set for all rooms.
     * @return An integer status code: 
     *         0 - Success, prices updated;
     *         1 - Hotel not found.
     */
    public int updateRoomPrice(String hotelName, double newPrice) {
        Hotel hotel = findHotel(hotelName);
        if (hotel != null) {
            LowLevelInfo lowLevelInfo = hotel.getLowLevelInfo();
            if (lowLevelInfo.canUpdateRoomPrice(newPrice, lowLevelInfo)) {
                for (Room room : lowLevelInfo.getRooms()) {
                	if (room instanceof StandardRoom) {
                        room.setPrice(newPrice);
                    } else if (room instanceof DeluxeRoom) {
                        room.setPrice(newPrice * 1.20); // 20% increase
                    } else if (room instanceof ExecutiveRoom) {
                        room.setPrice(newPrice * 1.35); // 35% increase
                    }
                }
                
                return 0;
            } else {
                
                return 1;
            }
        } else {
            
            return 2;
        }
    }



    /**
     * Finds a hotel by its name.
     * 
     * @param hotelName The name of the hotel to find.
     * @return The Hotel object if found, or null if not found.
     */
    private Hotel findHotel(String hotelName) { 
        for (Hotel hotel : hotels) {
            if (hotel.getHighLevelInfo().getHotelName().equalsIgnoreCase(hotelName)) {
                return hotel;
            }
        }
        return null;
    }
    
    
    /**
     * Checks if a hotel with the specified name exists in the tracker.
     * 
     * @param hotelName The name of the hotel to check.
     * @return True if the hotel exists, false otherwise.
     */
    private boolean hotelExists(String hotelName) { 
        for (Hotel hotel : hotels) {
            if (hotel.getHighLevelInfo().getHotelName().equalsIgnoreCase(hotelName)) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Removes a reservation for a guest from a specific hotel.
     * 
     * @param hotelName The name of the hotel where the reservation is made.
     * @param guestName The name of the guest with the reservation.
     * @return An integer status code: 
     *         0 - Success, reservation removed;
     *         1 - Reservation not found;
     *         2 - Low-level information not available;
     *         3 - Hotel not found.
     */
    public int removeReservation(String hotelName, String guestName) {
        for (Hotel hotel : hotels) {
            if (hotel.getHighLevelInfo().getHotelName().equals(hotelName)) {
                LowLevelInfo lowInfo = hotel.getLowLevelInfo();
                if (lowInfo != null) {
                    for (Reservation reservation : lowInfo.getReservations()) {
                        if (guestName.equals(reservation.getGuestName())) {
                            lowInfo.getReservations().remove(reservation);
                            
                            return 0;
                        }
                    }

                    return 1;
                } else {

                    return 2;
                }
                
            }
        }

        return 3;
    }
    
    
    /**
     * Removes a hotel from the list of hotels.
     * 
     * @param hotelName The name of the hotel to be removed.
     * @return An integer status code: 
     *         0 - Success, hotel removed;
     *         1 - Hotel not found.
     */
    public int removeHotel(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getHighLevelInfo().getHotelName().equals(hotelName)) {
                hotels.remove(hotel);

                return 0;
            }
        }

        return 1;
    }

    /**
     * Changes the rates for a specific day in a hotel's low-level information.
     * 
     * @param hotelName The name of the hotel.
     * @param day The day for which the rate is to be changed.
     * @param rate The new rate to set for the specified day.
     * @return An integer status code: 
     *         0 - Success, rate changed;
     *         1 - Hotel not found.
     */
    public int changeRates(String hotelName, int day, int rate){
        for(Hotel hotel : hotels){
            if(hotel.getHighLevelInfo().getHotelName().equals(hotelName)){
                double drate = rate;
                hotel.getLowLevelInfo().changeRates(day, drate);

                return 0;
            }
        }
        return 1;
    }
    
    
    
    /**
     * Simulates a booking for a guest in a specific hotel, applying any applicable discounts.
     * 
     * @param hotelName The name of the hotel.
     * @param guestName The name of the guest making the booking.
     * @param checkInDate The check-in date for the booking.
     * @param checkOutDate The check-out date for the booking.
     * @param roomType The type of room requested.
     * @param discountCode The discount code to apply.
     * @return An integer status code: 
     *         1 - I_WORK_HERE discount applied;
     *         2 - STAY4_GET1 discount applied;
     *         3 - PAYDAY discount applied;
     *         4 - No discount applied;
     *         5 - Room not available;
     *         6 - Low-level information not available;
     *         7 - Hotel not found;
     *         8 - Invalid dates.
     */
    public int simulateBooking(String hotelName, String guestName, int checkInDate, int checkOutDate, int roomType, String discountCode) {
        if (checkInDate < checkOutDate && checkInDate > 0 && checkOutDate <= 31) {
            for (Hotel hotel : hotels) {
                if (hotel.getHighLevelInfo().getHotelName().equals(hotelName)) {
                    LowLevelInfo lowInfo = hotel.getLowLevelInfo();
                    if (lowInfo != null) {
                        for (Room room : lowInfo.getRooms()) {
                            if (room.getRoomType() == roomType) {
                                boolean isAvailable = true;
                                for (int i = checkInDate; i < checkOutDate; i++) {     // Check if the room is available for the entire stay
                                    if (!room.getAvailabilityDay(i)) {
                                        isAvailable = false;
                                        break;
                                    }
                                }
                                
                                

                                if (isAvailable) {
                                    Reservation reservation = new Reservation(guestName, checkInDate, checkOutDate, room, 0.00);
                                    lowInfo.getReservations().add(reservation);

                                     // Mark the room as unavailable for the reservation dates
                                    for (int i = checkInDate; i < checkOutDate; i++) {
                                        room.setAvailabilityDay(i, false);
                                    }
                                    
                                    // Computing for the price on days with modified rates
                                    double basePrice = room.getPrice();
                                    NormalDates normal = new NormalDates(lowInfo.getModifiedDates());
                                    ModifiedDates modified = lowInfo.getModifiedDates();

                                    // Calculate the price for normal and modified dates
                                    normal.computePrice(checkInDate, checkOutDate, basePrice);
                                    modified.computePrice(checkInDate, checkOutDate, basePrice);

                                    double normalPrice = normal.getPrice();
                                    double modifiedPrice = modified.getPrice();
                                    
                                    // price is sum of prices on normal dates and modified dates
                                    double price = normalPrice + modifiedPrice;
                                    double priceOnDay = 0;
                                    
                                    
                                    // Find the price on a modified date if any
                                    for(int i = checkInDate; i<checkOutDate; i++){
                                        if(modified.getModifiedDate(i)!=0){
                                        priceOnDay = room.getPrice() * modified.getModifiedRate(checkInDate);
                                        break;
                                        }
                                    }
                                    
                                   // If no modified price found, use the room's base price
                                    if(priceOnDay == 0)
                                        priceOnDay = room.getPrice();


                                    // Apply discount based on discount code
                                    
                                    if ("I_WORK_HERE".equals(discountCode)) {
                                    	
                                    	// System.out.println("Discount: \"I_WORK_HERE\"");
                                    	I_WORK_HERE iWorkHereDiscount = new I_WORK_HERE(price);
                                        reservation.setReservationTotal(iWorkHereDiscount.applyDiscount());
                                     
                                        return 1;
      
                                    }
                                    
                                    else if (("STAY4_GET1".equals(discountCode) && ((reservation.getCheckOutDate() - reservation.getCheckInDate()) + 1) >= 5)) {
                                    	
                                    	
                                    	// System.out.println("Discount: \"STAY4_GET1\"");         	
                                    	STAY4_GET1 stay4Get1Discount = new STAY4_GET1(price, priceOnDay);
                                        reservation.setReservationTotal(stay4Get1Discount.applyDiscount());
   
                                        return 2;
                                    	
                                    }
                                    else if (("PAYDAY".equals(discountCode)) && ((reservation.getCheckInDate() <= 15 && 15 < reservation.getCheckOutDate()) || (reservation.getCheckInDate() <= 30 && 30 < reservation.getCheckOutDate()))) {
                                    	
                                    	
                                    	// System.out.println("Discount: \"PAYDAY\"");
                                    	PAYDAY paydayDiscount = new PAYDAY(price);
                                        reservation.setReservationTotal(paydayDiscount.applyDiscount());
    
                                        return 3;
                                    	
                                    }
                                    else {
                                    	
                                    	// System.out.println("Discount: No Discount");                           
                                    	reservation.setReservationTotal(price);

                                        return 4;
                                    }

                                }
                            }
                        }
                        
                        return 5;
                    } else {
                        
                    }
                    return 6;
                }
            }
            
            return 7;
        } else {
           
            return 8;
            
        }
        
        
    }
    
    
    /**
     * Retrieves and returns a list of hotel names.
     * 
     * This method iterates through the list of Hotel objects and collects the names of hotels 
     * from their high-level information. It first clears the existing list of hotel names to avoid 
     * duplicates if the method is called multiple times. If no hotel names are found, it returns null. 
     * Otherwise, it returns the list of hotel names.
     * 
     * @return An ArrayList of Strings containing the names of hotels. Returns null if no hotel names are found.
     */
    public ArrayList<String> displayHotelList() {
        hotelNames.clear(); // Clear the list to avoid duplicates if the method is called multiple times
        
        for (Hotel hotel : hotels) {
            String hotelName = hotel.getHighLevelInfo().getHotelName();
            if (hotelName != null) {
                hotelNames.add(hotelName);
            }
        }

        if (hotelNames.isEmpty()) {
            return null; // Return null if no hotel names were added to the list
        }

        return hotelNames; // Return the list of hotel names
    }

    /*
	public ArrayList<Hotel> getHotels() {
		// TODO Auto-generated method stub
		return hotels;

	}
	*/
}