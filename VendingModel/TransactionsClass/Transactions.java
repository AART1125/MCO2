package VendingModel.TransactionsClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import VendingModel.ItemsClass.Items;
import VendingModel.ItemsSlotsClass.ItemsSlots;

/**
 * This is the <code>Transactions</code> class. This represent the transactiions made in the vending machine.
 * It contains the total, the payment, the change, the transaction number, the boolean to check if it was seen
 * by maintenance, the item bought, and the date of the transaction.
 */
public class Transactions {
    private double total, payment, change;
    private int number;
    private boolean check, isProduct;
    private Items item;
    private LocalDateTime date;

    /**
     * Constructor class for a new <code>Transactions</code> object
     * @param total Total of the transaction
     * @param payment Payment of user in transaction
     * @param change Change produced in transaction
     * @param item Item bought in transaction
     * @param number Transaction number
     * @param isProduct Transaction was by product
     */
    public Transactions(double total, double payment, double change, Items item, int number, boolean isProduct){
        this.total = total;
        this.item = item;
        this.number = number;
        this.date = LocalDateTime.now();
        this.payment = payment;
        this.change = change;
        this.check = false;
        this.isProduct = isProduct;
    }

    /**
     * Constructor class in getting the <code>Transactions</code> object from files
     * @param total Total of the transaction
     * @param payment Payment of user in transaction
     * @param change Change produced in transaction
     * @param item Item bought in transaction
     * @param number Transaction number
     * @param check Transaction was checked
     * @param date Date of the transaction
     */
    public Transactions(double total, double payment, double change, Items item, int number, LocalDateTime date, boolean check, boolean isProduct){
        this.total = total;
        this.payment = payment;
        this.change = change;
        this.item = item;
        this.number = number;
        this.date = date;
        this.check = check;
        this.isProduct = isProduct;
    }


    /**
     * This method converts the date of the transaction into a string
     * @return String form of date
     */
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        return format.format(this.date);
    }
    
    /**
     * Gets the change of the <code>Transactions</code> object
     * @return Change of Transaction
     */
    public double getChange() {
        return this.change;
    }

    /**
     * Gets the payment of the <code>Transactions</code> object
     * @return Paymment of Transaction
     */
    public double getPayment() {
        return this.payment;
    }

    /**
     * Gets the total of the <code>Transactions</code> object
     * @return Total of Transaction
     */
    public double getTotal(){
        return this.total;
    }

    /**
     * Gets the item of the <code>Transactions</code> object
     * @return Item
     */
    public Items getItem(){
        return this.item;
    }

    /**
     * Gets the transaction number of the <code>Transactions</code> object
     * @return Number
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Gets the date of the <code>Transactions</code> object
     * @return Date of Transaction
     */
    public LocalDateTime getDate(){
        return this.date;
    }

    /**
     * Gets the check value of the <code>Transactions</code> object
     * @return Check
     */
    public boolean getCheck(){
        return this.check;
    }

    public boolean getIsProduct() {
        return this.isProduct;
    }

    /**
     * Sets the check value of the <code>Transactions</code> object
     * @param check Transaction is checked
     */
    public void setCheck(boolean check){
        this.check = check;
    }
}
