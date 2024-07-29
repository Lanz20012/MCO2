import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 * Controller class for managing interactions between the Hotel GUI and the Hotel Tracker model.
 * Implements ActionListener for handling button actions and DocumentListener for text field updates.
 */
public class HotelController implements ActionListener, DocumentListener {

    private HotelGUI view;
    private HotelTracker model;

    
    /**
     * Constructs a HotelController with the specified view and model.
     * 
     * @param view The GUI view for the hotel application.
     * @param model The model representing the hotel's data and logic.
     */
    public HotelController(HotelGUI view, HotelTracker model) {
        this.view = view;
        this.model = model;
        this.view.setActionListener(this);
        this.view.setDocumentListener(this);
    }

    
    /**
     * Handles action events from buttons in the GUI.
     * 
     * @param e The action event triggered by button interactions.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == view.getCreateHotelButton()) {
            view.displayCreateHotelDialog();
        } else if (source == view.getViewHotelButton()) {
            view.displayViewHotelDialog();
        } else if (source == view.getManageHotelButton()) {
            view.displayManageHotelDialog();
        } else if (source == view.getSimulateBookingButton()) {
            view.displaySimulateBookingDialog();
        } else if (source == view.getChangeRatesButton()) {
            view.displayChangeRates();
        } else if (source == view.getExitButton()) {
            System.exit(0);
        }
    }


    @Override
    public void insertUpdate(DocumentEvent e) {
        
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        
    }

    /**
     * Handles the creation of a hotel based on user input.
     * 
     * @param hotelName The name of the hotel to create.
     * @param roomsInput The number of rooms for the hotel.
     */
    public void handleCreateHotel(String hotelName, int roomsInput) {
    	try {

    		
    		int val = model.createHotel(hotelName, roomsInput);
        	
        	if (val == 0) {
        		
        		view.displayMessage("Hotel " + hotelName + " created successfully!");
        	}
        	else if (val == 1) {
        		
        		view.displayMessage("Hotel with name '" + hotelName + "' already exists.");
        		
        	}
        	else if (val == 2) {
        		view.displayMessage("Number of rooms must be between 1 and 50.");
        	}
    		
    		
    		

        } catch (NumberFormatException ex) {
            view.displayMessage("Please enter a valid number for rooms.");
        }
    	
    	
    }
    
    
    /**
     * Handles the retrieval and display of high-level hotel information.
     * 
     * @param hotelName The name of the hotel whose information is to be retrieved.
     */
    public void handleHighLevelInfo(String hotelName) {
        Hotel hotel;
    	hotel = model.displayHighLevelInfo(hotelName);
    	if (hotel != null) {
    		
    		StringBuilder message = new StringBuilder();
            message.append("Hotel Name: ").append(hotel.getHighLevelInfo().getHotelName()).append("\n");
            message.append("Number of Rooms: ").append(hotel.getHighLevelInfo().getNumberOfRooms()).append("\n");
            message.append("Total Earnings: ").append(String.format("%.2f", hotel.getHighLevelInfo().getEarnings())).append("\n");
            
            view.displayMessage(message.toString());
    	}
    	else
    		view.displayMessage("Hotel not found");
    }

    
    /**
     * Handles the display of total rooms available and booked on a specific date.
     * 
     * @param hotelName The name of the hotel.
     * @param selectedDate The date for which room availability is to be checked.
     */
	public void handleTotalRooms(String hotelName, int selectedDate) {
		// TODO Auto-generated method stub
		Hotel hotel;
    	hotel = model.displayLowLevelInfoDate(hotelName, selectedDate);
 
    	if (hotel != null) {
    		
    		StringBuilder message = new StringBuilder();
            message.append("Total Available Rooms: ").append(hotel.getLowLevelInfo().getAvailableRooms(selectedDate)).append("\n");
            message.append("Total Booked Rooms: ").append(hotel.getLowLevelInfo().getBookedRooms(selectedDate)).append("\n");
            
            view.displayMessage(message.toString());
    	}
    	else
    		view.displayMessage("Hotel not found");
		
	}

	
	
	/**
     * Handles the retrieval and display of specific room information.
     * 
     * @param hotelName The name of the hotel.
     * @param roomNumber The number of the room to retrieve information for.
     */
	public void handleSpecificRoomInfo(String hotelName, int roomNumber) {
		// TODO Auto-generated method stub
		
		Room room;
		
		try {
	
			room = model.displayLowLevelInfoRoom(hotelName, roomNumber);
        	
        	if (room != null) {
        		
        		StringBuilder message = new StringBuilder();
                message.append("Room Name: ").append(room.getRoomName()).append("\n");
                                      
                String roomTypeDescription;

                switch (room.getRoomType()) {
                    case 1:
                        roomTypeDescription = "Standard Room";
                        break;
                    case 2:
                        roomTypeDescription = "Deluxe Room";
                        break;
                    case 3:
                        roomTypeDescription = "Executive Room";
                        break;
                    default:
                        roomTypeDescription = "Unknown Room Type";
                        break;
                }

                message.append("Room Type: ").append(roomTypeDescription).append("\n");              
                
                message.append("Price per Night: ").append(String.format("%.2f", room.getPrice())).append("\n");

                
                for (int i = 0; i < 31; i++) {
                    if (room.getAvailabilityDay(i + 1)) {
                    	message.append("Room is available on day ").append(i + 1).append("\n");                
                        
                    } else {
                    	message.append("Room is booked on day ").append(i + 1).append("\n");
                        
                    }
                }
                
                view.displayMessage(message.toString());               
                
        	}
        	else {
        		
        		view.displayMessage("Hotel or room not found");
        		
        	}
    		   		
    		
        } catch (NumberFormatException ex) {
            view.displayMessage("Please enter a valid room number.");
        }
		
	}

	
	/**
     * Handles the retrieval and display of reservation information for a specific guest.
     * 
     * @param hotelName The name of the hotel.
     * @param guestName The name of the guest whose reservation is to be retrieved.
     */
	public void handleReservationInfo(String hotelName, String guestName) {
	    Reservation reservation;
	    
	    try {
	        reservation = model.displayLowLevelInfoGuest(hotelName, guestName);
	        
	        if (reservation != null) {
	            StringBuilder message = new StringBuilder();
	            message.append("Guest Name: ").append(reservation.getGuestName()).append("\n");
	            message.append("Check In Date: ").append(reservation.getCheckInDate()).append("\n");
	            message.append("Check Out Date: ").append(reservation.getCheckOutDate()).append("\n");
	            message.append("Room Name: ").append(reservation.getRoom().getRoomName()).append("\n");
	            
	            
	            
	            String roomTypeDescription;

	            switch (reservation.getRoom().getRoomType()) {
	                case 1:
	                    roomTypeDescription = "Standard Room";
	                    break;
	                case 2:
	                    roomTypeDescription = "Deluxe Room";
	                    break;
	                case 3:
	                    roomTypeDescription = "Executive Room";
	                    break;
	                default:
	                    roomTypeDescription = "Unknown Room Type";
	                    break;
	            }

	            message.append("Room Type: ").append(roomTypeDescription).append("\n");
         
	            
	            message.append("Price per Night: ")
	            .append(String.format("%.2f", reservation.getRoom().getPrice()))
	            .append("\n");

	            
	            double totalPrice = reservation.getReservationTotal();
	            message.append("Total Price: ")
	            .append(String.format("%.2f", totalPrice))
	            .append("\n");

	            
	            // Display the complete message
	            view.displayMessage(message.toString());
	        } else {
	            view.displayMessage("Hotel or reservation not found");
	        }
	        
	    } catch (NumberFormatException ex) {
	        view.displayMessage("Please enter a valid hotel name.");
	    }
	}


	
	 /**
     * Handles the simulation of booking a room in a hotel with optional discount codes.
     * 
     * @param hotelName The name of the hotel.
     * @param guestName The name of the guest.
     * @param checkInDate The check-in date.
     * @param checkOutDate The check-out date.
     * @param roomType The type of the room to book.
     * @param discountCode The discount code to apply.
     */
	public void handleSimulateBooking(String hotelName, String guestName, int checkInDate, int checkOutDate,
			int roomType, String discountCode) {
		// TODO Auto-generated method stub
		int val = model.simulateBooking(hotelName, guestName, checkInDate, checkOutDate, roomType, discountCode);
		
		try {
			
			switch (val) {
			case 1:
				view.displayMessage("Booking successful with 'I_WORK_HERE' discount applied.");
				break;
			case 2:
				view.displayMessage("Booking successful with 'STAY4_GET1' discount applied.");
				break;
			case 3:
				view.displayMessage("Booking successful with 'PAYDAY' discount applied.");
				break;
			case 4:
				view.displayMessage("Booking successful with no discount applied.");
				break;
			case 5:
				view.displayMessage("No available rooms for the selected dates.");
				break;
			case 6:
				view.displayMessage("Low-level info not found for hotel: " + hotelName);
				break;
			case 7:
				view.displayMessage("Hotel not found: " + hotelName);
				break;
			case 8:
				view.displayMessage("Invalid dates. Check-in date must be before check-out date, and both must be within the range of 1 to 31.");
				break;
			default:
				view.displayMessage("An unexpected error occurred");
				break;
		}
    		   		
    		
        } catch (NumberFormatException ex) {
            view.displayMessage("Please enter a valid hotel name.");
        }
		
	}
	
	
	 /**
     * Handles the removal of a specific room from a hotel.
     * 
     * @param hotelName The name of the hotel.
     * @param roomNumber The number of the room to be removed.
     */
	public void handleRemoveRoom(String hotelName, int roomNumber) {
		
		
		int val = model.removeRoom(hotelName, roomNumber);
		
		try {
			
			switch (val) {
			case 0:
				view.displayMessage("Room Removed: Room " + roomNumber);
				break;
			case 1:
				view.displayMessage("There are existing reservations for this room.");
				break;
			case 2:
				view.displayMessage("Low level info not found for hotel: " + hotelName);
				break;
			case 3:
				view.displayMessage("Hotel not found");
				break;
			case 4:
				view.displayMessage("Room not found");
				break;
			default:
				view.displayMessage("An unexpected error occurred");
				break;
		}
    		   		
    		
        } catch (NumberFormatException ex) {
            view.displayMessage("Please enter a valid room number.");
        }
		
		
	}

	
	 /**
     * Handles the removal of a reservation for a specific guest.
     * 
     * @param hotelName The name of the hotel.
     * @param guestName The name of the guest whose reservation is to be removed.
     */
	public void handleRemoveReservation(String hotelName, String guestName) {
		// TODO Auto-generated method stub
		
		int val = model.removeReservation(hotelName, guestName);
		
		try {
			
			switch (val) {
			case 0:
				view.displayMessage("Reservation Removed for guest: " + guestName);
				break;
			case 1:
				view.displayMessage("Reservation not found for guest: " + guestName);
				break;
			case 2:
				view.displayMessage("Low level info not found for hotel: " + hotelName);
				break;
			case 3:
				view.displayMessage("Hotel not found: " + hotelName);
				break;
			default:
				view.displayMessage("An unexpected error occurred");
				break;
		}
    		   		
    		
        } catch (NumberFormatException ex) {
            view.displayMessage("Please enter a valid hotel name.");
        }
		
		
		
	}

	
	 /**
     * Handles updating the price of all rooms in a hotel.
     * 
     * @param hotelName The name of the hotel.
     * @param newPrice The new price to set for all rooms.
     */
	public void handleUpdateRoomPrice(String hotelName, double newPrice) {
		// TODO Auto-generated method stub
		
		int val = model.updateRoomPrice(hotelName, newPrice);
		
		try {
			
			switch (val) {
			case 0:
				view.displayMessage("Price Updated for all rooms in hotel: " + hotelName);
				break;
			case 1:
				view.displayMessage("Cannot update prices because there are reservations or new price is less than 100.");
				break;
			case 2:
				view.displayMessage("Hotel not found.");
				break;
			default:
				view.displayMessage("An unexpected error occurred");
				break;
		}
    		   		
    		
        } catch (NumberFormatException ex) {
            view.displayMessage("Please enter a valid hotel name.");
        }
		
		
	}

	
	/**
     * Handles the removal of a hotel from the system.
     * 
     * @param hotelName The name of the hotel to be removed.
     */
	public void handleRemoveHotel(String hotelName) {
		// TODO Auto-generated method stub
		
		int val = model.removeHotel(hotelName);
		
		try {
			
			switch (val) {
			case 0:
				view.displayMessage("Hotel Removed: " + hotelName);
				break;
			case 1:
				view.displayMessage("Hotel not found");
				break;
			default:
				view.displayMessage("An unexpected error occurred");
				break;
		}
    		   		
    		
        } catch (NumberFormatException ex) {
            view.displayMessage("Please enter a valid hotel name.");
        }
		
	}

	
	/**
     * Handles adding a specified number of rooms to a hotel.
     * 
     * @param hotelName The name of the hotel.
     * @param numRooms The number of rooms to add.
     */
	public void handleAddRooms(String hotelName, int numRooms) {
		// TODO Auto-generated method stub
		
		
		int val = model.addRoom(hotelName, numRooms);
		
		if (val != 0) {
			
			view.displayMessage(val + " Room(s) Added.");
		}
		else {
			
			view.displayMessage("The hotel is at max capacity (50 rooms) or hotel is not found");
		}
		
	}

	
	 /**
     * Handles changing the name of a hotel.
     * 
     * @param currentHotelName The current name of the hotel.
     * @param newHotelName The new name to assign to the hotel.
     */
	public void handleChangeHotelName(String currentHotelName, String newHotelName) {
		// TODO Auto-generated method stub
		
		
		int val = model.changeHotelName(currentHotelName, newHotelName);
		
		try {
			
			switch (val) {
			case 0:
				view.displayMessage("Hotel Name Changed to: " + newHotelName);
				break;
			case 1:
				view.displayMessage("Hotel with name '" + newHotelName + "' already exists.");
				break;
			case 2:
				view.displayMessage("Hotel not found");
			default:
				view.displayMessage("An unexpected error occurred");
				break;
		}
    		   		
    		
        } catch (NumberFormatException ex) {
            view.displayMessage("Please enter a valid hotel name.");
        }
		
		
	}

	
	 /**
     * Handles changing the rates for a specific day in a hotel.
     * 
     * @param hotelName The name of the hotel.
     * @param day The day for which rates are to be changed.
     * @param rate The new rate to apply.
     */
	public void handleChangeRates(String hotelName, int day, int rate){
		// TODO Auto-generated method stub
		
		int val = model.changeRates(hotelName, day, rate);

		try{
			switch (val){
				case 0: 					
					view.displayMessage("Rates on day " + day + " have been changed to " + rate + "%");
				break;
				case 1:
					view.displayMessage("Hotel not found");			
					break;
				default:
					view.displayMessage("An unexpected error occurred");
					break;
			}
		} catch (NumberFormatException ex) {
				view.displayMessage("Please enter a valid hotel name.");
		}
	}

}
