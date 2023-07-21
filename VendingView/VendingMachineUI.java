package VendingView;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class VendingMachineUI {
    private JFrame mainFrame, maintenanceFrame;
    private ImageIcon icon;
    private JLabel label;
    private JPanel itemsPanel, productPanel, inputsPanel, cashPanel, keypadPanel, amountPanel, dispensePanel;

    public VendingMachineUI(){
        //Icon of the program
        this.icon = new ImageIcon("VendingView/Images/mainIcon.jpg");

        //Mainframe settings on start up
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        this.mainFrame = new JFrame("Smoothie Machine");
        this.mainFrame.setIconImage(this.icon.getImage());
        this.mainFrame.setLayout(new GridBagLayout());
        this.mainFrame.getContentPane().setBackground(Color.BLACK);
        this.mainFrame.setSize(750, 1080);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setVisible(true);
        this.mainFrame.setResizable(true);

        //Panel for items view
        this.itemsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.itemsPanel.setSize(new Dimension(750, 870));
        this.itemsPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));

        //Panel for product dispenser
        this.productPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.productPanel.setSize(new Dimension(500, 250));
        this.productPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
        
        JPanel panel = new JPanel(new GridLayout(5,5));
        panel.setBackground(Color.RED);
        panel.setPreferredSize(new Dimension(500, 750));
        panel.setBorder(BorderFactory.createDashedBorder(Color.black));

        for (int i = 0; i < 25; i++) {
            this.label = new JLabel("HelloWorld", SwingConstants.CENTER);
            this.label.setForeground(Color.white);
            this.label.setBorder(BorderFactory.createDashedBorder(Color.WHITE));
            panel.add(this.label);
        }
        this.itemsPanel.add(panel);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(500, 250));
        panel.setBorder(BorderFactory.createDashedBorder(Color.BLUE));
        panel.add(new JLabel("null"));

        this.productPanel.add(panel);

        this.mainFrame.add(this.itemsPanel);
        this.mainFrame.add(this.productPanel);

    }

    public static void main(String[] args){
        VendingMachineUI ui = new VendingMachineUI();
    }
}

