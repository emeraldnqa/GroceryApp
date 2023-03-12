package model;

import model.item.*;
import model.list.Section;
import model.list.exception.ItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreItemTest {

    private StoreItem produceItem;
    private StoreItem meatItem;
    private StoreItem dairyItem;
    private StoreItem groceryItem;
    private Section produces;
    private Section meats;
    private Section dairies;
    private Section groceries;

    @BeforeEach
    void runBefore() {
        produceItem = new Produce();
        meatItem = new Meat();
        dairyItem = new Dairy();
        groceryItem = new Grocery();
        produces = new Section("Produce");
        meats = new Section("Meat");
        dairies = new Section("Dairy");
        groceries = new Section("Grocery");

    }

    StoreItem setProduceItem() {
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

    StoreItem setMeatItem() {
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

    StoreItem setDairyItem() {
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

    StoreItem setGroceryItem() {
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


    @Test
    void testProduceItemGetters() {
        produceItem = setProduceItem();
        assertEquals("Carrots",produceItem.getName());
        assertEquals("Kirkland",produceItem.getBrand());
        assertEquals(140.45, produceItem.getBoughtPrice());
        assertEquals("kg",produceItem.getUnit());
        assertEquals(100,produceItem.getAmount());
        assertEquals(LocalDate.MAX, produceItem.getExpiryDate());

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
        assertEquals(LocalDate.MAX, groceryItem.getExpiryDate());
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

    @Test
    void testReduceStockOnce() {
        produceItem = setProduceItem();
        produceItem.reduceStock(50);
        assertEquals(50, produceItem.getAmount());
        assertEquals(140.45 / 100, produceItem.getPrice());
    }

    @Test
    void testIncreaseAndDecreaseStockMultiples() {
        StoreItem newProduceItem = setProduceItem();
        newProduceItem.increaseStock(50);
        newProduceItem.reduceStock(10);
        newProduceItem.reduceStock(100);
        newProduceItem.increaseStock(200);
        assertEquals(240, newProduceItem.getAmount());
        assertEquals(140.45 / 100, newProduceItem.getPrice());
    }

    @Test
    void testSectionConstructor() {
        assertEquals("Produce", produces.getType());
        assertEquals("Meat", meats.getType());
        assertEquals("Grocery", groceries.getType());
        assertEquals("Dairy", dairies.getType());
    }

    @Test
    void testGetItems() {
        produceItem = setProduceItem();
        meatItem = setMeatItem();
        assertEquals(0,produces.getNumOfItem());
        produces.addItem(produceItem);
        produces.addItem(meatItem);
        List<StoreItem> items = produces.getItems();
        assertEquals(2,items.size());
        assertEquals(2,produces.getNumOfItem());
    }

    @Test
    void testAddItems() {
        produceItem = setProduceItem();
        meatItem = setMeatItem();
        produces.addItem(produceItem);
        assertEquals(1,produces.getNumOfItem());
        produces.addItem(meatItem);
        assertEquals(2,produces.getNumOfItem());
        StoreItem newItem = new Produce();
        newItem.setName("Carrots");
        newItem.setName("Other brand");
        newItem.setAmount(100);
        newItem.setUnit("kg");
        newItem.setBoughtPrice(140.45);
        newItem.setExpiryDate(+999999999,12,31);
        produces.addItem(newItem);
        assertEquals(3,produces.getNumOfItem());
    }

    @Test
    void testRemoveItems() throws ItemNotFoundException {
        produceItem = setProduceItem();
        meatItem = setMeatItem();
        produces.addItem(produceItem);
        produces.addItem(meatItem);
        produces.removeItem(0);
        assertEquals(1,produces.getNumOfItem());
        produces.removeItem(0);
        assertEquals(0,produces.getNumOfItem());
        assertThrows(IllegalStateException.class,
                () -> produces.removeItem(1),
                "");
    }

    @Test
    void testRemoveItemMultiple() throws ItemNotFoundException {
        produceItem = setProduceItem();
        meatItem = setMeatItem();
        groceryItem = setGroceryItem();
        dairyItem = setDairyItem();
        produces.addItem(produceItem);
        produces.addItem(meatItem);
        produces.addItem(groceryItem);
        produces.addItem(dairyItem);
        produces.removeItem(2);
        assertEquals(3, produces.getNumOfItem());
        // Test for completely different item and brand



    }


    @Test
    void testAddAmount() throws ItemNotFoundException{
        produceItem = setProduceItem();
        meatItem = setMeatItem();
        groceryItem = setGroceryItem();
        dairyItem = setDairyItem();
        produces.addItem(produceItem);
        produces.addItem(meatItem);
        produces.addItem(groceryItem);
        produces.addItem(dairyItem);
        produces.addAmount(1,200);
        assertEquals(250,meatItem.getAmount());
        assertThrows(ItemNotFoundException.class,
                () -> produces.addAmount(5,200),
                "");
    }

    @Test
    void testReduceAmount() throws ItemNotFoundException {
        produceItem = setProduceItem();
        meatItem = setMeatItem();
        groceryItem = setGroceryItem();
        dairyItem = setDairyItem();
        produces.addItem(produceItem);
        produces.addItem(meatItem);
        produces.addItem(groceryItem);
        produces.addItem(dairyItem);
        produces.reduceAmount(1,50);
        assertEquals(0,meatItem.getAmount());
        assertThrows(ItemNotFoundException.class,
                () -> produces.reduceAmount(5,200),
                "");

    }

    @Test
    void testAddAmountEmptySection() {
        produceItem = setProduceItem();
        assertThrows(IllegalStateException.class,
                () -> produces.addAmount(0,100),
                "");
        assertEquals(0,produces.getNumOfItem());
    }

    @Test
    void testReduceAmountEmptySection() {
        produceItem = setProduceItem();
        assertThrows(IllegalStateException.class,
                () -> produces.reduceAmount(0,100),
                "");
        assertEquals(0,produces.getNumOfItem());
    }


}