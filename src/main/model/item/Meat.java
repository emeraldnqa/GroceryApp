package model.item;

import java.time.LocalDate;

//Represent StoreItem qualify as Meat, such as beef, pork, chicken, etc.
public class Meat extends StoreItem {

    private LocalDate expiryDate;

    public Meat() {
    }

    // REQUIRES: Year >= 2023 or current year, 0 < Month < 13, 0 < Date < 31
    // MODIFIES: this
    // EFFECTS: Set the expiry date
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
