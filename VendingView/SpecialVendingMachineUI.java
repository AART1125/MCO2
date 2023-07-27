package VendingView;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import VendingModel.VendingMachineClasses.SpecialVendingMachine;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class SpecialVendingMachineUI {
    private SpecialVendingMachine machine;

    private String[] pathNames;
    private JFrame mainFrame, maintenanceFrame;
    private JMenuBar menu, maintenanceMenu;
    private JMenu maintenanceMItem, vendingMItem;
    private JMenuItem openM, exitM, openV, exitV;
    private Font digitalFont;
    private ImageIcon icon;
    private ImageIcon[] itemsIcon;
    private JLabel cashLabel, displayLabel, receiptLabel, productLabel;
    private JPanel itemsPanel, productPanel, inputsPanel;// Front portion
    private JTextField cashField, itemsField, productNameField;
    private JTextArea cartArea, receiptArea, processArea;
    private JScrollPane cartPane, receiptPane;
    private JButton cashBtn1, cashBtn2, cashBtn3, cashBtn4, cashBtn5, cashBtn6, cashBtn7, cashBtn8, cashBtn9;// Buttons for Cash Inputs
    private JButton itemBtn1, itemBtn2, itemBtn3, itemBtn4, itemBtn5, itemBtnA, itemBtnB, itemBtnC, itemBtnD, itemBtnE, itemBtnF, itemBtnCon, itemBtnCan;// Buttons for Item inputs
    private JButton createBuyBtn, buyBtn, cancelBtn;//Buttons for transactions
    private JPanel addItemsPanel, inputMoneyPanel, transactionsPanel, collectMoney;//Back Portion
    private JLabel nameLabel, typeLabel, priceLabel, quantityLable, caloriesLabel;
    private JTextField nameField, typeField, priceField, quantityField, caloriesField;
    private JButton addCBtn1, addCBtn2, addCBtn3, addCBtn4, addCBtn5, addCBtn6, addCBtn7, addCBtn8, addCBtn9;

    public SpecialVendingMachineUI(SpecialVendingMachine machine){
        this.pathNames = setPathNames();

        //get the values of the machine
        //will be removed for controller
        this.machine = machine;

        try {
            this.digitalFont = Font.createFont(Font.TRUETYPE_FONT, new File("VendingView/Images/neon_pixel-7.ttf")).deriveFont(Font.BOLD);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(digitalFont);
        } catch (Exception e) {
            // TODO: handle exception
        }

        //Icon of the program
        this.icon = new ImageIcon("VendingView/Images/mainIcon.jpg");
        
        //Mainframe settings on start up
        this.mainFrame = new JFrame("Smoothie Machine");
        this.mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainFrame.setIconImage(this.icon.getImage());
        this.mainFrame.setLayout(new GridBagLayout());
        this.mainFrame.getContentPane().setBackground(Color.RED);
        this.mainFrame.setSize(750, 1000);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setResizable(false);

        //Menu bars for both frames
        this.menu = new JMenuBar();
        this.maintenanceMenu = new JMenuBar();

        //Menu option for both frames
        this.maintenanceMItem = new JMenu("Maintenance");
        this.vendingMItem = new JMenu("Vending Machine");

        //Menu items for both frames
        this.openM = new JMenuItem("Open Maintenance");
        this.openV = new JMenuItem("Go to Vending Machine");
        this.exitM = new JMenuItem("Exit Program");
        this.exitV = new JMenuItem("Exit Program");

        //Mainframe menu bar setup
        this.maintenanceMItem.add(this.openM);
        this.maintenanceMItem.add(this.exitM);
        this.menu.add(this.maintenanceMItem);
        this.mainFrame.setJMenuBar(this.menu);

        //Main Menu UI
        //Panel for items view
        this.itemsPanel = new JPanel(new GridBagLayout());
        this.itemsPanel.setPreferredSize(new Dimension(500, 720));
        this.itemsPanel.setMinimumSize(new Dimension(500, 720));
        this.itemsPanel.setBackground(Color.black);
        this.itemsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.gray));

        //Panel for product dispenser
        this.productPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.productPanel.setPreferredSize(new Dimension(500, 265));
        this.productPanel.setMinimumSize(new Dimension(500, 265));
        this.productPanel.setBackground(Color.BLACK);
        this.productPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.gray));

        //Panel for inputs
        this.inputsPanel = new JPanel(new GridLayout(4, 0));
        this.inputsPanel.setPreferredSize(new Dimension(225, 960));
        this.inputsPanel.setMinimumSize(new Dimension(225, 960));
        this.inputsPanel.setBackground(Color.black);
        this.inputsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.gray));

        //Label for cart
        this.displayLabel = new JLabel("Display cart: ", SwingConstants.CENTER);
        this.displayLabel.setFont(digitalFont.deriveFont(21f));
        this.displayLabel.setForeground(Color.GREEN);

        //Label for receipt
        this.receiptLabel = new JLabel("Receipt", SwingConstants.CENTER);
        this.receiptLabel.setFont(digitalFont.deriveFont(21f));
        this.receiptLabel.setForeground(Color.green);

        //Label for product
        this.productLabel = new JLabel(icon);
        this.productLabel.setMinimumSize(new Dimension(200, 180));

        //Text field for cash
        this.cashField = new JTextField("Cash Amount", 20);
        this.cashField.setBackground(Color.BLACK);
        this.cashField.setForeground(Color.GREEN);
        this.cashField.setFont(digitalFont.deriveFont(21f));
        this.cashField.setHorizontalAlignment(SwingConstants.CENTER);
        this.cashField.setMinimumSize(new Dimension(200, 25));
        this.cashField.setEditable(false);

        //Text field for the items panel
        this.itemsField = new JTextField("Input Item Label", 20);
        this.itemsField.setBackground(Color.BLACK);
        this.itemsField.setForeground(Color.GREEN);
        this.itemsField.setFont(digitalFont.deriveFont(21f));
        this.itemsField.setHorizontalAlignment(SwingConstants.CENTER);
        this.itemsField.setMinimumSize(new Dimension(200, 25));
        this.itemsField.setEditable(false);

        //Text field for the product name
        this.productNameField = new JTextField("Input Product Name", 20);
        this.productNameField.setBackground(Color.BLACK);
        this.productNameField.setForeground(Color.GREEN);
        this.productNameField.setCaretColor(Color.green);
        this.productNameField.setFont(digitalFont.deriveFont(21f));
        this.productNameField.setHorizontalAlignment(SwingConstants.CENTER);
        this.productNameField.setMinimumSize(new Dimension(200, 25));

        //Text area for the cart panel
        this.cartArea = new JTextArea("", 4, 20);
        this.cartArea.setEditable(false);
        this.cartArea.setLineWrap(true);
        this.cartPane = new JScrollPane(cartArea);
        this.cartPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.cartPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.cartPane.setMinimumSize(new Dimension(200, 100));

        //Text area for the receipt panel
        this.receiptArea = new JTextArea("", 5, 20);
        this.receiptArea.setEditable(false);
        this.receiptArea.setLineWrap(true);
        this.receiptPane = new JScrollPane(receiptArea);
        this.receiptPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.receiptPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.receiptPane.setMinimumSize(new Dimension(200, 180));

        //Text area for the process
        this.processArea = new JTextArea("", 5, 20);
        this.processArea.setFont(digitalFont.deriveFont(24f));
        this.processArea.setBackground(Color.black);
        this.processArea.setForeground(Color.green);
        this.processArea.setCaretColor(Color.green);
        this.processArea.setEditable(true);
        this.processArea.setLineWrap(true);
        this.processArea.setMinimumSize(new Dimension(243, 200));

        //Input cash buttons
        this.cashBtn1 = new JButton("1");
        this.cashBtn1.setFont(digitalFont.deriveFont(15f));
        this.cashBtn1.setPreferredSize(new Dimension(55, 55));
        this.cashBtn2 = new JButton("5");
        this.cashBtn2.setFont(digitalFont.deriveFont(15f));
        this.cashBtn2.setPreferredSize(new Dimension(55, 55));
        this.cashBtn3 = new JButton("10");
        this.cashBtn3.setFont(digitalFont.deriveFont(15f));
        this.cashBtn3.setPreferredSize(new Dimension(55, 55));
        this.cashBtn4 = new JButton("20");
        this.cashBtn4.setFont(digitalFont.deriveFont(15f));
        this.cashBtn4.setPreferredSize(new Dimension(55, 55));
        this.cashBtn5 = new JButton("50");
        this.cashBtn5.setFont(digitalFont.deriveFont(15f));
        this.cashBtn5.setPreferredSize(new Dimension(55, 55));
        this.cashBtn6 = new JButton("100");
        this.cashBtn6.setFont(digitalFont.deriveFont(15f));
        this.cashBtn6.setPreferredSize(new Dimension(55, 55));
        this.cashBtn7 = new JButton("200");
        this.cashBtn7.setFont(digitalFont.deriveFont(15f));
        this.cashBtn7.setPreferredSize(new Dimension(55, 55));
        this.cashBtn8 = new JButton("500");
        this.cashBtn8.setFont(digitalFont.deriveFont(15f));
        this.cashBtn8.setPreferredSize(new Dimension(55, 55));
        this.cashBtn9 = new JButton("1k");
        this.cashBtn9.setFont(digitalFont.deriveFont(15f));
        this.cashBtn9.setPreferredSize(new Dimension(55, 55));

        //Item input buttons
        this.itemBtn1 = new JButton("1");
        this.itemBtn1.setFont(digitalFont.deriveFont(12.2f));
        this.itemBtn1.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtn2 = new JButton("2");
        this.itemBtn2.setFont(digitalFont.deriveFont(12.2f));
        this.itemBtn2.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtn3 = new JButton("3");
        this.itemBtn3.setFont(digitalFont.deriveFont(12.2f));
        this.itemBtn3.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtn4 = new JButton("4");
        this.itemBtn4.setFont(digitalFont.deriveFont(12f));
        this.itemBtn4.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtn5 = new JButton("5");
        this.itemBtn5.setFont(digitalFont.deriveFont(12.2f));
        this.itemBtn5.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnA = new JButton("A");
        this.itemBtnA.setFont(digitalFont.deriveFont(12.2f));
        this.itemBtnA.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnB = new JButton("B");
        this.itemBtnB.setFont(digitalFont.deriveFont(12.2f));
        this.itemBtnB.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnC = new JButton("C");
        this.itemBtnC.setFont(digitalFont.deriveFont(12.2f));
        this.itemBtnC.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnD = new JButton("D");
        this.itemBtnD.setFont(digitalFont.deriveFont(12.2f));
        this.itemBtnD.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnE = new JButton("E");
        this.itemBtnE.setFont(digitalFont.deriveFont(12.2f));
        this.itemBtnE.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnF = new JButton("F");
        this.itemBtnF.setFont(digitalFont.deriveFont(12.2f));
        this.itemBtnF.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnCon = new JButton("✓");
        this.itemBtnCon.setBackground(Color.green);
        this.itemBtnCon.setForeground(Color.white);
        this.itemBtnCon.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnCan = new JButton("x");
        this.itemBtnCan.setFont(digitalFont.deriveFont(12.2f));
        this.itemBtnCan.setBackground(Color.RED);
        this.itemBtnCan.setForeground(Color.white);
        this.itemBtnCan.setMargin(new Insets(10, 12, 10, 12));

        //Button for buying
        this.createBuyBtn = new JButton("Create");
        this.createBuyBtn.setBackground(Color.green);
        this.createBuyBtn.setForeground(Color.white);
        this.buyBtn = new JButton("Buy cart");
        this.buyBtn.setBackground(Color.green);
        this.buyBtn.setForeground(Color.white);
        this.cancelBtn = new JButton("x");
        this.cancelBtn.setBackground(Color.RED);
        this.cancelBtn.setForeground(Color.white);
        this.cancelBtn.setFont(digitalFont.deriveFont(9f));
        this.cancelBtn.setMargin(new Insets(5, 7, 5, 7));
        
        //Panel for the items
        JPanel panel = setItems();

        this.itemsPanel.add(panel);

        //Panel for the finished products
        panel = new JPanel(new GridLayout(1, 2));
        panel.setBackground(Color.darkGray);
        panel.setPreferredSize(new Dimension(485, 220));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.gray));
        panel.add(processArea);
        panel.add(productLabel);

        this.productPanel.add(panel);

        //panel for input cash
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints panelCon = new GridBagConstraints();
        panel.setBackground(Color.gray);
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
            panelCon.gridx = 0;
            panelCon.gridy = 0;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 4;
            panelCon.weightx = 0;
            panelCon.weighty = 1;
            panelCon.anchor = GridBagConstraints.CENTER;
            panel.add(cashField, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 1;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 1;
            panelCon.weightx = 1;
            panelCon.weighty = 0.5;
            panel.add(cashBtn1, panelCon);

            panelCon.gridx = 1;
            panelCon.gridy = 1;
            panel.add(cashBtn2, panelCon);

            panelCon.gridx = 2;
            panelCon.gridy = 1;
            panel.add(cashBtn3, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 2;
            panel.add(cashBtn4, panelCon);

            panelCon.gridx = 1;
            panelCon.gridy = 2;
            panel.add(cashBtn5, panelCon);

            panelCon.gridx = 2;
            panelCon.gridy = 2;
            panel.add(cashBtn6, panelCon);

            panelCon.insets = new Insets(0, 0, 1, 0);
            panelCon.gridx = 0;
            panelCon.gridy = 3;
            panel.add(cashBtn7, panelCon);

            panelCon.gridx = 1;
            panelCon.gridy = 3;
            panel.add(cashBtn8, panelCon);

            panelCon.gridx = 2;
            panelCon.gridy = 3;
            panel.add(cashBtn9, panelCon);
        
            this.inputsPanel.add(panel);

        //Panel for item inputs
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

            panelCon.gridx = 0;
            panelCon.gridy = 0;
            panelCon.insets = new Insets(5, 5, 5, 5);
            panelCon.weightx = 0;
            panelCon.weighty = 0;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 5;
            
            panel.add(itemsField, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 1;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 1;
            
            panel.add(itemBtnA, panelCon);

            panelCon.gridx = 1;
            panelCon.gridy = 1;
            panel.add(itemBtnB, panelCon);

            panelCon.gridx = 2;
            panelCon.gridy = 1;
            panel.add(itemBtnC, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 2;
            panel.add(itemBtnD, panelCon);

            panelCon.gridx = 1;
            panelCon.gridy = 2;
            panel.add(itemBtnE, panelCon);

            panelCon.gridx = 2;
            panelCon.gridy = 2;
            panel.add(itemBtnF, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 3;
            panel.add(itemBtn1, panelCon);
            
            panelCon.gridx = 1;
            panelCon.gridy = 3;
            panel.add(itemBtn2, panelCon);

            panelCon.gridx = 2;
            panelCon.gridy = 3;
            panel.add(itemBtn3, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 4;
            panel.add(itemBtn4, panelCon);

            panelCon.gridx = 1;
            panelCon.gridy = 4;
            panel.add(itemBtn5, panelCon);

            panelCon.gridx = 4;
            panelCon.gridy = 2;
            panel.add(itemBtnCon, panelCon);

            panelCon.gridx = 4;
            panelCon.gridy = 3;
            panel.add(itemBtnCan, panelCon);

            this.inputsPanel.add(panel);

        //panel for cart display
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

            panelCon.gridx = 0;
            panelCon.gridy = 0;
            panelCon.insets = new Insets(5, 5, 5, 5);
            panelCon.weightx = 0;
            panelCon.weighty = 0;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 5;
            panelCon.anchor = GridBagConstraints.CENTER;
            panel.add(displayLabel, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 1;
            panelCon.anchor = GridBagConstraints.CENTER;
            panel.add(cartPane, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 2;
            panelCon.anchor = GridBagConstraints.CENTER;
            panel.add(productNameField, panelCon);

            panelCon.gridx = 2;
            panelCon.gridy = 3;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 1;
            panelCon.anchor = GridBagConstraints.EAST;
            panel.add(createBuyBtn, panelCon);

            panelCon.gridx = 3;
            panelCon.gridy = 3;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 1;
            panelCon.anchor = GridBagConstraints.WEST;
            panel.add(buyBtn, panelCon);

            panelCon.gridx = 4;
            panelCon.gridy = 3;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 1;
            panelCon.anchor = GridBagConstraints.WEST;
            panel.add(cancelBtn, panelCon);
            

            this.inputsPanel.add(panel);
        
        //Panel for receipts
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

            panelCon.gridx = 0;
            panelCon.gridy = 0;
            panelCon.weightx = 0;
            panelCon.weighty = 0;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 3;
            panelCon.anchor = GridBagConstraints.CENTER;
            panel.add(receiptLabel, panelCon);

            panelCon.gridy = 1;
            panel.add(receiptPane, panelCon);

            this.inputsPanel.add(panel);

        GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(3,3,3,3);
            c.weightx = 0.5;
            c.weighty = 0.5;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.NORTHWEST;

            this.mainFrame.add(itemsPanel, c);

            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0.5;
            c.weighty = 0.5;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.WEST;

            this.mainFrame.add(productPanel, c);

            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0.5;
            c.weighty = 0;
            c.gridheight = 2;
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.CENTER;

            this.mainFrame.add(inputsPanel, c);
            
        this.mainFrame.setVisible(true);
        this.mainFrame.revalidate();

        //----------------------------------------------------------------------------------------------------------------
        //Maintenance Menu UI

        //Maintenance frame settings on start up
        this.maintenanceFrame = new JFrame("Maintence");
        this.maintenanceFrame.setIconImage(this.icon.getImage());
        this.maintenanceFrame.setLayout(new GridBagLayout());
        this.maintenanceFrame.setSize(750, 1010);
        this.maintenanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.maintenanceFrame.setLocationRelativeTo(null);
        this.maintenanceFrame.setResizable(false);

         //Maintenance menu bar setup
        this.vendingMItem.add(this.openV);
        this.vendingMItem.add(this.exitV);
        this.maintenanceMenu.add(this.vendingMItem);
        this.maintenanceFrame.setJMenuBar(this.maintenanceMenu);

        this.nameLabel = new JLabel("Name: ", SwingConstants.RIGHT);
        this.nameLabel.setFont(digitalFont.deriveFont(21f));
        this.nameLabel.setForeground(Color.green);

        this.quantityLable = new JLabel("Quantity: ", SwingConstants.RIGHT);
        this.quantityLable.setFont(digitalFont.deriveFont(21f));
        this.quantityLable.setForeground(Color.green);

        this.priceLabel = new JLabel("Price: ", SwingConstants.RIGHT);
        this.priceLabel.setFont(digitalFont.deriveFont(21f));
        this.priceLabel.setForeground(Color.green);

        this.caloriesLabel = new JLabel("Calories: ", SwingConstants.RIGHT);
        this.caloriesLabel.setFont(digitalFont.deriveFont(21f));
        this.caloriesLabel.setForeground(Color.green);

        this.typeLabel = new JLabel("Type: ", SwingConstants.RIGHT);
        this.typeLabel.setFont(digitalFont.deriveFont(21f));
        this.typeLabel.setForeground(Color.green);

        this.nameField = new JTextField("Input New Item's Name", 20);
        this.nameField.setBackground(Color.BLACK);
        this.nameField.setForeground(Color.GREEN);
        this.nameField.setFont(digitalFont.deriveFont(21f));
        this.nameField.setHorizontalAlignment(SwingConstants.CENTER);
        this.nameField.setMinimumSize(new Dimension(200, 25));

        this.quantityField = new JTextField("Input New Item's Quantity", 20);
        this.quantityField.setBackground(Color.BLACK);
        this.quantityField.setForeground(Color.GREEN);
        this.quantityField.setFont(digitalFont.deriveFont(21f));
        this.quantityField.setHorizontalAlignment(SwingConstants.CENTER);
        this.quantityField.setMinimumSize(new Dimension(200, 25));

        this.priceField = new JTextField("Input New Item's Price", 20);
        this.priceField.setBackground(Color.BLACK);
        this.priceField.setForeground(Color.GREEN);
        this.priceField.setFont(digitalFont.deriveFont(21f));
        this.priceField.setHorizontalAlignment(SwingConstants.CENTER);
        this.priceField.setMinimumSize(new Dimension(200, 25));

        this.caloriesField = new JTextField("Input New Item's Calories", 20);
        this.caloriesField.setBackground(Color.BLACK);
        this.caloriesField.setForeground(Color.GREEN);
        this.caloriesField.setFont(digitalFont.deriveFont(21f));
        this.caloriesField.setHorizontalAlignment(SwingConstants.CENTER);
        this.caloriesField.setMinimumSize(new Dimension(200, 25));

        this.typeField = new JTextField("Input New Item's type", 20);
        this.typeField.setBackground(Color.BLACK);
        this.typeField.setForeground(Color.GREEN);
        this.typeField.setFont(digitalFont.deriveFont(21f));
        this.typeField.setHorizontalAlignment(SwingConstants.CENTER);
        this.typeField.setMinimumSize(new Dimension(200, 25));

        this.addItemsPanel = new JPanel(new GridLayout(5, 5));
        this.addItemsPanel.setBackground(Color.black);
        this.addItemsPanel.setPreferredSize(new Dimension(500, 720));
        this.addItemsPanel.setMinimumSize(new Dimension(500, 720));
        this.addItemsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.gray));

        this.addItemsPanel.add(nameLabel);
        this.addItemsPanel.add(nameField);
        this.addItemsPanel.add(quantityLable);
        this.addItemsPanel.add(quantityField);
        this.addItemsPanel.add(priceLabel);
        this.addItemsPanel.add(priceField);
        this.addItemsPanel.add(caloriesLabel);
        this.addItemsPanel.add(caloriesField);
        this.addItemsPanel.add(typeLabel);
        this.addItemsPanel.add(typeField);
        
        
        GridBagConstraints c2 = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(3,3,3,3);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        this.maintenanceFrame.add(addItemsPanel, c2);

        this.maintenanceFrame.setVisible(false);
        this.maintenanceFrame.revalidate();
    }

    public String[] setPathNames(){
        String[] pathNames = {
                                "VendingView/Images/Items/Strawberry.png",
                                "VendingView/Images/Items/Mango.jpg",
                                "VendingView/Images/Items/MixedBerries.png",
                                "VendingView/Images/Items/Banana.png",
                                "VendingView/Images/Items/Orange.png",
                                "VendingView/Images/Items/DragonFruit.jpg",
                                "VendingView/Images/Items/Watermelon.jpg",
                                "VendingView/Images/Items/Grapes.jpg",
                                "VendingView/Images/Items/Peach.jpg",
                                "VendingView/Images/Items/Apple.png",
                                "VendingView/Images/Items/Carrot.png",
                                "VendingView/Images/Items/Spinach.jpg",
                                "VendingView/Images/Items/Kale.jpg",
                                "VendingView/Images/Items/Milk.jpg",
                                "VendingView/Images/Items/AlmondMilk.png",
                                "VendingView/Images/Items/OatMilk.jpg",
                                "VendingView/Images/Items/Ice.png",
                                "VendingView/Images/Items/Sugar.png",
                                "VendingView/Images/Items/Graham.jpg",
                                "VendingView/Images/Items/Honey.png",
                                "VendingView/Images/Items/Oats.jpg",
                                "VendingView/Images/Items/Wafer.jpg",
                                "VendingView/Images/Items/Oreo.png",
                                "VendingView/Images/Items/PB.jpg",
                                "VendingView/Images/Items/SP.png",
                                "VendingView/Images/Items/PP.jpg",
                                "VendingView/Images/Items/Empty.png"
                            };
        return pathNames;
    }

    public JPanel setItems(){
        int i,j, k = 0;
        ImageIcon image;

        JLabel nameLabel;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(485, 690));

        GridBagConstraints itemsCon = new GridBagConstraints();
        itemsCon.gridheight = 1;
        itemsCon.gridwidth = 1;
        itemsCon.weightx = 0;
        itemsCon.weighty = 0;
        itemsCon.gridx = 0;
        itemsCon.gridy = 0;
        itemsCon.ipadx = 23;
        itemsCon.ipady = 50;

        for (i = 0; i < 6; i++) {
                for (j = 0; j < 5; j++) {
                image = new ImageIcon(new ImageIcon(this.pathNames[k]).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                nameLabel = new JLabel("Smoothie " + (char)('A'+i)+""+(j+1), image, SwingConstants.CENTER);
                nameLabel.setHorizontalTextPosition(JLabel.CENTER);
                nameLabel.setVerticalTextPosition(JLabel.BOTTOM);
                nameLabel.setForeground(Color.BLACK);
                nameLabel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                panel.add(nameLabel, itemsCon);
                itemsCon.gridx++;
                if (k < 26) {
                    k++;
                }
            }
            itemsCon.gridy++;
            itemsCon.gridx = 0;
        }
        return panel;
    }
    
    public void setItemBtn1Listener(ActionListener Actn) {
        this.itemBtn1.addActionListener(Actn);
    }
    
    public void setItemBtn2Listener(ActionListener Actn) {
        this.itemBtn2.addActionListener(Actn);
    }

    public void setItemsFieldText(String text) {
        this.itemsField.setText(text);
    }

    public void setOpenMListener(ActionListener actn) {
        this.openM.addActionListener(actn);
    }

    public void setOpenVListener(ActionListener actn) {
        this.openV.addActionListener(actn);
    }

    public void setExitMListerner(ActionListener actn){
        this.exitM.addActionListener(actn);
    }

    public void setExitVListerner(ActionListener actn){
        this.exitM.addActionListener(actn);
    }

    public JFrame getMainFrame() {
        return this.mainFrame;
    }

    public JFrame getMaintenanceFrame() {
        return this.maintenanceFrame;
    }

    public String getItemsFieldText() {
        return this.itemsField.getText();
    }

    public static void main(String[] args){
        SpecialVendingMachine machine = new SpecialVendingMachine();
        machine.initialization(machine.getStoredMoney());
        machine.initialization(machine.getUserMoney());
        machine.initialization(machine.getVendoItem());
        SpecialVendingMachineUI ui = new SpecialVendingMachineUI(machine);
    }
}

