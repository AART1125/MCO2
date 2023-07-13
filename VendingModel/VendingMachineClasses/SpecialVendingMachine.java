package VendingModel.VendingMachineClasses;

import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;

public class SpecialVendingMachine extends VendingMachine implements InterfaceVendingMachine{
    
    public SpecialVendingMachine(){
        super();
    }

    public void fileItemScan(){

    }
    public void fileItemWrite(){

    }
    public void fileMoneyScan(){

    }
    public void fileMoneyWrite(){

    }
    public void fileTransactionScan(){

    }
    public void fileTransactionWrite(){

    }

    // initialization methods
    public void initialization(ItemsSlots[][] vendoItems){

    }
    public void initialization(Money[] moneys){

    }

    // display methods
    public String display(){
        return null;
    }
    public void showTransactions(){

    }
    public void showNewTransactions(){

    }
    public void checkDenom(){

    }

    // Operating methods
    public void collectMoney(Money[] userMoneys){

    }
    public boolean buyItem(Money[] userMoneys){
        return false;
    }
    public void dispenseChange(Money[] userMoneys){

    }
    public double total(Money[] moneys){
        return 0.0;
    }
    public void inputDenomenations(){

    }
    public void inputItems(){

    }
    public void changePrice(){

    }
    public void decreaseItem(ItemsSlots[][] itemArr){

    }
    public void collectMoneyInMachine(){

    }
    public Money[] getUserMoney() {
        return this.userMoney;
    }
}
