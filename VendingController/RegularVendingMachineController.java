package VendingController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import VendingModel.VendingMachineClasses.RegularVendingMachine;
import VendingView.MenuUi;
import VendingView.RegularVendingMachineUI;

public class RegularVendingMachineController {
    private RegularVendingMachineUI ui;
    private RegularVendingMachine machine;
    private MenuUi menu;

    public RegularVendingMachineController(RegularVendingMachine machine, RegularVendingMachineUI ui, MenuUi menu){
        this.machine = machine;
        this.ui = ui;
        this.menu = menu;
        String currentText = ui.getItemsFieldText();

        this.ui.setCashBtn1Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                machine.collectMoney(1);
                ui.setCashFieldText("P "+machine.total(machine.getUserMoney()));
                ui.getMainFrame().revalidate();
            }
        });

        this.ui.setCashBtn2Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                machine.collectMoney(5);
                ui.setCashFieldText("P "+machine.total(machine.getUserMoney()));
                ui.getMainFrame().revalidate();
            }
        });

        this.ui.setCashBtn3Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                machine.collectMoney(10);
                ui.setCashFieldText("P "+machine.total(machine.getUserMoney()));
                ui.getMainFrame().revalidate();
            }
        });

        this.ui.setCashBtn4Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                machine.collectMoney(20);
                ui.setCashFieldText("P "+machine.total(machine.getUserMoney()));
                ui.getMainFrame().revalidate();
            }
        });

        this.ui.setCashBtn5Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                machine.collectMoney(50);
                ui.setCashFieldText("P "+machine.total(machine.getUserMoney()));
                ui.getMainFrame().revalidate();
            }
        });

        this.ui.setCashBtn6Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                machine.collectMoney(100);
                ui.setCashFieldText("P "+machine.total(machine.getUserMoney()));
                ui.getMainFrame().revalidate();
            }
        });

        this.ui.setCashBtn7Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                machine.collectMoney(200);
                ui.setCashFieldText("P "+machine.total(machine.getUserMoney()));
                ui.getMainFrame().revalidate();
            }
        });

        this.ui.setCashBtn8Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                machine.collectMoney(500);
                ui.setCashFieldText("P "+machine.total(machine.getUserMoney()));
                ui.getMainFrame().revalidate();
            }
        });

        this.ui.setCashBtn9Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                machine.collectMoney(1000);
                ui.setCashFieldText("P "+machine.total(machine.getUserMoney()));
                ui.getMainFrame().revalidate();
            }
        });

        this.ui.setItemBtn1Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(ui.getItemsFieldText().equals("Input Item Label")){
                    ui.setItemsFieldText("1");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+1);
                }
            }
                });
                
        this.ui.setItemBtn2Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("2");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+2);
                }
            }
        });

        this.ui.setItemBtn3Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("3");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+3);
                }
            }
        });

        this.ui.setItemBtn4Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("4");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+4);
                }
            }
        });

        this.ui.setItemBtn5Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("5");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+5);
                }
            }
        });

        this.ui.setItemBtnAListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("A");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"A");
                }
            }
        });

        this.ui.setItemBtnBListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("B");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"B");
                }
            }
        });

        this.ui.setItemBtnCListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("C");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"C");
                }
            }
        });

        this.ui.setItemBtnDListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("D");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"D");
                }
            }
        });

        this.ui.setItemBtnEListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("E");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"E");
                }
            }
        });

        this.ui.setItemBtnFListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("F");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"F");
                }
            }
        });

        this.ui.setItemBtnConListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String label = ui.getItemsFieldText();
                boolean success = machine.buyItem(label);
                if (success) {
                    ui.clearItemsField();
                    ui.setReceiptText(machine.display());
                    ui.getMainFrame().revalidate();
                } else {
                    ui.clearItemsField();
                }
            }
        });

        this.ui.setItemBtnCanListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.clearItemsField();
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
                ui.getMainFrame().dispose();
                ui.getMaintenanceFrame().dispose();
                menu.getMainFrame().setVisible(true);
            }
        });

        this.ui.setExitVListerner(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.getMainFrame().dispose();
                ui.getMaintenanceFrame().dispose();
                menu.getMainFrame().setVisible(true);
            }
        });
    }
}
