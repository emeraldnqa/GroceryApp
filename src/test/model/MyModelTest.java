package model;

import model.item.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StoreItemTest {

    private StoreItem produceItem;
    private StoreItem meatItem;
    private StoreItem dairyItem;
    private StoreItem groceryItem;

    @BeforeEach
    void runBefore() {
        produceItem = new Produce();
        meatItem = new Meat();
        dairyItem = new Dairy();
        groceryItem = new Grocery();

    }

    StoreItem setProduceItem() {
        produceItem.setName("Carrots");
        produceItem.setInitialAmount(100);
        produceItem.setUnit("kg");
        produceItem.setBrand("Kirkland");
        produceItem.setBoughtPrice(140.45);
        return produceItem;
    }

    StoreItem setMeatItem() {
        meatItem.setName("bacon");
        meatItem.setBrand("kirkland");
        meatItem.setUnit("packs");
        meatItem.setInitialAmount(50);
        meatItem.setBoughtPrice(150.50);
        meatItem.setExpiryDate(2023,12,31);
        return meatItem;
    }

    StoreItem setDairyItem() {
        dairyItem.setName("2% Milk");
        dairyItem.setBrand("Nelson");
        dairyItem.setUnit("cartons");
        dairyItem.setInitialAmount(12);
        dairyItem.setBoughtPrice(30.0);
        dairyItem.setExpiryDate(2023,12,31);
        return dairyItem;
    }

    StoreItem setGroceryItem() {
        groceryItem.setName("paper towel");
        groceryItem.setBrand("kirkland");
        groceryItem.setUnit("packs");
        groceryItem.setInitialAmount(12);
        groceryItem.setBoughtPrice(24.50);
        return groceryItem;
    }


    @Test
    void testProduceItemGetters() {
        produceItem = setProduceItem();
        assertEquals("Carrots",produceItem.getName());
        assertEquals("Kirkland",produceItem.getBrand());
        assertEquals(140.45, produceItem.getBoughtPrice());
        assertEquals("kg",produceItem.getUnit());
        assertEquals(100,produceItem.getAmount());

    }

    @Test
    void testProduceItemSetPrice() {
        produceItem = setProduceItem();
        double price = 140.45 / 100;
        assertEquals(price, produceItem.getPrice());

    }


    @Test
    void testMeatItemGetters() {
        meatItem = setMeatItem();
        assertEquals("bacon",meatItem.getName());
        assertEquals("kirkland",meatItem.getBrand());
        assertEquals("packs",meatItem.getUnit());
        assertEquals(50,meatItem.getAmount());
        assertEquals(150.50,meatItem.getBoughtPrice());
        assertEquals(LocalDate.of(2023,12,31),meatItem.getExpiryDate());
    }

    @Test
    void testMeatItemSetPrice() {
        meatItem = setMeatItem();
        double price = 150.50 / 50;
        assertEquals(price, meatItem.getPrice());
    }


    @Test
    void testGroceryItemGetters() {
        groceryItem = setGroceryItem();
        assertEquals("paper towel",groceryItem.getName());
        assertEquals("kirkland",groceryItem.getBrand());
        assertEquals("packs",groceryItem.getUnit());
        assertEquals(12,groceryItem.getAmount());
        assertEquals(24.50,groceryItem.getBoughtPrice());
    }

    @Test
    void testGroceryItemGetPrice() {
        groceryItem = setGroceryItem();
        double price = 24.50 / 12;
        assertEquals(price, groceryItem.getPrice());
    }

    @Test
    void testDairyItemGetters() {
        dairyItem = setDairyItem();
        assertEquals("2% Milk",dairyItem.getName());
        assertEquals("Nelson",dairyItem.getBrand());
        assertEquals("cartons",dairyItem.getUnit());
        assertEquals(12,dairyItem.getAmount());
        assertEquals(30.0,dairyItem.getBoughtPrice());
        assertEquals(LocalDate.of(2023,12,31),dairyItem.getExpiryDate());
    }

    @Test
    void testDairyItemGetPrice() {
        dairyItem = setDairyItem();
        double price = 30.0 / 12;
        assertEquals(price, dairyItem.getPrice());
    }

    @Test
    void testIncreaseStockOnce() {
        produceItem = setProduceItem();
        produceItem.increaseStock(10);
        assertEquals(110,produceItem.getAmount());
        assertEquals(140.45 / 100, produceItem.getPrice());
    }


}