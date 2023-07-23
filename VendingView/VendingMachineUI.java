package VendingView;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class VendingMachineUI {
    private JFrame mainFrame, maintenanceFrame;
    private ImageIcon icon;
    private JLabel label, cashLabel;
    private JPanel itemsPanel, productPanel, inputsPanel;// Front portion
    private JButton cashBtn1, cashBtn2, cashBtn3, cashBtn4, cashBtn5, cashBtn6, cashBtn7, cashBtn8, cashBtn9;// Buttons for Cash Inputs
    private JButton itemBtn1, itemBtn2, ItemBtn3, itemBtn4, itemBtn5, itemBtnA, itemBtnB, itemBtnC, itemBtnD, itemBtnE, itemBtnF, itemBtnCon, itemBtnCan;// Buttons for Item inputs
    private JPanel addItems, inputMoneyPanel, transactionsPanel, collectMoney;//Back Portion

    public VendingMachineUI(){
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

        this.maintenanceFrame = new JFrame("Maintence");
        this.maintenanceFrame.setIconImage(this.icon.getImage());
        this.maintenanceFrame.setLayout(new GridBagLayout());
        this.maintenanceFrame.setSize(750, 1010);
        this.maintenanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.maintenanceFrame.setVisible(false);

        //Panel for items view
        this.itemsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
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

        this.cashLabel = new JLabel("Total Cash: 0");
        this.cashLabel.setForeground(Color.WHITE);

        //Input cash buttons
        this.cashBtn1 = new JButton("1");
        this.cashBtn1.setPreferredSize(new Dimension(55, 55));
        this.cashBtn2 = new JButton("5");
        this.cashBtn2.setPreferredSize(new Dimension(55, 55));
        this.cashBtn3 = new JButton("10");
        this.cashBtn3.setPreferredSize(new Dimension(55, 55));
        this.cashBtn4 = new JButton("20");
        this.cashBtn4.setPreferredSize(new Dimension(55, 55));
        this.cashBtn5 = new JButton("50");
        this.cashBtn5.setPreferredSize(new Dimension(55, 55));
        this.cashBtn6 = new JButton("100");
        this.cashBtn6.setPreferredSize(new Dimension(55, 55));
        this.cashBtn7 = new JButton("200");
        this.cashBtn7.setPreferredSize(new Dimension(55, 55));
        this.cashBtn8 = new JButton("500");
        this.cashBtn8.setPreferredSize(new Dimension(55, 55));
        this.cashBtn9 = new JButton("1k");
        this.cashBtn9.setPreferredSize(new Dimension(55, 55));
        
        //Panel for the items
        JPanel panel = new JPanel(new GridLayout(6,5));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(485, 690));

        for (int i = 0; i < 30; i++) {
            this.label = new JLabel("HelloWorld", SwingConstants.CENTER);
            this.label.setForeground(Color.BLACK);
            this.label.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            panel.add(this.label);
        }
        
        this.itemsPanel.add(panel);

        //Panel for the finished products
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(485, 235));
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
        panelCon.gridwidth = 3;
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
        panelCon.anchor = GridBagConstraints.CENTER;
        panel.add(cashBtn1, panelCon);

        panelCon.gridx = 1;
        panelCon.gridy = 1;
        panelCon.gridheight = 1;
        panelCon.gridwidth = 1;
        panelCon.weightx = 0;
        panelCon.weighty = 0;
        panelCon.anchor = GridBagConstraints.CENTER;
        panel.add(cashBtn2, panelCon);

        panelCon.gridx = 2;
        panelCon.gridy = 1;
        panelCon.gridheight = 1;
        panelCon.gridwidth = 1;
        panelCon.weightx = 0;
        panelCon.weighty = 0;
        panelCon.anchor = GridBagConstraints.CENTER;
        panel.add(cashBtn3, panelCon);

        panelCon.gridx = 0;
        panelCon.gridy = 2;
        panelCon.gridheight = 1;
        panelCon.gridwidth = 1;
        panelCon.weightx = 0;
        panelCon.weighty = 0;
        panelCon.anchor = GridBagConstraints.CENTER;
        panel.add(cashBtn4, panelCon);

        panelCon.gridx = 1;
        panelCon.gridy = 2;
        panelCon.gridheight = 1;
        panelCon.gridwidth = 1;
        panelCon.weightx = 0;
        panelCon.weighty = 0;
        panelCon.anchor = GridBagConstraints.CENTER;
        panel.add(cashBtn5, panelCon);

        panelCon.gridx = 2;
        panelCon.gridy = 2;
        panelCon.gridheight = 1;
        panelCon.gridwidth = 1;
        panelCon.weightx = 0;
        panelCon.weighty = 0;
        panelCon.anchor = GridBagConstraints.CENTER;
        panel.add(cashBtn6, panelCon);

        panelCon.insets = new Insets(0, 0, 15, 0);
        panelCon.gridx = 0;
        panelCon.gridy = 3;
        panelCon.gridheight = 1;
        panelCon.gridwidth = 1;
        panelCon.weightx = 0;
        panelCon.weighty = 0;
        panelCon.anchor = GridBagConstraints.CENTER;
        panel.add(cashBtn7, panelCon);

        panelCon.gridx = 1;
        panelCon.gridy = 3;
        panelCon.gridheight = 1;
        panelCon.gridwidth = 1;
        panelCon.weightx = 0;
        panelCon.weighty = 0;
        panelCon.anchor = GridBagConstraints.CENTER;
        panel.add(cashBtn8, panelCon);

        panelCon.gridx = 2;
        panelCon.gridy = 3;
        panelCon.gridheight = 1;
        panelCon.gridwidth = 1;
        panelCon.weightx = 0;
        panelCon.weighty = 0;
        panelCon.anchor = GridBagConstraints.CENTER;
        panel.add(cashBtn9, panelCon);
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

    public static void main(String[] args){
        VendingMachineUI ui = new VendingMachineUI();
    }
}

