package VendingView;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

/**
 * This is the menu of the program
 */
public class MenuUi {
   private ImageIcon mainIcon;
   private JFrame mainFrame;
   private JLabel titleLabel, creatorLabel;
   private JButton createBtn, regBtn, spcBtn, testBtn, exitBtn;
   private JPanel mainPanel, optionsPanel;
   private Font digitalFont;

   /**
    * This is the constructor of the <code>MenuUI</code>. Components of the menu are shown here.
    */
   public MenuUi(){
      this.mainIcon = new ImageIcon("VendingView/Images/mainIcon.jpg");

      try {
         this.digitalFont = Font.createFont(Font.TRUETYPE_FONT, new File("VendingView/Images/neon_pixel-7.ttf")).deriveFont(Font.BOLD);
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
         ge.registerFont(digitalFont);
      } catch (Exception e) {
         
      }

      this.mainFrame = new JFrame("Smoothie Machine Factory");
      this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.mainFrame.setIconImage(this.mainIcon.getImage());
      this.mainFrame.setLayout(new GridLayout(5, 1));
      this.mainFrame.getContentPane().setBackground(Color.ORANGE);
      this.mainFrame.setSize(750, 1000);
      this.mainFrame.setLocationRelativeTo(null);
      this.mainFrame.setResizable(false);

      this.mainPanel = new JPanel(new GridLayout(5, 1));
      this.mainPanel.setBackground(Color.ORANGE);
      this.mainPanel.setSize(750, 1000);

      this.titleLabel = new JLabel("Smoothie Machine Factory", SwingConstants.CENTER);
      this.titleLabel.setFont(digitalFont.deriveFont(50f));

      mainPanel.add(titleLabel);

      this.createBtn = new JButton("Create Vending Machine");
      this.createBtn.setFont(digitalFont.deriveFont(25f));
      this.createBtn.setMaximumSize(new Dimension(500, 100));
      this.createBtn.setMinimumSize(new Dimension(500, 100));
      this.createBtn.setPreferredSize(new Dimension(500, 100));

      JPanel temp = new JPanel(new FlowLayout(FlowLayout.CENTER));
      temp.setPreferredSize(new Dimension(500, 250));
      temp.setBackground(Color.orange);
      temp.add(createBtn);      
      mainPanel.add(temp);

      this.testBtn = new JButton("Test Vending Machine");
      this.testBtn.setFont(digitalFont.deriveFont(25f));
      this.testBtn.setMaximumSize(new Dimension(500, 100));
      this.testBtn.setMinimumSize(new Dimension(500, 100));
      this.testBtn.setPreferredSize(new Dimension(500, 100));

      temp = new JPanel(new FlowLayout(FlowLayout.CENTER));
      temp.setPreferredSize(new Dimension(500, 250));
      temp.setBackground(Color.orange);
      temp.add(testBtn); 
      mainPanel.add(temp);

      this.exitBtn = new JButton("Exit Program");
      this.exitBtn.setFont(digitalFont.deriveFont(25f));
      this.exitBtn.setMaximumSize(new Dimension(500, 100));
      this.exitBtn.setMinimumSize(new Dimension(500, 100));
      this.exitBtn.setPreferredSize(new Dimension(500, 100));

      temp = new JPanel(new FlowLayout(FlowLayout.CENTER));
      temp.setPreferredSize(new Dimension(500, 250));
      temp.setBackground(Color.orange);
      temp.add(exitBtn);
      mainPanel.add(temp);

      this.creatorLabel = new JLabel("Made by Aaron Ace R. Toledo & Juan Pablo Benardo", SwingConstants.CENTER);
      this.creatorLabel.setFont(digitalFont.deriveFont(25f));
      mainPanel.add(creatorLabel);

      this.optionsPanel = new JPanel(new GridLayout(3, 1));
      optionsPanel.setBackground(Color.orange);
      optionsPanel.setVisible(true);

      JLabel tempL = new JLabel("Please choose a Vending Machine: ", SwingConstants.CENTER);
      tempL.setFont(digitalFont.deriveFont(25f));
      optionsPanel.add(tempL);

      temp = new JPanel(new FlowLayout(FlowLayout.CENTER));
      temp.setPreferredSize(new Dimension(500, 250));
      temp.setBackground(Color.orange);

      this.regBtn = new JButton("Regular Vending Machine");
      this.regBtn.setFont(digitalFont.deriveFont(25f));
      this.regBtn.setMaximumSize(new Dimension(500, 100));
      this.regBtn.setMinimumSize(new Dimension(500, 100));
      this.regBtn.setPreferredSize(new Dimension(500, 100));
      temp.add(regBtn);
      optionsPanel.add(temp);

      this.spcBtn = new JButton("Special Vending Machine");
      this.spcBtn.setFont(digitalFont.deriveFont(25f));
      this.spcBtn.setMaximumSize(new Dimension(500, 100));
      this.spcBtn.setMinimumSize(new Dimension(500, 100));
      this.spcBtn.setPreferredSize(new Dimension(500, 100));
      temp.add(spcBtn);
      optionsPanel.add(temp);

      this.mainFrame.setContentPane(mainPanel);

      this.mainFrame.setVisible(true);
   }

   /**
     * Sets the <code>actionListener</code> for the Create button in the menu
     * @param actn switch panel
     */
   public void setCreateBtnListener(ActionListener actn) {
       this.createBtn.addActionListener(actn);
   }

    /**
     * Sets the <code>actionListener</code> for the Test button in the menu
     * @param actn test vending machine
     */
   public void setTestBtnListener(ActionListener actn) {
       this.testBtn.addActionListener(actn);
   }

    /**
     * Sets the <code>actionListener</code> for the Exit button in the menu
     * @param actn exits program
     */
   public void setExitBtnListener(ActionListener actn) {
       this.exitBtn.addActionListener(actn);
   }

    /**
     * Sets the <code>actionListener</code> for the <code>RegularVendingMachine</code> creation button in the menu
     * @param actn create regular vending machine
     */
   public void setRegBtnListener(ActionListener actn) {
       this.regBtn.addActionListener(actn);
   }

    /**
     * Sets the <code>actionListener</code> for the <code>SpecialVendingMachine</code> creation button in the menu
     * @param actn create regular vending machine
     */
   public void setSpcBtnListener(ActionListener actn) {
       this.spcBtn.addActionListener(actn);
   }

    /**
     * Gets the main frame of the ui
     * @return main frame
     */
   public JFrame getMainFrame() {
       return this.mainFrame;
   }

   /**
    * Gets the main panel of the ui
    * @return main panel
    */
   public JPanel getMainPanel() {
       return this.mainPanel;
   }

   /**
    * Gets the options panel of the ui
    * @return options panel
    */
   public JPanel getOptionsPanel() {
       return this.optionsPanel;
   }

}
