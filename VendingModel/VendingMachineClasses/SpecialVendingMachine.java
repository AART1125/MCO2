package VendingModel.VendingMachineClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.awt.List;
import java.util.Map;

import VendingModel.ItemsClass.Items; 
import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;
import VendingModel.TransactionsClass.Transactions;   


public class SpecialVendingMachine extends VendingMachine implements InterfaceVendingMachineSpecial{
    private ArrayList<ItemsSlots> itemList;
    private int storedMoneyAmount;

    public SpecialVendingMachine(){
        super();
        this.itemList = new ArrayList<ItemsSlots>();
        this.storedMoneyAmount = 0;
    }

    public void initializeProductsRequirement(){
        
    }

    /**
     * A method that scans a file for the <code>Items</code> made in the program
     */
    @Override
    public void fileItemScan() {
        int quantity, calories, row = 0, col = 0;
        String name, itemType;
        double price;
        try {
            File contentFile = new File("./Files/Items.txt");
            Scanner reader = new Scanner(contentFile);
            this.occupiedRow = Integer.parseInt(reader.nextLine());
            this.occupiedSlots = Integer.parseInt(reader.nextLine());
            reader.nextLine();
            while (reader.hasNextLine()) {// reads file
                row = Integer.parseInt(reader.nextLine());
                col = Integer.parseInt(reader.nextLine());

                name = reader.nextLine();
                itemType = reader.nextLine();
                calories = Integer.parseInt(reader.nextLine());
                quantity = Integer.parseInt(reader.nextLine());
                price = Double.parseDouble(reader.nextLine());


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
    @Override
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
    @Override
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

                this.storedMoney[index] = new Money(value, amount);

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
    @Override
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
    @Override
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            while (reader.hasNextLine()) {// reads file
                number = Integer.parseInt(reader.nextLine());
                item = new Items(reader.nextLine(), Integer.parseInt(reader.nextLine()), reader.nextLine());
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
    @Override
    public void fileTransactionWrite() {
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
            //cathces error
        }
    }

    /**
     * Initializes all <code>ItemSlots</code> instances in the array
     * @param vendoItems ItemsSlots array
     */
    @Override
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
    @Override
     public void initialization(Money[] moneys){
        int i;
        for ( i = 0; i < moneys.length; i++) {
            moneys[i] = new Money();
        }
    }

    public boolean addItem(String label){
        int row, col;
        boolean success = false;
        ItemsSlots slot;

        row = label.toUpperCase().charAt(0) - 'A';
        col = Integer.parseInt(label.substring(1));

        try {
            slot = this.vendoItem[row][col];
            if (slot != null) {
                this.itemList.add(slot);
                success = true;
            }
        } catch (NullPointerException e) {
            // TODO: handle exception
        }

        return success;
    }


/**
 * This method returns the quantity and itemNames of each product
 * @param productName Product name being passed
 */
@Override
public static Hashtable<String, Integer> findProd(String productName) {

        Hashtable<String, Integer> requiredIngredients = new Hashtable<String, Integer>();
        if ("strawberry smoothie".equals(productName)) {
            requiredIngredients.put("strawberry", 3);
            requiredIngredients.put("milk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("sugar", 1);
        } else if ("mango smoothie".equals(productName)) {
            requiredIngredients.put("mango", 3);
            requiredIngredients.put("milk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("sugar", 1);
        } else if ("mixed berry smoothie".equals(productName)) {
            requiredIngredients.put("mixedberries", 2);
            requiredIngredients.put("strawberry", 1);
            requiredIngredients.put("milk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("sugar", 1);
        } else if ("strawberry banana smoothie".equals(productName)) {
            requiredIngredients.put("strawberry", 2);
            requiredIngredients.put("banana", 1);
            requiredIngredients.put("milk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("sugar", 1);
        } else if ("mango graham smoothie".equals(productName)) {
            requiredIngredients.put("mango", 3);
            requiredIngredients.put("milk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("graham", 1);
            requiredIngredients.put("sugar", 1);
        } else if ("spring smoothie".equals(productName)) {
            requiredIngredients.put("mango", 1);
            requiredIngredients.put("orange", 1);
            requiredIngredients.put("carrot", 1);
            requiredIngredients.put("milk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("honey", 1);
        } else if ("tropical dragon smoothie".equals(productName)) {
            requiredIngredients.put("dragonfruit", 2);
            requiredIngredients.put("mixedberries", 1);
            requiredIngredients.put("milk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("oats", 1);
        } else if ("watermelon smoothie".equals(productName)) {
            requiredIngredients.put("watermelon", 2);
            requiredIngredients.put("grapes", 1);
            requiredIngredients.put("milk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("honey", 1);
        } else if ("peach smoothie".equals(productName)) {
            requiredIngredients.put("peach", 2);
            requiredIngredients.put("orange", 1);
            requiredIngredients.put("milk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("wafer", 1);
        } else if ("oreo banana smoothie".equals(productName)) {
            requiredIngredients.put("banana", 3);
            requiredIngredients.put("milk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("oreo", 1);
        } else if ("pb banana smoothie".equals(productName)) {
            requiredIngredients.put("banana", 3);
            requiredIngredients.put("milk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("peanutbutter", 1);
        } else if ("health smoothie".equals(productName)) {
            requiredIngredients.put("apple", 2);
            requiredIngredients.put("spinach", 1);
            requiredIngredients.put("almondmilk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("spirulinapowder", 1);
        } else if ("forest smoothie".equals(productName)) {
            requiredIngredients.put("mango", 1);
            requiredIngredients.put("orange", 1);
            requiredIngredients.put("kale", 1);
            requiredIngredients.put("oatmilk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("honey", 1);
        } else if ("protein smoothie".equals(productName)) {
            requiredIngredients.put("banana", 3);
            requiredIngredients.put("oatmilk", 1);
            requiredIngredients.put("ice", 1);
            requiredIngredients.put("protein powder", 1);
        }
        
        return requiredIngredients;
    }

    
    /**
     * This method collects the money of the user and add it in the machines userMoney array
     * @param userMoney User's money in the machine
     */
    @Override
    public boolean collectMoney(double price) {
        boolean exists = false, success = false;
        int i = 0;
        
        while (i < this.currentMon && !exists) {
            if (this.storedMoney[i].getValue() == price) {
                exists = true;
                this.storedMoney[i] = new Money(price, this.storedMoney[i].getAmount()+1);
            }
            i++;
        }

        if (!exists) {
            this.storedMoney[this.currentMon] = new Money(price, 1);
            this.currentMon++;
        }
        
        return success;
    }

    /**
     * This method is where the buying of an item will be done
     * @param userMoneys User's Money
     * @return true or false
     */
    @Override
    public boolean buyItem(String input) {
        boolean success = true;
        int i = 0, origQuantity;
        double price = 0, change;

        while (i < this.itemList.size() && success) {
            if(isItemSellable(this.itemList.get(i).getProductItem()[0]) && (total(userMoney) >= this.itemList.get(i).getPrice())){
                success = true;
                origQuantity = this.itemList.get(i).getQuantity();
                price = this.itemList.get(i).getPrice();
                
                if (origQuantity > 0){
                    change = produceChange(userMoney, price);
                    this.transactionList.add(new Transactions(price, total(storedMoney), change, this.itemList.get(i).getProductItem()[0], transactionAmount+1));
                    this.itemList.get(i).decreaseQuantity(origQuantity);
                    this.itemList.get(i).decreaseItemsFromSlot(this.itemList.get(i).getProductItem(), origQuantity);

                } else {
                    success = false;
                }
                
            } else {
                success = false;
            }
            i++;
        }

        return success;
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
        int numBills = 0,  denomCount = 0;
        int i = DENOMINATIONS-1, j = 0;
        Money[] tempMoney = new Money[DENOMINATIONS];

        if(change < 0){
           
        } else {
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
                        for (Money money : tempMoney) {
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
            } 
        }
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
     * This method generates the transaction of the sale. It is used in the buyItems method
     * @param item Items being bought
     * @param total Total price
     * @param payment Payment for item
     * @param change Change from payment
     */
    public String createTransactions(Items item, double total, double payment, double change){
        StringBuilder builder = new StringBuilder();

        builder.append("\n\nTransaction #" + this.transactionList.get(this.transactionAmount).getNumber() + "\n");
        builder.append("----------------------------------------------\n");
        builder.append("|         Name       |  Calories  |   Total  |\n");
        builder.append("----------------------------------------------\n");

        for (ItemsSlots items : this.transactionList.get(this.transactionAmount).getItemArr()) {
            builder.append(String.format("|%-20s|",items.getProductItem()[0].getName()));
            builder.append(String.format("%10d g|",items.getProductItem()[0].getCalories()));
            builder.append(String.format("P%9.2f|",items.getPrice()));
        }

        builder.append("----------------------------------------------\n\n");
        builder.append(String.format("Total  : P%9.2f\n", this.transactionList.get(this.transactionAmount).getTotal()));
        builder.append(String.format("Payment: P%9.2f\n", this.transactionList.get(this.transactionAmount).getPayment()));
        builder.append(String.format("Change : P%9.2f\n", change));
        builder.append("----------------------------------------------\n\n");
        
        return builder.toString();
    }

    /**
     * This method dispenses the change of the user
     * @param userMoney User's Money
     */
    @Override
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
     * This method inputs <code>Items</code> objects into the <code>ItemsSlots</code> array
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
     */
    @Override
    public void changePrice(double newPrice){
        int row, col;
        String slotLabel;
        
        slotLabel = sc.next().toUpperCase();

        row = slotLabel.charAt(0) - 'A';
        col = Integer.parseInt(slotLabel.substring(1)) - 1;

        if(this.vendoItem[row][col] != null && this.vendoItem[row][col].getProductItem()[0].getName() != null){
            this.vendoItem[row][col].setPrice(newPrice);
        }
    } 

    /**
     * This method decreases the <code>Items</code> in a <code>ItemsSlots</code> array
     * @param itemArr Item Slot
     */
    @Override
    public void decreaseItem(String label, int decrease) {
        int row, col, origQuantity;
        String slotLabel;
        ItemsSlots item;

        slotLabel = sc.next().toUpperCase();
       
        row = slotLabel.charAt(0) - 'A';
        col = Integer.parseInt(slotLabel.substring(1)) - 1;
        
        origQuantity = this.vendoItem[row][col].getQuantity();

        if (row >= 0 && row < this.vendoItem.length && col >= 0 && col < this.vendoItem[row].length) {
            item = this.vendoItem[row][col];

            item.decreaseQuantity(decrease);
            item.decreaseItemsFromSlot(this.vendoItem[row][col].getProductItem(), origQuantity);

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
