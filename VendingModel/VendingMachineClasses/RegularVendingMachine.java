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
public class RegularVendingMachine extends VendingMachine implements InterfaceVendingMachine, InterfaceVendingMachineMaintenance{

    /**
     * A <code>RegularVendingMachine</code> constructor that calls on the contructor of the parent class
     */
    public RegularVendingMachine(){
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
            System.out.println("\nFile can't be read....");
        }
    }

    /**
     * A method that creates/overwrite a file for the <code>Items</code> in the program
     */
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
    public String display(){
        int i, j;
        StringBuilder build = new StringBuilder();
        build.append("------------------------------------------------------------------\n");
        build.append("| Slot |         Name       |   Price  |  Quantity  |  Calories  |\n");
        build.append("------------------------------------------------------------------\n");
        for(i = 0; i < MAXROW; i++)
            for(j = 0; j < MAXCOL; j++){
                if(this.vendoItem[i][j].getProductItem()[0] != null && this.vendoItem[i][j].getProductItem()[0].getName() != null){
                    build.append(String.format("|%-6s|", this.vendoItem[i][j].getLabel()));
                    build.append(String.format("%-20s|", this.vendoItem[i][j].getProductItem()[0].getName()));
                    build.append(String.format("P%9.2f|", this.vendoItem[i][j].getPrice()));
                    build.append(String.format("%12d|", this.vendoItem[i][j].getQuantity()));
                    build.append(String.format("%10d g|\n", this.vendoItem[i][j].getProductItem()[0].getCalories()));
                }
            }
        build.append("------------------------------------------------------------------\n");

        return build.toString();
    }

    /**
     * This method collects the money of the user and add it in the machines userMoney array
     * @param userMoney User's money in the machine
     */
    public void collectMoney(Money[] userMoney){
        boolean adding = true;
        int amount;
        double value;
        char choice;
        Money tempMoney;
        
        while(adding){
            do{
                System.out.print("\nEnter value: ");
                value = sc.nextDouble();
            } while(value != 1 && value != 5 && value != 10 && value != 20 && value != 50 && 
                    value != 100 && value != 200 && value != 500 && value != 1000);// get value
            
            do{
                System.out.print("\nEnter amount of denominations: ");
                amount = sc.nextInt();
            } while(amount < 0);// get amount
            
            System.out.print("\nAre you still adding? y/n: ");
            choice = sc.next().charAt(0);// prompt if adding more denominations
            
            if(value > 0 && amount > 0){
                tempMoney = new Money(value, amount);

                userMoney[this.currentMon] = tempMoney;
                this.currentMon++;

                switch(choice){
                    case 'Y':
                        case 'y':
                            adding = true;
                            break;
                    case 'N':
                        case 'n':
                            adding = false;
                            break;
                    default:
                        adding = false;
                        System.out.println("Invalid input! Returning to main");
                }
            } else {
                System.out.println("Invalid value inputted");
            }
        }
    }

    /**
     * This method is where the buying of an item will be done
     * @param userMoneys User's Money
     * @return true or false
     */
    public boolean buyItem(Money[] userMoneys) {
        boolean success = false;
        int row, col, origQuantity;
        double change, price, payment = total(userMoney);
        char choice;
        String slotLabel = null;
        ItemsSlots slot;
        
        System.out.print("Enter the label of the item (e.g., A1, B2, C3): ");
        slotLabel = sc.next().toUpperCase();

        row = slotLabel.charAt(0) - 'A';
        col = Integer.parseInt(slotLabel.substring(1)) - 1;

        if ((row >= 0 && row < MAXROW) && (col >= 0 && col < MAXCOL)) {// check if valid
            slot = vendoItem[row][col];

            if (slot != null && slot.getProductItem()[0].getName() != null) {
                //final check if user want to continue with the transactions
                if (isItemSellable(slot.getProductItem()[0])) {// checks if item is sellable
                    price = slot.getPrice();

                    System.out.println("Item details:\n");
                    System.out.println("Name: " + slot.getProductItem()[0].getName());
                    System.out.println("Price: " + price);

                    System.out.print("\nConfirm payment (Y/N): ");
                    choice = sc.next().charAt(0);

                    if (choice == 'Y' || choice == 'y') {
                        if (total(userMoney) >= price) {
                            change = produceChange(userMoney, price);// produce change in different denominations
                            if(change >= 0 && total(this.storedMoney) > 0){
                                success = true;
                                //create transaction
                                generateTransactions(slot.getProductItem()[0], slot.getPrice(), payment, change);

                                //decrease item from slot
                                origQuantity = slot.getQuantity();
                                slot.decreaseQuantity(1);
                                slot.decreaseItemsFromSlot(slot.getProductItem(), origQuantity);

                                System.out.println("Item purchased successfully!");

                                //dispense change
                                if(change > 0)
                                    dispenseChange(userMoneys);
                        
                                this.salesWasDone = true;
                            } else {
                                System.out.println("Insufficient denominations to produce change.");
                                dispenseChange(userMoneys);
                            }
                        } else {
                            System.out.println("\nInsufficient funds.");
                            dispenseChange(userMoneys);
                        }
                    } else {
                        System.out.println("Transaction canceled.");
                        dispenseChange(userMoneys);
                        }
                } else {
                    System.out.println("Item chosen is not sellable!");
                    dispenseChange(userMoneys);
                }
            } else {
                System.out.println("Invalid slot label inputted!");
                dispenseChange(userMoneys);
            }
        } else {
            System.out.println("Invalid slot label.");
            dispenseChange(userMoneys);
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
           System.out.println("Insufficient funds");
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
     * This method generates the transaction of the sale. It is used in the buyItems method
     * @param item Items being bought
     * @param total Total price
     * @param payment Payment for item
     * @param change Change from payment
     */
    private void generateTransactions(Items item, double total, double payment, double change){

        Transactions transactions = new Transactions(total, payment, change, item, this.transactionAmount + 1);

        this.transactionList.add(transactions);

        if(this.transactionList.get(this.transactionAmount) != null){

            System.out.println("\n\nTransaction #" + this.transactionList.get(this.transactionAmount).getNumber());
            System.out.println("----------------------------------------------");
            System.out.println("|         Name       |  Calories  |   Total  |");
            System.out.println("----------------------------------------------");

            System.out.print(String.format("|%-20s|", this.transactionList.get(this.transactionAmount).getItem().getName()));
            System.out.print(String.format("%10d g|", this.transactionList.get(this.transactionAmount).getItem().getCalories()));
            System.out.print(String.format("P%9.2f|\n", this.transactionList.get(this.transactionAmount).getTotal()));
            System.out.println("----------------------------------------------\n");
            System.out.println(String.format("Total  : P%9.2f", this.transactionList.get(this.transactionAmount).getTotal()));
            System.out.println(String.format("Payment: P%9.2f", this.transactionList.get(this.transactionAmount).getPayment()));
            System.out.println(String.format("Change : P%9.2f", change));
            System.out.println("----------------------------------------------\n");
            
            this.transactionAmount++;
        }
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
     * @param userMoney User's Money
     */
    public void dispenseChange(Money[] userMoneys){
        int i;
        for(i = 0; i < userMoneys.length; i++){
            if(userMoneys[i].getValue() > 0){
                System.out.println("\nDispensing change: P" + userMoneys[i].getValue() + "| Amount : " + userMoneys[i].getAmount());
                this.currentMon--;
            }
            
            userMoneys[i] = new Money();
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
        if(this.storedMoney[0].getValue() == 0){
            for(i = 0; i < DENOMINATIONS; i++){

            switch(i){
                case 0: value = 1; break;
                case 1: value = 5; break;
                case 2: value = 10; break;
                case 3: value = 20; break;
                case 4: value = 50; break;
                case 5: value = 100; break;
                case 6: value = 200; break;
                case 7: value = 500; break;
                case 8: value = 1000; break;
            }

            do {
                System.out.printf("\nInput the amount of P%.2f: ", value);
            } while (value < 0);
            
            tempAmount = sc.nextInt();
            tempMoney = new Money(value, tempAmount);
            this.storedMoney[i] = tempMoney;
            }
        } else {
            for(i = 0; i < DENOMINATIONS; i++){

            switch(i){
                case 0: value = 1; break;
                case 1: value = 5; break;
                case 2: value = 10; break;
                case 3: value = 20; break;
                case 4: value = 50; break;
                case 5: value = 100; break;
                case 6: value = 200; break;
                case 7: value = 500; break;
                case 8: value = 1000; break;
            }

            System.out.printf("\nCurrent amount of P%.2f %d, add: ", value, this.storedMoney[i].getAmount());
            do {
                tempAmount = sc.nextInt();
            } while (tempAmount < 0);
            tempAmount += this.storedMoney[i].getAmount();
            this.storedMoney[i] = new Money(value, tempAmount);
            }
        }
    }

    /**
     * This method checks the amount of every denomination left in the machine
     */
    public void checkDenom(){
        if(total(this.storedMoney) > 0){
            System.out.println("\n------------------------");
            System.out.println("|    Value    | Amount |");
            System.out.println("------------------------");
            for (Money money : this.storedMoney) {
                if (money.getValue() > 0) {
                    System.out.print(String.format("|P%12.2f|", money.getValue()));
                    System.out.println(String.format("%8d|", money.getAmount()));
                    System.out.println("------------------------");
                }
            }
        } else {
            System.out.println("\nThere is no money available in the machine");    
        }
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

        //if found, fill empty slot, if not add to new slot
        if(found){
            System.out.println("Empty slot found! Add an item into this slot\n");

            System.out.print("\nInput item name: ");
            this.vendoItem[row][col].getProductItem()[0].setName(sc.next());

            System.out.print("\nInput item price: ");
            this.vendoItem[row][col].setPrice(sc.nextDouble());

            System.out.print("\nInput item type: ");
            this.vendoItem[row][col].getProductItem()[0].setItemType(sc.next());

            do {
                System.out.print("\nInput item quantity: ");
                this.vendoItem[row][col].setQuantity(sc.nextInt());
            } while (this.vendoItem[row][col].getQuantity() > MAXITEMS);

            this.vendoItem[row][col].setProductItems(this.vendoItem[row][col].getProductItem()[0]);

            System.out.print("Input item calories: ");
            this.vendoItem[row][col].getProductItem()[0].setCalories(sc.nextInt());
        }
        else{
            while (!finish && this.occupiedRow < MAXROW){

                System.out.print("\nInput item name: ");
                this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].getProductItem()[0].setName(sc.next());
        
                System.out.print("\nInput item price: ");
                this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].setPrice(sc.nextDouble());
        
                System.out.print("\nInput item type: ");
                this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].getProductItem()[0].setItemType(sc.next());
        
                do {
                    System.out.print("\nInput item quantity: ");
                    this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].setQuantity(sc.nextInt());
                } while (this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].getQuantity() > MAXITEMS);
        
                System.out.print("\nInput item calories: ");
                this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].getProductItem()[0].setCalories(sc.nextInt());
        
                this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].setProductItems(this.vendoItem[this.occupiedRow][this.occupiedSlots % MAXCOL].getProductItem()[0]);
        
                this.occupiedSlots++;
        
                if(this.occupiedSlots > 0 && this.occupiedSlots % 5 == 0){
                    this.occupiedRow++;
                }
                
                System.out.print("Do you want to add another item? y/n: ");
                input = sc.next().charAt(0);
                System.out.print("\n");
        
                if(input == 'y'||input == 'Y')
                    finish = false;
                else if(input == 'n'||input == 'N')
                    finish = true;
                else {
                    System.out.println("Invalid Input! Going back to maintenance screen");
                    finish = true;
                }
            }
        }
    }

    /**
     * This method changes the price of <code>Items</code>
     */
    public void changePrice(){
        int row, col;
        double newPrice;
        String slotLabel;

        System.out.print("Enter label of the item (e.g., A1, B2, C3): ");
        slotLabel = sc.next().toUpperCase();

        row = slotLabel.charAt(0) - 'A';
        col = Integer.parseInt(slotLabel.substring(1)) - 1;

        if(this.vendoItem[row][col] != null && this.vendoItem[row][col].getProductItem()[0].getName() != null){
            
            do {
                System.out.print("Enter new price: ");
                newPrice = sc.nextDouble();
            } while (newPrice < 0);

            this.vendoItem[row][col].setPrice(newPrice);
            System.out.println("\nChange Price is successful!");
        } else
            System.out.println("\nChange Price is unsuccessful!");
    } 

    /**
     * This method decreases the <code>Items</code> in a <code>ItemsSlots</code> array
     * @param itemArr Item Slot
     */
    public void decreaseItem(ItemsSlots[][] itemArr) {
        int row, col, qnty, origQuantity;
        String slotLabel;
        ItemsSlots item;

        System.out.print("Enter label of the item (e.g., A1, B2, C3): ");
        slotLabel = sc.next().toUpperCase();
       
        row = slotLabel.charAt(0) - 'A';
        col = Integer.parseInt(slotLabel.substring(1)) - 1;
        
        origQuantity = itemArr[row][col].getQuantity();

        if (row >= 0 && row < itemArr.length && col >= 0 && col < itemArr[row].length) {
            do{
                System.out.print("Enter quantity to decrease: ");
                qnty = sc.nextInt();
            } while(qnty < 0);

            item = itemArr[row][col];
            if (item != null && item.getQuantity() > 0) {
                item.decreaseQuantity(qnty);
                item.decreaseItemsFromSlot(item.getProductItem(), origQuantity);

                if(item.getQuantity() == 0){// Essentially an item is removed from its slot
                    this.occupiedSlots--;
                    item.setPrice(0);
                }
                System.out.println("Item successfully decreased.");
            } else {
                System.out.println("Item not available or has no quantity.");
            }
        } else {
            System.out.println("Invalid Slot.");
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
                System.out.println("File can't be read....");
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
                if(contentFile.exists())
                    System.out.println("\nCollection is done!");
                else
                    System.out.println("\nCollection is done!");
            } catch (IOException e) {
                System.out.println("An error occured....");
            }
        } else {
            System.out.println("No money to collect");
        }
    }

    /**
     * This method shows the newest <code>Transactions</code> that the maintenance hasn't seen yet
     */
    public void showNewTransactions(){
        double sum = 0;

        if(this.transactionList != null && this.transactionAmount != 0){
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("|  TR#  |         Name       |   Total  |  Payment |  Change  |     Date     |");
            System.out.println("------------------------------------------------------------------------------");
            for (Transactions transaction : this.transactionList) {
                if(!transaction.getCheck()){
                    sum += transaction.getTotal();

                    System.out.print(String.format("|%7d|", transaction.getNumber()));
                    System.out.print(String.format("%20s|", transaction.getItem().getName()));
                    System.out.print(String.format("P%9.2f|", transaction.getTotal()));
                    System.out.print(String.format("P%9.2f|", transaction.getPayment()));
                    System.out.print(String.format("P%9.2f|", transaction.getChange()));
                    System.out.print(String.format("%14s|\n", transaction.toString()));
                    System.out.println("------------------------------------------------------------------------------");
                    transaction.setCheck(true);
                }
            }
            System.out.println("Total: " + sum + "\n");
        } else {
            System.out.println("There are no transactions available to check");
        }
    }

    /**
     * This method shows the all the <code>Transactions</code> made
     */
    public void showTransactions(){
        double sum = 0;
        if(this.transactionList != null && this.transactionAmount != 0){
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("|  TR#  |         Name       |   Total  |  Payment |  Change  |     Date     |");
            System.out.println("------------------------------------------------------------------------------");
            for (Transactions transaction : this.transactionList) {
                sum += transaction.getTotal();

                System.out.print(String.format("|%7d|", transaction.getNumber()));
                System.out.print(String.format("%20s|", transaction.getItem().getName()));
                System.out.print(String.format("P%9.2f|", transaction.getTotal()));
                System.out.print(String.format("P%9.2f|", transaction.getPayment()));
                System.out.print(String.format("P%9.2f|", transaction.getChange()));
                System.out.print(String.format("%14s|\n", transaction.toString()));
                System.out.println("------------------------------------------------------------------------------");
            }
            System.out.println("Total: " + sum + "\n");
        } else {
            System.out.println("There are no transactions available to check");
        }
    }

}