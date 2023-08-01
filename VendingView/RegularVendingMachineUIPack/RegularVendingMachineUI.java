package VendingView.RegularVendingMachineUIPack;

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
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * This is the UI for the <code>RegularVendingMachine</code>.
 */
public class RegularVendingMachineUI {

    private String[] pathNames;
    private JFrame mainFrame, maintenanceFrame;
    private JMenuBar menu, maintenanceMenu;
    private JMenu maintenanceMItem, vendingMItem;
    private JMenuItem openM, exitM, openV, exitV;
    private Font digitalFont;
    private ImageIcon icon;
    private JLabel receiptLabel, productLabel;
    private JPanel itemsPanel, productPanel, inputsPanel;// Front portion
    private JLabel[][] items;
    private JTextField cashField, itemsField;
    private JTextArea receiptArea, processArea;
    private JScrollPane receiptPane;
    private JButton cashBtn1, cashBtn2, cashBtn3, cashBtn4, cashBtn5, cashBtn6, cashBtn7, cashBtn8, cashBtn9;// Buttons for Cash Inputs
    private JButton itemBtn1, itemBtn2, itemBtn3, itemBtn4, itemBtn5, itemBtnA, itemBtnB, itemBtnC, itemBtnD, itemBtnE, itemBtnF, itemBtnCon, itemBtnCan;// Buttons for Item inputs
    private JPanel addItemsPanel, displayPanel, miscMaintenancePanel;//Back Portion
    private JLabel nameLabel, typeLabel, priceLabel, quantityLabel, caloriesLabel;
    private JTextField nameField, typeField, priceField, quantityField, caloriesField;
    private JButton addItemBtn, clearBtn;
    private JTextField inputMoneyField, incDecField;
    private JButton mCashBtn1, mCashBtn2, mCashBtn3, mCashBtn4, mCashBtn5, mCashBtn6, mCashBtn7, mCashBtn8, mCashBtn9;
    private JButton mItemBtn1, mItemBtn2, mItemBtn3, mItemBtn4, mItemBtn5, mItemBtnA, mItemBtnB, mItemBtnC, mItemBtnD, mItemBtnE, mItemBtnF, mItemBtnAdd, mItemBtnDec, mItemBtnClear;
    private JTextField labelField, newPriceField;
    private JButton mPriceBtn1, mPriceBtn2, mPriceBtn3, mPriceBtn4, mPriceBtn5, mPriceBtnA, mPriceBtnB, mPriceBtnC, mPriceBtnD, mPriceBtnE, mPriceBtnF, mPriceBtnCon, mPriceBtnCan;
    private JButton checkDenomBtn, showTransactionsBtn, collectMoneyBtn;
    private JLabel mDisplayLabel;
    private JTextArea mDisplayArea;
    private JScrollPane mDisplayPane;

    /**
     * This is the constructor for the <code>RegularVendingMachine</code> UI. It will show the different components of the machine
     */
    public RegularVendingMachineUI(){
        this.pathNames = setPathNames();
        this.items = new JLabel[6][5];

        try {
            this.digitalFont = Font.createFont(Font.TRUETYPE_FONT, new File("VendingView/Images/neon_pixel-7.ttf")).deriveFont(Font.BOLD);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(digitalFont);
        } catch (Exception e) {
            
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
        this.inputsPanel = new JPanel(new GridLayout(3, 1));
        this.inputsPanel.setPreferredSize(new Dimension(225, 960));
        this.inputsPanel.setMinimumSize(new Dimension(225, 960));
        this.inputsPanel.setBackground(Color.black);
        this.inputsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.gray));

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

        //Text area for the receipt panel
        this.receiptArea = new JTextArea("", 5, 25);
        this.receiptArea.setEditable(false);
        this.receiptArea.setFont(digitalFont.deriveFont(15f));
        this.receiptPane = new JScrollPane(receiptArea);
        this.receiptPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.receiptPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.receiptPane.setPreferredSize(new Dimension(200, 250));
        this.receiptPane.setMinimumSize(new Dimension(200, 250));

        //Text area for the process
        this.processArea = new JTextArea("", 5, 25);
        this.processArea.setFont(digitalFont.deriveFont(24f));
        this.processArea.setBackground(Color.black);
        this.processArea.setForeground(Color.green);
        this.processArea.setCaretColor(Color.green);
        this.processArea.setEditable(true);
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
            panelCon.weighty = 0.5;
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
            panelCon.weightx = 0.5;
            panelCon.weighty = 0.5;
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
        this.maintenanceFrame.getContentPane().setBackground(Color.RED);
        this.maintenanceFrame.setSize(750, 1010);
        this.maintenanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.maintenanceFrame.setLocationRelativeTo(null);
        this.maintenanceFrame.setResizable(false);

         //Maintenance menu bar setup
        this.vendingMItem.add(this.openV);
        this.vendingMItem.add(this.exitV);
        this.maintenanceMenu.add(this.vendingMItem);
        this.maintenanceFrame.setJMenuBar(this.maintenanceMenu);

        //Panel for the input item function
        this.addItemsPanel = new JPanel(new GridBagLayout());
        this.addItemsPanel.setBackground(Color.darkGray);
        this.addItemsPanel.setPreferredSize(new Dimension(500, 720));
        this.addItemsPanel.setMinimumSize(new Dimension(500, 720));
        this.addItemsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.white));

        //Panel for display
        this.displayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.displayPanel.setPreferredSize(new Dimension(500, 265));
        this.displayPanel.setMinimumSize(new Dimension(500, 265));
        this.displayPanel.setBackground(Color.BLACK);
        this.displayPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.gray));

        //Input denominations panel
        this.miscMaintenancePanel = new JPanel(new GridLayout(4, 1));
        this.miscMaintenancePanel.setPreferredSize(new Dimension(225, 960));
        this.miscMaintenancePanel.setMinimumSize(new Dimension(225, 960));
        this.miscMaintenancePanel.setBackground(Color.black);
        this.miscMaintenancePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.gray));

        //Label for name inputs
        this.nameLabel = new JLabel("Name: ", SwingConstants.RIGHT);
        this.nameLabel.setFont(digitalFont.deriveFont(21f));
        this.nameLabel.setForeground(Color.white);

        //Label for quantity inputs
        this.quantityLabel = new JLabel("Quantity: ", SwingConstants.RIGHT);
        this.quantityLabel.setFont(digitalFont.deriveFont(21f));
        this.quantityLabel.setForeground(Color.white);

        //Label for price
        this.priceLabel = new JLabel("Price: ", SwingConstants.RIGHT);
        this.priceLabel.setFont(digitalFont.deriveFont(21f));
        this.priceLabel.setForeground(Color.white);

        //Label for calories
        this.caloriesLabel = new JLabel("Calories: ", SwingConstants.RIGHT);
        this.caloriesLabel.setFont(digitalFont.deriveFont(21f));
        this.caloriesLabel.setForeground(Color.white);

        //Label for item type
        this.typeLabel = new JLabel("Type: ", SwingConstants.RIGHT);
        this.typeLabel.setFont(digitalFont.deriveFont(21f));
        this.typeLabel.setForeground(Color.white);

        //Label for maintenance display
        this.mDisplayLabel = new JLabel("Display for Maintenance", SwingConstants.CENTER);
        this.mDisplayLabel.setFont(digitalFont.deriveFont(21f));
        this.mDisplayLabel.setForeground(Color.GREEN);  
        this.mDisplayLabel.setForeground(Color.GREEN);

        //Text Field for the name
        this.nameField = new JTextField("Input New Item's Name", 30);
        this.nameField.setBackground(Color.BLACK);
        this.nameField.setForeground(Color.white);
        this.nameField.setFont(digitalFont.deriveFont(21f));
        this.nameField.setHorizontalAlignment(SwingConstants.CENTER);
        this.nameField.setMinimumSize(new Dimension(270, 25));        

        //Text Field for the quantity
        this.quantityField = new JTextField("Input New Item's Quantity", 30);
        this.quantityField.setBackground(Color.BLACK);
        this.quantityField.setForeground(Color.white);
        this.quantityField.setFont(digitalFont.deriveFont(21f));
        this.quantityField.setHorizontalAlignment(SwingConstants.CENTER);
        this.quantityField.setMinimumSize(new Dimension(200, 25));

        //Text Field for the price
        this.priceField = new JTextField("Input New Item's Price", 30);
        this.priceField.setBackground(Color.BLACK);
        this.priceField.setForeground(Color.white);
        this.priceField.setFont(digitalFont.deriveFont(21f));
        this.priceField.setHorizontalAlignment(SwingConstants.CENTER);
        this.priceField.setMinimumSize(new Dimension(200, 25));

        //Text Field for the calories
        this.caloriesField = new JTextField("Input New Item's Calories", 30);
        this.caloriesField.setBackground(Color.BLACK);
        this.caloriesField.setForeground(Color.white);
        this.caloriesField.setFont(digitalFont.deriveFont(21f));
        this.caloriesField.setHorizontalAlignment(SwingConstants.CENTER);
        this.caloriesField.setMinimumSize(new Dimension(200, 25));

        //Text field for the item type
        this.typeField = new JTextField("Input New Item's type", 30);
        this.typeField.setBackground(Color.BLACK);
        this.typeField.setForeground(Color.white);
        this.typeField.setFont(digitalFont.deriveFont(21f));
        this.typeField.setHorizontalAlignment(SwingConstants.CENTER);
        this.typeField.setMinimumSize(new Dimension(200, 25));

        //Money field for the machine
        this.inputMoneyField = new JTextField("Input Money", 20);
        this.inputMoneyField.setBackground(Color.BLACK);
        this.inputMoneyField.setForeground(Color.GREEN);
        this.inputMoneyField.setFont(digitalFont.deriveFont(21f));
        this.inputMoneyField.setHorizontalAlignment(SwingConstants.CENTER);
        this.inputMoneyField.setMinimumSize(new Dimension(200, 25));
        this.inputMoneyField.setEditable(false);

        //Text field for item increase/decrease
        this.incDecField = new JTextField("Input Item Label", 20);
        this.incDecField.setBackground(Color.BLACK);
        this.incDecField.setForeground(Color.GREEN);
        this.incDecField.setFont(digitalFont.deriveFont(21f));
        this.incDecField.setHorizontalAlignment(SwingConstants.CENTER);
        this.incDecField.setMinimumSize(new Dimension(200, 25));
        this.incDecField.setEditable(false);

        //Text Field for slot label in change price panel
        this.labelField = new JTextField("Item", 7);
        this.labelField.setBackground(Color.BLACK);
        this.labelField.setForeground(Color.GREEN);
        this.labelField.setFont(digitalFont.deriveFont(21f));
        this.labelField.setHorizontalAlignment(SwingConstants.CENTER);
        this.labelField.setMinimumSize(new Dimension(80, 25));
        this.labelField.setEditable(false);

        //Text Field for new price in change price panel
        this.newPriceField = new JTextField("Price", 7);
        this.newPriceField.setBackground(Color.BLACK);
        this.newPriceField.setForeground(Color.GREEN);
        this.newPriceField.setFont(digitalFont.deriveFont(21f));
        this.newPriceField.setHorizontalAlignment(SwingConstants.CENTER);
        this.newPriceField.setMinimumSize(new Dimension(80, 25));

        this.mDisplayArea = new JTextArea();
        this.mDisplayArea.setEditable(false);
        this.mDisplayArea.setFont(digitalFont.deriveFont(17f));
        this.mDisplayArea.setForeground(Color.RED);
        this.mDisplayPane = new JScrollPane(mDisplayArea);
        this.mDisplayPane.setPreferredSize(new Dimension(480, 200));
        this.mDisplayPane.setMinimumSize(new Dimension(480, 200));
        this.mDisplayPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.mDisplayPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Buttons for the add items
        this.addItemBtn = new JButton("Add");
        this.addItemBtn.setBackground(Color.green);
        this.addItemBtn.setForeground(Color.white);
        this.addItemBtn.setFont(digitalFont.deriveFont(21f));
        this.clearBtn = new JButton("x");
        this.clearBtn.setBackground(Color.red);
        this.clearBtn.setForeground(Color.white);
        this.clearBtn.setFont(digitalFont.deriveFont(21f));
        
        this.mCashBtn1 = new JButton("1");
        this.mCashBtn1.setFont(digitalFont.deriveFont(15f));
        this.mCashBtn1.setPreferredSize(new Dimension(55, 55));
        this.mCashBtn2 = new JButton("5");
        this.mCashBtn2.setFont(digitalFont.deriveFont(15f));
        this.mCashBtn2.setPreferredSize(new Dimension(55, 55));
        this.mCashBtn3 = new JButton("10");
        this.mCashBtn3.setFont(digitalFont.deriveFont(15f));
        this.mCashBtn3.setPreferredSize(new Dimension(55, 55));
        this.mCashBtn4 = new JButton("20");
        this.mCashBtn4.setFont(digitalFont.deriveFont(15f));
        this.mCashBtn4.setPreferredSize(new Dimension(55, 55));
        this.mCashBtn5 = new JButton("50");
        this.mCashBtn5.setFont(digitalFont.deriveFont(15f));
        this.mCashBtn5.setPreferredSize(new Dimension(55, 55));
        this.mCashBtn6 = new JButton("100");
        this.mCashBtn6.setFont(digitalFont.deriveFont(15f));
        this.mCashBtn6.setPreferredSize(new Dimension(55, 55));
        this.mCashBtn7 = new JButton("200");
        this.mCashBtn7.setFont(digitalFont.deriveFont(15f));
        this.mCashBtn7.setPreferredSize(new Dimension(55, 55));
        this.mCashBtn8 = new JButton("500");
        this.mCashBtn8.setFont(digitalFont.deriveFont(15f));
        this.mCashBtn8.setPreferredSize(new Dimension(55, 55));
        this.mCashBtn9 = new JButton("1k");
        this.mCashBtn9.setFont(digitalFont.deriveFont(15f));
        this.mCashBtn9.setPreferredSize(new Dimension(55, 55));

        //Item input buttons
        this.mItemBtn1 = new JButton("1");
        this.mItemBtn1.setFont(digitalFont.deriveFont(12.2f));
        this.mItemBtn1.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtn2 = new JButton("2");
        this.mItemBtn2.setFont(digitalFont.deriveFont(12.2f));
        this.mItemBtn2.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtn3 = new JButton("3");
        this.mItemBtn3.setFont(digitalFont.deriveFont(12.2f));
        this.mItemBtn3.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtn4 = new JButton("4");
        this.mItemBtn4.setFont(digitalFont.deriveFont(12f));
        this.mItemBtn4.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtn5 = new JButton("5");
        this.mItemBtn5.setFont(digitalFont.deriveFont(12.2f));
        this.mItemBtn5.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtnA = new JButton("A");
        this.mItemBtnA.setFont(digitalFont.deriveFont(12.2f));
        this.mItemBtnA.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtnB = new JButton("B");
        this.mItemBtnB.setFont(digitalFont.deriveFont(12.2f));
        this.mItemBtnB.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtnC = new JButton("C");
        this.mItemBtnC.setFont(digitalFont.deriveFont(12.2f));
        this.mItemBtnC.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtnD = new JButton("D");
        this.mItemBtnD.setFont(digitalFont.deriveFont(12.2f));
        this.mItemBtnD.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtnE = new JButton("E");
        this.mItemBtnE.setFont(digitalFont.deriveFont(12.2f));
        this.mItemBtnE.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtnF = new JButton("F");
        this.mItemBtnF.setFont(digitalFont.deriveFont(12.2f));
        this.mItemBtnF.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtnAdd = new JButton("+");
        this.mItemBtnAdd.setBackground(Color.green);
        this.mItemBtnAdd.setForeground(Color.white);
        this.mItemBtnAdd.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtnDec = new JButton("-");
        this.mItemBtnDec.setFont(digitalFont.deriveFont(12.2f));
        this.mItemBtnDec.setBackground(Color.red);
        this.mItemBtnDec.setForeground(Color.white);
        this.mItemBtnDec.setMargin(new Insets(10, 12, 10, 12));
        this.mItemBtnClear = new JButton("x");
        this.mItemBtnClear.setFont(digitalFont.deriveFont(12.2f));
        this.mItemBtnClear.setBackground(Color.red);
        this.mItemBtnClear.setForeground(Color.white);
        this.mItemBtnClear.setMargin(new Insets(10, 12, 10, 12));

        //Item input buttons
        this.mPriceBtn1 = new JButton("1");
        this.mPriceBtn1.setFont(digitalFont.deriveFont(12.2f));
        this.mPriceBtn1.setMargin(new Insets(10, 12, 10, 12));
        this.mPriceBtn2 = new JButton("2");
        this.mPriceBtn2.setFont(digitalFont.deriveFont(12.2f));
        this.mPriceBtn2.setMargin(new Insets(10, 12, 10, 12));
        this.mPriceBtn3 = new JButton("3");
        this.mPriceBtn3.setFont(digitalFont.deriveFont(12.2f));
        this.mPriceBtn3.setMargin(new Insets(10, 12, 10, 12));
        this.mPriceBtn4 = new JButton("4");
        this.mPriceBtn4.setFont(digitalFont.deriveFont(12f));
        this.mPriceBtn4.setMargin(new Insets(10, 12, 10, 12));
        this.mPriceBtn5 = new JButton("5");
        this.mPriceBtn5.setFont(digitalFont.deriveFont(12.2f));
        this.mPriceBtn5.setMargin(new Insets(10, 12, 10, 12));
        this.mPriceBtnA = new JButton("A");
        this.mPriceBtnA.setFont(digitalFont.deriveFont(12.2f));
        this.mPriceBtnA.setMargin(new Insets(10, 12, 10, 12));
        this.mPriceBtnB = new JButton("B");
        this.mPriceBtnB.setFont(digitalFont.deriveFont(12.2f));
        this.mPriceBtnB.setMargin(new Insets(10, 12, 10, 12));
        this.mPriceBtnC = new JButton("C");
        this.mPriceBtnC.setFont(digitalFont.deriveFont(12.2f));
        this.mPriceBtnC.setMargin(new Insets(10, 12, 10, 12));
        this.mPriceBtnD = new JButton("D");
        this.mPriceBtnD.setFont(digitalFont.deriveFont(12.2f));
        this.mPriceBtnD.setMargin(new Insets(10, 12, 10, 12));
        this.mPriceBtnE = new JButton("E");
        this.mPriceBtnE.setFont(digitalFont.deriveFont(12.2f));
        this.mPriceBtnE.setMargin(new Insets(10, 12, 10, 12));
        this.mPriceBtnF = new JButton("F");
        this.mPriceBtnF.setFont(digitalFont.deriveFont(12.2f));
        this.mPriceBtnF.setMargin(new Insets(10, 12, 10, 12));
        this.mPriceBtnCon = new JButton("\u2713");
        this.mPriceBtnCon.setBackground(Color.green);
        this.mPriceBtnCon.setForeground(Color.white);
        this.mPriceBtnCon.setMargin(new Insets(10, 12, 10, 12));
        this.mPriceBtnCan = new JButton("x");
        this.mPriceBtnCan.setFont(digitalFont.deriveFont(12.2f));
        this.mPriceBtnCan.setBackground(Color.red);
        this.mPriceBtnCan.setForeground(Color.white);
        this.mPriceBtnCan.setMargin(new Insets(10, 12, 10, 12));

        this.checkDenomBtn = new JButton("Check Money");
        this.checkDenomBtn.setFont(digitalFont.deriveFont(21f));
        this.checkDenomBtn.setBackground(Color.yellow);
        this.showTransactionsBtn = new JButton("Show Transactions");
        this.showTransactionsBtn.setFont(digitalFont.deriveFont(21f));
        this.showTransactionsBtn.setBackground(Color.orange);
        this.collectMoneyBtn = new JButton("Collect Money");
        this.collectMoneyBtn.setFont(digitalFont.deriveFont(21f));
        this.collectMoneyBtn.setBackground(Color.cyan);

        GridBagConstraints mPanelCon = new GridBagConstraints();

        mPanelCon.gridx = 0;
        mPanelCon.gridy = 0;
        mPanelCon.weightx = 0.5;
        mPanelCon.weighty = 0.5;
        mPanelCon.gridheight = 1;
        mPanelCon.gridwidth = 1;
        mPanelCon.anchor = GridBagConstraints.CENTER;

        this.addItemsPanel.add(nameLabel, mPanelCon);

        mPanelCon.gridx = 1;
        mPanelCon.gridy = 0;

        JPanel panel2 = new JPanel();
        panel2.add(nameField, mPanelCon);

        this.addItemsPanel.add(panel2);  

        mPanelCon.gridx = 0;
        mPanelCon.gridy = 1;

        this.addItemsPanel.add(quantityLabel, mPanelCon);

        mPanelCon.gridx = 1;
        mPanelCon.gridy = 1;

        panel2 = new JPanel();
        panel2.add(quantityField);
        
        this.addItemsPanel.add(panel2, mPanelCon);

        mPanelCon.gridx = 0;
        mPanelCon.gridy = 2;

        this.addItemsPanel.add(priceLabel, mPanelCon);

        mPanelCon.gridx = 1;
        mPanelCon.gridy = 2;

        panel2 = new JPanel();
        panel2.add(priceField);
        
        this.addItemsPanel.add(panel2, mPanelCon);

        mPanelCon.gridx = 0;
        mPanelCon.gridy = 3;

        this.addItemsPanel.add(caloriesLabel, mPanelCon);

        mPanelCon.gridx = 1;
        mPanelCon.gridy = 3;

        panel2 = new JPanel();
        panel2.add(caloriesField);
        
        this.addItemsPanel.add(panel2, mPanelCon);

        mPanelCon.gridx = 0;
        mPanelCon.gridy = 4;

        this.addItemsPanel.add(typeLabel, mPanelCon);

        mPanelCon.gridx = 1;
        mPanelCon.gridy = 4;

        panel2 = new JPanel();
        panel2.add(typeField);
        
        this.addItemsPanel.add(panel2, mPanelCon);

        mPanelCon.gridx = 0;
        mPanelCon.gridy = 5;
        mPanelCon.fill = GridBagConstraints.BOTH;

        this.addItemsPanel.add(clearBtn, mPanelCon);

        mPanelCon.gridx = 1;
        mPanelCon.gridy = 5;

        this.addItemsPanel.add(addItemBtn, mPanelCon);

        this.displayPanel.add(mDisplayLabel);
        this.displayPanel.add(mDisplayPane);

        //panel for input cash
        panel2 = new JPanel(new GridBagLayout());
        panel2.setBackground(Color.gray);
        panel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
            mPanelCon.gridx = 0;
            mPanelCon.gridy = 0;
            mPanelCon.gridheight = 1;
            mPanelCon.gridwidth = 4;
            mPanelCon.weightx = 0;
            mPanelCon.weighty = 1;
            mPanelCon.anchor = GridBagConstraints.CENTER;
            mPanelCon.fill = GridBagConstraints.NONE;
            panel2.add(inputMoneyField, mPanelCon);

            mPanelCon.gridx = 0;
            mPanelCon.gridy = 1;
            mPanelCon.gridheight = 1;
            mPanelCon.gridwidth = 1;
            mPanelCon.weightx = 1;
            mPanelCon.weighty = 1;
            panel2.add(mCashBtn1, mPanelCon);

            mPanelCon.gridx = 1;
            mPanelCon.gridy = 1;
            panel2.add(mCashBtn2, mPanelCon);

            mPanelCon.gridx = 2;
            mPanelCon.gridy = 1;
            panel2.add(mCashBtn3, mPanelCon);

            mPanelCon.gridx = 0;
            mPanelCon.gridy = 2;
            panel2.add(mCashBtn4, mPanelCon);

            mPanelCon.gridx = 1;
            mPanelCon.gridy = 2;
            panel2.add(mCashBtn5, mPanelCon);

            mPanelCon.gridx = 2;
            mPanelCon.gridy = 2;
            panel2.add(mCashBtn6, mPanelCon);

            mPanelCon.insets = new Insets(0, 0, 1, 0);
            mPanelCon.gridx = 0;
            mPanelCon.gridy = 3;
            panel2.add(mCashBtn7, mPanelCon);

            mPanelCon.gridx = 1;
            mPanelCon.gridy = 3;
            panel2.add(mCashBtn8, mPanelCon);

            mPanelCon.gridx = 2;
            mPanelCon.gridy = 3;
            panel2.add(mCashBtn9, mPanelCon);
        
            this.miscMaintenancePanel.add(panel2);

        //Panel for item inputs
        panel2 = new JPanel(new GridBagLayout());
        panel2.setBackground(Color.DARK_GRAY);
        panel2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

            mPanelCon.gridx = 0;
            mPanelCon.gridy = 0;
            mPanelCon.insets = new Insets(5, 5, 5, 5);
            mPanelCon.weightx = 0;
            mPanelCon.weighty = 0;
            mPanelCon.gridheight = 1;
            mPanelCon.gridwidth = 5;
            
            panel2.add(incDecField, mPanelCon);

            mPanelCon.gridx = 0;
            mPanelCon.gridy = 1;
            mPanelCon.gridheight = 1;
            mPanelCon.gridwidth = 1;
            
            panel2.add(mItemBtnA, mPanelCon);

            mPanelCon.gridx = 1;
            mPanelCon.gridy = 1;
            panel2.add(mItemBtnB, mPanelCon);

            mPanelCon.gridx = 2;
            mPanelCon.gridy = 1;
            panel2.add(mItemBtnC, mPanelCon);

            mPanelCon.gridx = 0;
            mPanelCon.gridy = 2;
            panel2.add(mItemBtnD, mPanelCon);

            mPanelCon.gridx = 1;
            mPanelCon.gridy = 2;
            panel2.add(mItemBtnE, mPanelCon);

            mPanelCon.gridx = 2;
            mPanelCon.gridy = 2;
            panel2.add(mItemBtnF, mPanelCon);

            mPanelCon.gridx = 0;
            mPanelCon.gridy = 3;
            panel2.add(mItemBtn1, mPanelCon);
            
            mPanelCon.gridx = 1;
            mPanelCon.gridy = 3;
            panel2.add(mItemBtn2, mPanelCon);

            mPanelCon.gridx = 2;
            mPanelCon.gridy = 3;
            panel2.add(mItemBtn3, mPanelCon);

            mPanelCon.gridx = 0;
            mPanelCon.gridy = 4;
            panel2.add(mItemBtn4, mPanelCon);

            mPanelCon.gridx = 1;
            mPanelCon.gridy = 4;
            panel2.add(mItemBtn5, mPanelCon);

            mPanelCon.gridx = 4;
            mPanelCon.gridy = 1;
            panel2.add(mItemBtnClear, mPanelCon);

            mPanelCon.gridx = 4;
            mPanelCon.gridy = 2;
            panel2.add(mItemBtnAdd, mPanelCon);

            mPanelCon.gridx = 4;
            mPanelCon.gridy = 3;
            panel2.add(mItemBtnDec, mPanelCon);

            this.miscMaintenancePanel.add(panel2);

        //Panel for item inputs
        panel2 = new JPanel(new GridBagLayout());
        panel2.setBackground(Color.DARK_GRAY);
        panel2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

            mPanelCon.gridx = 0;
            mPanelCon.gridy = 0;
            mPanelCon.insets = new Insets(5, 5, 5, 0);
            mPanelCon.weightx = 0;
            mPanelCon.weighty = 0;
            mPanelCon.gridheight = 1;
            mPanelCon.gridwidth = 2;
            
            panel2.add(labelField, mPanelCon);

            mPanelCon.gridx = 3;
            mPanelCon.gridy = 0;
            panel2.add(newPriceField, mPanelCon);

            mPanelCon.gridx = 0;
            mPanelCon.gridy = 1;
            mPanelCon.gridheight = 1;
            mPanelCon.gridwidth = 1;
            
            panel2.add(mPriceBtnA, mPanelCon);

            mPanelCon.gridx = 1;
            mPanelCon.gridy = 1;
            panel2.add(mPriceBtnB, mPanelCon);

            mPanelCon.gridx = 2;
            mPanelCon.gridy = 1;
            panel2.add(mPriceBtnC, mPanelCon);

            mPanelCon.gridx = 0;
            mPanelCon.gridy = 2;
            panel2.add(mPriceBtnD, mPanelCon);

            mPanelCon.gridx = 1;
            mPanelCon.gridy = 2;
            panel2.add(mPriceBtnE, mPanelCon);

            mPanelCon.gridx = 2;
            mPanelCon.gridy = 2;
            panel2.add(mPriceBtnF, mPanelCon);

            mPanelCon.gridx = 0;
            mPanelCon.gridy = 3;
            panel2.add(mPriceBtn1, mPanelCon);
            
            mPanelCon.gridx = 1;
            mPanelCon.gridy = 3;
            panel2.add(mPriceBtn2, mPanelCon);

            mPanelCon.gridx = 2;
            mPanelCon.gridy = 3;
            panel2.add(mPriceBtn3, mPanelCon);

            mPanelCon.gridx = 0;
            mPanelCon.gridy = 4;
            panel2.add(mPriceBtn4, mPanelCon);

            mPanelCon.gridx = 1;
            mPanelCon.gridy = 4;
            panel2.add(mPriceBtn5, mPanelCon);

            mPanelCon.gridx = 4;
            mPanelCon.gridy = 2;
            panel2.add(mPriceBtnCon, mPanelCon);

            mPanelCon.gridx = 4;
            mPanelCon.gridy = 3;
            panel2.add(mPriceBtnCan, mPanelCon);

            this.miscMaintenancePanel.add(panel2);

        panel2 = new JPanel(new GridLayout(3,1));
        panel2.setBackground(Color.DARK_GRAY);
        panel2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        panel2.add(checkDenomBtn);
        panel2.add(showTransactionsBtn);
        panel2.add(collectMoneyBtn);

        this.miscMaintenancePanel.add(panel2);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 1;
        c2.gridy = 0;
        c2.insets = new Insets(3,3,3,3);
        c2.weightx = 0.5;
        c2.weighty = 0.5;
        c2.gridheight = 1;
        c2.gridwidth = 1;
        c2.anchor = GridBagConstraints.NORTHEAST;
        this.maintenanceFrame.add(addItemsPanel, c2);

        c2.gridx = 0;
        c2.gridy = 1;
        c2.weightx = 0.5;
        c2.weighty = 0.5;
        c2.gridheight = 1;
        c2.gridwidth = 2;
        c2.anchor = GridBagConstraints.EAST;
        this.maintenanceFrame.add(displayPanel, c2);

        c2.gridx = 1;
        c2.gridy = 0;
        c2.weightx = 0.5;
        c2.weighty = 0;
        c2.gridheight = 2;
        c2.gridwidth = 1;
        c2.anchor = GridBagConstraints.WEST;
        this.maintenanceFrame.add(miscMaintenancePanel, c2);

        this.maintenanceFrame.setVisible(false);
        this.maintenanceFrame.revalidate();
    }

    private String[] setPathNames(){
        String[] pathNames = {
                                "VendingView/Images/Items/A1.png",
                                "VendingView/Images/Items/A2.jpg",
                                "VendingView/Images/Items/A3.png",
                                "VendingView/Images/Items/A4.png",
                                "VendingView/Images/Items/A5.png",
                                "VendingView/Images/Items/B1.jpg",
                                "VendingView/Images/Items/B2.jpg",
                                "VendingView/Images/Items/B3.jpg",
                                "VendingView/Images/Items/B4.jpg",
                                "VendingView/Images/Items/B5.png",
                                "VendingView/Images/Items/C1.png",
                                "VendingView/Images/Items/C2.jpg",
                                "VendingView/Images/Items/C3.jpg",
                                "VendingView/Images/Items/C4.jpg",
                                "VendingView/Images/Items/C5.png",
                                "VendingView/Images/Items/D1.jpg",
                                "VendingView/Images/Items/D2.png",
                                "VendingView/Images/Items/D3.png",
                                "VendingView/Images/Items/D4.jpg",
                                "VendingView/Images/Items/D5.png",
                                "VendingView/Images/Items/E1.jpg",
                                "VendingView/Images/Items/E2.jpg",
                                "VendingView/Images/Items/E3.png",
                                "VendingView/Images/Items/E4.jpg",
                                "VendingView/Images/Items/E5.png",
                                "VendingView/Images/Items/F1.jpg",
                                "VendingView/Images/Items/Empty.png"
                            };
        return pathNames;
    }

    private JPanel setItems(){
        int i,j, k = 0;
        ImageIcon image;

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
                this.items[i][j] = new JLabel(image, SwingConstants.CENTER);
                this.items[i][j].setPreferredSize(new Dimension(75, 63));
                this.items[i][j].setMinimumSize(new Dimension(75, 63));
                this.items[i][j].setHorizontalTextPosition(JLabel.CENTER);
                this.items[i][j].setVerticalTextPosition(JLabel.BOTTOM);
                this.items[i][j].setForeground(Color.BLACK);
                this.items[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                panel.add(this.items[i][j], itemsCon);
                itemsCon.gridx++;
                if (k < this.pathNames.length - 1) {
                    k++;
                }
            }
            itemsCon.gridy++;
            itemsCon.gridx = 0;
        }
        return panel;
    }

    /**
     * Sets the <code>actionListener</code> for the Cash button of P1
     * @param actn input P1
     */
    public void setCashBtn1Listener(ActionListener actn) {
        this.cashBtn1.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Cash button of P5
     * @param actn input P5
     */
    public void setCashBtn2Listener(ActionListener actn) {
        this.cashBtn2.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Cash button of P10
     * @param actn input P10
     */
    public void setCashBtn3Listener(ActionListener actn) {
        this.cashBtn3.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Cash button of P20
     * @param actn input P20
     */
    public void setCashBtn4Listener(ActionListener actn) {
        this.cashBtn4.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Cash button of P50
     * @param actn input P50
     */
    public void setCashBtn5Listener(ActionListener actn) {
        this.cashBtn5.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Cash button of P100
     * @param actn input P100
     */
    public void setCashBtn6Listener(ActionListener actn) {
        this.cashBtn6.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Cash button of P200
     * @param actn input P200
     */
    public void setCashBtn7Listener(ActionListener actn) {
        this.cashBtn7.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Cash button of P500
     * @param actn input P500
     */
    public void setCashBtn8Listener(ActionListener actn) {
        this.cashBtn8.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Cash button of P1000
     * @param actn input P1000
     */
    public void setCashBtn9Listener(ActionListener actn) {
        this.cashBtn9.addActionListener(actn);
    }    
    
    /**
     * Sets the <code>actionListener</code> for the Item button of input 1
     * @param actn input 1
     */
    public void setItemBtn1Listener(ActionListener actn) {
        this.itemBtn1.addActionListener(actn);
    }
    
    /**
     * Sets the <code>actionListener</code> for the Item button of input 2
     * @param actn input 2
     */
    public void setItemBtn2Listener(ActionListener actn) {
        this.itemBtn2.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Item button of input 3
     * @param actn input 3
     */
    public void setItemBtn3Listener(ActionListener actn) {
        this.itemBtn3.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Item button of input 4
     * @param actn input 5
     */
    public void setItemBtn4Listener(ActionListener actn) {
        this.itemBtn4.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Item button of input 5
     * @param actn input 5
     */
    public void setItemBtn5Listener(ActionListener actn) {
        this.itemBtn5.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Item button of input A
     * @param actn input A
     */
    public void setItemBtnAListener(ActionListener actn) {
        this.itemBtnA.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Item button of input B
     * @param actn input B
     */
    public void setItemBtnBListener(ActionListener actn) {
        this.itemBtnB.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Item button of input C
     * @param actn input C
     */
    public void setItemBtnCListener(ActionListener actn) {
        this.itemBtnC.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Item button of input D
     * @param actn input D
     */
    public void setItemBtnDListener(ActionListener actn) {
        this.itemBtnD.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Item button of input E
     * @param actn input E
     */
    public void setItemBtnEListener(ActionListener actn) {
        this.itemBtnE.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the Item button of input F
     * @param actn input F
     */
    public void setItemBtnFListener(ActionListener actn) {
        this.itemBtnF.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the buy item button
     * @param actn buy item
     */
    public void setItemBtnConListener(ActionListener actn) {
        this.itemBtnCon.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the clear button
     * @param actn clear fields
     */
    public void setItemBtnCanListener(ActionListener actn) {
        this.itemBtnCan.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the openM menu item
     * @param actn opens maintenance
     */
    public void setOpenMListener(ActionListener actn) {
        this.openM.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the exitM menu item
     * @param actn exit maintenance
     */
    public void setExitMListerner(ActionListener actn){
        this.exitM.addActionListener(actn);
    }

    /**
     * Sets the text for the process area
     * @param text processing text
     */
    public void setProcessAreaText(String text) {
        this.processArea.setText(text);
    }

    /**
     * Sets the text on the cash field
     * @param text total money string
     */
    public void setCashFieldText(String text){ 
        this.cashField.setText(text);
    }
    
    /**
     * Sets the text for the array of labels for items
     * @param row row value of the array
     * @param col collumn value of the array
     * @param text text of the label
     */
    public void setItemsText(int row, int col, String text){
        this.items[row][col].setText(text);
    }

    /**
     * Sets the image icon of the product
     * @param image image of product
     */
    public void setProductIcon(ImageIcon image) {
        this.productLabel.setIcon(image);
    }

    /**
     * Sets the text in the receipt area
     * @param text receipt
     */
    public void setReceiptAreaText(String text){
        this.receiptArea.setText(text);
    }

    /**
     * Sets the texts in the items field
     * @param text item label
     */
    public void setItemsFieldText(String text) {
        this.itemsField.setText(text);
    }

    /**
     * Sets the cash field to its initial text
     */
    public void clearCashField() {
        this.cashField.setText("Cash Amount");
    }

    /**
     * Sets the initial text of items field
     */
    public void clearItemsField(){
        this.itemsField.setText("Input Item Label");
    }

    /**
     * Gets the main frame of the ui
     * @return main frame
     */
    public JFrame getMainFrame() {
        return this.mainFrame;
    }
    
    /**
     * Gets the text from the items field
     * @return label of slot
     */
    public String getItemsFieldText() {
        return this.itemsField.getText();
    }
    
    // MAINTENANCE --------------------------
    
    /**
     * Sets the <code>actionListener</code> for the add items button
     * @param actn add item
     */
    public void setAddItemBtnListener(ActionListener actn) {
        this.addItemBtn.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the clear items field button
     * @param actn clear items field
     */
    public void setClearBtnBtnListener(ActionListener actn) {
        this.clearBtn.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MCash button of P1
     * @param actn input P1 to stored money
     */
     public void setMCashBtn1Listener(ActionListener actn) {
        this.mCashBtn1.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MCash button of P5
     * @param actn input P5 to stored money
     */
    public void setMCashBtn2Listener(ActionListener actn) {
        this.mCashBtn2.addActionListener(actn);
    }
    
    /**
     * Sets the <code>actionListener</code> for the MCash button of P10
     * @param actn input P10 to stored money
     */
    public void setMCashBtn3Listener(ActionListener actn) {
        this.mCashBtn3.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MCash button of P20
     * @param actn input P20 to stored money
     */
    public void setMCashBtn4Listener(ActionListener actn) {
        this.mCashBtn4.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MCash button of P50
     * @param actn input P50 to stored money
     */
    public void setMCashBtn5Listener(ActionListener actn) {
        this.mCashBtn5.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MCash button of P100
     * @param actn input P100 to stored money
     */
    public void setMCashBtn6Listener(ActionListener actn) {
        this.mCashBtn6.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MCash button of P200
     * @param actn input P200 to stored money
     */
    public void setMCashBtn7Listener(ActionListener actn) {
        this.mCashBtn7.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MCash button of P500
     * @param actn input P500 to stored money
     */
    public void setMCashBtn8Listener(ActionListener actn) {
        this.mCashBtn8.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MCash button of P1000
     * @param actn input P1000 to stored money
     */
    public void setMCashBtn9Listener(ActionListener actn) {
        this.mCashBtn9.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input 1
     * @param actn input 1
     */
    public void setMItemBtn1Listener(ActionListener actn) {
        this.mItemBtn1.addActionListener(actn);
    }
    
    /**
     * Sets the <code>actionListener</code> for the MItem button of input 2
     * @param actn input 2
     */
    public void setMItemBtn2Listener(ActionListener actn) {
        this.mItemBtn2.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input 3
     * @param actn input 3
     */
    public void setMItemBtn3Listener(ActionListener actn) {
        this.mItemBtn3.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input 4
     * @param actn input 4
     */
    public void setMItemBtn4Listener(ActionListener actn) {
        this.mItemBtn4.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input 5
     * @param actn input 5
     */
    public void setMItemBtn5Listener(ActionListener actn) {
        this.mItemBtn5.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input A
     * @param actn input A
     */
    public void setMItemBtnAListener(ActionListener actn) {
        this.mItemBtnA.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input B
     * @param actn input B
     */
    public void setMItemBtnBListener(ActionListener actn) {
        this.mItemBtnB.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input C
     * @param actn input C
     */
    public void setMItemBtnCListener(ActionListener actn) {
        this.mItemBtnC.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input D
     * @param actn input D
     */
    public void setMItemBtnDListener(ActionListener actn) {
        this.mItemBtnD.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input E
     * @param actn input E
     */
    public void setMItemBtnEListener(ActionListener actn) {
        this.mItemBtnE.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input F
     * @param actn input F
     */
    public void setMItemBtnFListener(ActionListener actn) {
        this.mItemBtnF.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the increase button
     * @param actn increase quantity
     */
    public void setMItemBtnAddListener(ActionListener actn) {
        this.mItemBtnAdd.addActionListener(actn);
    }
    
    /**
     * Sets the <code>actionListener</code> for the decrease button
     * @param actn decrease quantity
     */
    public void setMItemBtnDecListener(ActionListener actn) {
        this.mItemBtnDec.addActionListener(actn);
    }
    
    /**
     * Sets the <code>actionListener</code> for the MItem button of input 1
     * @param actn input 1
     */
    public void setMPriceBtn1Listener(ActionListener actn) {
    this.mPriceBtn1.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input 2
     * @param actn input 2
     */
    public void setMPriceBtn2Listener(ActionListener actn) {
        this.mPriceBtn2.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input 3
     * @param actn input 3
     */
    public void setMPriceBtn3Listener(ActionListener actn) {
        this.mPriceBtn3.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input 4
     * @param actn input 4
     */
    public void setMPriceBtn4Listener(ActionListener actn) {
        this.mPriceBtn4.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input 5
     * @param actn input 5
     */
    public void setMPriceBtn5Listener(ActionListener actn) {
        this.mPriceBtn5.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input A
     * @param actn input A
     */
    public void setMPriceBtnAListener(ActionListener actn) {
        this.mPriceBtnA.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input B
     * @param actn input B
     */
    public void setMPriceBtnBListener(ActionListener actn) {
        this.mPriceBtnB.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input C
     * @param actn input C
     */
    public void setMPriceBtnCListener(ActionListener actn) {
        this.mPriceBtnC.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input D
     * @param actn input D
     */
    public void setMPriceBtnDListener(ActionListener actn) {
        this.mPriceBtnD.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input E
     * @param actn input E
     */
    public void setMPriceBtnEListener(ActionListener actn) {
        this.mPriceBtnE.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the MItem button of input F
     * @param actn input F
     */
    public void setMPriceBtnFListener(ActionListener actn) {
        this.mPriceBtnF.addActionListener(actn);
    }
    
    /**
     * Sets the <code>actionListener</code> for the change price button
     * @param actn change price
     */
    public void setmPriceBtnConListener(ActionListener actn) {
        this.mPriceBtnCon.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the clear  button for change price
     * @param actn clear 
     */
    public void setmPriceBtnCanListener(ActionListener actn) {
        this.mPriceBtnCan.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the clear button for increase decrease field
     * @param actn clear increase decrease field
     */
    public void setMItemBtnClearListener(ActionListener actn) {
        this.mItemBtnClear.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the check denominations button
     * @param actn check denominations
     */
    public void setCheckDenomBtnAddListener(ActionListener actn) {
        this.checkDenomBtn.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the show transactions button
     * @param actn show transactions
     */
    public void setShowTransactionsBtnListener(ActionListener actn) {
        this.showTransactionsBtn.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the collect money button
     * @param actn collect money
     */
    public void setCollectMoneyBtnListener(ActionListener actn) {
        this.collectMoneyBtn.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the openV button
     * @param actn opens vending machine
     */
    public void setOpenVListener(ActionListener actn) {
        this.openV.addActionListener(actn);
    }

    /**
     * Sets the <code>actionListener</code> for the exitV button
     * @param actn exits vending machine
     */
    public void setExitVListerner(ActionListener actn){
        this.exitV.addActionListener(actn);
    }

    /**
     * Sets the increase decrease field to its initial text
     */
    public void clearIncDecFieldText() {
        this.incDecField.setText("Input Item Label");
    }

    /**
     * Sets the input money field to its initial text
     */
    public void clearInputMoneyField() {
        this.inputMoneyField.setText("Input Money");
    }

    /**
     * Sets the name field to its initial text
     */
    public void clearNameField() {
        this.nameField.setText("Input New Item's Name");
    }

    /**
     * Sets the quantity field to its initial text
     */
    public void clearQuantityLabelField() {
        this.quantityField.setText("Input New Item's Quantity");
    }

    /**
     * Sets the price field to its initial text
     */
    public void clearPriceLabelField() {
        this.priceField.setText("Input New Items's Price");
    }
    
    /**
     * Sets the calories field to its initial text
     */
    public void clearCaloriesLabelField() {
        this.caloriesField.setText("Input New Items's Calories");
    }

    /**
     * Sets the type field to its initial text
     */
    public void clearTypeLabelField() {
        this.typeField.setText("Input New Item's Type");
    }
    
    /**
     * Sets the new price field to its initial text
     */
    public void clearNewPriceField() {
        this.newPriceField.setText("Price");
    }

    /**
     * Sets the label field to its initial text
     */
    public void clearLabelField() {
        this.labelField.setText("Item");
    }

    /**
     * Clears the MDisplay area
     */
    public void clearMDisplayText(){
        this.mDisplayArea.setText("");
    }

    /**
     * Sets the text in the increase decrease field
     * @param text label
     */
    public void setIncDecFieldText(String text) {
        this.incDecField.setText(text);
    }

    /**
     * Sets the text in the label field 
     * @param text label
     */
    public void setLabelFieldText(String text) {
        this.labelField.setText(text);
    }

    /**
     * Sets the text in the new price field
     * @param text new price
     */
    public void setNewPriceFieldText(String text) {
        this.newPriceField.setText(text);
    }
    
    /**
     * Sets the text in the input money field
     * @param text stored money
     */
    public void setInputMoneyFieldText(String text){
        this.inputMoneyField.setText(text);
    }

    /**
     * Sets the text in the MDisplay area
     * @param text string outputs
     */
    public void setMDisplayText(String text){
        this.mDisplayArea.setText(text);
    }
    
    /**
     * Gets the text in the increase decrease field
     * @return label
     */
    public String getIncDecFieldText() {
        return this.incDecField.getText();
    }

    /**
     * Gets the text in the label field
     * @return label
     */
    public String getLabelFieldText() {
        return this.labelField.getText();
    }

    /**
     * Gets the text in the new price field
     * @return new price
     */
    public String getNewPriceFieldText() {
        return this.newPriceField.getText();
    }

    /**
     * Gets the text in the name field
     * @return name
     */
    public String getNameFieldText() {
        return this.nameField.getText();
    }

    /**
     * Gets the text in the type field 
     * @return item type
     */
    public String getTypeFieldText() {
        return this.typeField.getText();
    }

    /**
     * Gets the integer value in the quantity text
     * @return quantity
     */
    public int getQuantityFieldText() {
        String text = this.quantityField.getText();
        int quantity = Integer.parseInt(text);

        return quantity;
    } 

    /**
     * Gets the double value in the price field
     * @return price
     */
    public double getPriceFieldText() {
        String text = this.priceField.getText();
        double price = Double.parseDouble(text);
        return price;
    }

    /**
     * Gets the integer value in the calories field
     * @return calories
     */
    public int getCaloriesFieldText() {
        String text = this.caloriesField.getText();
        int calories = Integer.parseInt(text);
        return calories;
    }

    /**
     * Gets the maintenance frame
     * @return maintenance frame
     */
    public JFrame getMaintenanceFrame() {
        return this.maintenanceFrame;
    }

}