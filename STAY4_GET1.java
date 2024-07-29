/**
 * The STAY4_GET1 class represents a discount that provides one free night for every four nights stayed.
 * It extends the Discount class and calculates the total discount based on the provided base price 
 * and the price of a single night.
 */
public class STAY4_GET1 extends Discount {

  private double priceOnDay;

  /**
   * Constructs a STAY4_GET1 discount with the specified base price and price for one night.
   * 
   * @param basePrice The total price for the stay before discount is applied.
   * @param priceOnDay The price of one night.
   */
    public STAY4_GET1(double basePrice, double priceOnDay) {
      super(basePrice);
      this.priceOnDay = priceOnDay;
    }

    /**
     * Applies the STAY4_GET1 discount to the base price. The discount is calculated as the base price 
     * minus the price for one night (which represents the free night).
     * 
     * @return The total price after applying the STAY4_GET1 discount.
     */
    public double applyDiscount(){

      double discountTotal = (basePrice - priceOnDay);
      return discountTotal;

    }
}