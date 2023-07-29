package VendingModel.VendingMachineClasses;

import VendingModel.ItemsClass.Items;
import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;
import VendingModel.TransactionsClass.Transactions;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This is the <code>RegularVendingMachine</code> class. This represents the whole entire vending machine and
 * most of it's processes are placed here. It contains a the amount of occupied rows, occupied slots,
 * amount of money of the user, amount of transactions, a boolean of whether or not a sale was done, 
 * money stored in the vending machine, user's money, a list of the transactions, and the item slots.
 */
public class RegularVendingMachine extends VendingMachine implements InterfaceVendingMachine{

    /**
     * A <code>RegularVendingMachine</code> constructor that calls on the contructor of the parent class
     */
    public RegularVendingMachine(){
        super();
    }

    /**
     * A method that scans a file for the <code>Items</code> made in the program
     */
    @Override
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
            System.out.println("\nFile can't be read....");
        }
    }

    /**
     * A method that creates/overwrite a file for the <code>Items</code> in the program
     */
    @Override
    public void fileItemWrite(){
        int i, j;
        try {
            File contentFile = new File("./Files/Items.txt");
            PrintWriter mainWriter = new PrintWriter(contentFile);
            mainWriter.println(this.occupiedRow);
            mainWriter.println(this.occupiedSlots);
            mainWriter.print("\n");
            for(i = 0; i < MAXROW; i++){
                for(j = 0; j < MAXCOL; j++){// write to file
                    if(this.vendoItem[i][j].getProductItem()[0] != null && this.vendoItem[i][j].getProductItem()[0].getName() != null){
                        mainWriter.println(i);
                        mainWriter.println(j);

                        mainWriter.println(this.vendoItem[i][j].getProductItem()[0].getName());
                        mainWriter.println(this.vendoItem[i][j].getProductItem()[0].getItemType());
                        mainWriter.println(this.vendoItem[i][j].getProductItem()[0].getCalories());
                        mainWriter.println(this.vendoItem[i][j].getQuantity());
                        mainWriter.println(Double.toString(this.vendoItem[i][j].getPrice()));
                        mainWriter.print("\n");
                    }
                }
            }
            mainWriter.close();

        } catch (IOException e) {
            System.out.println("\nAn error occurred.");
        }
    }

    /**
     * A method that scans a file for the <code>Money</code> made in the program
     */
    @Override
    public void fileMoneyScan(){
        double value;
        int amount, index;

        try {
            File contentFile = new File("./Files/Money.txt");
            Scanner reader = new Scanner(contentFile);
            while (reader.hasNextLine()) {// reads file
                index = Integer.parseInt(reader.nextLine());
                value = Double.parseDouble(reader.nextLine());
                amount = Integer.parseInt(reader.nextLine());

                this.storedMoney[index] = new Money(value, amount);

                reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile can't be read....");
        }
    }

    /**
     * A method that creates/overwrite a file for the <code>Money</code> in the program
     */
    @Override
    public void fileMoneyWrite(){
        int i;
        try {
            File contentFile = new File("./Files/Money.txt");
            PrintWriter mainWriter = new PrintWriter(contentFile);
            for(i = 0; i < DENOMINATIONS; i++){
                if(this.storedMoney[i] != null){// write to file
                    mainWriter.println(i);
                    mainWriter.println(this.storedMoney[i].getValue());
                    mainWriter.println(this.storedMoney[i].getAmount());
                    mainWriter.print("\n");
                }
            }
            mainWriter.close();
        } catch (IOException e) {
            System.out.println("\nAn error occurred.");
        }
    }

    /**
     * A method that scans a file for the <code>Transactions</code> made in the program
     */
    @Override
    public void fileTransactionScan(){
        int amount, number;
        double total, payment, change;
        boolean check;
        LocalDate date;
        Items item;
        Transactions temp;
        try {
            File contentFile = new File("./Files/Transactions.txt");
            Scanner reader = new Scanner(contentFile);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            amount = Integer.parseInt(reader.nextLine());
            this.transactionAmount = amount;
            while (reader.hasNextLine()) {// reads file
                number = Integer.parseInt(reader.nextLine());
                item = new Items(reader.nextLine(), Integer.parseInt(reader.nextLine()), reader.nextLine());
                total = Double.parseDouble(reader.nextLine());
                payment = Double.parseDouble(reader.nextLine());
                change = Double.parseDouble(reader.nextLine());
                date = LocalDate.parse(reader.nextLine(), formatter);
                check = Boolean.parseBoolean(reader.nextLine());
                reader.nextLine();
                
                if(!check)
                    this.salesWasDone = true;

                temp = new Transactions(total, payment, change, item, number, date, check);
                this.transactionList.add(temp);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile can't be read....");
        }
    }

    /**
     * A method that creates/overwrite a file for the <code>Transactions</code> made in the program
     */
    @Override
    public void fileTransactionWrite(){
        try {
            File contentFile = new File("./Files/Transactions.txt");
            PrintWriter mainWriter = new PrintWriter(contentFile);
            mainWriter.println(this.transactionAmount);
            for (Transactions transactions : this.transactionList) {// write to file
                mainWriter.println(transactions.getNumber());
                mainWriter.println(transactions.getItem().getName());
                mainWriter.println(transactions.getItem().getCalories());
                mainWriter.println(transactions.getItem().getItemType());
                mainWriter.println(transactions.getTotal());
                mainWriter.println(transactions.getPayment());
                mainWriter.println(transactions.getChange());
                mainWriter.println(transactions.toString());
                mainWriter.println(transactions.getCheck());
                mainWriter.print("\n");
            }
            mainWriter.close();
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    /**
     * Initializes all <code>ItemSlots</code> instances in the array
     * @param vendoItems ItemsSlots array
     */
    @Override
    public void initialization(ItemsSlots[][] vendoItems){
        int i, j;
        int itemCount = 0;
        
        for (i = 0; i < MAXROW; i++){
            for(j = 0; j < MAXCOL; j++){
                vendoItems[i][j] = new ItemsSlots(i,j);
            }
        }

        for (i = 0; i < MAXROW; i++) {
            for (j = 0; j < MAXCOL; j++) {
                for(itemCount = 0; itemCount < MAXITEMS; itemCount++){
                    vendoItems[i][j].getProductItem()[itemCount] = new Items();
                }
            }
        }
    }

    /**
     * Initializes all <code>Money</code> instances in the array
     * @param moneys Money array
     */
    @Override
    public void initialization(Money[] moneys){
        int i;
        for ( i = 0; i < moneys.length; i++) {
            moneys[i] = new Money();
        }
    }

    /**
     * Displays the available items in the <code>ItemsSlots</code> array of the machine
     * @return Display of items
     */
    @Override
    public String display(){
        StringBuilder builder = new StringBuilder();
        double change = this.transactionList.get(this.transactionAmount).getPayment() - this.transactionList.get(this.transactionAmount).getTotal();

        if(this.transactionList.get(this.transactionAmount) != null){

            builder.append("\n\nTransaction #" + this.transactionList.get(this.transactionAmount).getNumber());
            builder.append("----------------------------------------------");
            builder.append("|         Name       |  Calories  |   Total  |");
            builder.append("----------------------------------------------");

            builder.append(String.format("|%-20s|", this.transactionList.get(this.transactionAmount).getItem().getName()));
            builder.append(String.format("%10d g|", this.transactionList.get(this.transactionAmount).getItem().getCalories()));
            builder.append(String.format("P%9.2f|\n", this.transactionList.get(this.transactionAmount).getTotal()));
            builder.append("----------------------------------------------\n");
            builder.append(String.format("Total  : P%9.2f", this.transactionList.get(this.transactionAmount).getTotal()));
            builder.append(String.format("Payment: P%9.2f", this.transactionList.get(this.transactionAmount).getPayment()));
            builder.append(String.format("Change : P%9.2f", change));
            builder.append("----------------------------------------------\n");
            
            this.transactionAmount++;
        }
        return builder.toString();
    }

    /**
     * This method collects the money of the user and add it in the machines userMoney array
     * @param price Gets the value of the money being inputted
     * @return true or false if successful
     */
    @Override
    public boolean collectMoney(double price) {
        boolean exists = false, success = false;
        int i = 0;
        
        while (i < this.currentMon && !exists) {
            if (this.userMoney[i].getValue() == price) {
                exists = true;
                this.userMoney[i] = new Money(price, this.storedMoney[i].getAmount()+1);
            }
            i++;
        }

        if (!exists) {
            this.userMoney[this.currentMon] = new Money(price, 1);
            this.currentMon++;
        }
        
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
        ItemsSlots slot;
        

        
        row = input.charAt(0) - 'A';
        col = Integer.parseInt(input.substring(1)) - 1;

        if ((row >= 0 && row < MAXROW) && (col >= 0 && col < MAXCOL)) {// check if valid
            slot = vendoItem[row][col];

            if (slot != null && slot.getProductItem()[0].getName() != null) {
                //final check if user want to continue with the transactions
                if (isItemSellable(slot.getProductItem()[0]) && total(userMoney) >= slot.getPrice()) {// checks if item is sellable
                    price = slot.getPrice();
                    change = produceChange(userMoney, price);// produce change in different denominations
                        if(change >= 0 && total(this.storedMoney) > 0){
                            success = true;
                            //create transaction
                            this.transactionList.add(new Transactions(slot.getPrice(), payment, change, slot.getProductItem()[0], this.transactionAmount + 1));

                            //decrease item from slot
                            origQuantity = slot.getQuantity();
                            if (origQuantity > 0) {
                                slot.decreaseQuantity(1);
                                slot.decreaseItemsFromSlot(slot.getProductItem());
                            }
                    
                            this.salesWasDone = true;
                        }
                    }
                }
            }
        return success;
    }

    /**
     * This method checks if the item is sellaable. It is used in the buyItems method
     * @param item item to be checked
     * @returnn true or false
     */
    private boolean isItemSellable(Items item){
        boolean sellable = true;

        if(item.getItemType() != null){
            if(item.getItemType().equalsIgnoreCase("Add-ons") ||
               item.getItemType().equalsIgnoreCase("Addons")){
                   sellable = false;
               }
        }

        return sellable;
    }

    /**
     * This method gets all the <code>Money</code> in the user. It is used in the buyItems method
     * @param userMoney User's Money 
     */
    private void addPaymentToMachine(Money[] userMoney){
        int newAmount;
        int i, j;
        
        for(i = 0; i < DENOMINATIONS; i++){
            for(j = 0; j < this.currentMon; j++){
                if(this.storedMoney[i].getValue() == userMoney[j].getValue()){
                    newAmount = this.storedMoney[i].getAmount() + userMoney[j].getAmount();
                    this.storedMoney[i] = new Money(this.storedMoney[i].getValue(), newAmount);
                }
            }
        }

        initialization(userMoney);
    }

    /**
     * This method produces the change for the user. It is used in the buyItems method
     * @param userMoneys User's money
     * @param total Total price
     * @return change
     */
    private double produceChange(Money[] userMoneys, double total){
        double change = total(userMoneys) - total,
               temp = change;
        boolean isEnough = true;
        int numBills = 0,  denomCount = 0;
        int i = DENOMINATIONS-1, j = 0;
        Money[] tempMoney = new Money[DENOMINATIONS];

        if(change < 0){
            
        } else {
            //puts the denominations of user in temp in case of lacking denominations in machine
            for (Money money : userMoneys) {
                tempMoney[j] = new Money(money.getValue(), money.getAmount());
                j++;
            }
            addPaymentToMachine(userMoneys);
            while(i >= 0 && isEnough){
                numBills = (int)temp/(int)this.storedMoney[i].getValue();
                if(numBills > 0){
                    if(this.storedMoney[i].getAmount() >= numBills){
                        temp -= this.storedMoney[i].getValue()*numBills;
                        removeDenomination(this.storedMoney[i].getValue(), numBills);

                        userMoneys[denomCount] = new Money(this.storedMoney[i].getValue(), numBills);
                        denomCount++;
                    } else {
                        j = 0;
                        for (Money money : tempMoney) {//returns the initial denominations of user
                            userMoneys[j] = new Money(money.getValue(), money.getAmount());
                            j++;
                        }
                        change = -1;
                    }
                }
                i--;
            }

        }
        return change;
    }

    /**
     * This method removes the denominations of <code>Money</code> objects in an array. It is used in the buyItems method
     * @param value Value of the money
     * @param amount Ammount of the money
     */
    private void removeDenomination(double value, int amount) {
        int denominationIndex = -1,
            decrement = 0;
        // Find index
        switch ((int) value) {
            case 1: denominationIndex = 0; break;
            case 5: denominationIndex = 1; break;
            case 10: denominationIndex = 2; break;
            case 20: denominationIndex = 3; break;
            case 50: denominationIndex = 4; break;
            case 100: denominationIndex = 5; break;
            case 200: denominationIndex = 6; break;
            case 500: denominationIndex = 7; break;
            case 1000: denominationIndex = 8; break;
        }
        // If the denomination is found, remove 1
        if (denominationIndex != -1) {
            if (this.storedMoney[denominationIndex].getAmount() > 0) {
                decrement = this.storedMoney[denominationIndex].getAmount() - amount;
                this.storedMoney[denominationIndex].setAmount(decrement);
            } else {
                System.out.println("Denomination of P" + value + " not available.");
            }
        } else {
            System.out.println("Invalid denomination.");
        }
    }

    /**
     * This method dispenses the change of the user
     * @return String of change
     */
    @Override
    public String dispenseChange(){
        StringBuilder builder = new StringBuilder();
        int i;

        if(total(this.userMoney) > 0){
            for(i = 0; i < this.currentMon; i++){
                if(this.userMoney[i].getValue() > 0){
                    builder.append("\nDispensing change: P" + this.userMoney[i].getValue() + "| Amount : " + this.userMoney[i].getAmount() + "\n");
                    this.currentMon--;
                }
                this.userMoney[i] = new Money();
            }
        } else {
            builder.append("Exact Amount Given\n");
        }
        return builder.toString();
    }

    /**
     * This method generates the total of a <code>Money</code> array
     * @param moneys Money array
     * @return Sum
     */
    @Override
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
    @Override
    public void inputDenomenations(double price){
        int i = 0;
        boolean exists = false;

        while (i < this.storedMoney.length && !exists) {
            if (this.storedMoney[i].getValue() == price) {
                exists = true;
                this.storedMoney[i] = new Money(price, this.storedMoney[i].getAmount()+1);
            }
            i++;
        }

        if (!exists) {
            this.storedMoney[this.storedMoneyAmount] = new Money(price, 1);
            this.storedMoneyAmount++;
        }
    }

    /**
     * This method checks the amount of every denomination left in the machine
     * @return String of denominations
     */
    @Override
    public String checkDenom(){
        StringBuilder builder = new StringBuilder();
        if(total(this.storedMoney) > 0){
            builder.append("\n------------------------\n");
            builder.append("|    Value    | Amount |\n");
            builder.append("------------------------\n");
            for (Money money : this.storedMoney) {
                if (money.getValue() > 0) {
                    builder.append(String.format("|P%12.2f|", money.getValue()));
                    builder.append(String.format("%8d|\n", money.getAmount()));
                    builder.append("------------------------\n");
                }
            }
        } else {
            builder.append("\nThere is no money available in the machine");    
        }

        return builder.toString();
    }

    /**
     * This method inputs <code>Items</code> objects into the <code>ItemsSlots</code> array
     * @param name Name of the Itemm
     * @param type Item type
     * @param price Price of the Item
     * @param quantity Amount of the Item
     * @param calories Calories of the Item
     */
    @Override
    public void inputItems(String name, String type, double price, int quantity, int calories){
        boolean found = false;
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

        if (found){
            this.vendoItem[row][col].setPrice(price);
            this.vendoItem[row][col].setQuantity(quantity);
            this.vendoItem[row][col].getProductItem()[0] = new Items(name, calories, type);
            this.vendoItem[row][col].setProductItems(this.vendoItem[row][col].getProductItem()[0]);
        } else {
            this.vendoItem[this.occupiedRow][this.occupiedSlots].setPrice(price);
            this.vendoItem[this.occupiedRow][this.occupiedSlots].setQuantity(quantity);
            this.vendoItem[this.occupiedRow][this.occupiedSlots].getProductItem()[0] = new Items(name, calories, type);
            this.vendoItem[this.occupiedRow][this.occupiedSlots].setProductItems(this.vendoItem[this.occupiedRow][this.occupiedSlots].getProductItem()[0]);
        
            this.occupiedSlots++;
        
            if(this.occupiedSlots > 0 && this.occupiedSlots % MAXCOL == 0){
                this.occupiedRow++;
            }
        } 
    }

    /**
     * This method changes the price of <code>Items</code>
     * @param newPrice new price of an item
     */
    @Override
    public boolean changePrice(double newPrice){
        int row, col;
        boolean success = false;
        String slotLabel;
        
        slotLabel = sc.next().toUpperCase();

        row = slotLabel.charAt(0) - 'A';
        col = Integer.parseInt(slotLabel.substring(1)) - 1;

        if(this.vendoItem[row][col] != null && this.vendoItem[row][col].getProductItem()[0].getName() != null){
            this.vendoItem[row][col].setPrice(newPrice);
            success = true;
        }
        
        return success;
    } 

    /**
     * 
     */
    public void increaseItem(String label){
        int row, col, origQuantity;
        String slotLabel;
        ItemsSlots item;

        slotLabel = sc.next().toUpperCase();
       
        row = slotLabel.charAt(0) - 'A';
        col = Integer.parseInt(slotLabel.substring(1)) - 1;

        origQuantity = this.vendoItem[row][col].getQuantity();

        if (row >= 0 && row < this.vendoItem.length && col >= 0 && col < this.vendoItem[row].length) {
            item = this.vendoItem[row][col];

            item.increaseQuantity(1);
            item.increaseItemsFromSlot(this.vendoItem[row][col].getProductItem(), origQuantity);

        } 
    }

    /**
     * This method decreases the <code>Items</code> in a <code>ItemsSlots</code> array
     * @param itemArr Item Slot
     */
    @Override
    public void decreaseItem(String label) {
        int row, col, origQuantity;
        String slotLabel;
        ItemsSlots item;

        slotLabel = sc.next().toUpperCase();
       
        row = slotLabel.charAt(0) - 'A';
        col = Integer.parseInt(slotLabel.substring(1)) - 1;
        
        origQuantity = this.vendoItem[row][col].getQuantity();

        if (row >= 0 && row < this.vendoItem.length && col >= 0 && col < this.vendoItem[row].length) {
            item = this.vendoItem[row][col];

            item.decreaseQuantity(1);
            item.decreaseItemsFromSlot(this.vendoItem[row][col].getProductItem());

            if(item.getQuantity() == 0){// Essentially an item is removed from its slot
                this.occupiedSlots--;
                item.setPrice(0);
            }
        } 
    }

    /**
     * This method collects the <code>Money</code> in the machine and saves them in a file
     */
    @Override
    public boolean collectMoneyInMachine(){
        int i = 0, amount = 0;
        double sum = 0;
        boolean reachedTotal = false, success = false;
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
                success = true;
            } catch (IOException e) {
                
            }
        } 
        return success;
    }

    /**
     * This method shows the newest <code>Transactions</code> that the maintenance hasn't seen yet
     */
    @Override
    public String showNewTransactions(){
        StringBuilder builder = new StringBuilder();
        double sum = 0;
        int i = 0, j = 0, curQuantity = 0;
        boolean found = false;
        if(this.transactionList != null && this.transactionAmount != 0){
            builder.append("----------------------------------------------------------------------------------------------------------\n");
            builder.append("|  TR#  |         Name       |  Quantity  |  in Machine  |   Total  |  Payment |  Change  |     Date     |\n");
            builder.append("----------------------------------------------------------------------------------------------------------\n");
            for (Transactions transaction : this.transactionList) {
                sum += transaction.getTotal();

                while(i < this.occupiedRow && !found){
                    while (j < this.occupiedSlots % 5 && !found) {
                        if (this.vendoItem[i][j].getProductItem()[0].getName().equals(transaction.getItem().getName())) {
                            curQuantity = this.vendoItem[i][j].getQuantity();
                            found = true;
                        }
                    }
                }

                builder.append(String.format("|%7d|", transaction.getNumber()));
                builder.append(String.format("%20s|", transaction.getItem().getName()));
                builder.append(String.format("%12d|", 1));
                builder.append(String.format("%7d - %6d|", curQuantity, 1+curQuantity));
                builder.append(String.format("P%9.2f|", transaction.getTotal()));
                builder.append(String.format("P%9.2f|", transaction.getPayment()));
                builder.append(String.format("P%9.2f|", transaction.getChange()));
                builder.append(String.format("%14s|", transaction.toString()));
                builder.append("----------------------------------------------------------------------------------------------------------\n");
            }
            builder.append("Total: " + sum + "\n");
        } else {
            builder.append("There are no transactions available to check\n");
        }
        

        return builder.toString();
    }

    /**
     * This method shows the all the <code>Transactions</code> made
     */
    @Override
    public String showTransactions(){
        StringBuilder builder = new StringBuilder();
        double sum = 0;
        if(this.transactionList != null && this.transactionAmount != 0){
            builder.append("------------------------------------------------------------------------------\n");
            builder.append("|  TR#  |         Name       |   Total  |  Payment |  Change  |     Date     |\n");
            builder.append("------------------------------------------------------------------------------\n");
            for (Transactions transaction : this.transactionList) {
                if(!transaction.getCheck()){
                    sum += transaction.getTotal();

                    builder.append(String.format("|%7d|", transaction.getNumber()));
                    builder.append(String.format("%20s|", transaction.getItem().getName()));
                    builder.append(String.format("P%9.2f|", transaction.getTotal()));
                    builder.append(String.format("P%9.2f|", transaction.getPayment()));
                    builder.append(String.format("P%9.2f|", transaction.getChange()));
                    builder.append(String.format("%14s|\n", transaction.toString()));
                    builder.append("------------------------------------------------------------------------------\n");
                    transaction.setCheck(true);
                }
            }
            builder.append("Total: " + sum + "\n\n");
        } else {
            builder.append("There are no transactions available to check");
        }

        return builder.toString();
    }

}