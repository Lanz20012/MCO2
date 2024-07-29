
/**
 * The NormalDates class extends DatePriceModifier and calculates the price for dates
 * that are not modified by special rates. It uses the provided ModifiedDates instance
 * to determine which dates have modified rates.
 */
public class NormalDates extends DatePriceModifier {
	
    private ModifiedDates modified;

    /**
     * Constructs a NormalDates instance with a reference to ModifiedDates.
     * 
     * @param modified An instance of ModifiedDates that contains modified rates for specific days.
     */
    public NormalDates(ModifiedDates modified) {
        this.modified = modified;
    }

    /**
     * Computes the total price for a stay based on normal rates, excluding modified dates.
     * 
     * @param checkInDate The check-in date (1 to 31).
     * @param checkOutDate The check-out date (1 to 31).
     * @param basePrice The base price of the room.
     */
    @Override
    public void computePrice(int checkInDate, int checkOutDate, double basePrice) {
        double price = 0;

        for (int i = checkInDate; i < checkOutDate; i++) {
            if (i <= 31 && modified.getModifiedDate(i) == 0) {
                price += basePrice;
            }
        }

        super.setPrice(price);
       //  System.out.println("Normal Dates Price: " + price);
    }
}
