package ui.gui;

import javax.swing.*;

public class Frame extends JPanel {
    private String name;

    public Frame(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
