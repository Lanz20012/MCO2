
/**
 * The ModifiedDates class extends DatePriceModifier and manages date-specific rate modifications.
 * It allows setting and retrieving modified rates for specific days and computes prices based on these rates.
 */
public class ModifiedDates extends DatePriceModifier {
	
    private int[] modifiedDates = new int[31];
    private double[] rates = new double[31]; // Store rates as multipliers (e.g., 1.10 for 110%)

    /**
     * Adds a modified rate for a specific day of the month.
     * 
     * @param day The day of the month to apply the rate modification (1 to 31).
     * @param rate The rate to apply, expressed as a percentage (e.g., 110 for 10% increase).
     */
    public void addModifiedDates(int day, double rate) {
        if (day >= 1 && day <= 31) {
            modifiedDates[day - 1] = day;
            rates[day - 1] = rate / 100; // Convert percentage to multiplier
        }
    }

    /**
     * Retrieves the modified date for a specific index.
     * 
     * @param index The index of the modified date (1 to 31).
     * @return The modified date if it exists, otherwise 0.
     */
    public int getModifiedDate(int index) {
        if (index >= 1 && index <= 31) {
            return modifiedDates[index - 1];
        } else {
            return 0;
        }
    }

    /**
     * Retrieves the modified rate for a specific index.
     * 
     * @param index The index of the rate (1 to 31).
     * @return The rate multiplier for the specified day, or 1.0 if no modification.
     */
    public double getModifiedRate(int index) {
        if (index >= 1 && index <= 31) {
            return rates[index - 1];
        } else {
            return 1.0; // Default rate is 1.0 (no change)
        }
    }

    /**
     * Computes the total price for a stay based on the modified rates.
     * 
     * @param checkInDate The check-in date (1 to 31).
     * @param checkOutDate The check-out date (1 to 31).
     * @param basePrice The base price of the room.
     */
    @Override
    public void computePrice(int checkInDate, int checkOutDate, double basePrice) {
        double price = 0;

        for (int date = checkInDate; date < checkOutDate; date++) {
            double rate = getModifiedRate(date); // Get the rate for the current date
            price += basePrice * rate; // Apply the rate
        }

        super.setPrice(price);
        // System.out.println("Modified Dates: " + price);
    }
}
