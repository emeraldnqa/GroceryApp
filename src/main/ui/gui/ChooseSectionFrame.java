package ui.gui;

import model.list.Section;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class ChooseSectionFrame extends Frame implements ActionListener {
    //TODO: Reconsider if we still need ChooseSectionFrame
    private JRadioButton groceryButton, meatButton, dairyButton, produceButton;
    private GridLayout layout = new GridLayout(5,1);
    private Container pane;
    private static String groceryLabel = "Grocery";
    private static String meatLabel = "Meat";
    private static String dairyLabel = "Dairy";
    private static String produceLabel = "Produce";
    private Frame frame;
    private List<Section> sections;

    public ChooseSectionFrame(Frame frame, List<Section> sections) {
        super("Choose Section");
        this.frame = frame;
        this.sections = sections;
        groceryButton = new JRadioButton(groceryLabel);
        groceryButton.setMnemonic(KeyEvent.VK_C);
        groceryButton.setActionCommand(groceryLabel);
        groceryButton.setSelected(true);
        pane = getContentPane();

        meatButton = new JRadioButton(meatLabel);
        meatButton.setMnemonic(KeyEvent.VK_C);
        meatButton.setActionCommand(meatLabel);

        dairyButton = new JRadioButton(dairyLabel);
        dairyButton.setMnemonic(KeyEvent.VK_C);
        dairyButton.setActionCommand(dairyLabel);


        produceButton = new JRadioButton(produceLabel);
        produceButton.setMnemonic(KeyEvent.VK_C);
        produceButton.setActionCommand(produceLabel);

        groupButton();
        registerListener();
        addToPanel();
        frame.setPreferredSize(new Dimension(100,100));
        frame.setResizable(false);
        frame.pack();

    }

    private void groupButton() {
        ButtonGroup group = new ButtonGroup();
        group.add(groceryButton);
        group.add(meatButton);
        group.add(dairyButton);
        group.add(produceButton);
    }

    private void registerListener() {
        groceryButton.addActionListener(this);
        meatButton.addActionListener(this);
        dairyButton.addActionListener(this);
        produceButton.addActionListener(this);
    }

    private void addToPanel() {
        JPanel radioPanel = new JPanel(layout);
        radioPanel.add(groceryButton);
        radioPanel.add(produceButton);
        radioPanel.add(meatButton);
        radioPanel.add(dairyButton);
        pane.add(radioPanel);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals(groceryButton.getActionCommand())) {
            // get Grocery section
            Section grocery = sections.get(3);
            //if (frame.getName().equals("View Section")) {
                frame = new ViewSection(actionCommand, grocery);
                frame.setVisible(true);
//            } else if (frame.getName().equals("Create New Item")) {
//                frame = new AddItemFrame(actionCommand,grocery);
//                frame.setVisible(true);
//            }
        } else if (actionCommand.equals(meatButton.getActionCommand())) {
            // get meat section
            Section meat = sections.get(1);
            //if (frame.getName().equals("View Section")) {
                frame = new ViewSection(actionCommand, meat);
                frame.setVisible(true);
//            } else if (frame.getName().equals("Create New Item")) {
//                frame = new AddItemFrame(actionCommand,meat);
//                frame.setVisible(true);
//            }
        } else if (actionCommand.equals(produceButton.getActionCommand())) {
            // get produce section
            Section produces = sections.get(0);
            //if (frame.getName().equals("View Section")) {
                frame = new ViewSection(actionCommand, produces);
                frame.setVisible(true);
//            } else if (frame.getName().equals("Create New Item")) {
//                frame = new AddItemFrame(actionCommand,produces);
//                frame.setVisible(true);
//            }
        } else if (actionCommand.equals(dairyButton.getActionCommand())) {
            // get dairy section
            Section dairies = sections.get(2);
            //if (frame.getName().equals("View Section")) {
                frame = new ViewSection(actionCommand, dairies);
                frame.setVisible(true);
//            } else if (frame.getName().equals("Create New Item")) {
//                frame = new AddItemFrame(actionCommand,dairies);
//                frame.setVisible(true);
//            }
        }

    }

}
