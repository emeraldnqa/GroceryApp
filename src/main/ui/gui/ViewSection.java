package ui.gui;

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
    private DefaultListModel itemNameList;
    private JButton viewItemButton;
    private JButton addItemButton;
    private JButton removeItemButton;
    private Section chosenSection;
    private JScrollPane listScrollPane;
    private Frame frame;



    public ViewSection(String sectionName, Section chosenSection) {
        super("View Section");
        this.chosenSection = chosenSection;
        this.listModel = new DefaultListModel<>();
        this.itemNameList = new DefaultListModel<>();

        for (StoreItem item : chosenSection.getItems()) {
            listModel.addElement(item);
            itemNameList.addElement(item.getName());
        }
        this.chosenSection = chosenSection;
        viewItemButton = new JButton("View this item");
        viewItemButton.addActionListener(this);
        addItemButton = new JButton("Add New Item");
        addItemButton.addActionListener(this);
        removeItemButton = new JButton("Remove Item");
        removeItemButton.addActionListener(this);

        list = new JList(itemNameList);
        list.toString();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(10);
        list.addListSelectionListener(this);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(viewItemButton);
        buttonPane.add(addItemButton);
        buttonPane.add(removeItemButton);
        listScrollPane = new JScrollPane(list);
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        setPreferredSize(new Dimension(200,500));
        pack();

    }

    public JList getJList() {
        return this.list;
    }

    public DefaultListModel getListModel() {
        return this.listModel;
    }

    public DefaultListModel getItemNameList() {
        return this.itemNameList;
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
        int index = list.getSelectedIndex();
        if (e.getSource() == viewItemButton) {
            StoreItem chosenItem = (StoreItem) listModel.get(list.getSelectedIndex());
            JFrame viewItemFrame = new ViewItemPanel(chosenItem);
            viewItemFrame.setVisible(true);
        } else if (e.getSource() == addItemButton) {
            AddItemFrame addItemFrame = new AddItemFrame(e.getActionCommand(),chosenSection);
            addItemFrame.setVisible(true);
            StoreItem newItem = addItemFrame.getLatestItem();

            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.addElement(newItem);
            itemNameList.addElement(newItem.getName());
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());


            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        } else if (e.getSource() == removeItemButton) {
            listModel.remove(index);
            itemNameList.remove(index);
            chosenSection.getItems().remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                removeItemButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }

    }
}
