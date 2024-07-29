

/**
 * This abstract class serves as a base for different types of discounts that can be applied
 * to a base price. 
 */
public abstract class Discount {

    protected double basePrice;
    protected double discountTotal;

    
    /**
     * Constructs a Discount with the specified base price.
     * 
     * @param basePrice The base price to which the discount will be applied.
     */
    public Discount(double basePrice) {
        this.basePrice = basePrice;
        discountTotal = 0.0;
    }

    /**
     * Returns the base price.
     * 
     * @return The base price of the discount.
     */
    public double getBasePrice() {
        return this.basePrice;
    }

    /**
     * Abstract method to apply discount, to be implemented by subclasses
     * @return The total amount after applying the discount.
     */
    public abstract double applyDiscount();
}