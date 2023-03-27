package ui.gui;

import model.item.Grocery;
import model.item.StoreItem;
import model.list.Section;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewSection extends Frame implements ListSelectionListener, ActionListener {
    private JList list;
    private DefaultListModel listModel;
    private Container pane;
    private JButton viewItemButton;
    private Section chosenSection;

    public ViewSection(String sectionName, Section chosenSection) {
        super("View Section");
        setSize(100,200);

        this.listModel = new DefaultListModel<>();
        for (StoreItem item : chosenSection.getItems()) {
            listModel.addElement(item.getName());
        }
        this.chosenSection = chosenSection;
        viewItemButton = new JButton("View this item");
        viewItemButton.addActionListener(this);

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(10);
        list.addListSelectionListener(this);
        pane = getContentPane();
        pane.setSize(100,200);
        pane.add(list);
        setResizable(false);

    }


    /**
     * Called whenever the value of the selection changes.
     *
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {

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
