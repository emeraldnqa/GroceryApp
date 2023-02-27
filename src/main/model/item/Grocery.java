package model.item;

import java.time.LocalDate;

public class Grocery extends StoreItem {

    private LocalDate expiryDate;

    public Grocery() {
        super();
    }

    @Override
    public LocalDate setExpiryDate(int year, int month, int date) {

        return  expiryDate = LocalDate.MAX;
    }

    // EFFECTS: Grocery item does not have expiry date
    @Override
    public LocalDate getExpiryDate() {

        return expiryDate;
    }
}
