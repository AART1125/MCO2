package VendingModel.VendingMachineClasses;

import VendingModel.ItemsClass.Items;
import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;
import VendingModel.TransactionsClass.Transactions;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This is the <code>RegularVendingMachine</code> class. This class extends to the <code>VendingMachine</code> class and implements the 
 * <code>InterfaceVendingMachine</code> methods. The main process involved in this class is the buying of an item based on a given slot.
 */
public class RegularVendingMachine extends VendingMachine implements InterfaceVendingMachine{

    /**
     * This is the constructor of the <code>VendingMachine</code> class and it calls on the constructor of the superclass
     */
    public RegularVendingMachine(){
        super();
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public void fileItemScan(){
        int quantity, calories, row = 0, col = 0;
        String name, itemType;
        double price;
        try{
            File contentFile = new File("./VendingModel/VendingMachineClasses/Files/Items.txt");
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
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public void fileItemWrite(){
        int i, j;
        try {
            File contentFile = new File("./VendingModel/VendingMachineClasses/Files/Items.txt");
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
            
        }
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public void fileMoneyScan(){
        double value;
        int amount, index;

        try {
            File contentFile = new File("./VendingModel/VendingMachineClasses/Files/Money.txt");
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
            
        }
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public void fileMoneyWrite(){
        int i;
        try {
            File contentFile = new File("./VendingModel/VendingMachineClasses/Files/Money.txt");
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
            
        }
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public void fileTransactionScan(){
        int amount, number;
        double total, payment, change;
        boolean check, isProduct;
        LocalDateTime date;
        Items item;
        Transactions temp;
        try {
            File contentFile = new File("./VendingModel/VendingMachineClasses/Files/Transactions.txt");
            Scanner reader = new Scanner(contentFile);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            amount = Integer.parseInt(reader.nextLine());
            this.transactionAmount = amount;
            while (reader.hasNextLine()) {// reads file
                number = Integer.parseInt(reader.nextLine());
                item = new Items(reader.nextLine(), Integer.parseInt(reader.nextLine()), reader.nextLine());
                total = Double.parseDouble(reader.nextLine());
                payment = Double.parseDouble(reader.nextLine());
                change = Double.parseDouble(reader.nextLine());
                date = LocalDateTime.parse(reader.nextLine(), formatter);
                check = Boolean.parseBoolean(reader.nextLine());
                isProduct = Boolean.parseBoolean(reader.nextLine());
                reader.nextLine();

                temp = new Transactions(total, payment, change, item, number, date, check, isProduct);
                this.transactionList.add(temp);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            
        }
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public void fileTransactionWrite(){
        try {
            File contentFile = new File("./VendingModel/VendingMachineClasses/Files/Transactions.txt");
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
                mainWriter.println(transactions.getIsProduct());
                mainWriter.print("\n");
            }
            mainWriter.close();
            
        } catch (IOException e) {
            
        }
    }
    
    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
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
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public void initialization(Money[] moneys){
        int i;
        for ( i = 0; i < moneys.length; i++) {
            moneys[i] = new Money();
        }
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public String display(){
        StringBuilder builder = new StringBuilder();
        double change = this.transactionList.get(this.transactionList.size()-1).getPayment() - this.transactionList.get(this.transactionList.size()-1).getTotal();

        if(this.transactionList.get(this.transactionList.size()-1) != null){

            builder.append("Transaction #" + this.transactionList.get(this.transactionList.size()-1).getNumber() + "\n");
            builder.append("Name: " + this.transactionList.get(this.transactionList.size()-1).getItem().getName()  + "\n");
            builder.append("Calories(g): "+ this.transactionList.get(this.transactionList.size()-1).getItem().getCalories()  + "\n");
            builder.append("Total: " + this.transactionList.get(this.transactionList.size()-1).getTotal()  + "\n");
            builder.append("\n----------------------------\n");
            builder.append("Total: "+ this.transactionList.get(this.transactionList.size()-1).getTotal()  + "\n");
            builder.append("Payment: "+ this.transactionList.get(this.transactionList.size()-1).getPayment()  + "\n");
            builder.append("Change: "+ change + "\n");
            builder.append("\n----------------------------\n");
            
            this.transactionAmount++;
        }
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
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
     * This method buys the items based on the slot given
     * @param input slot of the item to buy
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
                            this.transactionList.add(new Transactions(slot.getPrice(), payment, change, slot.getProductItem()[0], this.transactionAmount + 1, false));

                            //decrease item from slot
                            origQuantity = slot.getQuantity();
                            if (origQuantity > 0) {
                                slot.decreaseQuantity(1);
                                slot.updateItemsFromSlot(slot.getProductItem());
                            }
                        } else {
                            success = false;
                        }
                    } else {
                        success = false;
                    }
                } else {
                    success = false;
                }
            } else {
                success = false;
            }
        return success;
    }

    //Checks if item is sellable
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

    //Adds the payment into the machine
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

    //Produce the denominations for change
    private double produceChange(Money[] userMoneys, double total){
        double change = total(userMoneys) - total,
               temp = change;
        int numBills = 0,  denomCount = 0;
        int i = DENOMINATIONS-1, j = 0;
        Money[] tempMoney = new Money[DENOMINATIONS];

        if(change >= 0){
            for (Money money : userMoneys) {
                tempMoney[j] = new Money(money.getValue(), money.getAmount());
                j++;
            }
            addPaymentToMachine(userMoneys);
            while(i >= 0){
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

    //Remove some of these denomination
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
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public String dispenseChange() {
        StringBuilder builder = new StringBuilder();
        int i;

        if(total(this.userMoney) > 0){
            for(i = 0; i < userMoney.length; i++){
                if(this.userMoney[i].getValue() >= 20){
                    builder.append("\nDispensing change: P\n" + this.userMoney[i].getValue() + " bills | Amount : " + this.userMoney[i].getAmount() + "\n");
                } else if (this.userMoney[i].getValue() < 20 && this.userMoney[i].getValue() > 0) {
                    builder.append("\nDispensing change: P\n" + this.userMoney[i].getValue() + " coins | Amount : " + this.userMoney[i].getAmount() + "\n");
                }
                this.userMoney[i] = new Money();
            }
        } else {
            builder.append("Exact Amount Given\n");
        }
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public String displayProcess(){
        StringBuilder process = new StringBuilder();
        
        process.append("Proccessing Input...\n");
        process.append("Searching for Item...\n");
        process.append("Dispensing Item...\n\n");
        process.append("Success!");

        return process.toString();
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
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
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public void inputDenomenations(double price){
        int i = 0;
        boolean exists = false;

        while (i < this.storedMoney.length && !exists) {
            if (this.storedMoney[i].getValue() == price) {
                exists = true;
                this.storedMoney[i] = new Money(price, this.storedMoney[i].getAmount()+1);
                sortMoney();
            }
            i++;
        }

        if (!exists) {
            this.storedMoney[this.storedMoneyAmount] = new Money(price, 1);
            this.storedMoneyAmount++;
        }
    }

    //sorts money array using selection sort
    private void sortMoney(){
        int i, j, min;
        Money temp;
        for (i = 0; i < this.storedMoneyAmount - 1; i++) {
            min = i;
            for (j = i + 1; j < this.storedMoneyAmount; j++) {
                if (this.storedMoney[j].getValue() < this.storedMoney[min].getValue()) {
                    min = j;
                }
            }

            if (min != i) {
                temp = this.storedMoney[i];
                this.storedMoney[i] = this.storedMoney[min];
                this.storedMoney[min] = temp; 
            }
        }
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public String checkDenom(){
        StringBuilder builder = new StringBuilder();
        if(total(this.storedMoney) > 0){
            for (Money money : this.storedMoney) {
                if (money.getValue() > 0) {
                    builder.append("Value: " + money.getValue());
                    builder.append("Amount: " +  money.getAmount());
                    builder.append("------------------------\n");
                }
            }
        } else {
            builder.append("\nThere is no money available in the machine");    
        }

        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public boolean inputItems(String name, String type, double price, int quantity, int calories){
        boolean found = false, success = false;
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
            success = true;
            this.vendoItem[row][col].setPrice(price);
            this.vendoItem[row][col].setQuantity(quantity);
            this.vendoItem[row][col].getProductItem()[0] = new Items(name, calories, type);
            this.vendoItem[row][col].setProductItems(this.vendoItem[row][col].getProductItem()[0]);
        } else {
            success = true;
            this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].setPrice(price);
            this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].setQuantity(quantity);
            this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].getProductItem()[0] = new Items(name, calories, type);
            this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].setProductItems(this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].getProductItem()[0]);
        
            this.occupiedSlots++;
        
            if(this.occupiedSlots > 0 && this.occupiedSlots % MAXCOL == 0){
                this.occupiedRow++;
            }
        } 
        return success;
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public boolean changePrice(double newPrice, String label){
        int row, col;
        boolean success = false;

        row = label.charAt(0) - 'A';
        col = Integer.parseInt(label.substring(1)) - 1;

        if(this.vendoItem[row][col] != null && this.vendoItem[row][col].getProductItem()[0].getName() != null){
            this.vendoItem[row][col].setPrice(newPrice);
            success = true;
        }
        
        return success;
    } 

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public boolean increaseItem(String label){
        int row, col, origQuantity;
        boolean success = false;
        ItemsSlots item;
       
        row = label.charAt(0) - 'A';
        col = Integer.parseInt(label.substring(1)) - 1;

        origQuantity = this.vendoItem[row][col].getQuantity();

        if (row >= 0 && row < this.vendoItem.length && col >= 0 && col < this.vendoItem[row].length) {
            item = this.vendoItem[row][col];

            if (origQuantity < MAXITEMS) {
                item.increaseQuantity(1);
                item.updateItemsFromSlot(this.vendoItem[row][col].getProductItem());
                success = true;
            } else {
                success = false;
            }
            
        } 

        return success;
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public boolean decreaseItem(String label) {
        int row, col;
        boolean success = false;
        ItemsSlots item;
       
        row = label.charAt(0) - 'A';
        col = Integer.parseInt(label.substring(1)) - 1;

        if (row >= 0 && row < this.vendoItem.length && col >= 0 && col < this.vendoItem[row].length) {
            item = this.vendoItem[row][col];
            item.decreaseQuantity(1);
            item.updateItemsFromSlot(this.vendoItem[row][col].getProductItem());

            success = true;
            if(item.getQuantity() == 0){// Essentially an item is removed from its slot
                this.occupiedSlots--;
                item.setPrice(0);
            }
        } 
        
        return success;
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
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
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public String showNewTransactions(){
        StringBuilder builder = new StringBuilder();
        double sum = 0;
        int curQuantity = 0;
        if(this.transactionList != null && this.transactionAmount != 0){
            
            for (Transactions transaction : this.transactionList) {
                if(!transaction.getCheck()){
                    sum += transaction.getTotal();

                    builder.append("Transaction #"+ transaction.getNumber() + "\n");
                    builder.append("Name: " + transaction.getItem().getName() + "\n");
                    builder.append("Amount of Items: " + "1 | Before - After (Inventory): " + curQuantity+1 + " - " + curQuantity + "\n");
                    builder.append("Total: " + transaction.getTotal() + "\n");
                    builder.append("Payment: " + transaction.getPayment() + "\n");
                    builder.append("Change: " + transaction.getChange() + "\n");
                    builder.append("Date and Time: " + transaction.toString());
                    builder.append("\n--------------------------------------------------------------\n");
                    transaction.setCheck(true);
                }
            }
            builder.append("Total: " + sum + "\n\n");
        } else {
            builder.append("There are no transactions available to check");
        }

        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public String showTransactions(){
        StringBuilder builder = new StringBuilder();
        double sum = 0;
        int i = 0, j = 0, curQuantity = 0;
        boolean found = false;
        if(this.transactionList != null && this.transactionAmount != 0){
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

                builder.append("Transaction #"+ transaction.getNumber() + "\n");
                builder.append("Name: " + transaction.getItem().getName() + "\n");
                builder.append("Amount of Items: " + "1 | Before - After (Inventory): " + curQuantity+1 + " - " + curQuantity + "\n");
                builder.append("Total: " + transaction.getTotal() + "\n");
                builder.append("Payment: " + transaction.getPayment() + "\n");
                builder.append("Change: " + transaction.getChange() + "\n");
                builder.append("Date and Time: " + transaction.toString());
                builder.append("\n--------------------------------------------------------------\n");
            }
            builder.append("Total: " + sum + "\n");
        } else {
            builder.append("There are no transactions available to check\n");
        }
        

        return builder.toString();
    }
    
}