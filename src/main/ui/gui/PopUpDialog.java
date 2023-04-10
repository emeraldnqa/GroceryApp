package ui.gui;

import javax.swing.*;
import java.awt.*;

// Represent a popup dialog whenever user save, load, remove, or add item action is successful
public class PopUpDialog extends JOptionPane {
    private JFrame frame;
    private String message;

    // EFFECTS: Construct a popup dialog whenever an action relating to StoreItem and Section perform successfully,
    // with given frame, and message
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
