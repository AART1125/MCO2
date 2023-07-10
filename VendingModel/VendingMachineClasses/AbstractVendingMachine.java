package VendingModel.VendingMachineClasses;
import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;
import VendingModel.TransactionsClass.Transactions;

import java.util.ArrayList;
import java.util.Scanner;

public class AbstractVendingMachine{
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

    protected AbstractVendingMachine(){
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

    public int getOccupiedSlots(){
        return this.occupiedSlots;
    }

    public boolean getSalesWasDone() {
        return this.salesWasDone;
    }
    public ItemsSlots[][] getVendoItem() {
        return this.vendoItem;
    }

    public Money[] getStoredMoney(){
        return this.storedMoney;
    }

    public Money[] getUserMoney(){
        return this.userMoney;
    }

    public void setSalesWasDone(boolean salesWasDone) {
        this.salesWasDone = salesWasDone;
    }
}