package ui.gui;

import model.item.StoreItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewItemFrame extends JFrame implements ActionListener {

    private StoreItem item;
    private JButton closeFrameButton;

    public ViewItemFrame(StoreItem item) {
        this.item = item;

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
