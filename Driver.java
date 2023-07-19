import java.util.Scanner;

import VendingController.VendingMachineController;
import VendingModel.VendingMachineClasses.RegularVendingMachine;
import VendingModel.VendingMachineClasses.SpecialVendingMachine;
import VendingView.VendingMachineUI;

public class Driver {
    static boolean UserinUse = false, MaintenanceinUse = false, VendinginUse = false, MaininUse = true;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice = 0, choice2 = 0;
        RegularVendingMachine regularMachine = null;
        SpecialVendingMachine specialMachine = null;

        while (MaininUse) {
            System.out.println("\nSelect an option: ");
            System.out.println("1. Create a regular vending machine ");
            System.out.println("2. Test vending machine ");
            System.out.println("3. Exit");
            choice = sc.nextInt();

            switch(choice){
                case 1: 
                        System.out.println("\nSelect a Vending Machine: ");
                        System.out.println("\n1. Regular Vending Machine ");
                        System.out.println("\n2. Special Vending Machine ");

                        choice2 = sc.nextInt();
                        switch (choice2) {
                            case 1:
                                regularMachine = createRegularVendingMachine();
                                specialMachine = null;
                                break;

                            case 2:
                                specialMachine = createSpecialVendingMachine();
                                regularMachine = null;
                                break;
                        
                            default:
                                System.out.println("Invalid Input! Please try again");
                                break;
                        }
                        break;

                case 2: VendinginUse = true; 
                        if(regularMachine != null)
                            testVendingMachine(regularMachine);
                        else if(regularMachine == null && specialMachine != null)
                            testVendingMachine(specialMachine);
                        else
                            System.out.println("Vending Machine is not initialized please try again");    
                        break;

                case 3: MaininUse = false;
                        regularMachine.fileItemWrite();
                        regularMachine.fileMoneyWrite();
                        regularMachine.fileTransactionWrite();
                        sc.close();
                        break;
            }
        }
        
    }

    static RegularVendingMachine createRegularVendingMachine(){
        RegularVendingMachine machine = new RegularVendingMachine();

        machine.initialization(machine.getVendoItem());
        machine.initialization(machine.getUserMoney());
        machine.initialization(machine.getStoredMoney());
        machine.fileItemScan();
        machine.fileMoneyScan();
        machine.fileTransactionScan();
        
        System.out.println("\nCreation is successful");

        return machine;
    }

    static SpecialVendingMachine createSpecialVendingMachine(){
        SpecialVendingMachine machine = new SpecialVendingMachine();

        machine.fileItemScan();
        machine.fileMoneyScan();
        machine.fileTransactionScan();
        
        System.out.println("\nCreation is successful");

        return machine;
    }

    static void testVendingMachine(RegularVendingMachine machine){
        int choice;
        if (machine != null) {
            while(VendinginUse){
                System.out.println("\nWelcome to the Smoothie Maker!\n");
                if(machine.getOccupiedSlots() > 0){
                    System.out.println("Here are the options of fruits, veggies, milks, and add-ons");
                    System.out.println(machine.display());
                }
                
                System.out.println("Hello! please choose an option: ");
                System.out.println("1. Vending features ");
                System.out.println("2. Maintenance features");
                System.out.println("3. Exit ");

                choice = sc.nextInt();
                
                switch(choice){
                    case 1:{
                        if(machine.getOccupiedSlots() > 0 && machine.total(machine.getStoredMoney()) > 0){
                            UserinUse = true;
                            while(UserinUse)
                                Usermenu(machine);
                            choice = 0;
                        } else {
                            if(machine.total(machine.getStoredMoney()) == 0){
                                System.out.println("Machine has no money in it please wait for maintenance to add it");
                            } else {
                                System.out.println("Machine has no Items yet");
                            }
                        }
                        break;
                    }
                    case 2:{
                        MaintenanceinUse = true;
                        while(MaintenanceinUse)
                            MaintenanceMenu(machine);
                        choice = 0;
                        break;
                    }
                    case 3:{
                        VendinginUse = false;
                        break;
                    }
                    default: 
                        break;
                }
            }    
        } else{
            System.out.println("Vending machine has not been created yet!");
            VendinginUse = false;
        }
        
    }

    static void testVendingMachine(SpecialVendingMachine machine){
        VendingMachineUI ui = new VendingMachineUI();
        VendingMachineController controller = new VendingMachineController(machine, ui);
    }

    static void Usermenu(RegularVendingMachine machine){
        int input;

        System.out.print("\n" + machine.display());
        
        System.out.println("Hello dear customer! Please input money: ");
        System.out.println("(Value : 1,5,10,20,etc.)");
        machine.collectMoney(machine.getUserMoney());

        System.out.print(machine.display());
        System.out.println("\nHello dear customer! How will we serve you today?: ");
        System.out.println("\nCurrent money: |P" + machine.total(machine.getUserMoney()) + "|\n");
        System.out.println("1. Buy Item");
        System.out.println("2. Exit");

        input = sc.nextInt();

        switch(input){
            case 1:;
                    if(!machine.buyItem(machine.getUserMoney())){
                        UserinUse = false;
                    } else{
                        machine.fileTransactionWrite();
                        UserinUse = false;
                    }
                    break;
            case 2:
                   machine.dispenseChange(machine.getUserMoney());
                   UserinUse = false; break;
        }
        
    }

    static void MaintenanceMenu(RegularVendingMachine machine){
        int input;

        if(machine.getSalesWasDone()){
            System.out.println("\nA transaction was done!");
            machine.showNewTransactions();
            machine.setSalesWasDone(false);
        }

        if(machine.total(machine.getStoredMoney()) > 0)
            System.out.println("\nTotal money stored in vending machine is: |P" + machine.total(machine.getStoredMoney()) + "|\n");

        System.out.println("\nHello boss! please choose an option: ");
        System.out.println("1. Check money");
        System.out.println("2. Input money");
        System.out.println("3. Input Items");
        System.out.println("4. Change item price");
        System.out.println("5. Decrease/Delete Items");
        System.out.println("6. Collect Money");
        System.out.println("7. Transactions");
        System.out.println("8. Exit");

        input = sc.nextInt();

        switch(input){
            case 1: machine.checkDenom(); break;
            case 2: machine.inputDenomenations();  break;
            case 3: machine.inputItems();  break;
            case 4: machine.changePrice(); break;
            case 5: machine.decreaseItem(machine.getVendoItem()); break;
            case 6: machine.collectMoneyInMachine(); break;
            case 7: machine.showTransactions(); break;
            case 8: MaintenanceinUse = false; break;
        }
        machine.fileItemWrite();
        machine.fileMoneyWrite();
        machine.fileTransactionWrite();
    }
}
