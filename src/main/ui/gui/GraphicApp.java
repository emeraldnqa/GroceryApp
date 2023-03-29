package ui.gui;

import model.list.Section;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphicApp implements ActionListener {

    // Declare graphic component
    private JFrame mainFrame;
    private JMenuItem saveMenu;
    private JMenuItem loadMenu;
    private JMenuItem viewItemMenu;
    private JMenuItem addItemMenu;
    //private JMenuItem removeItemMenu;
    //private JMenuItem sectionMenu;
    private JMenuBar menuBar;
    private JPanel viewSectionPanel;
    private JPanel producePanel;
    private JPanel meatPanel;
    private JPanel dairiesPanel;
    private JPanel groceriesPanel;
    private CardLayout cl;
    private JButton addItemButton;
    private Frame frame;
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
        mainFrame.setLayout(new GridLayout(2,2));
        mainFrame.setPreferredSize(new Dimension(1000,500));
        createMenuBar();
        mainFrame.setJMenuBar(menuBar);
        //setUpViewSectionPanels();
        viewSectionPanel = new JPanel();
        viewSectionPanel.setSize(100,200);
        mainFrame.add(viewSectionPanel);





        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

    }

    private void setUpViewSectionPanels() {
//        cl = new CardLayout();
//        viewSectionPanel = new JPanel();
//        viewSectionPanel.setSize(100,200);
//        viewSectionPanel.setLayout(cl);
//        produces = sections.get(0);
//        meats = sections.get(1);
//        dairies = sections.get(2);
//        groceries = sections.get(3);
//        cl.show(viewSectionPanel,"1");
//        producePanel = new ViewSection("Produce",produces);
//        //producePanel.setBackground(Color.BLACK);
//        meatPanel = new ViewSection("Meat",meats);
//        //meatPanel.setBackground(Color.BLUE);
//        groceriesPanel = new ViewSection("Grocery",groceries);
//        dairiesPanel = new ViewSection("Dairy",dairies);
//        viewSectionPanel.add(producePanel,"Produce");
//        viewSectionPanel.add(meatPanel,"Meat");
//        viewSectionPanel.add(groceriesPanel,"Grocery");
//        viewSectionPanel.add(dairiesPanel,"Dairy");

    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        saveMenu = new JMenuItem("Save");
        saveMenu.setActionCommand("Save");
        saveMenu.addActionListener(this);
        loadMenu = new JMenuItem("Load");
        loadMenu.setActionCommand("Load");
        loadMenu.addActionListener(this);
        viewItemMenu = createMenuItem("Sections");
        //addItemMenu = createMenuItem("Add Item");
        //removeItemMenu = createMenuItem("Remove Item");
        viewItemMenu.addActionListener(this);
        //addItemMenu.addActionListener(this);
        //removeItemMenu.addActionListener(this);
        saveMenu.addActionListener(this);
        loadMenu.addActionListener(this);

        menuBar.add(viewItemMenu);
        //menuBar.add(addItemMenu);
        //menuBar.add(removeItemMenu);
        menuBar.add(saveMenu);
        menuBar.add(loadMenu);

    }

    private JMenuItem createMenuItem(String menuLabel) {
        JMenuItem menuItem = new JMenuItem(menuLabel);
        menuItem.setActionCommand(menuLabel);
        return menuItem;
    }

    // EFFECTS: Load Sections to file
    private List<Section> loadSections() {
        try {
            sections = jsonReader.read();
            setUpViewSectionPanels();
//            produces = sections.get(0);
//            meats = sections.get(1);
//            dairies = sections.get(2);
//            groceries = sections.get(3);
            System.out.println("Loaded Different sections from" + JSON_STORE);
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
            System.out.println("Saved " + produces.getType() + "to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == addItemMenu) {
//            frame = new Frame("Create New Item");
//            sectionFrame = new ChooseSectionFrame(frame,sections);
//            sectionFrame.setVisible(true);
//        } else if (e.getSource() == removeItemMenu) {
//            // open remove item frame;
//            frame = new Frame("Remove Item");
//            sectionFrame = new ChooseSectionFrame(frame,sections);
//            sectionFrame.setVisible(true);
//        } else
        if (e.getSource() == saveMenu) {
            saveSections();
        } else if (e.getSource() == loadMenu) {
            sections = loadSections();
        } else if (e.getSource() == viewItemMenu) {
            frame = new Frame("View Section");
            sectionFrame = new ChooseSectionFrame(frame,sections);
            sectionFrame.setVisible(true);

        }

    }
}
