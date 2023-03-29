package ui.gui;

import model.item.StoreItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ViewItemPanel extends Frame implements ActionListener {
    //TODO : Change ViewItemPanel to an Panel

    private StoreItem item;
    private JTextArea textArea;
    private JButton closeFrameButton;
    private Container pane;

    public ViewItemPanel(StoreItem item) {
        super("Item details");
        closeFrameButton = new JButton("Close window");
        closeFrameButton.addActionListener(this);
        this.item = item;
        textArea = new JTextArea ("Name: " + item.getName() + "\n"
                + "Stock: " + item.getAmount() +"\n" +
                "Sale price: " + item.getPrice() + "\n" +
                "Expiry date: " + toString(item.getExpiryDate()));
        textArea.setEditable(false);
        pane = getContentPane();
        pane.add(textArea,BorderLayout.NORTH);
        pane.add(closeFrameButton, BorderLayout.SOUTH);
        pack();


    }

    private String toString(LocalDate expiryDate) {
        return expiryDate.getYear() + "/" + expiryDate.getMonth() + "/" + expiryDate.getDayOfMonth();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();

    }
}
