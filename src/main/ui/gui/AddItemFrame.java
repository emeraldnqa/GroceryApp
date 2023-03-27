package ui.gui;

import model.item.*;
import model.list.Section;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemFrame extends Frame implements ActionListener {
    private JTextField name, brand, amountBought, unit, boughtPrice, year, month, date;
    private JLabel sName, sBrand, sAmountBought, sUnit, sBoughtPrice, sYear, sMonth, sDate;
    private GridLayout layout = new GridLayout(8,2);
    private Container pane;
    private JButton addItemButton;
    private String sectionName;
    private Section chosenSection;

    public AddItemFrame(String sectionName, Section chosenSection) {
        super("Create New Item");
        this.sectionName = sectionName;
        this.chosenSection = chosenSection;
        name = new JTextField();
        brand = new JTextField();
        amountBought = new JTextField();
        unit = new JTextField();
        boughtPrice = new JTextField();
        year = new JTextField();
        month = new JTextField();
        date = new JTextField();
        sName = new JLabel("Name");
        sBrand = new JLabel("Brand");
        sAmountBought = new JLabel("Amount Bought");
        sUnit = new JLabel("Unit:");
        sBoughtPrice = new JLabel("Purchased Price:");
        sYear = new JLabel("Year");
        sMonth = new JLabel("Month");
        sDate = new JLabel("Date");
        pane = getContentPane();
        addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(this);
        addItemButton.setSize(10,20);
        addComponentToPane();
        pack();
        setResizable(false);


    }

    public void addComponentToPane() {
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());
        panel.setLayout(layout);
        panel.add(sName);
        panel.add(name);
        panel.add(sBrand);
        panel.add(brand);
        panel.add(sAmountBought);
        panel.add(amountBought);
        panel.add(sBoughtPrice);
        panel.add(boughtPrice);
        panel.add(sUnit);
        panel.add(unit);
        panel.add(sYear);
        panel.add(year);
        panel.add(sMonth);
        panel.add(month);
        panel.add(sDate);
        panel.add(date);
        buttonPanel.add(addItemButton);
        buttonPanel.setSize(10,20);
        pane.add(panel,BorderLayout.NORTH);
        pane.add(buttonPanel, BorderLayout.SOUTH);

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        int getYear = Integer.parseInt(year.getText());
        int getDate = Integer.parseInt(date.getText());
        int getMonth = Integer.parseInt(month.getText());
        switch (sectionName) {
            case "Produce":
                StoreItem produce = new Produce();
                getFields(produce);
                produce.setExpiryDate(getYear,getMonth,getDate);
                chosenSection.addItem(produce);
            case "Grocery":
                StoreItem grocery = new Grocery();
                getFields(grocery);
                grocery.setExpiryDate(getYear,getMonth,getDate);
                chosenSection.addItem(grocery);
            case "Meat":
                StoreItem meat = new Meat();
                getFields(meat);
                meat.setExpiryDate(getYear, getMonth, getDate);
                chosenSection.addItem(meat);
            case "Dairy":
                StoreItem dairy = new Dairy();
                getFields(dairy);
                dairy.setExpiryDate(getYear, getMonth, getDate);
                chosenSection.addItem(dairy);
        }


    }

    public void getFields(StoreItem item) {
        String getName = name.getText();
        String getBrand = brand.getText();
        int getAmountBought = Integer.parseInt(amountBought.getText());
        String getUnit = unit.getText();
        double getBoughtPrice = Double.parseDouble(boughtPrice.getText());


        setField(item, getName, getBrand, getAmountBought,
        getBoughtPrice, getUnit, getAmountBought);
    }

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
