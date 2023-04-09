package ui.gui;

import model.item.StoreItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

// A frame that show details information on the item
public class ViewItemPanel extends Frame implements ActionListener {

    private JTextArea textArea;
    private JButton closeFrameButton;
    private Container pane;

    public ViewItemPanel(StoreItem item) {
        super("Item details");
        closeFrameButton = new JButton("Close window");
        closeFrameButton.addActionListener(this);
        textArea = new JTextArea("Name: " + item.getName() + "\n"
                + "Stock: " + item.getAmount() + "\n"
                + "Sale price: " + item.getPrice() + "\n"
                + "Expiry date: " + toString(item.getExpiryDate()));
        textArea.setEditable(false);
        pane = getContentPane();
        pane.add(textArea,BorderLayout.NORTH);
        pane.add(closeFrameButton, BorderLayout.SOUTH);
        pack();
    }

    // REQUIRES: LocalDate be non-null
    // MODIFIES: this
    // EFFECTS: Turn local date to string
    private String toString(LocalDate expiryDate) {
        return expiryDate.getYear() + "/" + expiryDate.getMonth() + "/" + expiryDate.getDayOfMonth();
    }

    // REQUIRES: ActionEvent
    // MODIFIES: this
    // EFFECTS: Perform events whenever a button is pressed.
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();

    }
}
