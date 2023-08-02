package VendingModel.VendingMachineClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

import VendingModel.ItemsClass.Items; 
import VendingModel.ItemsSlotsClass.ItemsSlots;
import VendingModel.MoneyClass.Money;
import VendingModel.TransactionsClass.Transactions;   

/**
 * This is the <code>SpecialVendingMachine</code> class. It is a subclass of the VendingMachine class and it inherits the methods of the InterfaceVendingMachine.
 * It has additional attributes from the superclass which are an ArrayList of ItemsSlots for the user, and a counter attribute for the transactions made in a single sale,
 * Here, not only is the buying of items is done. but the creation of a product as well.
 */
public class SpecialVendingMachine extends VendingMachine implements InterfaceVendingMachine{
    private ArrayList<ItemsSlots> userCart;
    private int transactionsMade;

    /**
     * This is the contructor for the <code>SpecialVendingMachine</code> that initializes its extended attributes from the <code>VendingMachine></code> class and its local attributes
     */
    public SpecialVendingMachine(){
        super();
        this.userCart = new ArrayList<ItemsSlots>();
        this.transactionsMade = 0;
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
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
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
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
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public void fileTransactionScan() {
        int number, amount;
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
            //cathces error
        }
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
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
                mainWriter.println(transactions.getIsProduct());
                mainWriter.print("\n");
            }
            mainWriter.close();

        } catch (IOException e) {
            //cathces error
        }
    }

    /**
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
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
        int i;
        StringBuilder build = new StringBuilder();
        for(i = 0; i < this.userCart.size(); i++){
            build.append("Slot: " + this.userCart.get(i).getLabel() + "\n");
            build.append("Name: " + this.userCart.get(i).getProductItem()[0].getName() + "\n");
            build.append("Price: " + this.userCart.get(i).getPrice() + "\n");
            build.append("Calories(g): " + this.userCart.get(i).getProductItem()[0].getCalories() + "\n");    
            build.append("\n----------------------------\n");
        }

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
     * @return true or false
     */
    public boolean buyProduct(String input) {   
        boolean success = true; 
        int i = 0;
        double price = 0.0, change = 0.0, payment = 0.0;
        String productName = input.toLowerCase(); 

        Hashtable<String, Integer> requiredIngredients = findProd(productName);
        Hashtable<String, Integer> itemCounts = new Hashtable<String, Integer>();

        // Count the occurrences of each item in the cart
        for (ItemsSlots cartItem : userCart) {
            String itemName = cartItem.getProductItem()[0].getName().toLowerCase(); 
            if (!itemCounts.containsKey(itemName)) {
                if (cartItem.getProductItem()[0].getName().toLowerCase().contains("milk") && requiredIngredients.containsKey("milk")) {
                    itemCounts.put("milk", 1);
                } else {
                    itemCounts.put(itemName.toLowerCase(), 1);
                }
                
            } else {
                itemCounts.replace(itemName.toLowerCase(), itemCounts.get(itemName) + 1);
            }
        }

        while (i < this.userCart.size() && success) {
            int requiredQuantity = 0;
            int cartQuantity = 0;

            for (String key : requiredIngredients.keySet()) {
                    requiredQuantity = requiredIngredients.get(key);
                    cartQuantity = itemCounts.getOrDefault(key, 0);
                    if (cartQuantity < requiredQuantity) {
                        success = false;
                    }
                
            }
            i++;
        }
        
        if (success){
            i=0;
            while (i < this.userCart.size()) {
                price += this.userCart.get(i).getPrice();
                i++;
            }

            price *= 2;
            payment = total(userMoney);
            change = produceChange(userMoney, price);

            if (change >= 0) {
                for (i = 0; i < this.userCart.size(); i++){
                    this.transactionList.add(new Transactions(price, payment, change, this.userCart.get(i).getProductItem()[0], this.transactionAmount+1, true));
                    this.transactionsMade++;
                    this.userCart.get(i).decreaseQuantity(1);
                    this.userCart.get(i).updateItemsFromSlot(this.userCart.get(i).getProductItem());
                    if (this.userCart.get(i).getQuantity() == 0) {
                        this.occupiedSlots--;
                        this.userCart.get(i).setPrice(0);
                    }
                }
                this.userCart.clear();
            } else {
                success = false;
            }
            
        }
        return success; 
    }

    //Creates a hashtable for the products
    private Hashtable<String, Integer> findProd(String productName) {

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
                requiredIngredients.put("almond milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("spirulinapowder", 1);
            } else if ("forest smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("mango", 1);
                requiredIngredients.put("orange", 1);
                requiredIngredients.put("kale", 1);
                requiredIngredients.put("oat milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("honey", 1);
            } else if ("protein smoothie".equalsIgnoreCase(productName)) {
                requiredIngredients.put("banana", 3);
                requiredIngredients.put("oat milk", 1);
                requiredIngredients.put("ice", 1);
                requiredIngredients.put("protein powder", 1);
            }
            
            return requiredIngredients;
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
                    if (change >= 0) {
                        this.transactionList.add(new Transactions(price, payment, change, this.userCart.get(i).getProductItem()[0], this.transactionAmount+1, false));
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
                
            } else {
                success = false;
            }
            i++;
        }

        if (success) {
            this.userCart.clear();
        }

        return success;
    }

    //Produce the denominations for change
    private double produceChange(Money[] userMoneys, double total){
        double change = total(userMoneys) - total,
               temp = change;
        int numBills = 0,  denomCount = 0;
        int i = DENOMINATIONS-1, j = 0;
        Money[] tempMoney = new Money[DENOMINATIONS];

        if(change >= 0){
           for (Money money : userMoney) {
                tempMoney[j] = new Money(money.getValue(), money.getAmount());
                j++;
            }
            addPaymentToMachine(userMoney);
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
            } 
        }
    }

    //Checks if item is sellable in buy meny
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

        initialization(this.userMoney);
    }

    /**
     * This method generates the transaction of the sale. It is used in the buyItems method
     * @param num number of items in transaction
     * @return String of the transactions
     */
    public String createTransactions(int num){
        StringBuilder builder = new StringBuilder();
        int i, transactionNum, calories, latestNum = this.transactionList.size()-this.transactionsMade;;
        double price, total = 0, change = 0, payment = this.transactionList.get(latestNum).getPayment();
        String name;
        for (i = 0; i < num; i++) {
            latestNum = this.transactionList.size()-this.transactionsMade;
            if (this.transactionAmount+1 == this.transactionList.get(latestNum).getNumber()) {
                transactionNum =  this.transactionList.get(latestNum).getNumber();
                name =  this.transactionList.get(latestNum).getItem().getName();
                calories =  this.transactionList.get(latestNum).getItem().getCalories();
                price = this.transactionList.get(latestNum).getTotal();
                total += this.transactionList.get(latestNum).getTotal();
                
                builder.append("Transaction #" + transactionNum + "\n");
                builder.append("Name: " + name + "\n");
                builder.append("Calories: " + calories + "\n");
                builder.append("Price: " + price + "\n\n");
                this.transactionsMade--;
            }

        }

        change = payment - total;

        builder.append("\n----------------------------\n");
        builder.append("Total  : " + total + "\n");
        builder.append("Payment: " + payment + "\n");
        builder.append("Change : " + change + "\n");
        builder.append("\n----------------------------\n");
        
        this.transactionAmount++;
        
        return builder.toString();
    }

    /**
     * This method generates the transaction of the sale for products. It is used in the buyItems method
     * @param num number of items in transaction
     * @return String of the transactions
     */
    public String createProductTransactions(int num){
        StringBuilder builder = new StringBuilder();
        int i, j, k, transactionNum, calories, latestNum = this.transactionList.size()-this.transactionsMade;
        double total = this.transactionList.get(latestNum).getTotal(), payment = this.transactionList.get(latestNum).getPayment();
        double price = 0, change = 0;
        boolean found = false;
        String name;
        for (i = 0; i < num; i++) {
            latestNum = this.transactionList.size()-this.transactionsMade;
            if (this.transactionAmount+1 == this.transactionList.get(latestNum).getNumber()) {
                transactionNum =  this.transactionList.get(latestNum).getNumber();
                name =  this.transactionList.get(latestNum).getItem().getName();
                calories =  this.transactionList.get(latestNum).getItem().getCalories();
                
                k=0;
                j=0;
                found = false;

                while (j < MAXROW && !found) {
                    while (k < MAXCOL && !found) {
                        if (this.vendoItem[j][k].getProductItem()[0].getName().equals(name)) {
                            found = true;
                            price = this.vendoItem[j][k].getPrice();
                        }
                        k++;
                    }
                    k=0;
                    j++;
                }

                builder.append("Transaction #" + transactionNum + "\n");
                builder.append("Name: " + name + "\n");
                builder.append("Calories: " + calories + "\n");
                builder.append("Price: " + price + "\n\n");
                this.transactionsMade--;
            }

        }

        change = payment - total;

        builder.append("\n----------------------------\n");
        builder.append("Total: " + total + "\n");
        builder.append("Payment: " + payment + "\n");
        builder.append("Change : " + change + "\n");
        builder.append("\n----------------------------\n");
        
        this.transactionAmount++;
        
        return builder.toString();
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
        
        process.append("Getting Ingridients...\n");
        process.append("Chopping Ingredients..\n");
        process.append("Putting ingredients inblender..\n\n");
        process.append("Blending...\n\n");
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
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public void inputDenomenations(double value){
        int i = 0;
        boolean exists = false;

        while (i < this.storedMoney.length && !exists) {
            if (this.storedMoney[i].getValue() == value) {
                exists = true;
                this.storedMoney[i] = new Money(value, this.storedMoney[i].getAmount()+1);
                sortMoney();
            }
            i++;
        }

        if (!exists) {
            this.storedMoney[this.storedMoneyAmount] = new Money(value, 1);
            this.storedMoneyAmount++;
        }
    }

    //Sorts the money array using selection sort
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
    public boolean inputItems(String name, String type, double price, int quantity, int calories){
        boolean found = false, success = false;
        int row = 0, col = 0, i = 0, j = 0;

        //looks for an empty slot within the occupied range
        while(i < MAXROW && !found){
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
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
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
     * {@inheritDoc}
     * Inherited from <code>InterfaceVendingMachine</code>
     */
    @Override
    public String showNewTransactions(){
        StringBuilder builder = new StringBuilder();
        double sum = 0;
        int i = 0, j = 0, k, count = 0, curQuantity = 0;
        boolean found;

        if(this.transactionList != null && this.transactionAmount != 0){
            for (Transactions transaction : this.transactionList) {
                if(!transaction.getCheck()){
                    transaction.setCheck(true);
                    sum += transaction.getTotal();
                    found = false;

                    count = 0;
                    for (k = 0; k < this.transactionList.size(); k++) {
                        if (this.transactionList.get(k).getNumber() == transaction.getNumber() && this.transactionList.get(k).getItem().getName().equals(transaction.getItem().getName())) {
                            count++;
                        }
                    }

                    i = 0;
                    j = 0;
                    
                    while(i < MAXROW && !found){
                        while (j <  MAXCOL && !found) {
                            if (this.vendoItem[i][j].getProductItem()[0].getName().equals(transaction.getItem().getName())) {
                                curQuantity = this.vendoItem[i][j].getQuantity();        
                                found = true;                 
                            } 
                            j++;
                        }
                        j=0;
                        i++;
                    }

                    builder.append("Transaction #"+ transaction.getNumber() + "\n");
                    builder.append("Name: " + transaction.getItem().getName() + "\n");
                    builder.append("Amount of Items: " + "1 | Before - After (Inventory): " + (curQuantity+count) + " - " + curQuantity + "\n");
                    builder.append("Total: " + transaction.getTotal() + "\n");
                    builder.append("Payment: " + transaction.getPayment() + "\n");
                    builder.append("Change: " + transaction.getChange() + "\n");
                    builder.append("Date and Time: " + transaction.toString() + "\n");
                    builder.append("Is a Product?: " + transaction.getIsProduct() + "\n");
                    builder.append("\n--------------------------------------------------------------\n");
                    this.transactionsMade--;
                    transaction.setCheck(false);
                }
                
            }
            
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
        if(this.transactionList != null && this.transactionAmount != 0){
            
            for (Transactions transaction : this.transactionList) {
                sum += transaction.getTotal();

                builder.append("Transaction #"+ transaction.getNumber() + "\n");
                builder.append("Name: " + transaction.getItem().getName() + "\n");
                builder.append("Total: " + transaction.getTotal() + "\n");
                builder.append("Payment: " + transaction.getPayment() + "\n");
                builder.append("Change: " + transaction.getChange() + "\n");
                builder.append("Date and Time: " + transaction.toString() + "\n");
                builder.append("Is a Product?: " + transaction.getIsProduct() + "\n");
                
                builder.append("\n--------------------------------------------------------------\n");
            }
            builder.append("Total: " + sum + "\n");
        } else {
            builder.append("There are no transactions available to check\n");
        }
        return builder.toString();
    }

    /**
     * Gets the cart of the user
     * @return Usercart
     */
    public ArrayList<ItemsSlots> getUserCart() {
        return this.userCart;
    }

    /**
     * Gets the amount of transaction made in one sale
     * @return transactions made in a sale
     */
    public int getTransactionsMade() {
        return this.transactionsMade;
    }
    
}