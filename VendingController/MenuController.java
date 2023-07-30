package VendingController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VendingModel.VendingMachineClasses.RegularVendingMachine;
import VendingModel.VendingMachineClasses.SpecialVendingMachine;
import VendingView.MenuUi;
import VendingView.RegularVendingMachineUI;
import VendingView.SpecialVendingMachineUI;

public class MenuController {
    private MenuUi ui;

    private SpecialVendingMachine spcMachine;
    private SpecialVendingMachineUI spcUi;
    private SpecialVendingMachineController spcController;

    private RegularVendingMachine regMachine;
    private RegularVendingMachineUI regUi;
    private RegularVendingMachineController regController;

    public MenuController(MenuUi ui){
        this.ui = ui;

        this.ui.setRegBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (spcMachine != null) {
                    spcMachine = null;
                }

               regMachine = new RegularVendingMachine();
               regMachine.initialization(regMachine.getStoredMoney());
               regMachine.initialization(regMachine.getUserMoney());
               regMachine.initialization(regMachine.getVendoItem());
               ui.getMainFrame().setContentPane(ui.getMainPanel());
            }
        });

        this.ui.setSpcBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (regMachine != null) {
                    regMachine = null;
                }

               spcMachine = new SpecialVendingMachine();
               spcMachine.initialization(spcMachine.getStoredMoney());
               spcMachine.initialization(spcMachine.getUserMoney());
               spcMachine.initialization(spcMachine.getVendoItem());
               ui.getMainFrame().setContentPane(ui.getMainPanel());
            }
        });

        this.ui.setCreateBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.getMainFrame().setContentPane(ui.getOptionsPanel());
                ui.getMainFrame().revalidate();
            }
        });

        this.ui.setTestBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e ){
                if (spcMachine != null && regMachine == null) {
                    spcUi = new SpecialVendingMachineUI();
                    spcController = new SpecialVendingMachineController(spcMachine, spcUi, ui);
                    ui.getMainFrame().setVisible(false);
                    
                    
                } else if (regMachine != null && spcMachine == null){
                    regUi = new RegularVendingMachineUI(regMachine);
                    regController = new RegularVendingMachineController(regMachine, regUi, ui);
                    ui.getMainFrame().setVisible(false);
                }
            }
        });

        this.ui.setExitBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.getMainFrame().dispose();
            }
        });
    }

}
