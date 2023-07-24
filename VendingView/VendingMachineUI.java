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
import java.io.File;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class VendingMachineUI {
    private SpecialVendingMachine machine;

    private String[] pathNames = {
                                  "VendingMachineView/Images/Strawberry.png"
                                };

    private JFrame mainFrame, maintenanceFrame;
    private JMenuBar menu, maintenanceMenu;
    private JMenu maintenanceMItem, vendingMItem;
    private JMenuItem openM, openV;
    private Font digitalFont;
    private ImageIcon icon;
    private ImageIcon[] itemsIcon;
    private JLabel cashLabel, displayLabel;
    private JPanel itemsPanel, productPanel, inputsPanel;// Front portion
    private JTextField itemsField, productNameField;
    private JTextArea transactionsArea;
    private JScrollPane transactionsPane;
    private JButton cashBtn1, cashBtn2, cashBtn3, cashBtn4, cashBtn5, cashBtn6, cashBtn7, cashBtn8, cashBtn9;// Buttons for Cash Inputs
    private JButton itemBtn1, itemBtn2, itemBtn3, itemBtn4, itemBtn5, itemBtnA, itemBtnB, itemBtnC, itemBtnD, itemBtnE, itemBtnF, itemBtnCon, itemBtnCan;// Buttons for Item inputs
    private JButton createBuyBtn, buyBtn;//Buttons for transactions
    private JPanel addItems, inputMoneyPanel, transactionsPanel, collectMoney;//Back Portion

    public VendingMachineUI(SpecialVendingMachine machine){
        //get the values of the machine
        //will be removed for controller
        this.machine = machine;

        try {
            this.digitalFont = Font.createFont(Font.TRUETYPE_FONT, new File("VendingView/Images/digital-7.ttf")).deriveFont(Font.BOLD);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(digitalFont);
        } catch (Exception e) {
            // TODO: handle exception
        }

        //Icon of the program
        this.icon = new ImageIcon(new ImageIcon("VendingView/Images/mainIcon.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        
        //Mainframe settings on start up
        this.mainFrame = new JFrame("Smoothie Machine");
        this.mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainFrame.setIconImage(this.icon.getImage());
        this.mainFrame.setLayout(new GridBagLayout());
        this.mainFrame.getContentPane().setBackground(Color.RED);
        this.mainFrame.setSize(750, 1000);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setResizable(false);

        //Maintenance frame settings on start up
        this.maintenanceFrame = new JFrame("Maintence");
        this.maintenanceFrame.setIconImage(this.icon.getImage());
        this.maintenanceFrame.setLayout(new GridBagLayout());
        this.maintenanceFrame.setSize(750, 1010);
        this.maintenanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.maintenanceFrame.setLocationRelativeTo(null);
        this.maintenanceFrame.setVisible(false);

        //Menu bars for both frames
        this.menu = new JMenuBar();
        this.maintenanceMenu = new JMenuBar();

        //Menu option for both frames
        this.maintenanceMItem = new JMenu("Maintenance");
        this.vendingMItem = new JMenu("Vending Machine");

        //Menu items for both frames
        this.openM = new JMenuItem("Open Maintenance");
        this.openV = new JMenuItem("Go to Vending Machine");

        //Mainframe menu bar setup
        this.maintenanceMItem.add(this.openM);
        this.menu.add(this.maintenanceMItem);
        this.mainFrame.setJMenuBar(this.menu);
        this.openM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mainFrame.setVisible(false);
                maintenanceFrame.setVisible(true);
            }
        });

        //Maintenance menu bar setup
        this.vendingMItem.add(this.openV);
        this.maintenanceMenu.add(this.vendingMItem);
        this.maintenanceFrame.setJMenuBar(this.maintenanceMenu);
        this.openV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mainFrame.setVisible(true);
                maintenanceFrame.setVisible(false);
            }
        });

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

        //Label for the cash inputs
        this.cashLabel = new JLabel("Total Cash: " + this.machine.total(this.machine.getUserMoney()), SwingConstants.CENTER);
        this.cashLabel.setForeground(Color.WHITE);
        this.cashLabel.setFont(digitalFont.deriveFont(21f));

        //Label for displays
        this.displayLabel = new JLabel("Display: ", SwingConstants.CENTER);
        this.displayLabel.setFont(this.digitalFont.deriveFont(21f));
        this.displayLabel.setForeground(Color.GREEN);

        //Text field for the items panel
        this.itemsField = new JTextField("Input Item Label", 20);
        this.itemsField.setBackground(Color.BLACK);
        this.itemsField.setForeground(Color.GREEN);
        this.itemsField.setFont(this.digitalFont.deriveFont(21f));
        this.itemsField.setHorizontalAlignment(SwingConstants.CENTER);
        this.itemsField.setMinimumSize(new Dimension(200, 25));
        this.itemsField.setEditable(false);

        //Text field for the product name
        this.productNameField = new JTextField("Input Product Name", 20);

        //Text area for the transactions panel
        this.transactionsArea = new JTextArea("", 4, 20);
        this.transactionsArea.setEditable(false);
        this.transactionsArea.setLineWrap(true);
        this.transactionsPane = new JScrollPane(this.transactionsArea);
        this.transactionsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.transactionsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.transactionsPane.setPreferredSize(new Dimension(200, 100));

        //Input cash buttons
        this.cashBtn1 = new JButton("1");
        this.cashBtn1.setFont(this.digitalFont.deriveFont(12f));
        this.cashBtn1.setPreferredSize(new Dimension(55, 55));
        this.cashBtn2 = new JButton("5");
        this.cashBtn2.setFont(this.digitalFont.deriveFont(12f));
        this.cashBtn2.setPreferredSize(new Dimension(55, 55));
        this.cashBtn3 = new JButton("10");
        this.cashBtn3.setFont(this.digitalFont.deriveFont(12f));
        this.cashBtn3.setPreferredSize(new Dimension(55, 55));
        this.cashBtn4 = new JButton("20");
        this.cashBtn4.setFont(this.digitalFont.deriveFont(12f));
        this.cashBtn4.setPreferredSize(new Dimension(55, 55));
        this.cashBtn5 = new JButton("50");
        this.cashBtn5.setFont(this.digitalFont.deriveFont(12f));
        this.cashBtn5.setPreferredSize(new Dimension(55, 55));
        this.cashBtn6 = new JButton("100");
        this.cashBtn6.setFont(this.digitalFont.deriveFont(12f));
        this.cashBtn6.setPreferredSize(new Dimension(55, 55));
        this.cashBtn7 = new JButton("200");
        this.cashBtn7.setFont(this.digitalFont.deriveFont(12f));
        this.cashBtn7.setPreferredSize(new Dimension(55, 55));
        this.cashBtn8 = new JButton("500");
        this.cashBtn8.setFont(this.digitalFont.deriveFont(12f));
        this.cashBtn8.setPreferredSize(new Dimension(55, 55));
        this.cashBtn9 = new JButton("1k");
        this.cashBtn9.setFont(this.digitalFont.deriveFont(12f));
        this.cashBtn9.setPreferredSize(new Dimension(55, 55));

        //Item input buttons
        this.itemBtn1 = new JButton("1");
        this.itemBtn1.setFont(digitalFont.deriveFont(12f));
        this.itemBtn1.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtn2 = new JButton("2");
        this.itemBtn2.setFont(digitalFont.deriveFont(12f));
        this.itemBtn2.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtn3 = new JButton("3");
        this.itemBtn3.setFont(digitalFont.deriveFont(12f));
        this.itemBtn3.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtn4 = new JButton("4");
        this.itemBtn4.setFont(digitalFont.deriveFont(12f));
        this.itemBtn4.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtn5 = new JButton("5");
        this.itemBtn5.setFont(digitalFont.deriveFont(12f));
        this.itemBtn5.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnA = new JButton("A");
        this.itemBtnA.setFont(digitalFont.deriveFont(12f));
        this.itemBtnA.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnB = new JButton("B");
        this.itemBtnB.setFont(digitalFont.deriveFont(12f));
        this.itemBtnB.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnC = new JButton("C");
        this.itemBtnC.setFont(digitalFont.deriveFont(12f));
        this.itemBtnC.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnD = new JButton("D");
        this.itemBtnD.setFont(digitalFont.deriveFont(12f));
        this.itemBtnD.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnE = new JButton("E");
        this.itemBtnE.setFont(digitalFont.deriveFont(12f));
        this.itemBtnE.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnF = new JButton("F");
        this.itemBtnF.setFont(digitalFont.deriveFont(12f));
        this.itemBtnF.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnCon = new JButton("✓");
        this.itemBtnCon.setBackground(Color.green);
        this.itemBtnCon.setForeground(Color.white);
        this.itemBtnCon.setMargin(new Insets(10, 12, 10, 12));
        this.itemBtnCan = new JButton("x");
        this.itemBtnCan.setFont(digitalFont.deriveFont(12f));
        this.itemBtnCan.setBackground(Color.RED);
        this.itemBtnCan.setForeground(Color.white);
        this.itemBtnCan.setMargin(new Insets(10, 12, 10, 12));

        this.createBuyBtn = new JButton("Create");
        this.createBuyBtn.setBackground(Color.green);
        this.createBuyBtn.setForeground(Color.white);
        this.buyBtn = new JButton("Buy cart");
        this.buyBtn.setBackground(Color.green);
        this.buyBtn.setForeground(Color.white);
        
        //Panel for the items
        JPanel panel = setItems();

        this.itemsPanel.add(panel);

        //Panel for the finished products
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(485, 220));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.gray));
        panel.add(new JLabel("null", SwingConstants.CENTER));

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
            panel.add(cashLabel, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 1;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 1;
            panelCon.weightx = 0;
            panelCon.weighty = 0;
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

            panelCon.insets = new Insets(0, 0, 15, 0);
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
            
            panel.add(this.itemsField, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 1;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 1;
            
            panel.add(this.itemBtnA, panelCon);

            panelCon.gridx = 1;
            panelCon.gridy = 1;
            panel.add(this.itemBtnB, panelCon);

            panelCon.gridx = 2;
            panelCon.gridy = 1;
            panel.add(this.itemBtnC, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 2;
            panel.add(this.itemBtnD, panelCon);

            panelCon.gridx = 1;
            panelCon.gridy = 2;
            panel.add(this.itemBtnE, panelCon);

            panelCon.gridx = 2;
            panelCon.gridy = 2;
            panel.add(this.itemBtnF, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 3;
            panel.add(this.itemBtn1, panelCon);
            
            panelCon.gridx = 1;
            panelCon.gridy = 3;
            panel.add(this.itemBtn2, panelCon);

            panelCon.gridx = 2;
            panelCon.gridy = 3;
            panel.add(this.itemBtn3, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 4;
            panel.add(this.itemBtn4, panelCon);

            panelCon.gridx = 1;
            panelCon.gridy = 4;
            panel.add(this.itemBtn5, panelCon);

            panelCon.gridx = 4;
            panelCon.gridy = 2;
            panel.add(this.itemBtnCon, panelCon);

            panelCon.gridx = 4;
            panelCon.gridy = 3;
            panel.add(this.itemBtnCan, panelCon);

            this.inputsPanel.add(panel);

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

            panelCon.gridx = 0;
            panelCon.gridy = 1;
            panelCon.insets = new Insets(5, 5, 5, 5);
            panelCon.weightx = 0;
            panelCon.weighty = 0;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 4;
            panelCon.anchor = GridBagConstraints.CENTER;
            panel.add(this.displayLabel, panelCon);

            panelCon.gridx = 0;
            panelCon.gridy = 2;
            panelCon.anchor = GridBagConstraints.CENTER;
            panel.add(this.transactionsPane, panelCon);

            panelCon.gridx = 2;
            panelCon.gridy = 3;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 1;
            panelCon.anchor = GridBagConstraints.WEST;
            panel.add(this.createBuyBtn, panelCon);

            panelCon.gridx = 3;
            panelCon.gridy = 3;
            panelCon.gridheight = 1;
            panelCon.gridwidth = 1;
            panelCon.anchor = GridBagConstraints.EAST;
            panel.add(this.buyBtn, panelCon);
            

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

            this.mainFrame.add(this.itemsPanel, c);

            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0.5;
            c.weighty = 0.5;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.WEST;

            this.mainFrame.add(this.productPanel, c);

            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0.5;
            c.weighty = 0;
            c.gridheight = 2;
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.CENTER;

            this.mainFrame.add(this.inputsPanel, c);
            
        this.mainFrame.setVisible(true);
        this.mainFrame.revalidate();
    }

    public JPanel setItems(){
        int i,j;
        ImageIcon image = new ImageIcon(new ImageIcon("VendingView/Images/Strawberry.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

        JLabel nameLabel, slotLabel;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(485, 690));

        GridBagConstraints itemsCon = new GridBagConstraints();
        itemsCon.gridheight = 1;
        itemsCon.gridwidth = 1;
        itemsCon.weightx = 0.5;
        itemsCon.weighty = 0.5;
        itemsCon.gridx = 0;
        itemsCon.gridy = 0;
        itemsCon.ipadx = 40;
        itemsCon.ipady = 50;

        for (i = 0; i < 6; i++) {
                for (j = 0; j < 5; j++) {
                slotLabel = new JLabel(this.machine.getVendoItem()[i][j].getLabel(), SwingConstants.RIGHT);
                nameLabel = new JLabel("Smoothie", image, SwingConstants.CENTER);
                nameLabel.setHorizontalTextPosition(JLabel.CENTER);
                nameLabel.setVerticalTextPosition(JLabel.BOTTOM);
                nameLabel.setForeground(Color.BLACK);
                nameLabel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                panel.add(slotLabel,itemsCon);
                panel.add(nameLabel, itemsCon);
                itemsCon.gridx++;
            }
            itemsCon.gridy++;
            itemsCon.gridx = 0;
        }
        return panel;
    }

    public static void main(String[] args){
        SpecialVendingMachine machine = new SpecialVendingMachine();
        machine.initialization(machine.getStoredMoney());
        machine.initialization(machine.getUserMoney());
        machine.initialization(machine.getVendoItem());
        VendingMachineUI ui = new VendingMachineUI(machine);
    }
}

