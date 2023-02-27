package model.list;

import model.item.*;
import model.list.exception.ItemAlreadyThereException;
import model.list.exception.ItemNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Represent a section in a grocery store. The section has its own type, which adhere to four type that we
// have: produce, grocery, meat, dairy. Each section will also have numOfItem to represent the number of
// different product in the section
public class Section {

    private String type;
    private List<StoreItem> items;
    private int numOfItem;
    public static final LocalDate EXPIRY_DATE = LocalDate.of(+99999,12, 31);

    // EFFECT: Construct itemList class with an ArrayList, and the list type. Section Type is set in the ui.
    //         However, the initial section has an empty List<StoreItem>, and the number of item in section is
    //         set to 0.
    public Section(String type) {
        this.type = type;
        this.items = new ArrayList<>();
        this.numOfItem = 0;
    }

    public String getType() {
        return this.type;
    }

    // EFFECT: Whenever the function is called, List of StoreItem in the section is returned.
    public List<StoreItem> getItems() {
        List<StoreItem> itemsList = new ArrayList<>();
        for (StoreItem i : items) {
            itemsList.add(i);
        }
        return itemsList;
    }

    public int getNumOfItem() {
        return this.numOfItem;
    }

    // REQUIRES: a valid store item
    // MODIFIES: this, numOfItem
    // EFFECT: Add StoreItem into Section. An item cannot be added, if the name, the price, and the brand is the same.
    //         increase numOfItem in Section by 1.
    public List<StoreItem> addItem(StoreItem newItem) throws ItemAlreadyThereException {
        if (items.isEmpty()) {
            items.add(newItem);
            this.numOfItem++;
        } else {
            for (StoreItem item : items) {
                if (newItem.getName().equals(item.getName()) && newItem.getBrand().equals(item.getBrand())
                        && newItem.getPrice() == item.getPrice()) {
                    throw new ItemAlreadyThereException();
                } else {
                    items.add(newItem);
                    this.numOfItem++;
                    return items;
                }
            }
        }
        return items;
    }


    // REQUIRES: itemNo <= numOfItem
    // MODIFIES: this, numOfItem
    // EFFECT: Remove StoreItem from the Section. An  item cannot be removed if it's not in the list
    //         Reduce numOfItem by 1.
    public List<StoreItem> removeItem(int itemNo) throws ItemNotFoundException {
        if (items.isEmpty()) {
            throw new IllegalStateException();
        } else {
            StoreItem itemToRemove = items.get(itemNo);
            items.remove(itemToRemove);
            numOfItem--;
        }
        return items;
    }

    // REQUIRES: itemNo >= 0, amountIncrease > 0
    // MODIFIES: this, StoreItem
    // EFFECT: Increase an item stock in the section by given amount,
    //         according to the item index number in the section
    public void addAmount(int itemNo, int amountIncrease) throws ItemNotFoundException {
        if (items.isEmpty()) {
            throw new IllegalStateException();
        } else {
            if (itemNo <= getNumOfItem()) {
                items.get(itemNo).increaseStock(amountIncrease);
            } else {
                throw new ItemNotFoundException();
            }
        }
    }

    // REQUIRES: itemNo >= 0, amountIncrease < item.amount
    // MODIFIES: this, StoreItem
    // EFFECT: Decrease an item stock in the section by given amount,
    //         according to the item index number in the section. The decrease value cannot be larger than initial
    //         item amount
    public void reduceAmount(int itemNo, int amountIncrease) throws ItemNotFoundException {
        if (items.isEmpty()) {
            throw new IllegalStateException();
        } else {
            if (itemNo <= getNumOfItem()) {
                items.get(itemNo).reduceStock(amountIncrease);
            } else {
                throw new ItemNotFoundException();
            }
        }
    }
}







