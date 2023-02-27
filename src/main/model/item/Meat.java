package model.item;

import java.time.LocalDate;

public class Meat extends StoreItem {

    private LocalDate expiryDate;

    public Meat() {
    }

    @Override
    public LocalDate setExpiryDate(int year, int month, int date) {
        return this.expiryDate = LocalDate.of(year, month, date);
    }

    // EFFECTS: return the expiry date of meat item, seen on the package
    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
