package VendingController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VendingModel.VendingMachineClasses.SpecialVendingMachine;
import VendingView.VendingMachineUI;

public class VendingMachineController {
    private SpecialVendingMachine vendingMachine;
    private VendingMachineUI ui;

    public VendingMachineController(SpecialVendingMachine vendingMachine, VendingMachineUI ui){
        this.vendingMachine = vendingMachine;
        this.ui = ui;

        this.ui.setItemBtn1Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(ui.getItemsFieldText() == "Input Item Label"){
                    ui.setItemsFieldText("1");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+1);
                }
            }
        });
    }
}
