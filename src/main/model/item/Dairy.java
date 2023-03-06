package model.item;

import java.time.LocalDate;

// Represent StoreItem qualified as Dairy product such as milk, cheese, yoghurt, etc.
public class Dairy extends StoreItem {

    public Dairy() {

        super();
    }

    // REQUIRES: Year >= 2023 or current year, 0 < Month < 13, 0 < Date < 31
    // MODIFIES: this
    // EFFECTS: Set the expiry date
    @Override
    public LocalDate setExpiryDate(int year, int month, int date) {
        return expiryDate = LocalDate.of(year, month, date);
    }

}
