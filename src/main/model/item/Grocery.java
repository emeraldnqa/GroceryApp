package model.item;

import java.time.LocalDate;

public class Grocery extends StoreItem {

    private static final LocalDate EXPIRY_DATE = LocalDate.of(+99999,12, 31);
    private LocalDate expiryDate;

    public Grocery() {
        super();
    }

    @Override
    public LocalDate setExpiryDate(int year, int month, int date) {
        return  this.expiryDate = EXPIRY_DATE;
    }

    // EFFECTS: Grocery item does not have expiry date
    @Override
    public LocalDate getExpiryDate() {
        return EXPIRY_DATE;
    }
}
