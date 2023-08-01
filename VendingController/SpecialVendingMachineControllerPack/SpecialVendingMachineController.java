package VendingController.SpecialVendingMachineControllerPack;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import VendingModel.VendingMachineClasses.SpecialVendingMachine;
import VendingView.MenuUIPack.MenuUi;
import VendingView.SpecialVendingMachineUIPack.SpecialVendingMachineUI;

/**
 * This is the controller for the Special Vending Machine program. This contains the ui, machine processes, as well as the initial menu ui.
 * All the function calls for the program are set here.
 */
public class SpecialVendingMachineController {
    private Hashtable<String,String> productPaths;
    private MenuUi menu;    
    private SpecialVendingMachine machine;
    private SpecialVendingMachineUI ui;

    /**
     * Contructor of the <code>SpecialVendingMachineController</code>. Function calls are set here
     * @param machine Special Vending Machine
     * @param ui UI of the Special Vending Machine
     * @param menu menu UI
     */
    public SpecialVendingMachineController(SpecialVendingMachine machine, SpecialVendingMachineUI ui, MenuUi menu){
        this.machine = machine;
        this.ui = ui;
        this.menu = menu;
        this.productPaths = setProductImages();

        for (int i = 0; i < SpecialVendingMachine.getMaxrow(); i++) {
            for (int j = 0; j < SpecialVendingMachine.getMaxcol(); j++) {
                String items = this.machine.getVendoItem()[i][j].getLabel() + "|" +  this.machine.getVendoItem()[i][j].getQuantity() + "|P " + this.machine.getVendoItem()[i][j].getPrice();
                ui.setItemsText(i, j, items);
            }
        }

        if (machine.total(machine.getStoredMoney()) > 0) {
            ui.setInputMoneyFieldText("P " + machine.total(machine.getStoredMoney()));
        }
        
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
                    ui.setItemsFieldText(ui.getItemsFieldText()+"1");
                }
            }
                });
                
        this.ui.setItemBtn2Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("2");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"2");
                }
            }
        });

        this.ui.setItemBtn3Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("3");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"3");
                }
            }
        });

        this.ui.setItemBtn4Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("4");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"4");
                }
            }
        });

        this.ui.setItemBtn5Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getItemsFieldText().equals("Input Item Label")) {
                    ui.setItemsFieldText("5");
                } else {
                    ui.setItemsFieldText(ui.getItemsFieldText()+"5");
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
                boolean success = machine.addItemToCart(label);

                if (success) {
                ui.setCartAreaFieldText(machine.display());
                ui.clearItemsField();
                ui.getMainFrame().revalidate();
                    
                }else{
                    ui.clearItemsField();
                }
            }
        });


        this.ui.setItemBtnCanListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.clearItemsField();
            }
        });

        
        this.ui.setbuyBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                boolean success = machine.buyItem();

                if (success) {
                    ui.setReceiptText(machine.createTransactions(machine.getTransactionsMade()) + machine.dispenseChange());
                    ui.setInputMoneyFieldText("P " + machine.total(machine.getStoredMoney()));
                    ui.clearCartAreaField();
                    ui.clearCashField();
                    ui.clearItemsField();
                    ui.setMDisplayText(machine.showNewTransactions());
                    for (int i = 0; i < SpecialVendingMachine.getMaxrow(); i++) {
                        for (int j = 0; j < SpecialVendingMachine.getMaxcol(); j++) {
                            String items = machine.getVendoItem()[i][j].getLabel() + "|" +  machine.getVendoItem()[i][j].getQuantity() + "|P " + machine.getVendoItem()[i][j].getPrice();
                            ui.setItemsText(i, j, items);
                        }
                    }
                    ui.getMainFrame().revalidate();
                    ui.getMaintenanceFrame().revalidate();
                } else {
                    ui.clearItemsField();
                }
            }
        });

  
        this.ui.setCreateBuyBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String name = ui.getProductNameField();
                boolean success = machine.buyProduct(name);

                if (success) {
                    for (int i = 0; i < SpecialVendingMachine.getMaxrow(); i++) {
                        for (int j = 0; j < SpecialVendingMachine.getMaxcol(); j++) {
                            String items = machine.getVendoItem()[i][j].getLabel() + "|" +  machine.getVendoItem()[i][j].getQuantity() + "|P " + machine.getVendoItem()[i][j].getPrice();
                            ui.setItemsText(i, j, items);
                        }
                    }
                    ui.clearCartAreaField();
                    ui.clearProductField();
                    ui.clearCashField();
                    ui.setProcessArea(machine.displayProcess());
                    ui.setInputMoneyFieldText("P " + machine.total(machine.getStoredMoney()));
                    ui.setReceiptText(machine.createProductTransactions(machine.getTransactionsMade()) + machine.dispenseChange());
                    ui.setMDisplayText(machine.showNewTransactions());
                    ui.setProductIcon(new ImageIcon(new ImageIcon(productPaths.get(name)).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
                } else {
                    ui.setReceiptText("Fail");
                }
                
            }   
        });

        this.ui.setCancelBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                machine.getUserCart().clear();
                ui.clearCartAreaField();
            }
        });

        this.ui.setOpenMListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.getMainFrame().setVisible(false);
                ui.getMaintenanceFrame().setVisible(true);
                ui.clearReceiptArea();
                ui.clearProductField();
                ui.setProductIcon(new ImageIcon(new ImageIcon("./VendingView/Images/mainIcon.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));

            }
        });

        this.ui.setOpenVListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.getMainFrame().setVisible(true);
                ui.getMaintenanceFrame().setVisible(false);
                ui.clearMDisplayText();
                ui.clearIncDecFieldText();
            }
        });

        this.ui.setExitMListerner(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.getMainFrame().dispose();
                ui.getMaintenanceFrame().dispose();
                ui.getProductsFrame().dispose();
                machine.fileItemWrite();
                machine.fileMoneyWrite();
                machine.fileTransactionWrite();
                menu.getMainFrame().setVisible(true);
            }
        });

        this.ui.setExitVListerner(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.getMainFrame().dispose();
                ui.getMaintenanceFrame().dispose();
                ui.getProductsFrame().dispose();
                machine.fileItemWrite();
                machine.fileMoneyWrite();
                machine.fileTransactionWrite();
                menu.getMainFrame().setVisible(true);
            }
        });
        
        // MAINTENANCE BUTTONS ----------------------------------------------------------------------------

        this.ui.setAddItemBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                boolean success = machine.inputItems(ui.getNameFieldText(), ui.getTypeFieldText(), ui.getPriceFieldText(), ui.getQuantityFieldText(), ui.getCaloriesFieldText());

                if (success) {
                    ui.clearCaloriesLabelField();
                    ui.clearNameField();
                    ui.clearPriceLabelField();
                    ui.clearTypeLabelField();
                    ui.clearQuantityLabelField();
                    ui.setMDisplayText("Successfully Added!");
                    for (int i = 0; i < SpecialVendingMachine.getMaxrow(); i++) {
                        for (int j = 0; j < SpecialVendingMachine.getMaxcol(); j++) {
                            String items = machine.getVendoItem()[i][j].getLabel() + "|" +  machine.getVendoItem()[i][j].getQuantity() + "|P " + machine.getVendoItem()[i][j].getPrice();
                            ui.setItemsText(i, j, items);
                        }
                    }
                } else {
                    ui.clearCaloriesLabelField();
                    ui.clearNameField();
                    ui.clearPriceLabelField();
                    ui.clearTypeLabelField();
                    ui.clearQuantityLabelField();
                    ui.setMDisplayText("Unsuccessful");
                }
            }
        });

        this.ui.setClearBtnBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                ui.clearNameField();
                ui.clearQuantityLabelField();
                ui.clearPriceLabelField();
                ui.clearCaloriesLabelField();
                ui.clearTypeLabelField();
                
            }
        });

        this.ui.setMCashBtn1Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                machine.inputDenomenations(1);
                ui.setInputMoneyFieldText("P "+machine.total(machine.getStoredMoney()));
                ui.getMaintenanceFrame().revalidate();
            }
        });

        this.ui.setMCashBtn2Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                machine.inputDenomenations(5);
                ui.setInputMoneyFieldText("P "+machine.total(machine.getStoredMoney()));
                ui.getMaintenanceFrame().revalidate();
            }
        });

        this.ui.setMCashBtn3Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                machine.inputDenomenations(10);
                ui.setInputMoneyFieldText("P "+machine.total(machine.getStoredMoney()));
                ui.getMaintenanceFrame().revalidate();
            }
        });

        this.ui.setMCashBtn4Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                machine.inputDenomenations(20);
                ui.setInputMoneyFieldText("P "+machine.total(machine.getStoredMoney()));
                ui.getMaintenanceFrame().revalidate();
            }
        });

        this.ui.setMCashBtn5Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                machine.inputDenomenations(50);
                ui.setInputMoneyFieldText("P "+machine.total(machine.getStoredMoney()));
                ui.getMaintenanceFrame().revalidate();
            }
        });

        this.ui.setMCashBtn6Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                machine.inputDenomenations(100);
                ui.setInputMoneyFieldText("P "+machine.total(machine.getStoredMoney()));
                ui.getMaintenanceFrame().revalidate();
            }
        }); 

        this.ui.setMCashBtn7Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                machine.inputDenomenations(200);
                ui.setInputMoneyFieldText("P "+machine.total(machine.getStoredMoney()));
                ui.getMaintenanceFrame().revalidate();
            }
        });

        this.ui.setMCashBtn8Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                machine.inputDenomenations(500);
                ui.setInputMoneyFieldText("P "+machine.total(machine.getStoredMoney()));
                ui.getMaintenanceFrame().revalidate();
            }
        });

        this.ui.setMCashBtn9Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                machine.inputDenomenations(1000);
                ui.setInputMoneyFieldText("P "+machine.total(machine.getStoredMoney()));
                ui.getMaintenanceFrame().revalidate();
            }
        });


        this.ui.setMItemBtn1Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                if(ui.getIncDecFieldText().equals("Input Item Label")){
                    ui.setIncDecFieldText("1");
                } else {
                    ui.setIncDecFieldText(ui.getIncDecFieldText()+"1");
                }
            }
        });

        this.ui.setMItemBtn2Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getIncDecFieldText().equals("Input Item Label")) {
                    ui.setIncDecFieldText("2");
                } else {
                    ui.setIncDecFieldText(ui.getIncDecFieldText()+"2");
                }
            }
        });

        this.ui.setMItemBtn3Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getIncDecFieldText().equals("Input Item Label")) {
                    ui.setIncDecFieldText("3");
                } else {
                    ui.setIncDecFieldText(ui.getIncDecFieldText()+"3");
                }
            }
        });

       this.ui.setMItemBtn4Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getIncDecFieldText().equals("Input Item Label")) {
                    ui.setIncDecFieldText("4");
                } else {
                    ui.setIncDecFieldText(ui.getIncDecFieldText()+"4");
                }
            }
        });

        this.ui.setMItemBtn5Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getIncDecFieldText().equals("Input Item Label")) {
                    ui.setIncDecFieldText("5");
                } else {
                    ui.setIncDecFieldText(ui.getIncDecFieldText()+"5");
                }
            }
        });
 
        this.ui.setMItemBtnAListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getIncDecFieldText().equals("Input Item Label")) {
                    ui.setIncDecFieldText("A");
                } else {
                    ui.setIncDecFieldText(ui.getIncDecFieldText()+"A");
                }
            }
        });

        this.ui.setMItemBtnBListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getIncDecFieldText().equals("Input Item Label")) {
                    ui.setIncDecFieldText("B");
                } else {
                    ui.setIncDecFieldText(ui.getIncDecFieldText()+"B");
                }
            }
        });

        this.ui.setMItemBtnCListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getIncDecFieldText().equals("Input Item Label")) {
                    ui.setIncDecFieldText("C");
                } else {
                    ui.setIncDecFieldText(ui.getIncDecFieldText()+"C");
                }
            }
        });

        this.ui.setMItemBtnDListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getIncDecFieldText().equals("Input Item Label")) {
                    ui.setIncDecFieldText("D");
                } else {
                    ui.setIncDecFieldText(ui.getIncDecFieldText()+"D");
                }
            }
        });

        this.ui.setMItemBtnEListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getIncDecFieldText().equals("Input Item Label")) {
                    ui.setIncDecFieldText("E");
                } else {
                    ui.setIncDecFieldText(ui.getIncDecFieldText()+"E");
                }
            }
        });

        this.ui.setMItemBtnEListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getIncDecFieldText().equals("Input Item Label")) {
                    ui.setIncDecFieldText("F");
                } else {
                    ui.setIncDecFieldText(ui.getIncDecFieldText()+"F");
                }
            }
        });

        this.ui.setMPriceBtn1Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(ui.getLabelFieldText().equals("Item")){
                    ui.setLabelFieldText("1");
                } else {
                    ui.setLabelFieldText(ui.getLabelFieldText()+1);
                }
            }
        });

        this.ui.setMPriceBtn2Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getLabelFieldText().equals("Item")) {
                    ui.setLabelFieldText("2");
                } else {
                    ui.setLabelFieldText(ui.getLabelFieldText()+2);
                }
            }
        });

        this.ui.setMPriceBtn3Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (ui.getLabelFieldText().equals("Item")) {
                    ui.setLabelFieldText("3");
                } else {
                    ui.setLabelFieldText(ui.getLabelFieldText()+3);
                }
            }
        });

        this.ui.setMPriceBtn4Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    if (ui.getLabelFieldText().equals("Item")) {
                        ui.setLabelFieldText("4");
                    } else {
                        ui.setLabelFieldText(ui.getLabelFieldText()+4);
                    }
                }
            });

        this.ui.setMPriceBtn5Listener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    if (ui.getLabelFieldText().equals("Item")) {
                        ui.setLabelFieldText("5");
                    } else {
                        ui.setLabelFieldText(ui.getLabelFieldText()+5);
                    }
                }
            });

        this.ui.setMPriceBtnAListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    if (ui.getLabelFieldText().equals("Item")) {
                        ui.setLabelFieldText("A");
                    } else {
                        ui.setLabelFieldText(ui.getLabelFieldText()+"A");
                    }
                }
            });

        this.ui.setMPriceBtnBListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    if (ui.getLabelFieldText().equals("Item")) {
                        ui.setLabelFieldText("B");
                    } else {
                        ui.setLabelFieldText(ui.getLabelFieldText()+"B");
                    }
                }
            });

        this.ui.setMPriceBtnCListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    if (ui.getLabelFieldText().equals("Item")) {
                        ui.setLabelFieldText("C");
                    } else {
                        ui.setLabelFieldText(ui.getLabelFieldText()+"C");
                    }
                }
            });

        this.ui.setMPriceBtnDListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    if (ui.getLabelFieldText().equals("Item")) {
                        ui.setLabelFieldText("D");
                    } else {
                        ui.setLabelFieldText(ui.getLabelFieldText()+"D");
                    }
                }
            });

        this.ui.setMPriceBtnEListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    if (ui.getLabelFieldText().equals("Item") ) {
                        ui.setLabelFieldText("E");
                    } else {
                        ui.setLabelFieldText(ui.getLabelFieldText()+"E");
                    }
                }
            });

        this.ui.setMPriceBtnFListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    if (ui.getLabelFieldText().equals("Item")) {
                        ui.setLabelFieldText("F");
                    } else {
                        ui.setLabelFieldText(ui.getLabelFieldText()+"F");
                    }
                }
            });

        this.ui.setMItemBtnClearListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.clearIncDecFieldText();
            }
        });


        this.ui.setMItemBtnDecListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String label = ui.getIncDecFieldText();
                boolean added = machine.decreaseItem(label);

                if (added) {
                    ui.setMDisplayText("Successfully Increased!");
                    for (int i = 0; i < SpecialVendingMachine.getMaxrow(); i++) {
                        for (int j = 0; j < SpecialVendingMachine.getMaxcol(); j++) {
                            String items = machine.getVendoItem()[i][j].getLabel() + "|" +  machine.getVendoItem()[i][j].getQuantity() + "|P " + machine.getVendoItem()[i][j].getPrice();
                            ui.setItemsText(i, j, items);
                        }
                    }
                } else {
                    ui.setMDisplayText("Unsuccessful");
                    ui.clearIncDecFieldText();
                }
            }
        });

        this.ui.setMItemBtnAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String label = ui.getIncDecFieldText();
                boolean added = machine.increaseItem(label);

                if (added) {
                    ui.setMDisplayText("Successfully Increased!");
                    for (int i = 0; i < SpecialVendingMachine.getMaxrow(); i++) {
                        for (int j = 0; j < SpecialVendingMachine.getMaxcol(); j++) {
                            String items = machine.getVendoItem()[i][j].getLabel() + "|" +  machine.getVendoItem()[i][j].getQuantity() + "|P " + machine.getVendoItem()[i][j].getPrice();
                            ui.setItemsText(i, j, items);
                        }
                    }
                } else {
                    ui.setMDisplayText("Unsuccessful");
                    ui.clearIncDecFieldText();
                }

                
            }
        });

        this.ui.setmPriceBtnConListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                    String text = ui.getNewPriceFieldText();
                    double price = Double.parseDouble(text);
                    boolean success = machine.changePrice(price, ui.getLabelFieldText());
                    
                    if (success){
                        ui.clearLabelField();
                        ui.clearNewPriceField();
                        ui.setMDisplayText("Successfully Changed!");
                        for (int i = 0; i < SpecialVendingMachine.getMaxrow(); i++) {
                            for (int j = 0; j < SpecialVendingMachine.getMaxcol(); j++) {
                                String items = machine.getVendoItem()[i][j].getLabel() + "|" +  machine.getVendoItem()[i][j].getQuantity() + "|P " + machine.getVendoItem()[i][j].getPrice();
                                ui.setItemsText(i, j, items);
                            }
                        }
                    }else{
                        ui.clearLabelField();
                        ui.clearNewPriceField();
                        ui.setMDisplayText("Unsuccessful");
                    }  
                }
            });

        this.ui.setmPriceBtnCanListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    ui.clearLabelField();
                    ui.clearNewPriceField();
            }
        });


        this.ui.setCheckDenomBtnAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.clearMDisplayText();
                ui.setMDisplayText(machine.checkDenom());
            
            }
        });

        this.ui.setShowTransactionsBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                ui.clearMDisplayText();
                ui.setMDisplayText(machine.showTransactions());
            }
        });
                
        this.ui.setCollectMoneyBtnListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){
            boolean success = machine.collectMoneyInMachine();
            
            if (success) {
                ui.clearInputMoneyField();
                ui.setMDisplayText("Money Successfully Collected!");
            } else {
                ui.setMDisplayText("Error!!!");
            }
            
            }
        });  
                
    }

    private Hashtable<String,String> setProductImages() {
        Hashtable<String,String> pathNames = new Hashtable<String,String>();

        pathNames.put("strawberry smoothie", "./VendingView/Images/Product/Strawberry Smoothie.png");
        pathNames.put("mango smoothie", "./VendingView/Images/Product/Mango Smoothie.png");
        pathNames.put("mixed berry smoothie", "./VendingView/Images/Product/Mixed Berry Smoothie.png");
        pathNames.put("strawberry banana smoothie", "./VendingView/Images/Product/Strawberry Banana Smoothie.png");
        pathNames.put("mango graham smoothie", "./VendingView/Images/Product/Mango Graham Smoothie.png");
        pathNames.put("mango graham smoothie", "./VendingView/Images/Product/Mango Graham Smoothie.png");
        pathNames.put("spring smoothie", "./VendingView/Images/Product/Spring Smoothie.png");
        pathNames.put("tropical dragon smoothie", "./VendingView/Images/Product/Tropical Dragon Smoothie.png");
        pathNames.put("watermelon smoothie", "./VendingView/Images/Product/Watermelon Smoothie.png");
        pathNames.put("peach smoothie", "./VendingView/Images/Product/Peach Smoothie.png");
        pathNames.put("oreo banana smoothie", "./VendingView/Images/Product/Oreo Banana Smoothie.png");
        pathNames.put("pb banana Smoothie", "./VendingView/Images/Product/PB Banana Smoothie.png");
        pathNames.put("health Smoothie", "./VendingView/Images/Product/Health Smoothie.png");
        pathNames.put("forest Smoothie", "./VendingView/Images/Product/PB Banana Smoothie.png");
        pathNames.put("protein Smoothie", "./VendingView/Images/Product/Protein Smoothie.png");

        return pathNames;
    }
}

