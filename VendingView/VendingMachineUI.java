package VendingView;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.GridLayout;

public class VendingMachineUI {

    private JFrame mainFrame;
    private ImageIcon icon;

    public VendingMachineUI(){
        this.icon = new ImageIcon("VendingView/Images/mainIcon.jpg");
        
        this.mainFrame = new JFrame("Smoothie Machine");
        this.mainFrame.setIconImage(this.icon.getImage());
        this.mainFrame.setLayout(new GridLayout(5, 3));
        this.mainFrame.setSize(500, 750);
        this.mainFrame.setVisible(true);
        
    }

    public static void main(String[] args){
        VendingMachineUI ui = new VendingMachineUI();
    }
}

