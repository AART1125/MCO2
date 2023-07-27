package VendingController;

import VendingModel.VendingMachineClasses.RegularVendingMachine;
import VendingView.RegularVendingMachineUI;

public class RegularVendingMachineController {
    private RegularVendingMachineUI ui;
    private RegularVendingMachine machine;

    public RegularVendingMachineController(RegularVendingMachine machine, RegularVendingMachineUI ui){
        this.ui = ui;
        this.machine = machine;
    }
}
