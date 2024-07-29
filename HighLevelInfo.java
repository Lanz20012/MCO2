
/**
 * Represents high-level information about a hotel.
 * This class contains details such as the hotel's name, the number of rooms, and earnings.
 * It also manages the hotel's profit using a ProfitManager.
 */
public class HighLevelInfo {
    private String hotelName;
    private int numberOfRooms;
    private double earnings;
    private ProfitManager profit;

    /**
     * Constructs a HighLevelInfo object with the specified hotel name, number of rooms, and low-level info.
     * 
     * @param hotelName The name of the hotel.
     * @param numberOfRooms The total number of rooms in the hotel.
     * @param lowinfo The low level info used to initialize the ProfitManager.
     */
    public HighLevelInfo(String hotelName, int numberOfRooms, LowLevelInfo lowinfo) {
        this.hotelName = hotelName;
        this.numberOfRooms = numberOfRooms;
        this.profit = new ProfitManager(lowinfo); // Initialize ProfitManager with lowinfo
        this.earnings = profit.getTotalEarnings();
    }

    /**
     * Returns the name of the hotel.
     * 
     * @return The name of the hotel.
     */
    public String getHotelName() {
        return this.hotelName;
    }

    /**
     * Returns the total number of rooms in the hotel.
     * 
     * @return The total number of rooms.
     */
    public int getNumberOfRooms() {
        return this.numberOfRooms;
    }

    
    /**
     * Returns the total earnings of the hotel.
     * @return The total earnings of the hotel.
     */
    public double getEarnings() {
        this.earnings = profit.getTotalEarnings();
        return this.earnings;
    }

    /**
     * Sets the name of the hotel.
     * 
     * @param hotelName The new name of the hotel.
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    
    /**
     * Sets the total number of rooms in the hotel.
     * 
     * @param numberOfRooms The new total number of rooms.
     */
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
}
