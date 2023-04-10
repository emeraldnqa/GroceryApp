package ui.gui;

import javax.swing.*;

// Create a public abstract class called Frame
public class Frame extends JFrame {
    private String name;

    // EFFECTS: Construct and empty frame with given frame name
    public Frame(String name) {
        super();
        this.name = name;
    }
    
    public String getName() {
        return this.name;

    }
}
