
/**
 * The StandardRoom class represents a standard type room in a hotel. 
 * It extends the Room class and specifies the room type as Standard.
 */
public class StandardRoom extends Room {
    private static final int ROOM_TYPE = 1; // Room type for Standard Room

    /**
     * Constructs a StandardRoom with the specified name, type, and price.
     * 
     * @param roomName The name of the room.
     * @param type The type of the room (should be 1 for StandardRoom).
     * @param price The price of the room per night.
     */
    public StandardRoom(String roomName, int type, double price) {
        super(roomName, type, price);
    }

    /**
     * Returns the type of the room.
     * 
     * @return The room type identifier for Standard Room, which is 1.
     */
    @Override
    public int getRoomType() {
        return ROOM_TYPE;
    }
    

}
