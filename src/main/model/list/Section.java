package model.list;

import model.item.*;
import model.list.exception.ItemAlreadyThereException;
import model.list.exception.ItemNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Section {

    private String type;
    private List<StoreItem> items;
    private int numOfItem;
    public static final LocalDate EXPIRY_DATE = LocalDate.of(+99999,12, 31);

    // EFFECT: Construct itemList class with an ArrayList, and the list type
    public Section(String type) {
        this.type = type;
        this.items = new ArrayList<>();
        this.numOfItem = 0;
    }

    public String getType() {
        return this.type;
    }

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
    // MODIFIES: this
    // EFFECT: Add StoreItem into Section. An item cannot be added, if the name, the price, and the brand is the same
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


    // REQUIRES: a valid store item
    // MODIFIES: this
    // EFFECT: Remove StoreItem from the Section. An  item cannot be removed if it's not in the list
    public List<StoreItem> removeItem(String name, String brand) throws ItemNotFoundException {
        if (items.isEmpty()) {
            throw new IllegalStateException();
        }
        for (StoreItem i : items) {
            if (i.getName().equals(name) && i.getBrand().equals(brand)) {
                items.remove(i);
                this.numOfItem -= 1;
                return items;
            } else {
                throw new ItemNotFoundException();
            }
        }
        return items;
    }

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







