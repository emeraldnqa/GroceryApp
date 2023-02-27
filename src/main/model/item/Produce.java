package model.item;


import java.time.LocalDate;

public class Produce extends StoreItem {

    private LocalDate expiryDate;

    public Produce() {
        super();

    }

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
