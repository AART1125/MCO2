package VendingController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VendingModel.VendingMachineClasses.SpecialVendingMachine;
import VendingView.VendingMachineUI;

public class VendingMachineController {
    private SpecialVendingMachine vendingMachine;
    private VendingMachineUI ui;
    private String currentText;
    
    public VendingMachineController(SpecialVendingMachine vendingMachine, VendingMachineUI ui){
        this.vendingMachine = vendingMachine;
        this.ui = ui;

        this.ui.setItemBtn1Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(ui.getItemsFieldText() == "Input Item Label"){
                    ui.setItemsFieldText("1");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+1);
                    int currentValue = Integer.parseInt(currentText);
                    int newValue = currentValue + 1;
                    ui.setItemsFieldText(String.valueOf(newValue));
                }
            }
                });
                
        this.ui.setItemBtn2Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("2");
                } else {
                    String currentText = ui.getItemsFieldText();
                    int currentValue = Integer.parseInt(currentText);
                    int newValue = currentValue + 2;
                    ui.setItemsFieldText(String.valueOf(newValue));
                }
            }
        });

        this.ui.setItemBtn3Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("3");
                } else {
                    String currentText = ui.getItemsFieldText();
                    int currentValue = Integer.parseInt(currentText);
                    int newValue = currentValue + 3;
                    ui.setItemsFieldText(String.valueOf(newValue));
                }
            }
        });

        this.ui.setItemBtn4Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("4");
                } else {
                    String currentText = ui.getItemsFieldText();
                    int currentValue = Integer.parseInt(currentText);
                    int newValue = currentValue + 4;
                    ui.setItemsFieldText(String.valueOf(newValue));
                }
            }
        });

        this.ui.setItemBtn5Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("5");
                } else {
                    String currentText = ui.getItemsFieldText();
                    int currentValue = Integer.parseInt(currentText);
                    int newValue = currentValue + 5;
                    ui.setItemsFieldText(String.valueOf(newValue));
                }
            }
        });

    }
}
