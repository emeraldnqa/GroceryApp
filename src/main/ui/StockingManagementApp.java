package ui;

import model.item.*;
import model.list.Section;
import model.list.exception.ItemAlreadyThereException;
import model.list.exception.ItemNotFoundException;
import model.list.exception.WrongTypeException;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static model.list.Section.EXPIRY_DATE;

// A grocery store stocking management app;
public class StockingManagementApp {
    private static final String JSON_STORE = "./data/sections.json";
    private Section produces;
    private Section meats;
    private Section dairies;
    private Section groceries;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Scanner input;
    private List<Section> sections;

    //EFFECTS: run the Grocery Stocking Management application
    protected StockingManagementApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // REFERENCE: The structure for runApp() is inspired by TellerApp, an example provided in CPSC210
    //            for this project
    private void runApp() {
        boolean runningCommand = true;
        String command = null;

        try {
            init();

            while (runningCommand) {
                showMenu();
                command = input.next();
                command = command.toLowerCase();

                if (command.equals("q")) {
                    runningCommand = false;
                } else {
                    runCommand(command);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    // MODIFIES: this
    // EFFECTS: Initializes different sections in a grocery store.
    // REFERENCE: The structure for init() is inspired by TellerApp, an example provided in CPSC210
    //            for this project
    private void init() {
        produces = new Section("Produce");
        meats = new Section("Meat");
        dairies = new Section("Dairy");
        groceries = new Section("Grocery");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        sections = new ArrayList<>();
        sections.add(produces);
        sections.add(meats);
        sections.add(dairies);
        sections.add(groceries);

    }

    // EFFECTS: display options for user
    // REFERENCE: The structure for showMenu() is inspired by TellerApp, an example provided in CPSC210
    //            for this project
    private void showMenu() {
        System.out.println("\nWelcome to your Grocery Management App. What would you like to do?:");
        System.out.println("\ta -> Add new item");
        System.out.println("\tr -> Remove item");
        System.out.println("\tv -> View item");
        System.out.println("\ta/r -> Add or Reduce amount in individual item");
        System.out.println("\ts -> Save sections");
        System.out.println("\tl -> Load sections");
        System.out.println("\tq -> Quit");

    }

    // REQUIRES: command cannot be null.
    // MODIFIES: this
    // EFFECTS: running user command
    // REFERENCE: The structure for showMenu() is inspired by TellerApp, an example provided in CPSC210
    //            for this project
    private void runCommand(String command) throws WrongTypeException, IOException, ItemAlreadyThereException {
        switch (command) {
            case "a":
                addCommand();
                break;
            case "r":
                removeCommand();
                break;
            case "v":
                getSection();
                break;
            case "a/r":
                addOrReduceAmount();
                break;
            case "s":
                saveSections();
                break;
            case "l":
                loadSections();
                break;
            default:
                System.out.println("There's no such option");
                break;
        }
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

    // EFFECTS: Load Sections to file
    private void loadSections() throws WrongTypeException, IOException, ItemAlreadyThereException {
        try {
            sections = jsonReader.read();
            produces = sections.get(0);
            meats = sections.get(1);
            dairies = sections.get(2);
            groceries = sections.get(3);
            System.out.println("Loaded Different sections from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (WrongTypeException e) {
            throw new RuntimeException(e);
        } catch (ItemAlreadyThereException e) {
            throw new RuntimeException(e);
        }
    }


    // EFFECTS: Ask user which section they would conduct the action
    private void sectionMenu() {
        System.out.println("\nWhich section would you like to conduct the action on?");
        System.out.println("\tp -> produce");
        System.out.println("\tg -> grocery");
        System.out.println("\tm -> meat");
        System.out.println("\td -> dairy");
    }

    // REQUIRES: items that match the chosen Section
    // EFFECT: Print out all the items in the Section
    private void printItem(Section items) {
        System.out.println("Section: " + items.getType());
        System.out.println("Number of item in section: " + items.getNumOfItem());
        List<StoreItem> itemsList = items.getItems();
        int count = -1;
        for (StoreItem i : itemsList) {
            count++;
            System.out.println("\nNo: " + count);
            System.out.println("\tName: " + i.getName());
            System.out.println("\tBrand: " + i.getBrand());
            System.out.println("\tPrice per unit: " + i.getPrice() + "/" + i.getUnit());
            System.out.println("\tStock available " + i.getAmount());
            if (i.getExpiryDate().equals(EXPIRY_DATE)) {
                System.out.println("Expiry Date: N/A");
            } else {
                System.out.println("Expiry Date " + i.getExpiryDate());
            }
        }
    }


    // EFFECT: Find the section that the customer want to print out
    private Section getSection() {
        sectionMenu();
        String chosenSection = input.next();
        switch (chosenSection) {
            case "p":
                printItem(produces);
                return produces;
            case "g":
                printItem(groceries);
                return groceries;
            case "m":
                printItem(meats);
                return meats;
            case "d":
                printItem(dairies);
                return dairies;
            default:
                System.out.println("Section is not available");
                break;
        }
        return createNewSection();
    }

    // MODIFIES: this
    // EFFECT: create a new section, with user inputting the type of section. If user choose not to
    //         create a new section, quit the method
    private Section createNewSection() {
        System.out.println("Would you like to make a new section?(y/n)");
        String yesOrNo = input.next();
        yesOrNo = yesOrNo.toLowerCase();
        if  (yesOrNo.equals("n")) {
            System.out.println("Please enter the name of the section again: ");
            getSection();
        }
        System.out.println("Enter the type of new for your new section: ");
        String newSection = input.nextLine();
        return new Section(newSection);
    }


    // MODIFIES: this
    // EFFECTS: Remove item from chosen section
    private void removeCommand() {
        Section chosenSection = getSection();
        printItem(chosenSection);
        System.out.println("Which item would you like to remove? Enter the item number");
        int itemNo = input.nextInt();
        try {
            chosenSection.removeItem(itemNo);
        } catch (ItemNotFoundException e) {
            System.out.println("Item does not available in the section. Please try again");
            removeCommand();
        } catch (IllegalStateException e) {
            System.out.println("No item can be remove since the section is currently empty");
        }
    }

    // MODIFIES: this
    // EFFECTS: Add an item to chosen section
    private void addCommand() {
        Section chosenSection = getSection();
        System.out.println("Please add information about your item");
        try {
            StoreItem newItem = setUpItem(chosenSection);
            chosenSection.addItem(newItem);
        } catch (WrongTypeException e) {
            System.out.println("\nWe currently don't have that section available in store. Please choose again");
        }
    }

    // REQUIRES: type can only be Produce, Grocery, Meat, or Dairy
    // EFFECT: Create a new Item
    public StoreItem createItem(String type) throws WrongTypeException {
        switch (type) {
            case "Produce":
                return new Produce();
            case "Grocery":
                return new Grocery();
            case "Meat":
                return new Meat();
            case "Dairy":
                return new Dairy();
            default:
                throw new WrongTypeException();
        }
    }

    // REQUIRES: a valid item in StoreItem
    // MODIFIES: StoreItem
    // EFFECT: Enter all the information about the item
    private StoreItem setUpItem(Section items) throws WrongTypeException {
        StoreItem item = createItem(items.getType());
        System.out.println("Please add information about your new product below");
        System.out.println("\nName: ");
        String name = input.next();
        System.out.println("\nBrand: ");
        String brand = input.next();
        System.out.println("\nAmount Bought: ");
        int amount = input.nextInt();
        System.out.println("\nUnit: ");
        String unit = input.next();
        System.out.println("\nBought Price: ");
        double boughtPrice = input.nextDouble();
        if (items.getType().equals("Produce") || items.getType().equals("Grocery")) {
            item.setExpiryDate(9999,12,31);
            return addItemData(item, name, brand, unit, boughtPrice, amount);
        }
        setItemExpiryDate(item);
        return addItemData(item, name, brand, unit, boughtPrice, amount);
    }

    // MODIFIES: StoreItem
    // EFFECT: Set the user input about the item into the StoreItem object
    private StoreItem addItemData(StoreItem item, String name, String brand, String unit,
                                 double boughtPrice, int amount) {
        item.setName(name);
        item.setBrand(brand);
        item.setUnit(unit);
        item.setBoughtPrice(boughtPrice);
        item.setAmount(amount);
        item.setInitialAmount(amount);
        item.setPrice();
        return item;
    }

    // MODIFIES: StoreItem
    // EFFECT: Set the StoreItem expiryDate according to the item type
    private void setItemExpiryDate(StoreItem item) {
        System.out.println("Please enter the expiry date of the product");
        System.out.println("\nYear: ");
        int year = input.nextInt();
        System.out.println("\nMonth: ");
        int month = input.nextInt();
        System.out.println("\nDate: ");
        int date = input.nextInt();
        item.setExpiryDate(year,month,date);
    }

    // MODIFIES: Section
    // EFFECT: User can choose to reduce/add stock amount from an item in the section
    private void addOrReduceAmount() {
        System.out.println("Which would you like to do? Add amount or reduce? (a/r)");
        String action = input.next();
        Section sectionChosen = getSection();
        try {
            if (action.equals("a")) {
                increaseAmount(sectionChosen);
            } else if (action.equals("r")) {
                decreaseAmount(sectionChosen);
            }
        } catch (ItemNotFoundException e) {
            System.out.println("Item is not found, please try again");
            addOrReduceAmount();
        }
    }

    // REQUIRES: sectionChosen can only be the sections that was initialized at the beginning
    // MODIFIES: StoreItem
    // EFFECT: increase a certain item stock by their index position, by a certain amount
    private void increaseAmount(Section section) {
        System.out.println("\nEnter the number of the item that you would like to add: ");
        int itemNo = input.nextInt();
        System.out.println("\nEnter the amount that you would like to add to the item: ");
        int amountIncrease = input.nextInt();
        try {
            section.addAmount(itemNo,amountIncrease);
        } catch (IllegalStateException e) {
            System.out.println("The current section is empty");
        } catch (ItemNotFoundException e) {
            System.out.println("Item not found, please try again");
        }

    }

    // REQUIRES: sectionChosen can only be the sections that was initialized at the beginning
    // MODIFIES: StoreItem
    // EFFECT: Decrease a certain item stock by their index position, by a certain amount
    private void decreaseAmount(Section section) throws ItemNotFoundException {
        System.out.println("Enter the number of the item that you would like to remove: ");
        int itemNo = input.nextInt();
        System.out.println("\nEnter the amount that you would like to decrease to the item: ");
        int amountDecrease = input.nextInt();
        try {
            section.reduceAmount(itemNo, amountDecrease);
        } catch (IllegalStateException e) {
            System.out.println("The current section is empty");
        } catch (ItemNotFoundException e) {
            System.out.println("Item not found, please try again");
        }

    }










}

