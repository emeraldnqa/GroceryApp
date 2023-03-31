package ui.gui;

import javax.swing.*;
import java.awt.*;

public class PopUpDialog extends JOptionPane {
    private JFrame frame;
    private String message;

    public PopUpDialog(JFrame frame, String message) {
        super();
        this.frame = frame;
        this.message = message;
        ImageIcon checkmarkIcon = new ImageIcon("./data/checkmark.png");
        Image checkmark = checkmarkIcon.getImage();
        Image scaledCheckMark = checkmark.getScaledInstance(50,50,checkmark.SCALE_DEFAULT);
        Icon scaledIcon = new ImageIcon(scaledCheckMark);
        showMessageDialog(frame,message,"Confirm",JOptionPane.PLAIN_MESSAGE,scaledIcon);

    }
}
