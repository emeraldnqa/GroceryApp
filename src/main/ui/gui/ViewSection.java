package ui.gui;

import model.item.*;
import model.list.Section;
import model.list.exception.ItemNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Frame to show the items in the section
public class ViewSection extends Frame implements ActionListener {
    private JList<Object> list;
    private DefaultListModel listModel;
    private DefaultListModel<Object> itemNameList;
    private JButton viewItemButton;
    private JButton addItemButton;
    private JButton removeItemButton;
    private Section chosenSection;
    private JScrollPane listScrollPane;
    private JPanel buttonPane = new JPanel();


    // EFFECTS: Construct a frame that show all the items name in the given section, a
    // long with buttons to operate on the items in the given section. It's given the section name.
    public ViewSection(String sectionName, Section chosenSection) {
        super(sectionName);
        this.chosenSection = chosenSection;
        this.listModel = new DefaultListModel<>();
        this.itemNameList = new DefaultListModel<>();

        addItemToListModel(chosenSection);
        this.chosenSection = chosenSection;
        viewItemButton = createButton("View this item");
        addItemButton = createButton("Add New Item");
        removeItemButton = createButton("Remove Item");

        list = new JList<Object>(itemNameList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(10);

        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        addButtonToPane();
        listScrollPane = new JScrollPane(list);
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        setPreferredSize(new Dimension(500,500));
        setResizable(false);
        pack();

    }

    // MODIFIES: this
    // EFFECT: add element to list model
    private void addItemToListModel(Section chosenSection) {
        for (StoreItem item : chosenSection.getItems()) {
            listModel.addElement(item);
            itemNameList.addElement(item.getName());
        }
    }

    // EFFECT: Create new JButton
    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        return button;
    }

    // MODIFIES: this
    // EFFECT: add new button to panel
    private void addButtonToPane() {
        buttonPane.add(viewItemButton);
        buttonPane.add(addItemButton);
        buttonPane.add(removeItemButton);
    }

    // REQUIRES: ActionEvent
    // MODIFIES: this
    // EFFECTS: Add, remove or view item when one of the three button on the frame is pressed.
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = list.getSelectedIndex();
        if (e.getSource() == viewItemButton) {
            viewItemAction();
        } else if (e.getSource() == addItemButton) {
            addItemAction(index);

        } else if (e.getSource() == removeItemButton) {
            try {
                removeItemAction(index);
            } catch (ItemNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    // REQUIRES: Integer
    // MODIFIES: This
    // EFFECTS: remove item from section
    private void removeItemAction(int index) throws ItemNotFoundException {
        listModel.remove(index);
        itemNameList.remove(index);
        chosenSection.removeItem(index);

        int size = listModel.getSize();

        if (size == 0) {
            removeItemButton.setEnabled(false);

        } else {
            if (index == listModel.getSize()) {
                index--;
            }

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }

    // REQUIRES: Integer
    // MODIFIES: This
    // EFFECTS: add item from section
    private void addItemAction(int index) {
        AddItemFrame addItemFrame = new AddItemFrame(chosenSection,
                listModel, itemNameList, list);
        addItemFrame.setVisible(true);


        list.setSelectedIndex(index);
        list.ensureIndexIsVisible(index);
    }


    // MODIFIES: This
    // EFFECTS: view item from section
    private void viewItemAction() {
        StoreItem chosenItem = (StoreItem) listModel.get(list.getSelectedIndex());
        JFrame viewItemFrame = new ViewItemPanel(chosenItem);
        viewItemFrame.setVisible(true);
    }
}
