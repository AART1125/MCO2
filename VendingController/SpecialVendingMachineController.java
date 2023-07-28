package VendingController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

import VendingModel.VendingMachineClasses.SpecialVendingMachine;
import VendingView.MenuUi;
import VendingView.SpecialVendingMachineUI;

public class SpecialVendingMachineController {
    private MenuUi menu;    
    private SpecialVendingMachine machine;
    private SpecialVendingMachineUI ui;

    public SpecialVendingMachineController(SpecialVendingMachine machine, SpecialVendingMachineUI ui, MenuUi menu){
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
                    int currentValue = Integer.parseInt(currentText);
                    String newValue = currentValue + "B";
                    ui.setItemsFieldText(String.valueOf(newValue));
                }
            }
        });

        this.ui.setItemBtnCListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("C");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"C");
                    int currentValue = Integer.parseInt(currentText);
                    String newValue = currentValue + "C";
                    ui.setItemsFieldText(String.valueOf(newValue));
                }
            }
        });

        this.ui.setItemBtnDListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("D");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"D");
                    int currentValue = Integer.parseInt(currentText);
                    String newValue = currentValue + "D";
                    ui.setItemsFieldText(String.valueOf(newValue));
                }
            }
        });

        this.ui.setItemBtnEListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("E");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"E");
                    int currentValue = Integer.parseInt(currentText);
                    String newValue = currentValue + "E";
                    ui.setItemsFieldText(String.valueOf(newValue));
                }
            }
        });

        this.ui.setItemBtnFListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("F");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"F");
                    int currentValue = Integer.parseInt(currentText);
                    String newValue = currentValue + "F";
                    ui.setItemsFieldText(String.valueOf(newValue));
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
