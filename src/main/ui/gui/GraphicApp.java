package ui.gui;

import model.Event;
import model.EventLog;
import model.list.Section;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// A graphical stocking management app
public class GraphicApp implements ActionListener {

    // Declare graphic component
    private JFrame mainFrame;
    private JMenuItem saveMenu;
    private JMenuItem loadMenu;
    private JMenuItem viewItemMenu;
    private JMenuItem quitMenu;
    private JMenuBar menuBar;
    private JPanel mainPanel;
    private ChooseSectionFrame sectionFrame;





    // Declare model
    private List<Section> sections;
    private Section produces;
    private Section dairies;
    private Section meats;
    private Section groceries;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/sections.json";

    public GraphicApp() {
        init();
    }

    // MODIFIES: this
    // EFFECTS: Declare all the fields, and set the main frame, and main panel
    private void init() {
        // Setup model
        produces = new Section("Produce");
        meats = new Section("Meat");
        dairies = new Section("Dairy");
        groceries = new Section("Grocery");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        sections = new ArrayList<>();
        sections.add(produces);
        sections.add(meats);
        sections.add(dairies);
        sections.add(groceries);

        // Set up Graphic component
        mainFrame = new JFrame("Stocking Management App");
        mainFrame.setPreferredSize(new Dimension(1000,500));
        createMenuBar();
        mainFrame.setJMenuBar(menuBar);

        mainPanel = new MainPanel();
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECT: Set up the Menu Bar and its Menu items
    private void createMenuBar() {
        menuBar = new JMenuBar();
        saveMenu = createMenuItem("Save");

        loadMenu = createMenuItem("Load");

        viewItemMenu = createMenuItem("Section");

        quitMenu = createMenuItem("Quit");

        menuBar.add(viewItemMenu);
        menuBar.add(saveMenu);
        menuBar.add(loadMenu);
        menuBar.add(quitMenu);

    }



    // MODIFIES: This
    // EFFECTS: set up menu item
    private JMenuItem createMenuItem(String label) {
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.setActionCommand(label);
        menuItem.addActionListener(this);
        return menuItem;
    }

    // MODIFIES: this
    // EFFECTS: Load Sections to file
    private List<Section> loadSections() {
        try {
            sections = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return sections;
    }

    // EFFECTS: Save sections to file
    private void saveSections() {
        try {
            jsonWriter.open();
            jsonWriter.write(sections);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // REQUIRES: ActionEvent
    // MODIFIES: this
    // EFFECTS: Perform events whenever a button is pressed.
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == saveMenu) {
            saveSections();
            mainPanel.add(new PopUpDialog(mainFrame,"Saved all sections to" + JSON_STORE));
        } else if (source == loadMenu) {
            sections = loadSections();
            mainPanel.add(new PopUpDialog(mainFrame,"Loaded Different sections from" + JSON_STORE));
        } else if (source == viewItemMenu) {
            sectionFrame = new ChooseSectionFrame("View Section",sections);
            sectionFrame.setVisible(true);
        } else if (source == quitMenu) {
            mainFrame.dispose();
            printLog(EventLog.getInstance());
            System.exit(0);

        }

    }

    private void printLog(EventLog el) {
        for (Event next: el) {
            System.out.println(next.toString());
        }
    }
}
