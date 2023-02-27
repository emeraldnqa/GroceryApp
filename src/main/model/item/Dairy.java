package model.item;

import java.time.LocalDate;

public class Dairy extends StoreItem {

    private LocalDate expiryDate;

    public Dairy() {
        super();
    }

    @Override
    public LocalDate setExpiryDate(int year, int month, int date) {
        return this.expiryDate = LocalDate.of(year, month, date);
    }

    // EFFECTS: Return the expiry date of the dairy item
    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
