package ui.gui;

import javax.swing.*;

// Create a public abstract class called Frame
public class Frame extends JFrame {
    private String name;

    public Frame(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return this.name;

    }
}
