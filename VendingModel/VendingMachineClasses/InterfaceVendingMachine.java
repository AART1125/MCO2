package VendingModel.VendingMachineClasses;
import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;

/**
 * InterfaceVendingMachine
 */
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

    // display methods
    public String display();
    public void showTransactions();
    public void showNewTransactions();
    public void checkDenom();

    // Operating methods
    public void collectMoney(Money[] userMoneys);
    public boolean buyItem(Money[] userMoneys);
    public void dispenseChange(Money[] userMoneys);
    public double total(Money[] moneys);
    public void inputDenomenations();
    public void inputItems();
    public void changePrice();
    public void decreaseItem(ItemsSlots[][] itemArr);
    public void collectMoneyInMachine();

}
