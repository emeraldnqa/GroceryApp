package ui.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Build the main panel for the graphic app
public class MainPanel extends JPanel {
    private BufferedImage image;
    private static final String PATHNAME = "./data/Java_logo_icon.jpg";

    public MainPanel() {
        setPreferredSize(new Dimension(1000,500));
        try {
            image = ImageIO.read(new File(PATHNAME));
        } catch (IOException e) {
            System.out.println("Image not found");
        }
        Color bgColor = new Color(197, 221, 235);
        setBackground(bgColor);

    }


    // MODIFIES: this
    //EFFECTS: Set up graphics for the main panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        createGraphics(g2d);
        String firstline = "How to use this app:";
        String secondline = "Create new Item by clicking Create Item button.";
        String thirdline = "Remove item from each section by clicking on Remove Item button.";
        String fourthline = "View details on each item by clicking on them, and click on View Item button.";
        String fifthline = "To start this using this app, click on the sections button, "
                + "and choose the section you would like to work on.";
        String sixthline = "Remember to click Save in the menu-bar if you want to save your progress.";
        String seventhline = "If you want to work on your previous progress, click on Load in the menu-bar.";
        String eighthline = "To quit the app, click Quit in the menu-bar.";
        String ninthline = "Note:";
        String tenthline = "Things to be aware when entering information for new item.";
        String eleventhline = "Amount Bought, Year, Date, and Month only accept integer value.";
        String twefthline = "Purchases Price only accept decimal value.";
        String thirteenthline = "Be aware that by not loading your data before adding new item to the section, "
                + "you are risk overwriting.";
        String fourteenthline = "or deleting your new data.";
        g2d.setFont(new Font("Arial",Font.PLAIN,12));
        g2d.setColor(Color.BLACK);

        createString(g2d, firstline, secondline, thirdline, fourthline, fifthline, sixthline, seventhline, eighthline,
                ninthline, tenthline, eleventhline, twefthline, thirteenthline, fourteenthline);

    }


    // EFFECTS: create String
    private void createString(Graphics2D g2d, String firstline, String secondline, String thirdline, String fourthline,
                              String fifthline, String sixthline, String seventhline, String eighthline,
                              String ninthline, String tenthline, String eleventhline, String twefthline,
                              String thirteenthline, String fourteenthline) {
        drawString(g2d, firstline,155,112,165,118);

        drawString(g2d, secondline,180,140,190,145);

        drawString(g2d, thirdline,180,165,190,170);

        drawString(g2d, fourthline,180,190,190,195);

        drawString(g2d, fifthline,180,215,190,220);

        drawString(g2d, sixthline,180,240,190,245);

        drawString(g2d, seventhline,180,265,190,270);

        drawString(g2d, eighthline,180,290,190,295);

        drawString(g2d, ninthline,155,315,165,320);

        drawString(g2d, tenthline,180,330,190,335);

        drawString(g2d, eleventhline,180,355,190,360);

        drawString(g2d, twefthline,180,380,190,385);

        drawString(g2d, thirteenthline,180,405,190,410);
        g2d.drawString(fourteenthline,190,425);
    }

    // EFFECTS: Create Graphics
    private void createGraphics(Graphics2D g2d) {
        g2d.drawString("App supported by: ",5,10);
        g2d.drawImage(image,5,12, 90,70,this);
        Font font = new Font("Arial",Font.PLAIN,40);
        Color fontColor = new Color(0,51,102);
        g2d.setColor(fontColor);
        g2d.setFont(font);
        g2d.drawString("Welcome to Stock Management App!",150,80);
        Color rectColor = new Color(102,178,225);
        g2d.setColor(rectColor);
        g2d.fillRect(150,100,700,340);
    }

    // EFFECTS: Create String
    private void drawString(Graphics2D g2d, String line,int shapeX, int shapeY, int stringX, int stringY) {
        g2d.fillOval(shapeX,shapeY,5,5);
        g2d.drawString(line,stringX,stringY);
    }
}
