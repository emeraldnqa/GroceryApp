package model.item;

import com.sun.jdi.event.VMDeathEvent;

import java.time.LocalDate;

public class Produce extends StoreItem {

    private static final LocalDate EXPIRY_DATE = LocalDate.of(+99999,12, 31);
    private LocalDate expiryDate;

    public Produce() {
        super();

    }

    @Override
    public LocalDate setExpiryDate(int year, int month, int date) {
        return this.expiryDate = EXPIRY_DATE;
    }

    // EFFECTS: Produce such as vegetable has no expiry date
    @Override
    public LocalDate getExpiryDate() {
        return EXPIRY_DATE;

    }
}
