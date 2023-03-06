package model.item;


import java.time.LocalDate;

// Represent StoreItem that is qualified as Produce such as apple, bananas, vegetables, etc.
public class Produce extends StoreItem {

    public Produce() {
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
