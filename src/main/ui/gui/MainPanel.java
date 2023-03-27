package ui.gui;

import model.list.Section;
import persistance.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/sections.json";;
    private AddItemFrame addItemFrame;
    private JButton addItemButton, removeItemButton, viewSectionButton, saveButton, loadButton;
    private GridLayout layout = new GridLayout(5,1);
    private Container pane;
    private ChooseSectionFrame sectionFrame;
    private Frame frame;
    private JsonReader jsonReader;
    private List<Section> sections;



    public MainPanel() {
        super("Grocery Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 900));
        addItemButton = createButton("Add new Item");
        removeItemButton = createButton("Remove Item");
        viewSectionButton = createButton("View Item");
        saveButton = createButton("Save");
        loadButton = createButton("Load");
        pane = getContentPane();
        activateButtons();
        addComponentToPane();
        pack();
        setVisible(true);
        setResizable(false);
        jsonReader = new JsonReader(JSON_STORE);
        Section produces = new Section("Produce");
        Section meats = new Section("Meat");
        Section dairies = new Section("Dairy");
        Section groceries = new Section("Grocery");
        sections = new ArrayList<>();
        sections.add(produces);
        sections.add(meats);
        sections.add(dairies);
        sections.add(groceries);

    }

    private JButton createButton(String buttonLabel) {
        JButton button = new JButton(buttonLabel);
        return button;
    }

    private void activateButtons() {
        addItemButton.addActionListener(this);
        removeItemButton.addActionListener(this);
        viewSectionButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

    }

    private void addComponentToPane() {
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(addItemButton);
        panel.add(removeItemButton);
        panel.add(viewSectionButton);
        panel.add(saveButton);
        panel.add(loadButton);
        pane.add(panel);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addItemButton) {
            frame = new Frame("Create New Item");
            sectionFrame = new ChooseSectionFrame(frame,sections);
            sectionFrame.setVisible(true);
        } else if (e.getSource() == removeItemButton) {
            // open remove item frame;
        } else if (e.getSource() == saveButton) {
            // Save method;
        } else if (e.getSource() == loadButton) {
             sections = loadSections();
        } else if (e.getSource() == viewSectionButton) {
            frame = new Frame("View Section");
            sectionFrame = new ChooseSectionFrame(frame,sections);
            sectionFrame.setVisible(true);

        }


    }

    // EFFECTS: Load Sections to file
    private List<Section> loadSections() {
        try {
            sections = jsonReader.read();
            System.out.println("Loaded Different sections from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return sections;
    }



}
