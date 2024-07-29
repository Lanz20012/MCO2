/**
 * The I_WORK_HERE class represents a specific type of discount that applies
 * a 10% discount on the base price of the booking.
 */
public class I_WORK_HERE extends Discount {
	
    private static final double DISCOUNT_RATE = 0.9; // 10% discount

    
    /**
     * Constructs an I_WORK_HERE discount object with the specified base price.
     * 
     * @param basePrice The base price of the booking before applying the discount.
     */
    public I_WORK_HERE(double basePrice) {

      super(basePrice);
    }

    /**
     * Applies the I_WORK_HERE discount to the base price.
     * 
     * @return The total price after applying the discount.
     */
    public double applyDiscount(){

      double discountTotal =  basePrice * DISCOUNT_RATE;
      return discountTotal;

    }
}