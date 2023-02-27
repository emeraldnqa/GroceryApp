package model.item;


import java.time.LocalDate;

// Represent StoreItem that is qualified as Produce such as apple, bananas, vegetables, etc.
public class Produce extends StoreItem {

    private LocalDate expiryDate;

    public Produce() {
        super();

    }

    // MODIFIES: this
    // EFFECTS: Set the expiry date to the maximum date possible, since grocery item has undetermined
    // expiry date.
    @Override
    public LocalDate setExpiryDate(int year, int month, int date) {

        return this.expiryDate = LocalDate.MAX;
    }

    // EFFECTS: Produce such as vegetable has no expiry date
    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;

    }
}
