
/**
 * This class extends the Room class and applies a specific pricing multiplier for Executive Rooms.
 */
public class ExecutiveRoom extends Room {
	
    private static final int ROOM_TYPE = 3; // Room type for Executive Room

    
    /**
     * Constructs an ExecutiveRoom with the specified room name, type, and base price.
     * The base price is increased by 35% for Executive Rooms.
     * 
     * @param roomName The name of the room.
     * @param type The type of the room (should be 3 for Executive Rooms).
     * @param price The base price of the room before the increase.
     */
    public ExecutiveRoom(String roomName, int type, double price) {
        super(roomName, type, price * 1.35);
    }

    
    /**
     * Returns the room type specific to Executive Rooms, which is room type 3.
     * 
     * @return The room type for Executive Rooms (3).
     */
    @Override
    public int getRoomType() {
        return ROOM_TYPE;
    }
    

}
