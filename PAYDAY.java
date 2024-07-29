
/**
 * The PAYDAY class extends Discount and applies a discount for reservations
 * that include specific "payday" dates. It provides a 7% discount on the base price.
 */
public class PAYDAY extends Discount {

  private static final double DISCOUNT_RATE = 0.93; // 7% discount


  /**
   * Constructs a PAYDAY discount with the base price of the reservation.
   * 
   * @param basePrice The base price of the reservation before applying the discount.
   */
    public PAYDAY(double basePrice) {

      super(basePrice);
    }

    /**
     * Applies the PAYDAY discount to the base price.
     * 
     * @return The total price after applying the 7% discount.
     */
    public double applyDiscount(){

      double discountTotal = basePrice * DISCOUNT_RATE;
      return discountTotal;

    }
}