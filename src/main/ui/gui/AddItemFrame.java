package ui.gui;

import model.item.*;
import model.list.Section;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Create the Frame that can help add new Item to Section
public class AddItemFrame extends Frame implements ActionListener {
    private JTextField name;
    private JTextField brand;
    private JTextField amountBought;
    private JTextField unit;
    private JTextField boughtPrice;
    private JTextField year;
    private JTextField month;
    private JTextField date;
    private JLabel labelName;
    private JLabel labelBrand;
    private JLabel labelAmountBought;
    private JLabel labelUnit;
    private JLabel labelBoughtPrice;
    private JLabel labelYear;
    private JLabel labelMonth;
    private JLabel labelDate;
    private GridLayout layout = new GridLayout(8,2);
    private Container pane;
    private JButton addItemButton;
    private Section chosenSection;
    private StoreItem newItem = null;
    private DefaultListModel listModel;
    private DefaultListModel itemNameList;
    private JList list;
    private JPanel panel;

    public AddItemFrame(Section chosenSection, DefaultListModel listModel, DefaultListModel itemNameList,
                        JList list) {
        super("Create New Item");
        this.listModel = listModel;
        this.itemNameList = itemNameList;
        this.list = list;
        this.chosenSection = chosenSection;
        pane = getContentPane();
        panel = new JPanel();
        createAddItemButton();
        initiateLabel();
        initiateField();
        addComponentToPane(labelName,name);
        addComponentToPane(labelBrand,brand);
        addComponentToPane(labelAmountBought,amountBought);
        addComponentToPane(labelBoughtPrice,boughtPrice);
        addComponentToPane(labelUnit,unit);
        addComponentToPane(labelYear,year);
        addComponentToPane(labelMonth,month);
        addComponentToPane(labelDate,date);
        addAllToMainContainer();
        pack();
        setResizable(false);


    }

    // EFFECTS: create AddItemButton
    private void createAddItemButton() {
        addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(this);
        addItemButton.setSize(10,20);
    }

    // EFFECTS: Initiate text field
    private void initiateField() {
        name = new JTextField();
        brand = new JTextField();
        amountBought = new JTextField();
        unit = new JTextField();
        boughtPrice = new JTextField();
        year = new JTextField();
        month = new JTextField();
        date = new JTextField();
    }

    // EFFECTS: Initiate label
    private void initiateLabel() {
        labelName = new JLabel("Name");
        labelBrand = new JLabel("Brand");
        labelAmountBought = new JLabel("Amount Bought");
        labelUnit = new JLabel("Unit:");
        labelBoughtPrice = new JLabel("Purchased Price:");
        labelYear = new JLabel("Year");
        labelMonth = new JLabel("Month");
        labelDate = new JLabel("Date");

    }

    // MODIFIES: this
    // EFFECTS: Adding Components to Panel
    public void addComponentToPane(JLabel label, JTextField textField) {
        panel.setLayout(layout);
        panel.add(label);
        panel.add(textField);

    }

    // MODIFIES: this
    // EFFECTS: Adding Components to Panel
    private void addAllToMainContainer() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());
        buttonPanel.add(addItemButton);
        buttonPanel.setSize(10,20);
        pane.add(panel,BorderLayout.NORTH);
        pane.add(buttonPanel, BorderLayout.SOUTH);

    }




    // REQUIRES: e
    // MODIFIES: this
    // EFFECTS: Create the newly added item, and popup dialog notify user that item has been added
    @Override
    public void actionPerformed(ActionEvent e) {
        int getYear = Integer.parseInt(year.getText());
        int getDate = Integer.parseInt(date.getText());
        int getMonth = Integer.parseInt(month.getText());
        String confirmItemAdded = "Item has been added!";
        switch (chosenSection.getType()) {
            case "Produce":
                StoreItem produce = new Produce();
                addItemAction(getYear, getDate, getMonth, confirmItemAdded, produce);
                break;
            case "Grocery":
                StoreItem grocery = new Grocery();
                addItemAction(getYear, getDate, getMonth, confirmItemAdded, grocery);
                break;
            case "Meat":
                StoreItem meat = new Meat();
                addItemAction(getYear, getDate, getMonth, confirmItemAdded, meat);
                break;
            case "Dairy":
                StoreItem dairy = new Dairy();
                addItemAction(getYear, getDate, getMonth, confirmItemAdded, dairy);
                setVisible(false);

                break;
        }

    }


    // REQUIRES: year, date, month must be Integer, Message must be String, item must be non-null
    // MODIFIES: this
    // EFFECTS: Add item to the chosen section
    private void addItemAction(int getYear, int getDate, int getMonth, String message, StoreItem item) {
        getFields(item);
        item.setExpiryDate(getYear, getMonth, getDate);
        chosenSection.addItem(item);
        newItem = item;
        listModel.addElement(item);
        itemNameList.addElement(item.getName());
        setVisible(false);
        pane.add(new PopUpDialog(this, message));
    }




    // REQUIRES: item, item cannot be null
    // MODIFIES: this
    // EFFECTS: Get all the text gotten from the JTextField, and set the items field
    private void getFields(StoreItem item) {
        String getName = name.getText();
        String getBrand = brand.getText();
        int getAmountBought = Integer.parseInt(amountBought.getText());
        String getUnit = unit.getText();
        double getBoughtPrice = Double.parseDouble(boughtPrice.getText());
        setField(item, getName, getBrand, getAmountBought,
                getBoughtPrice, getUnit, getAmountBought);
    }

    // REQUIRES: item, name, brand and unit cannot be null, amount and initialAmount has to be int, and boughtPrice
    // has to be Double
    // MODIFIES: this
    // EFFECTS: Set all the field available an item
    private void setField(StoreItem item, String name, String brand, int amount,
                          double boughtPrice, String unit, int initialAmount) {
        item.setName(name);
        item.setBrand(brand);
        item.setAmount(amount);
        item.setInitialAmount(initialAmount);
        item.setUnit(unit);
        item.setBoughtPrice(boughtPrice);
        item.setPrice();
    }

}
