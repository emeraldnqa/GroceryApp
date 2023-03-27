package ui.gui;

import javax.swing.*;

public class Frame extends JFrame {
    private String name;

    public Frame(String name) {
        super(name);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
