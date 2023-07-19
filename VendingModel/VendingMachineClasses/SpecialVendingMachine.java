package VendingModel.VendingMachineClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import VendingModel.ItemsClass.Items; 
import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;

public class SpecialVendingMachine extends VendingMachine implements InterfaceVendingMachine{
    private ArrayList<Items> itemList = new ArrayList<Items>();

    public SpecialVendingMachine(){
        super();
    }

    /**
     * A method that scans a file for the <code>Items</code> made in the program
     */
    public void fileItemScan(){
        int quantity, calories, row = 0, col = 0;
        String name, itemType;
        double price;
        try{
            File contentFile = new File("./Files/Items.txt");
            Scanner reader = new Scanner(contentFile);
            this.occupiedRow = Integer.parseInt(reader.nextLine());
            this.occupiedSlots = Integer.parseInt(reader.nextLine());
            reader.nextLine();
            while(reader.hasNextLine()){// reads file
                row = Integer.parseInt(reader.nextLine());
                col = Integer.parseInt(reader.nextLine());

                name = reader.nextLine();
                itemType = reader.nextLine();
                calories = Integer.parseInt(reader.nextLine());
                quantity = Integer.parseInt(reader.nextLine());
                price = Double.parseDouble(reader.nextLine());

                this.vendoItem[row][col].getProductItem()[0] = new Items(name, calories, itemType);
                this.vendoItem[row][col].setQuantity(quantity);
                this.vendoItem[row][col].setPrice(price);
                this.vendoItem[row][col].setProductItems(this.vendoItem[row][col].getProductItem()[0]);
                
                reader.nextLine();
            }
            reader.close();
        } catch(FileNotFoundException e){
            
        }
    }

    /**
     * A method that creates/overwrite a file for the <code>Items</code> in the program
     */
    public void fileItemWrite(){

    }

    /**
     * A method that scans a file for the <code>Money</code> made in the program
     */
    public void fileMoneyScan(){

    }

    /**
     * A method that creates/overwrite a file for the <code>Transactions</code> made in the program
     */
    public void fileMoneyWrite(){

    }

    /**
     * A method that scans a file for the <code>Transactions</code> made in the program
     */
    public void fileTransactionScan(){

    }

    /**
     * A method that creates/overwrite a file for the <code>Transactions</code> made in the program
     */
    public void fileTransactionWrite(){

    }

    /**
     * Initializes all <code>ItemSlots</code> instances in the array
     * @param vendoItems ItemsSlots array
     */
    public void initialization(ItemsSlots[][] vendoItems){

    }

    /**
     * Initializes all <code>Money</code> instances in the array
     * @param moneys Money array
     */
    public void initialization(Money[] moneys){

    }

    /**
     * Displays the available items in the <code>ItemsSlots</code> array of the machine
     * @return Display of items
     */
    public String display(){
        return null;
    }
    public void showTransactions(){

    }
    public void showNewTransactions(){

    }
    public void checkDenom(){

    }

    /**
     * This method collects the money of the user and add it in the machines userMoney array
     * @param userMoney User's money in the machine
     */
    public void collectMoney(Money[] userMoneys){

    }

    /**
     * This method is where the buying of an item will be done
     * @param userMoneys User's Money
     * @return true or false
     */
    public boolean buyItem(Money[] userMoneys){
        return false;
    }

    /**
     * This method dispenses the change of the user
     * @param userMoney User's Money
     */
    public void dispenseChange(Money[] userMoneys){

    }

    /**
     * This method generates the total of a <code>Money</code> array
     * @param moneys Money array
     * @return Sum
     */
    public double total(Money[] moneys){
        return 0.0;
    }
    
}
