package VendingModel.VendingMachineClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

import VendingModel.ItemsClass.Items; 
import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;
import VendingModel.TransactionsClass.Transactions;


public class SpecialVendingMachine extends VendingMachine implements InterfaceVendingMachineSpecial{
    private ArrayList<Items> itemList = new ArrayList<Items>();

    public SpecialVendingMachine(){
        super();
    }

    /**
     * A method that scans a file for the <code>Items</code> made in the program
     */
    public void fileItemScan() {
        int row = 0, col = 0;
        try {
            File contentFile = new File("./Files/Items.txt");
            Scanner reader = new Scanner(contentFile);
            this.occupiedRow = Integer.parseInt(reader.nextLine());
            this.occupiedSlots = Integer.parseInt(reader.nextLine());
            reader.nextLine();
            while (reader.hasNextLine()) {// reads file
                row = Integer.parseInt(reader.nextLine());
                col = Integer.parseInt(reader.nextLine());

                // Omit name, itemType, calories, quantity, and price variables

                this.vendoItem[row][col].getProductItem()[0] = new Items();
                this.vendoItem[row][col].setQuantity(0);
                this.vendoItem[row][col].setPrice(0.0);
                this.vendoItem[row][col].setProductItems(this.vendoItem[row][col].getProductItem()[0]);

                reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // Handle the exception
        }
    }


    /**
     * A method that creates/overwrite a file for the <code>Items</code> in the program
     */
    public void fileItemWrite() {
        int i, j;
        try {
            File contentFile = new File("./Files/Items.txt");
            PrintWriter mainWriter = new PrintWriter(contentFile);
            mainWriter.println(this.occupiedRow);
            mainWriter.println(this.occupiedSlots);
            mainWriter.print("\n");
            for (i = 0; i < MAXROW; i++) {
                for (j = 0; j < MAXCOL; j++) {// write to file
                    if (this.vendoItem[i][j].getProductItem()[0] != null && this.vendoItem[i][j].getProductItem()[0].getName() != null) {
                        mainWriter.println(i);
                        mainWriter.println(j);

                        // Omitting the name, itemType, calories, quantity, and price output

                        mainWriter.print("\n");
                    }
                }
            }
            mainWriter.close();

        } catch (IOException e) {
            //cathces error
        }
    }


    /**
     * A method that scans a file for the <code>Money</code> made in the program
     */
    public void fileMoneyScan() {
        double value;
        int amount, index;

        try {
            File contentFile = new File("./Files/Money.txt");
            Scanner reader = new Scanner(contentFile);
            while (reader.hasNextLine()) {// reads file
                index = Integer.parseInt(reader.nextLine());
                value = Double.parseDouble(reader.nextLine());
                amount = Integer.parseInt(reader.nextLine());

                this.storedMoney[index] = new Money();

                reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            //cathces error        
        }
    }


    /**
     * A method that creates/overwrite a file for the <code>Transactions</code> made in the program
     */
    public void fileMoneyWrite() {
        int i;
        try {
            File contentFile = new File("./Files/Money.txt");
            PrintWriter mainWriter = new PrintWriter(contentFile);
            for (i = 0; i < DENOMINATIONS; i++) {
                if (this.storedMoney[i] != null) {// write to file
                    mainWriter.println(i);
                    mainWriter.println(this.storedMoney[i].getValue());
                    mainWriter.println(this.storedMoney[i].getAmount());
                    mainWriter.print("\n");
                }
            }
            mainWriter.close();
        } catch (IOException e) {
            //cathces error
        }
    }

    /**
     * A method that scans a file for the <code>Transactions</code> made in the program
     */
    public void fileTransactionScan() {
        int number;
        double total, payment, change;
        boolean check;
        LocalDate date;
        Items item;
        Transactions temp;
        try {
            File contentFile = new File("./Files/Transactions.txt");
            Scanner reader = new Scanner(contentFile);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            while (reader.hasNextLine()) {// reads file
                number = Integer.parseInt(reader.nextLine());
                item = new Items();
                total = Double.parseDouble(reader.nextLine());
                payment = Double.parseDouble(reader.nextLine());
                change = Double.parseDouble(reader.nextLine());
                date = LocalDate.parse(reader.nextLine(), formatter);
                check = Boolean.parseBoolean(reader.nextLine());
                reader.nextLine();

                if (!check)
                    this.salesWasDone = true;

                temp = new Transactions(total, payment, change, item, number, date, check);
                this.transactionList.add(temp);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            //cathces error
        }
    }

    /**
     * A method that creates/overwrite a file for the <code>Transactions</code> made in the program
     */
    public void fileTransactionWrite() {
        try {
            File contentFile = new File("./Files/Transactions.txt");
            PrintWriter mainWriter = new PrintWriter(contentFile);
            mainWriter.println(this.transactionAmount);
            for (Transactions transactions : this.transactionList) {// write to file
                mainWriter.println(transactions.getNumber());

                // Omit output of transactions.getItem().getName(), transactions.getItem().getCalories(),
                // transactions.getItem().getItemType(), transactions.getTotal(), transactions.getPayment(),
                // transactions.getChange(), transactions.toString(), and transactions.getCheck()

                mainWriter.print("\n");
            }
            mainWriter.close();

        } catch (IOException e) {
            //cathces error
        }
    }

    /**
     * Initializes all <code>ItemSlots</code> instances in the array
     * @param vendoItems ItemsSlots array
     */
    public void initialization(ItemsSlots[][] vendoItems) {
        int i, j;
        int itemCount = 0;

        for (i = 0; i < MAXROW; i++) {
            for (j = 0; j < MAXCOL; j++) {
                vendoItems[i][j] = new ItemsSlots(i, j);
            }
        }

        for (i = 0; i < MAXROW; i++) {
            for (j = 0; j < MAXCOL; j++) {
                for (itemCount = 0; itemCount < MAXITEMS; itemCount++) {
                    vendoItems[i][j].getProductItem()[itemCount] = new Items();
                }
            }
        }
    }

    /**
     * Initializes all <code>Money</code> instances in the array
     * @param moneys Money array
     */
     public void initialization(Money[] moneys){
        int i;
        for ( i = 0; i < moneys.length; i++) {
            moneys[i] = new Money();
        }
    }


    /**
     * This method collects the money of the user and add it in the machines userMoney array
     * @param userMoney User's money in the machine
     */
    public boolean collectMoney() {
        boolean adding = true, success = false;
        int amount = 0;
        double value = 0;
        Money tempMoney;

                

        return success;
    }



    /**
     * This method is where the buying of an item will be done
     * @param userMoneys User's Money
     * @return true or false
     */
    public boolean buyItem(String input) {
        boolean success = false;
        int row, col, origQuantity;
        double change, price, payment = total(userMoney);
        char choice;
        String slotLabel = null;
        ItemsSlots slot;

        // omit slotLabel value
        slotLabel = slotLabel.toUpperCase();

        return success;
    }


    /**
     * This method dispenses the change of the user
     * @param userMoney User's Money
     */
    public void dispenseChange() {
        for (int i = 0; i < this.userMoney.length; i++) {
            if (this.userMoney[i].getValue() > 0) {
                System.out.println("\nDispensing change: P" + this.userMoney[i].getValue() + "| Amount : " + this.userMoney[i].getAmount());
                this.currentMon--;
            }
            
            this.userMoney[i] = new Money();
        }
    }

    /**
     * This method generates the total of a <code>Money</code> array
     * @param moneys Money array
     * @return Sum
     */
    public double total(Money[] moneys){
        int i;
        double sum = 0.0;
        for(i = 0; i < moneys.length; i++){
            sum += moneys[i].getTotal();
        }
        return sum;
    }

    /**
     * This method inputs the denomination of money
     */
    public void inputDenomenations(){
        int i, tempAmount = 0; 
        double value = 0;
        Money tempMoney;
        
    }

    /**
     * This method inputs <code>Items</code> objects into the <code>ItemsSlots</code> array
     */
    public void inputItems(){
        boolean finish = false, found = false;
        char input;
        int row = 0, col = 0, i = 0, j = 0;

        //looks for an empty slot within the occupied range
        while(i < this.occupiedRow+1 && !found){
            while(j < this.occupiedSlots % MAXCOL && !found){
                if (this.vendoItem[i][j % MAXCOL].getQuantity() == 0 && this.vendoItem[i][j % MAXCOL].getProductItem()[0].getName() == null) {
                    row = i;
                    col = j % MAXCOL;
                    found = true;
                }
                j++;
            }
            i++;
        }

        
    }

    /**
     * This method changes the price of <code>Items</code>
     */
    public void changePrice(){
        int row, col;
        double newPrice;
        String slotLabel;

        
        slotLabel = sc.next().toUpperCase();

        row = slotLabel.charAt(0) - 'A';
        col = Integer.parseInt(slotLabel.substring(1)) - 1;

        if(this.vendoItem[row][col] != null && this.vendoItem[row][col].getProductItem()[0].getName() != null){
            
        }
    } 

    /**
     * This method decreases the <code>Items</code> in a <code>ItemsSlots</code> array
     * @param itemArr Item Slot
     */
    public void decreaseItem(ItemsSlots[][] itemArr) {
        int row, col, qnty, origQuantity;
        String slotLabel;
        ItemsSlots item;

        slotLabel = sc.next().toUpperCase();
       
        row = slotLabel.charAt(0) - 'A';
        col = Integer.parseInt(slotLabel.substring(1)) - 1;
        
        origQuantity = itemArr[row][col].getQuantity();

        if (row >= 0 && row < itemArr.length && col >= 0 && col < itemArr[row].length) {
        
        } 
    }

    /**
     * This method collects the <code>Money</code> in the machine and saves them in a file
     */
    public void collectMoneyInMachine(){
        int i = 0, amount = 0;
        double sum = 0;
        boolean reachedTotal = false;
        String check = null;
        File contentFile = new File("./Files/Collections.txt");

        //checks if money is available
        if(total(storedMoney) > 0){
            try {//scans the current collection in file
            Scanner reader = new Scanner(contentFile);
            while (reader.hasNextLine() && !reachedTotal) {
                check = reader.nextLine();
                if (!check.equals("Total: ")) {
                    amount = Integer.parseInt(reader.nextLine());
                    reader.nextLine();
                } else {
                    sum += Double.parseDouble(reader.nextLine());
                    reachedTotal = true;
                }
            }
            reader.close();
            } catch (IOException e) {
                //
            }

            try {//print collection money and total to file
                PrintWriter mainWriter = new PrintWriter(contentFile);

                for (i = 0; i < storedMoney.length; i++) {
                    if (this.storedMoney[i] != null) {
                        sum += this.storedMoney[i].getValue() * this.storedMoney[i].getAmount();
                        mainWriter.println(this.storedMoney[i].getValue());
                        mainWriter.println(this.storedMoney[i].getAmount() + amount);
                        mainWriter.print("\n");
                    }
                    this.storedMoney[i] = new Money();
                }
                mainWriter.println("Total: ");
                mainWriter.println(sum);
                mainWriter.close();
                
            } catch (IOException e) {
                
            }
        } 
    }
       
}