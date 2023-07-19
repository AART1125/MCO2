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
        System.out.println("\nAn error occurred.");
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
        System.out.println("\nFile can't be read....");
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
        System.out.println("\nAn error occurred.");
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
        System.out.println("\nFile can't be read....");
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
        System.out.println("An error occurred.");
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
     * Displays the available items in the <code>ItemsSlots</code> array of the machine
     * @return Display of items
     */
    public String display() {
        int j;
        int i;
        StringBuilder build = new StringBuilder();
        build.append("------------------------------------------------------------------\n");
        build.append("| Slot |         Name       |   Price  |  Quantity  |  Calories  |\n");
        build.append("------------------------------------------------------------------\n");
        for (i = 0; i < MAXROW; i++) {
            for (j = 0; j < MAXCOL; j++) {
                if (this.vendoItem[i][j].getProductItem()[0] != null && this.vendoItem[i][j].getProductItem()[0].getName() != null) {
                    build.append(String.format("|%-6s|", this.vendoItem[i][j].getLabel()));
                    build.append(String.format("%-20s|", "")); // omit itemName
                    build.append(String.format("P%9.2f|", this.vendoItem[i][j].getPrice()));
                    build.append(String.format("%12d|", this.vendoItem[i][j].getQuantity()));
                    build.append(String.format("%10d g|\n", 0)); // omit calorie amount
            }
        }
    }
    build.append("------------------------------------------------------------------\n");

    return build.toString();
}
    
    /**
     * This method shows the all the <code>Transactions</code> made
     */
    public void showTransactions() {
    double sum = 0;
    if (this.transactionList != null && this.transactionAmount != 0) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|  TR#  |         Name       |   Total  |  Payment |  Change  |     Date     |");
        System.out.println("------------------------------------------------------------------------------");
        for (Transactions transaction : this.transactionList) {
            sum += transaction.getTotal();

            System.out.print(String.format("|%7d|", transaction.getNumber()));
            System.out.print(String.format("%20s|", "")); // omit itemName
            System.out.print(String.format("P%9.2f|", transaction.getTotal()));
            System.out.print(String.format("P%9.2f|", transaction.getPayment()));
            System.out.print(String.format("P%9.2f|", transaction.getChange()));
            System.out.print(String.format("%14s|\n", "")); // transaction string
            System.out.println("------------------------------------------------------------------------------");
        }
        System.out.println("Total: " + sum + "\n");
    } else {
        System.out.println("There are no transactions available to check");
    }
}
    
 /**
     * This method shows the newest <code>Transactions</code> that the maintenance hasn't seen yet
     */
    public void showNewTransactions() {
    double sum = 0;

    if (this.transactionList != null && this.transactionAmount != 0) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|  TR#  |         Name       |   Total  |  Payment |  Change  |     Date     |");
        System.out.println("------------------------------------------------------------------------------");
        for (Transactions transaction : this.transactionList) {
            if (!transaction.getCheck()) {
                sum += transaction.getTotal();

                System.out.print(String.format("|%7d|", transaction.getNumber()));
                System.out.print(String.format("%20s|", "")); // omit itemName
                System.out.print(String.format("P%9.2f|", transaction.getTotal()));
                System.out.print(String.format("P%9.2f|", transaction.getPayment()));
                System.out.print(String.format("P%9.2f|", transaction.getChange()));
                System.out.print(String.format("%14s|\n", "")); // transaction string
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
     * This method checks the amount of every denomination left in the machine
     */
    public void checkDenom() {
    if (total(this.storedMoney) > 0) {
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
     * This method collects the money of the user and add it in the machines userMoney array
     * @param userMoney User's money in the machine
     */
    public void collectMoney(Money[] userMoney) {
    boolean adding = true;
    int amount;
    double value;
    char choice;
    Money tempMoney;

    while (adding) {
        do {
            // omit input values
        } while (value != 1 && value != 5 && value != 10 && value != 20 && value != 50 &&
                value != 100 && value != 200 && value != 500 && value != 1000);// get value

        do {
            // omit input values
        } while (amount < 0);// get amount

        // Prompt if adding more denominations
        // omit input values

        if (value > 0 && amount > 0) {
            tempMoney = new Money(value, amount);

            userMoney[this.currentMon] = tempMoney;
            this.currentMon++;

            switch (choice) {
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

    // omit slotLabel value
    slotLabel = slotLabel.toUpperCase();

    row = slotLabel.charAt(0) - 'A';
    col = Integer.parseInt(slotLabel.substring(1)) - 1;

    if ((row >= 0 && row < MAXROW) && (col >= 0 && col < MAXCOL)) {// check if valid
        slot = vendoItem[row][col];

        if (slot != null && slot.getProductItem()[0].getName() != null) {
            //final check if user want to continue with the transactions
            if (isItemSellable(slot.getProductItem()[0])) {// checks if item is sellable
                price = slot.getPrice();

                
                System.out.println("Item details:\n");
                System.out.println("Name: " + "");
                System.out.println("Price: " + price);

                // omit input of choice value

                if (choice == 'Y' || choice == 'y') {
                    if (total(userMoney) >= price) {
                        change = produceChange(userMoney, price);// produce change in different denominations
                        if (change >= 0 && total(this.storedMoney) > 0) {
                            success = true;

                            generateTransactions(slot.getProductItem()[0], slot.getPrice(), payment, change);

                            origQuantity = slot.getQuantity();
                            slot.decreaseQuantity(1);
                            slot.decreaseItemsFromSlot(slot.getProductItem(), origQuantity);

                            System.out.println("Item purchased successfully!");
                            if (change > 0)
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
     * This method dispenses the change of the user
     * @param userMoney User's Money
     */
    public void dispenseChange(Money[] userMoneys) {
    for (int i = 0; i < userMoneys.length; i++) {
        if (userMoneys[i].getValue() > 0) {
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
    
}
