package VendingModel.VendingMachineClasses;

import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;
import VendingModel.TransactionsClass.Transactions;

import java.util.ArrayList;

/**
 * This is an abstract class for the <code>VendingMachine</code>. It serves as a template for both the Regular and Special Vending Machine.
 * It contains the consatants for the total aount of Items, Denominations, Row, and Collumn. It also contains counting attributes for the different arrays.
 * It has two arrays of <code>Money</code> for user and the machine itself, an ArrayList of <code>Transactions</code>,
 * and a 2 dimensional array for the <code>ItemsSlots</code> 
 */
abstract class VendingMachine{
    /**Constant for max row */
    protected static final int MAXROW = 6;
    /**Constant for max collumn */
    protected static final int MAXCOL = 5;
    /**Constant for max denominations */
    protected static final int DENOMINATIONS = 9;
    /**Constant for max items*/
    protected static final int MAXITEMS = 20;
    /**Counting variables for row */ /**Counting variables for slots */ 
    /**Counting variables for money of user */ /**Counting variables for transacions */
    protected int occupiedRow, occupiedSlots, storedMoneyAmount, currentMon, transactionAmount;
    /**<code>Money</code> array for the machine's money */
    protected Money[] storedMoney;
    /**<code>Money</code> array for the user's money */
    protected Money[] userMoney;
    /**<code>ArrayList</code> for the list of <code>Transactions</code>*/
    protected ArrayList<Transactions> transactionList;
    /**<code>ItemsSlots</code> array for the items in the vending machine */
    protected ItemsSlots[][] vendoItem;

    /**
     * This is a contructor that initializes the values of a <code>VendingMachine</code>
     */
    public VendingMachine(){
        this.occupiedRow = 0;
        this.occupiedSlots = 0;
        this.storedMoneyAmount = 0;
        this.currentMon = 0;
        this.transactionAmount = 0;
        this.storedMoney = new Money[DENOMINATIONS];
        this.userMoney = new Money[DENOMINATIONS];
        this.transactionList = new ArrayList<Transactions>();
        this.vendoItem = new ItemsSlots[MAXROW][MAXCOL];
    }

    /**
     * Gets the maximunm row of an <code>ItemsSlots</code> array
     * @return Maximum row
     */
    public static int getMaxrow() {
        return MAXROW;
    }

    /**
     * Gets the maximunm collumn of an <code>ItemsSlots</code> array
     * @return Maximum collumn
     */
    public static int getMaxcol() {
        return MAXCOL;
    }

    /**
     * Gets the occupied slots in the object
     * @return Occupied slots
     */
    public int getOccupiedSlots() {
        return this.occupiedSlots;
    }

    /**
     * Gets the <code>ItemsSlots</code> of the machine
     * @return Item Slots
     */
    public ItemsSlots[][] getVendoItem() {
        return this.vendoItem;
    }

    /**
     * Gets the stored money in the machine
     * @return Stored money
     */
    public Money[] getStoredMoney() {
        return this.storedMoney;
    }

    /**
     * Gets the User's money in the machine
     * @return User's Money array
     */
    public Money[] getUserMoney() {
         return this.userMoney;
    }
}