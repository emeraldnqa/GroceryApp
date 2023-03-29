//package ui.gui;
//
//import model.list.Section;
//import persistance.JsonReader;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainFrame extends JFrame implements ActionListener {
//    //TODO: Add another smaller frame on the side, gridlayout the content panel
//    //TODO: Visual representation for the app: Splash screen.
//    private JsonReader jsonReader;
//    private List<Section> sections;
//    private static final String JSON_STORE = "./data/sections.json";
//    private JMenu addItemMenu, removeItemMenu, viewSectionMenu, saveMenu, loadMenu, sectionMenu;
//    private JMenuBar menuBar;
//    private ChooseSectionFrame sectionFrame;
//    private Frame frame;
//    //private GridBagLayout gridBagLayout;
//    private JPanel panel;
//    private CardLayout cardLayout;
//
//
//    public MainFrame() {
//        super("Stocking Management App");
//        jsonReader = new JsonReader(JSON_STORE);
//        Section produces = new Section("Produce");
//        Section meats = new Section("Meat");
//        Section dairies = new Section("Dairy");
//        Section groceries = new Section("Grocery");
//        sections = new ArrayList<>();
//        sections.add(produces);
//        sections.add(meats);
//        sections.add(dairies);
//        sections.add(groceries);
//        setSize(400,500);
//        menuBar = createMenuBar();
//        panel = new JPanel();
//        cardLayout = new CardLayout();
//        setJMenuBar(menuBar);
//        pack();
//        setVisible(true);
//
//    }
//
//    public JMenuBar createMenuBar() {
//        menuBar = new JMenuBar();
//        //addItemMenu = createMenu("Add new Item");
//        //removeItemMenu = createMenu("Remove Item");
//        //viewSectionMenu = createMenu("View Item");
//        saveMenu = createMenu("Save");
//        loadMenu = createMenu("Load");
//        sectionMenu = createMenu("Sections");
//        for (Section section : sections) {
//            JMenuItem subSection = new JMenuItem(section.getType());
//            subSection.setActionCommand(section.getType());
//            subSection.addActionListener(this);
//            sectionMenu.add(subSection);
//        }
//        activateMenu();
//        //menuBar.add(addItemMenu);
//        //menuBar.add(removeItemMenu);
//        menuBar.add(sectionMenu);
//        menuBar.add(saveMenu);
//        menuBar.add(loadMenu);
//        return menuBar;
//
//    }
//
//    private JMenu createMenu(String menuLabel) {
//        JMenu menu = new JMenu(menuLabel);
//        menu.setActionCommand(menuLabel);
//        return menu;
//    }
//
//    private void activateMenu() {
//        //addItemMenu.addActionListener(this);
//        //removeItemMenu.addActionListener(this);
//        //viewSectionMenu.addActionListener(this);
//        saveMenu.addActionListener(this);
//        loadMenu.addActionListener(this);
//
//    }
//
////    private GridBagLayout setPanelLayout() {
////        GridBagLayout gridBagLayout = new GridBagLayout();
////        GridBagConstraints c = new GridBagConstraints();
////        ;
////        c.weightx = 1.0;
////        c.gridx = 1;
////        makebutton("Button1",gridBagLayout, c);
////        c.gridx = 2;
////        c.fill = GridBagConstraints.VERTICAL;
////        makebutton("Button2",gridBagLayout,c);
////
////        return gridBagLayout;
////
////
////    }
//
////    protected void makebutton(String name,
////                              GridBagLayout gridbag,
////                              GridBagConstraints c) {
////        Button button = new Button(name);
////        //gridbag.setConstraints(button, c);
////        add(button);
////    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String actionCommand = e.getActionCommand();
//        if (actionCommand.equals("Produce")) {
//
//        } else if(actionCommand.equals("Meat")) {
//
//        } else if (actionCommand.equals("Grocery")) {
//
//        } else if (actionCommand.equals("Dairy")) {
//
//        } else if (actionCommand.equals("Save")) {
//
//        } else if (actionCommand.equals("Load")) {
//            sections = loadSections();
//        }
////        if (e.getSource() == addItemMenu) {
////            frame = new Frame("Create New Item");
////            sectionFrame = new ChooseSectionFrame(frame, sections);
////            sectionFrame.setVisible(true);
////        } else if (e.getSource() == removeItemMenu) {
////            // open remove item frame;
////        } else if (e.getSource() == saveMenu) {
////            // Save method;
////        } else if (e.getSource() == loadMenu) {
////            sections = loadSections();
////        } else if (e.getSource() == viewSectionMenu) {
////            frame = new Frame("View Section");
////            sectionFrame = new ChooseSectionFrame(frame, sections);
////            sectionFrame.setVisible(true);
////        }
//    }
//
//    // EFFECTS: Load Sections to file
//    private List<Section> loadSections() {
//        try {
//            sections = jsonReader.read();
//            System.out.println("Loaded Different sections from" + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//        return sections;
//    }
//}
