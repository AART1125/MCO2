package VendingModel.VendingMachineClasses;

import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;
import VendingModel.TransactionsClass.Transactions;

import java.util.ArrayList;
import java.util.Scanner;

interface InterfaceVendingMachineRegular {
    // methods used for files
    public void fileItemScan();
    public void fileItemWrite();
    public void fileMoneyScan();
    public void fileMoneyWrite();
    public void fileTransactionScan();
    public void fileTransactionWrite();

    // initialization methods
    public void initialization(ItemsSlots[][] vendoItems);
    public void initialization(Money[] moneys);

    // Operating methods
    public boolean collectMoney(Money[] userMoneys);
    public boolean buyItem(Money[] userMoneys);
    public void dispenseChange(Money[] userMoneys);
    public double total(Money[] moneys);

    public void inputDenomenations();
    public void inputItems();
    public void changePrice();
    public void decreaseItem(ItemsSlots[][] itemArr);
    public void collectMoneyInMachine();

}

interface InterfaceVendingMachineSpecial {
    // methods used for files
    public void fileItemScan();
    public void fileItemWrite();
    public void fileMoneyScan();
    public void fileMoneyWrite();
    public void fileTransactionScan();
    public void fileTransactionWrite();

    // initialization methods
    public void initialization(ItemsSlots[][] vendoItems);
    public void initialization(Money[] moneys);

    // Operating methods
    public boolean addItem(String label);
    public boolean createProductFromItems(ArrayList<ItemsSlots> purchasedItems, String productName);
    public boolean collectMoney(double price);
    public boolean buyItem(String input);
    public void dispenseChange();
    public double total(Money[] moneys);

    public void inputDenomenations(double price);
    public void inputItems(String name, String type, double price, int quantity, int calories);
    public void changePrice(double newPrice);
    public void decreaseItem(String label, int decrease);
    public void collectMoneyInMachine();

}

interface VendingDisplays{
    public String display();
    public void showTransactions();
    public void showNewTransactions();
    public void checkDenom();
}

abstract class VendingMachine{
    protected static final int MAXROW = 6;
    protected static final int MAXCOL = 5;
    protected static final int DENOMINATIONS = 9;
    protected static final int MAXITEMS = 20;
    protected static Scanner sc = new Scanner(System.in);

    protected int occupiedRow, occupiedSlots, currentMon, transactionAmount;
    protected boolean salesWasDone;
    protected Money[] storedMoney;
    protected Money[] userMoney;
    protected ArrayList<Transactions> transactionList;
    protected ItemsSlots[][] vendoItem;

    protected VendingMachine(){
        this.occupiedRow = 0;
        this.occupiedSlots = 0;
        this.currentMon = 0;
        this.transactionAmount = 0;
        this.salesWasDone = false;
        this.storedMoney = new Money[DENOMINATIONS];
        this.userMoney = new Money[DENOMINATIONS];
        this.transactionList = new ArrayList<Transactions>();
        this.vendoItem = new ItemsSlots[MAXROW][MAXCOL];
    }

    /**
     * Gets the occupied slots in the object
     * @return Occupied slots
     */
    public int getOccupiedSlots() {
        return this.occupiedSlots;
    }

    /**
     * Gets the value of whether a sale has been done
     * @return true or false
     */
    public boolean getSalesWasDone() {
        return this.salesWasDone;
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

    /**
     * Sets the value of salesWasDone variable
     * @param salesWasDone Value used to set the variable
     */
    public void setSalesWasDone(boolean salesWasDone) {
        this.salesWasDone = salesWasDone;
    }
}