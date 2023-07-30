package VendingModel.VendingMachineClasses;

import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;

interface InterfaceVendingMachine {
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

    // Display methods
    public String display();
    public String displayProcess();
    public String showTransactions();
    public String showNewTransactions();
    public String checkDenom();

    // Operating methods
    public boolean collectMoney(double price);
    public String dispenseChange();
    public double total(Money[] moneys);

    public void inputDenomenations(double price);
    public boolean inputItems(String name, String type, double price, int quantity, int calories);
    public boolean changePrice(double newPrice, String label);
    public boolean increaseItem(String label);
    public boolean decreaseItem(String label);
    public boolean collectMoneyInMachine();

}
