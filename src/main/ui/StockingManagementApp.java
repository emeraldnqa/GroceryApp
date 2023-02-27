package ui;

import model.item.StoreItem;
import model.list.Section;
import model.list.exception.ItemAlreadyThereException;
import model.list.exception.ItemNotFoundException;
import model.list.exception.WrongTypeException;

import java.util.List;
import java.util.Scanner;

import static model.list.Section.EXPIRY_DATE;

public class StockingManagementApp {
    private Section produces;
    private Section meats;
    private Section dairies;
    private Section groceries;
    private Scanner input;

    //EFFECTS: run the Grocery Stocking Management application
    protected StockingManagementApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
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
    // EFFECTS: Create different sections in a grocery store.
    private void init() {
        produces = new Section("Produce");
        meats = new Section("Meat");
        dairies = new Section("Dairy");
        groceries = new Section("Grocery");
        input = new Scanner(System.in);
        input.useDelimiter("\n");


    }

    // EFFECTS: display options for user
    private void showMenu() {
        System.out.println("\nWelcome to your Grocery Management App. What would you like to do?:");
        System.out.println("\ta -> Add new item");
        System.out.println("\tr -> Remove item");
        System.out.println("\tv -> View item");
        System.out.println("\ta/r -> Add or Reduce amount in individual item");
        System.out.println("\tq -> Quit");

    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: running user command
    private void runCommand(String command) {
        switch (command) {
            case "a":
                addCommand();
                break;
            case "r": {
                removeCommand();
                break;
            }
            case "v": {
                getSection();
                break;
            }
            case "a/r":
                addOrReduceAmount();
                break;
            default:
                System.out.println("There's no such option");
                break;
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

    // REQUIRES:
    // EFFECT: Print out all the item in the item list
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

    //REQUIRES:
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

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: Remove item from chosen section
    private void removeCommand() {
        Section chosenSection = getSection();
        System.out.println("Which item would you like to remove?");
        System.out.println("\nName of product to remove: ");
        String nameInput = input.next();
        System.out.println("\nBrand of product: ");
        String brandInput = input.next();
        try {
            chosenSection.removeItem(nameInput,brandInput);
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
        } catch (ItemAlreadyThereException e) {
            System.out.println("The Item is already being added. Please try again");
            addCommand();
        }
    }

    private StoreItem setUpItem(Section items) throws WrongTypeException {
        StoreItem item = items.createItem(items.getType());
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

    private StoreItem addItemData(StoreItem item, String name, String brand, String unit,
                                 double boughtPrice, int amount) {
        item.setName(name);
        item.setBrand(brand);
        item.setUnit(unit);
        item.setBoughtPrice(boughtPrice);
        item.setInitialAmount(amount);
        return item;
    }

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

    private void increaseAmount(Section sectionChosen) throws ItemNotFoundException {
        System.out.println("\nEnter the number of the item that you would like to add: ");
        int itemNo = input.nextInt();
        System.out.println("\nEnter the amount that you would like to add to the item: ");
        int amountIncrease = input.nextInt();
        try {
            sectionChosen.addAmount(itemNo,amountIncrease);
        } catch (IllegalStateException e) {
            System.out.println("The current section is empty");
        }

    }

    private void decreaseAmount(Section sectionChosen) throws ItemNotFoundException {
        System.out.println("Enter the number of the item that you would like to remove: ");
        int itemNo = input.nextInt();
        System.out.println("\nEnter the amount that you would like to decrease to the item: ");
        int amountDecrease = input.nextInt();
        try {
            sectionChosen.reduceAmount(itemNo, amountDecrease);
        } catch (IllegalStateException e) {
            System.out.println("The current section is empty");
        }

    }










}

