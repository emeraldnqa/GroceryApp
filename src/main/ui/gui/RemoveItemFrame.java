package ui.gui;

import model.list.Section;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveItemFrame extends Frame implements ActionListener {
    private String name;
    private Section chosenSection;
    private ViewSection frame;
    private JButton removeButton;

    public RemoveItemFrame(String name, Section chosenSection) {
        super(name);
        this.name = name;
        this.chosenSection = chosenSection;
        frame = new ViewSection(name,chosenSection);
        removeButton = new JButton("Remove Item from list");

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
