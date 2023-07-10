package VendingModel.MoneyClass;
/**
 * This is <code>Money</code> class. This represents the money inside the vending machine.
 * It has a value, a total, and the amount of denominations
 */
public class Money {
    private double value, total;
    private int amount;/*1 is 1php, 2 is 5php, 3 is 10php, 4 is 20php, 
                                5 is 50php, 6 is 100php, 7 is 200php, 8 is 500php, 9 is 100 php*/
    
    /**
     * Empty constructor of the <code>Money</code> class
     */
    public Money(){
        this.value = 0;
        this.amount = 0;
        this.total = 0;
    }

    /**
     * Constructor of the <code>Money</code> class
     * @param value Value of the Money
     * @param amount Amount of the Money
     */
    public Money(double value, int amount){
        this.value = value;
        this.amount = amount;
        this.total = this.value*this.amount;
    }

    /**
     * Gets the value of the <code>Money</code> object
     * @return Value
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Gets the amount of the <code>Money</code> object
     * @return Amount
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Gets the total of the <code>Money</code> object
     * @return Amount
     */
    public double getTotal() {
        return this.total;
    }

    /**
     * Sets the amount of the <code>Money</code> object
     * @param amount New amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
