

/**
 * The ProfitManager class is responsible for managing and computing total earnings
 * from reservations within a given LowLevelInfo instance.
 */
public class ProfitManager {

    private LowLevelInfo lowinfo;
    private double totalEarnings;

    /**
     * Constructs a ProfitManager with the specified LowLevelInfo.
     * 
     * @param lowinfo The LowLevelInfo instance that contains reservations data.
     */
    public ProfitManager(LowLevelInfo lowinfo) {
        this.lowinfo = lowinfo;
    }

    /**
     * Computes the total earnings by iterating through all reservations in the
     * associated LowLevelInfo instance.
     */
    public void computeTotalEarnings() {
        this.totalEarnings = 0.0; // Reset total earnings

        for (Reservation reservation : lowinfo.getReservations()) {
            double reserveTotal = reservation.getReservationTotal();
            this.totalEarnings += reserveTotal;
        }
    }

    /**
     * Returns the total earnings, computing it if necessary.
     * @return The total earnings calculated from the reservations.
     */
    public double getTotalEarnings() {
        computeTotalEarnings(); // Refresh total earnings
        return this.totalEarnings;
    }

    /*
    // Set total earnings (not typically used)
    public void setTotalEarnings(double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }
    */
}

