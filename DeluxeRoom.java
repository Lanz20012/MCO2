
/**
 * 
 * This class extends the Room class and applies a price modifier specific to Deluxe Rooms.
 */
public class DeluxeRoom extends Room {
	
    private static final int ROOM_TYPE = 2; // Room type for Deluxe Room

    
    /**
     * Constructs a DeluxeRoom with the specified room name, type, and base price.
     * The price of the Deluxe Room is increased by 20% from the provided base price.
     * 
     * @param roomName The name of the room.
     * @param type The type of the room (typically should match the ROOM_TYPE constant).
     * @param price The base price of the room. The final price will be 120% of this base price.
     */
    public DeluxeRoom(String roomName, int type, double price) {
        super(roomName, type, price * 1.20);
    }

    /**
     * Returns the room type for this Deluxe Room.
     * 
     * @return The room type identifier for Deluxe Room, which is {@value #ROOM_TYPE}.
     */
    @Override
    public int getRoomType() {
        return ROOM_TYPE;
    }
    
    
   
}
