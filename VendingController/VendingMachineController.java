package VendingController;

import VendingModel.VendingMachineClasses.SpecialVendingMachine;
import VendingView.VendingMachineUI;

public class VendingMachineController {
    private SpecialVendingMachine vendingMachine;
    private VendingMachineUI ui;

    public VendingMachineController(SpecialVendingMachine vendingMachine, VendingMachineUI ui){
        this.vendingMachine = vendingMachine;
        this.ui = ui;
    }
}
