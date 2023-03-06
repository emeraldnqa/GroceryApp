package model.item;

import java.time.LocalDate;

// Represent item in StoreItem qualified as Grocery, it can be oil, salt, sugar, etc.
public class Grocery extends StoreItem {

    public Grocery() {

        super();
    }

    // MODIFIES: this
    // EFFECTS: Set the expiry date to the maximum date possible, since grocery item has undetermined
    // expiry date.
    @Override
    public LocalDate setExpiryDate(int year, int month, int date) {
        return defaultExpiryDate();
    }

}
