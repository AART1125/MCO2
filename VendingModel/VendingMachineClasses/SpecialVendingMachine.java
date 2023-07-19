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
    
    public Money[] getUserMoney() {
        return this.userMoney;
    }
}
