package VendingModel.VendingMachineClasses;

import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;

/**
 * Interface for the <code>VendingMachine</code> subclasses containing the general methods that it will be used in its processing
 */
public interface InterfaceVendingMachine {
    // File processing methods

    /**
     * This method scans the Items file for <code>Items</code> in the machine.
     */
    public void fileItemScan();

    /**
     * This method writes the Items file for <code>Items</code> in the machine.
     */
    public void fileItemWrite();

    /**
     * This method scans the Money file for the <code>Money</code> in the machine.
     */
    public void fileMoneyScan();

    /**
     * This method writes the Money file for the <code>Money</code> in the machine.
     */
    public void fileMoneyWrite();

    /**
     * This method scans the Transactions file for the <code>Transactions</code> in the machine.
     */
    public void fileTransactionScan();

    /**
     * This method writes the Transactions file for the <code>Transactions</code> in the machine.
     */
    public void fileTransactionWrite();

    // initialization methods

    /**
     * This method initializes the <code>ItemsSlots</code> arrays in the machine.
     * 
     * @param vendoItems ItemsSlots array
     */
    public void initialization(ItemsSlots[][] vendoItems);

    /**
     * This method initializes the <code>Money</code> arrays in the machine.
     * 
     * @param moneys Money Array
     */
    public void initialization(Money[] moneys);

    
    // Display methods

    /**
     * This method displays the receipt of the transaction.
     * 
     * @return receipt of the transaction
     */
    public String display();

    /**
     * This method displays the process of the machine being done.
     * 
     * @return processing text
     */
    public String displayProcess();

    /**
     * This method displays all the transactions done in the machine.
     * 
     * @return all transactions done
     */
    public String showTransactions();

    /**
     * This method displays all the recent transactions made.
     * 
     * @return recent transactions
     */
    public String showNewTransactions();

    /**
     * This function displays the different denomincations available in the machine.
     * 
     * @return denominations in vending machine
     */
    public String checkDenom();

    // Operating methods

    /**
     * This method collects the money of the user.
     * 
     * @param value amount to be collected
     * @return true or false
     */
    public boolean collectMoney(double value);

    /**
     * This method dispenses the change of the user.
     * 
     * @return string of money
     */
    public String dispenseChange();

    /**
     * This method computes the total money in a money array.
     * 
     * @param moneys array of money
     * @return total
     */
    public double total(Money[] moneys);

    /**
     * This method inputs the denominations into the machine.
     * 
     * @param value value of the money
     */
    public void inputDenomenations(double value);

    /**
     * This method inputs the item information into the machine.
     * 
     * @param name name of the item
     * @param type type of the item
     * @param price price of the item
     * @param quantity quantity of the item
     * @param calories calories of the item
     * @return true or false
     */
    public boolean inputItems(String name, String type, double price, int quantity, int calories);

    /**
     * This method changes the price of an <code>ItemsSlots</code>.
     * 
     * @param newPrice new price of the slot
     * @param label the label of the slot
     * @return true or false
     */
    public boolean changePrice(double newPrice, String label);

    /**
     * This method increases the <code>Items</code> in an <code>ItemsSlots</code>.
     * 
     * @param label label of the slot
     * @return true or false
     */
    public boolean increaseItem(String label);

    /**
     * This method decreases the <code>Items</code> in an <code>ItemsSlots</code>.
     * 
     * @param label label of the slot
     * @return true or false
     */
    public boolean decreaseItem(String label);

    /**
     * This method collects the money in the stored money array and puts it in a file.
     * 
     * @return true or false
     */
    public boolean collectMoneyInMachine();

}
