package ui.gui;

import model.list.Section;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

// A Frame that allow use to choose which section that they would operate on
public class ChooseSectionFrame extends JFrame implements ActionListener {
    private JRadioButton groceryButton;
    private JRadioButton meatButton;
    private JRadioButton dairyButton;
    private JRadioButton produceButton;
    private GridLayout layout = new GridLayout(5,1);
    private Container pane;
    private static final String GROCERY_LABEL = "Grocery";
    private static final String MEAT_LABEL = "Meat";
    private static final String DAIRY_LABEL = "Dairy";
    private static final String PRODUCE_LABEL = "Produce";
    private Frame frame;
    private List<Section> sections;

    public ChooseSectionFrame(String frameName, List<Section> sections) {
        super(frameName);
        this.sections = sections;

        groceryButton = createRadioButton(GROCERY_LABEL);


        meatButton = createRadioButton(MEAT_LABEL);

        dairyButton = createRadioButton(DAIRY_LABEL);


        produceButton = createRadioButton(PRODUCE_LABEL);

        pane = getContentPane();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        groupButton();
        registerListener();
        addToPanel();
        setPreferredSize(new Dimension(100,200));
        setResizable(false);
        pack();

    }

    private JRadioButton createRadioButton(String label) {
        JRadioButton radioButton = new JRadioButton(label);
        radioButton.setMnemonic(KeyEvent.VK_C);
        radioButton.setActionCommand(label);
        return radioButton;
    }


    // MODIFIES: this
    // EFFECTS: Add all the button in the button group.
    private void groupButton() {
        ButtonGroup group = new ButtonGroup();
        group.add(groceryButton);
        group.add(meatButton);
        group.add(dairyButton);
        group.add(produceButton);
    }

    // MODIFIES: This
    // EFFECTS: Register action listener for each button
    private void registerListener() {
        groceryButton.addActionListener(this);
        meatButton.addActionListener(this);
        dairyButton.addActionListener(this);
        produceButton.addActionListener(this);
    }

    // MODIFIES: THIS
    // EFFECTS: all the button into a panel
    private void addToPanel() {
        JPanel radioPanel = new JPanel(layout);
        radioPanel.add(groceryButton);
        radioPanel.add(produceButton);
        radioPanel.add(meatButton);
        radioPanel.add(dairyButton);
        pane.add(radioPanel);
    }

    // REQUIRES: ActionEvent
    // MODIFIES: this
    // EFFECTS: Perform events whenever a button is pressed.
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals(groceryButton.getActionCommand())) {
            Section grocery = sections.get(3);
            this.frame = new ViewSection(actionCommand, grocery);
            frame.setVisible(true);

        } else if (actionCommand.equals(meatButton.getActionCommand())) {
            Section meat = sections.get(1);
            this.frame = new ViewSection(actionCommand, meat);
            frame.setVisible(true);

        } else if (actionCommand.equals(produceButton.getActionCommand())) {
            Section produces = sections.get(0);
            this.frame = new ViewSection(actionCommand, produces);
            frame.setVisible(true);

        } else if (actionCommand.equals(dairyButton.getActionCommand())) {
            Section dairies = sections.get(2);
            this.frame = new ViewSection(actionCommand, dairies);
            frame.setVisible(true);
        }

    }

}
