
/**
 * 
 * Abstract class that provides a framework for calculating the price for normal dates
 * and dates with price changes.
 */
public abstract class DatePriceModifier{

  protected double price; 

  
  
  /**
   * Computes the price for the given range of dates (from check-in to check-out) based on the base price.
   * The specific pricing logic will be implemented by subclasses, which will determine how the base price
   * is modified based on the dates provided.
   * 
   * @param checkInDate The start date of the reservation (inclusive).
   * @param checkOutDate The end date of the reservation (exclusive).
   * @param basePrice The base price per day before any modifications are applied.
   */
  public abstract void computePrice(int checkInDate, int checkOutDate, double basePrice);

  
  /**
   * Retrieves the computed price for the specified date range.
   * 
   * @return The computed price for the date range as a double value.
   */
  public double getPrice(){
    return this.price;
  }

  /**
   * Sets the computed price for the specified date range.
   * 
   * @param price The computed price to be set.
   */
  public void setPrice(double price){
    this.price = price;
  }

}