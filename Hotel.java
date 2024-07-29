/**
 * Represents a hotel with high-level and low-level information.
 * This class contains instances of HighLevelInfo and LowLevelInfo to manage the hotel's overall details and operations.
 */
public class Hotel {
    private HighLevelInfo highinfo;
    private LowLevelInfo lowinfo;

    
    /**
     * Constructs a Hotel object with the specified high-level and low-level information.
     * 
     * @param highinfo The high level info instance containing the high-level details of the hotel.
     * @param lowinfo The low level info instance containing the low-level details of the hotel.
     */
    public Hotel(HighLevelInfo highinfo, LowLevelInfo lowinfo) {
        this.highinfo = highinfo;
        this.lowinfo = lowinfo;
    }

    
    /**
     * Returns the high-level information of the hotel.
     * 
     * @return The high level info instance representing the high-level details of the hotel.
     */
    public HighLevelInfo getHighLevelInfo() {
        return this.highinfo;
    }

    /**
     * Returns the low-level information of the hotel.
     * 
     * @return The low level info instance representing the low-level details of the hotel.
     */
    public LowLevelInfo getLowLevelInfo() {
        return this.lowinfo;
    }
    
    
    
}
