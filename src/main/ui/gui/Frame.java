package ui.gui;

import javax.swing.*;

public class Frame extends JFrame {
    private String name;

    public Frame(String name) {
        //TODO: Consider if we need to gridlayout the Frame or not
        //TODO: Add CardLayout to change the ContentPanel whenever an AddItem Button is pressed.
        super();
        this.name = name;
    }

    public String getName() {
        return this.name;

    }
}
