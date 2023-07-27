package VendingController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

import VendingModel.VendingMachineClasses.SpecialVendingMachine;
import VendingView.MenuUi;
import VendingView.SpecialVendingMachineUI;

public class SpecialVendingMachineController {
    private MenuUi ui2;    
    private SpecialVendingMachine vendingMachine;
    private SpecialVendingMachineUI ui;

    public SpecialVendingMachineController(SpecialVendingMachine vendingMachine, SpecialVendingMachineUI ui, MenuUi ui2){
        this.vendingMachine = vendingMachine;
        this.ui = ui;
        this.ui2 = ui2;

        this.ui.setItemBtn1Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(ui.getItemsFieldText().contains("Input Item Label")) {
                    ui.setItemsFieldText("1");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"1");
                }
            }
        });

        this.ui.setOpenMListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.getMainFrame().setVisible(false);
                ui.getMaintenanceFrame().setVisible(true);
            }
        });

        this.ui.setOpenVListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.getMainFrame().setVisible(true);
                ui.getMaintenanceFrame().setVisible(false);
            }
        });

        this.ui.setExitMListerner(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.getMainFrame().setVisible(false);
                ui2.getMainFrame().setVisible(true);
            }
        });

        this.ui.setExitVListerner(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.getMainFrame().setVisible(false);
                ui2.getMainFrame().setVisible(true);
            }
        });
    }
}
