package persistance;

import model.item.*;
import model.list.Section;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected StoreItem setProduceItem() {
        StoreItem produceItem = new Produce();
        produceItem.setName("Carrots");
        produceItem.setAmount(100);
        produceItem.setInitialAmount(100);
        produceItem.setUnit("kg");
        produceItem.setBrand("Kirkland");
        produceItem.setBoughtPrice(140.45);
        produceItem.setExpiryDate(+999999999,12,31);
        produceItem.setPrice();
        return produceItem;
    }

    protected StoreItem setMeatItem() {
        StoreItem meatItem = new Meat();
        meatItem.setName("bacon");
        meatItem.setBrand("kirkland");
        meatItem.setUnit("packs");
        meatItem.setAmount(50);
        meatItem.setInitialAmount(50);
        meatItem.setBoughtPrice(150.50);
        meatItem.setExpiryDate(2023,12,31);
        meatItem.setPrice();
        return meatItem;
    }

    protected StoreItem setDairyItem() {
        StoreItem dairyItem = new Dairy();
        dairyItem.setName("2% Milk");
        dairyItem.setBrand("Nelson");
        dairyItem.setUnit("cartons");
        dairyItem.setAmount(12);
        dairyItem.setInitialAmount(12);
        dairyItem.setBoughtPrice(30.0);
        dairyItem.setExpiryDate(2023,12,31);
        dairyItem.setPrice();
        return dairyItem;
    }

    protected StoreItem setGroceryItem() {
        StoreItem groceryItem = new Grocery();
        groceryItem.setName("paper towel");
        groceryItem.setBrand("kirkland");
        groceryItem.setUnit("packs");
        groceryItem.setAmount(12);
        groceryItem.setInitialAmount(12);
        groceryItem.setBoughtPrice(24.50);
        groceryItem.setExpiryDate(+999999999,12,31);
        groceryItem.setPrice();
        return groceryItem;
    }

    protected Section setMeats() {
        Section meats = new Section("Meat");
        meats.addItem(setMeatItem());
        return meats;

    }

    protected Section setProduce() {
        Section produces = new Section("Produce");
        produces.addItem(setProduceItem());
        return produces;

    }

    protected Section setGrocery() {
        Section groceries = new Section("Grocery");
        groceries.addItem(setGroceryItem());
        return groceries;

    }

    protected Section setDairy() {
        Section dairies = new Section("Dairy");
        dairies.addItem(setDairyItem());
        return dairies;

    }

    protected void checkSections(StoreItem item, Section section) {
        StoreItem sectionItem = section.getItems().get(0);

        assertEquals(item.getName(),sectionItem.getName());
        assertEquals(item.getBrand(),sectionItem.getBrand());
        assertEquals(item.getBoughtPrice(), sectionItem.getBoughtPrice());
        assertEquals(item.getUnit(),sectionItem.getUnit());
        assertEquals(item.getAmount(),sectionItem.getAmount());
        assertEquals(item.getExpiryDate(),sectionItem.getExpiryDate());

    }

}
