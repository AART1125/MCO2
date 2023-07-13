package VendingView;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

public class VendingMachineUI {

    private JFrame mainFrame;
    private ImageIcon icon;

    public VendingMachineUI(){
        this.icon = new ImageIcon("VendingView/Images/mainIcon.jpg");

        this.mainFrame = new JFrame("Smoothie Machine");
        this.mainFrame.setIconImage(this.icon.getImage());
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.mainFrame.setSize(500, 750);

        this.mainFrame.setVisible(true);
    }

    public static void main(String[] args){
        VendingMachineUI ui = new VendingMachineUI();
    }
}

