package VendingModel.VendingMachineClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

import VendingModel.ItemsClass.Items; 
import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;
import VendingModel.TransactionsClass.Transactions;   


public class SpecialVendingMachine extends VendingMachine implements InterfaceVendingMachine{
    private ArrayList<ItemsSlots> userCart;
    private int transactionsMade;

    public SpecialVendingMachine(){
        super();
        this.userCart = new ArrayList<ItemsSlots>();
        this.transactionsMade = 0;
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
            File contentFile = new File("./VendingModel/VendingMachineClasses/Files/Items.txt");
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


                this.vendoItem[row][col].getProductItem()[0] = new Items(name, calories, itemType);
                this.vendoItem[row][col].setQuantity(quantity);
                this.vendoItem[row][col].setPrice(price);
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
            System.out.println("\nAn error occurred.");
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
            File contentFile = new File("./VendingModel/VendingMachineClasses/Files/Money.txt");
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
        LocalDateTime date;
        Items item;
        Transactions temp;
        try {
            File contentFile = new File("./VendingModel/VendingMachineClasses/Files/Transactions.txt");
            Scanner reader = new Scanner(contentFile);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            while (reader.hasNextLine()) {// reads file
                number = Integer.parseInt(reader.nextLine());
                item = new Items(reader.nextLine(), Integer.parseInt(reader.nextLine()), reader.nextLine());
                total = Double.parseDouble(reader.nextLine());
                payment = Double.parseDouble(reader.nextLine());
                change = Double.parseDouble(reader.nextLine());
                date = LocalDateTime.parse(reader.nextLine(), formatter);
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

    /**
     * Displays the cart items of the user
     * @return Display of cart items
     */
    @Override
    public String display(){
        int i;
        StringBuilder build = new StringBuilder();
        for(i = 0; i < this.userCart.size(); i++){
            build.append("Slot: " + this.userCart.get(i).getLabel() + "\n");
            build.append("Name: " + this.userCart.get(i).getProductItem()[0].getName() + "\n");
            build.append("Price: " + this.userCart.get(i).getPrice() + "\n");
            build.append("Calories(g): " + this.userCart.get(i).getProductItem()[0].getCalories() + "\n");    
        }
        build.append("\n------------------------------------------------------------------------\n");

        return build.toString();
    }

    /**
     * a method that inputs an itemslot into the user cart
     * @param label label of the slot in the machine
     * @return true or false
     */
    public boolean addItemToCart(String label){
        int row, col;
        boolean success = false;
        ItemsSlots slot;

        row = label.toUpperCase().charAt(0) - 'A';
        col = Integer.parseInt(label.substring(1)) - 1;

        try {
            slot = this.vendoItem[row][col];
            if (slot != null) {
                this.userCart.add(slot);
                success = true;
            }
        } catch (NullPointerException e) {
            // TODO: handle exception
        }

        return success;
    }
    
    /**
     * This method returns true if the purchased items are enough to buy the inputted product
     * @param input Product name being passed
     */
    public boolean buyProduct(String input, List<ItemsSlots> userCart, double userMoney, int transactionAmount) {
    boolean success = true; 
    int i = 0;
    double price = 0.0, change = 0.0, payment = 0.0;
    String productName = input.toLowerCase(); 

    Hashtable<String, Integer> requiredIngredients = findProd(productName);
    Hashtable<String, Integer> itemCounts = new Hashtable<>();

    for (ItemsSlots cartItem : this.userCart) {
        String itemName = cartItem.getProductItem()[0].getName().toLowerCase();
        itemCounts.put(itemName, itemCounts.getOrDefault(itemName, 0) + 1);
    }

    while (i < this.userCart.size() && success) {
        int requiredQuantity = 0;
        int cartQuantity = 0;
        for (String key : requiredIngredients.keySet()) {
            if (key.contains(this.userCart.get(i).getProductItem()[0].getName().toLowerCase())) {
                requiredQuantity = requiredIngredients.get(key);
                cartQuantity = itemCounts.getOrDefault(key, 0);
            }
        }

        if (cartQuantity < requiredQuantity) {
            success = false;
        }
        i++;
    }

    if (success) {
        for (i = 0; i < this.userCart.size(); i++) {
            price += this.userCart.get(i).getPrice();
        }

        payment = userMoney;
        change = produceChange(this.userMoney, price);

        for (i = 0; i < this.userCart.size(); i++) {
            this.transactionList.add(new Transactions(price, payment, change, this.userCart.get(i).getProductItem()[0], transactionAmount + 1));
        }
        this.userCart.clear();
    }
    return success;
}

    /**
     * This method returns the quantity and itemNames of each product
     * @param productName Product name being passed
     */
    public static Hashtable<String, Integer> findProd(String productName) {

            Hashtable<String, Integer> requiredIngredients = new Hashtable<String, Integer>();
            if ("strawberry smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("strawberry", 3);
                requiredIngredients.put("milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("sugar", 1);
            } else if ("mango smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("mango", 3);
                requiredIngredients.put("milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("sugar", 1);
            } else if ("mixed berry smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("mixedberries", 2);
                requiredIngredients.put("strawberry", 1);
                requiredIngredients.put("milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("sugar", 1);
            } else if ("strawberry banana smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("strawberry", 2);
                requiredIngredients.put("banana", 1);
                requiredIngredients.put("milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("sugar", 1);
            } else if ("mango graham smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("mango", 3);
                requiredIngredients.put("milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("graham", 1);
                requiredIngredients.put("sugar", 1);
            } else if ("spring smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("mango", 1);
                requiredIngredients.put("orange", 1);
                requiredIngredients.put("carrot", 1);
                requiredIngredients.put("milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("honey", 1);
            } else if ("tropical dragon smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("dragonfruit", 2);
                requiredIngredients.put("mixedberries", 1);
                requiredIngredients.put("milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("oats", 1);
            } else if ("watermelon smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("watermelon", 2);
                requiredIngredients.put("grapes", 1);
                requiredIngredients.put("milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("honey", 1);
            } else if ("peach smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("peach", 2);
                requiredIngredients.put("orange", 1);
                requiredIngredients.put("milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("wafer", 1);
            } else if ("oreo banana smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("banana", 3);
                requiredIngredients.put("milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("oreo", 1);
            } else if ("pb banana smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("banana", 3);
                requiredIngredients.put("milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("peanutbutter", 1);
            } else if ("health smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("apple", 2);
                requiredIngredients.put("spinach", 1);
                requiredIngredients.put("almondmilk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("spirulinapowder", 1);
            } else if ("forest smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("mango", 1);
                requiredIngredients.put("orange", 1);
                requiredIngredients.put("kale", 1);
                requiredIngredients.put("oatmilk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("honey", 1);
            } else if ("protein smoothie".equalsIgnoreCase(productName)) {
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
            if (this.userMoney[i].getValue() == price) {
                exists = true;
                this.userMoney[i] = new Money(price, this.userMoney[i].getAmount()+1);
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
     * @param input
     * @return true or false
     */
    public boolean buyItem() {
        boolean success = true;
        int i = 0, origQuantity;
        double price = 0, change, payment = total(userMoney);
    
        while (i < this.userCart.size() && success) {
            if(isItemSellable(this.userCart.get(i).getProductItem()[0]) && (total(userMoney) >= this.userCart.get(i).getPrice())){
                success = true;
                origQuantity = this.userCart.get(i).getQuantity();
                price = this.userCart.get(i).getPrice();
                
                if (origQuantity > 0){
                    change = produceChange(userMoney, price);
                    this.transactionList.add(new Transactions(price, payment, change, this.userCart.get(i).getProductItem()[0], this.transactionAmount+1));
                    this.transactionsMade++;
                    this.userCart.get(i).decreaseQuantity(1);
                    this.userCart.get(i).updateItemsFromSlot(this.userCart.get(i).getProductItem());
                    if (this.userCart.get(i).getQuantity() == 0) {
                        this.occupiedSlots--;
                        this.userCart.get(i).setPrice(0);
                    }
                } else {
                    success = false;
                }
                
            } else {
                success = false;
            }
            i++;
        }

        if (success) {
            this.userCart.clear();
            this.salesWasDone = true;
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
            for (Money money : userMoney) {
                tempMoney[j] = new Money(money.getValue(), money.getAmount());
                j++;
            }
            addPaymentToMachine(userMoney);
            while(i >= 0){
                try {
                    numBills = (int)temp/(int)this.storedMoney[i].getValue();
                } catch (ArithmeticException e) {
                    // TODO: handle exception
                }
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
               item.getItemType().equalsIgnoreCase("Addons")  ||
               item.getItemType().equalsIgnoreCase("Add-on")  ||
               item.getItemType().equalsIgnoreCase("Addon"))  {
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

        initialization(this.userMoney);
    }

    /**
     * This method generates the transaction of the sale. It is used in the buyItems method
     * @param num number of items in transaction
     * @return String of the transactions
     */
    public String createTransactions(int num){
        StringBuilder builder = new StringBuilder();
        int i, transactionNum, calories;
        double price, total = 0, change = 0, payment = this.transactionList.get(this.transactionAmount).getPayment();
        String name;
        for (i = 0; i < num; i++) {
            transactionNum =  this.transactionList.get(this.transactionAmount + i).getNumber();
            name =  this.transactionList.get(this.transactionAmount + i).getItem().getName();
            calories =  this.transactionList.get(this.transactionAmount + i).getItem().getCalories();
            price = this.transactionList.get(this.transactionAmount + i).getTotal();
            total += this.transactionList.get(this.transactionAmount + i).getTotal();
            
            builder.append("Transaction #" + transactionNum + "\n");
            builder.append("Name: " + name + "\n");
            builder.append("Calories: " + calories + "\n");
            builder.append("Price: " + price + "\n\n");

            this.transactionsMade--;
        }

        change = payment - total;

        builder.append("----------------------------------------------\n");
        builder.append("Total  : " + total + "\n");
        builder.append("Payment: " + payment + "\n");
        builder.append("Change : " + change + "\n");
        builder.append("----------------------------------------------\n\n");
        
        this.transactionAmount++;
        
        return builder.toString();
    }

    /**
     * This method dispenses the change of the user
     * @param userMoney User's Money
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

    public String displayProcess(){
        StringBuilder process = new StringBuilder();
        
        process.append("Getting Ingridients...\n");
        process.append("Chopping Ingredients...\n");
        process.append("Placing ingredients in blender...\n\n");
        process.append("Blending...\n\n");
        process.append("Success!");

        return process.toString();
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
     * This method checks the amount of every denomination left in the machine
     * @return String of denominations
     */
    @Override
    public String checkDenom(){
        StringBuilder builder = new StringBuilder();
        if(total(this.storedMoney) > 0){
            builder.append("-----------------------\n");
            for (Money money : this.storedMoney) {
                if (money.getValue() > 0) {
                    builder.append(String.format("P" + money.getValue() + " | "));
                    builder.append(String.format("Amount: " + money.getAmount() + "\n"));
                    builder.append("-----------------------\n");
                }
            }
        } else {
            builder.append("\nThere is no money available in the machine");    
        }

        return builder.toString();
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
                sortMoney();
            }
            i++;
        }

        if (!exists) {
            this.storedMoney[this.storedMoneyAmount] = new Money(price, 1);
            this.storedMoneyAmount++;
        }
    }

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
     * This method inputs <code>Items</code> objects into the <code>ItemsSlots</code> array
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
            this.vendoItem[this.occupiedRow][this.occupiedSlots].setPrice(price);
            this.vendoItem[this.occupiedRow][this.occupiedSlots].setQuantity(quantity);
            this.vendoItem[this.occupiedRow][this.occupiedSlots].getProductItem()[0] = new Items(name, calories, type);
            this.vendoItem[this.occupiedRow][this.occupiedSlots].setProductItems(this.vendoItem[this.occupiedRow][this.occupiedSlots].getProductItem()[0]);
        
            this.occupiedSlots++;
        
            if(this.occupiedSlots > 0 && this.occupiedSlots % MAXCOL == 0){
                this.occupiedRow++;
            }
        } 

        return success;
    }

    /**
     * This method changes the price of <code>Items</code>
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
     * This method increases the items in the item slots
     */
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
     * This method decreases the <code>Items</code> in a <code>ItemsSlots</code> array
     * @param itemArr Item Slot
     */
    @Override
    public boolean decreaseItem(String label) {
        int row, col, origQuantity;
        boolean success = false;
        ItemsSlots item;
       
        row = label.charAt(0) - 'A';
        col = Integer.parseInt(label.substring(1)) - 1;

        origQuantity = this.vendoItem[row][col].getQuantity();

        if (row >= 0 && row < this.vendoItem.length && col >= 0 && col < this.vendoItem[row].length) {
            item = this.vendoItem[row][col];

            if (origQuantity > 0) {
                item.decreaseQuantity(1);
                item.updateItemsFromSlot(this.vendoItem[row][col].getProductItem());
                success = true;
            } else {
                success = false;
            }

            if(item.getQuantity() == 0){// Essentially an item is removed from its slot
                this.occupiedSlots--;
                item.setPrice(0);
            }
        } 

        return success;
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
        File contentFile = new File("./VendingModel/VendingMachineClasses/Files/Collections.txt");

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
                    this.storedMoneyAmount--;
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
            for (Transactions transaction : this.transactionList) {
                if(!transaction.getCheck()){
                    transaction.setCheck(true);
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
                    builder.append("\n----------------------------------------------------------------------------------------------------------\n");
                }
            }
            builder.append("Total: " + sum + "\n");
        } else {
            builder.append("There are no transactions available to check");
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
                
                builder.append("\n----------------------------------------------------------------------------------------------------------\n");
            }
            builder.append("Total: " + sum + "\n");
        } else {
            builder.append("There are no transactions available to check\n");
        }
        return builder.toString();
    }

    public ArrayList<ItemsSlots> getUserCart() {
        return this.userCart;
    }

    public int getTransactionsMade() {
        return this.transactionsMade;
    }
       
}
