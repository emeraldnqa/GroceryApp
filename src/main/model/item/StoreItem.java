package model.item;

import java.time.LocalDate;

// Represent a StoreItem having name, brand, price, isShelfStable, expiryDate
public abstract class StoreItem {

    private String name;
    private String brand;
    private double price;
    private int amount;
    private static int initialAmount;
    private double boughtPrice;
    private String unit;



    // EFFECTS: Construct a StoreItem, with the name, brand, expiry date, bought price, initial amounts bought, and
    //          unit not yet set
    public StoreItem() {

    }

    public String setName(String name) {
        return this.name = name;
    }

    public String setBrand(String brand) {
        return this.brand = brand;
    }

    public double setBoughtPrice(double boughtPrice) {
        return this.boughtPrice = boughtPrice;
    }

    public int setInitialAmount(int amount) {
        initialAmount = amount;
        this.amount = amount;
        return initialAmount;
    }

    public String setUnit(String unit) {
        return this.unit = unit;
    }

    //EFFECT: return StoreItem name
    public String getName() {
        return this.name;
    }

    //EFFECT: return StoreItem brand
    public String getBrand() {
        return this.brand;
    }

    //EFFECT: return StoreItem price
    public double getPrice() {
        return this.price = setPrice();
    }

    //EFFECT: return StoreItem amount
    public int getAmount() {
        return this.amount;
    }

    //EFFECT: return item bought price:
    public double getBoughtPrice() {
        return this.boughtPrice;

    }

    //EFFECT: return item unit
    public String getUnit() {
        return this.unit;
    }

    //REQUIRES: Amount > 0, boughtPrice > 0
    //MODIFIES: this
    //EFFECT: set the price of an item price to amount/boughtPrice.
    private double setPrice() {
        price = boughtPrice / setInitialAmount(initialAmount);
        return price;
    }

    //REQUIRES: 0 < reduceAmount <= initialAmount
    //MODIFIES: this
    //EFFECT: Reduce amount in stock by reduceAmount
    public int reduceStock(int reduceAmount) {
        this.amount -= reduceAmount;
        return this.amount;
    }

    //REQUIRES: 0 < increaseAmount
    //MODIFIES: this
    //EFFECT: increase amount in stock by reduceAmount
    public int increaseStock(int increaseAmount) {
        this.amount += increaseAmount;
        return this.amount;
    }

    // REQUIRES: Year >= 2023 or current year, 0 < Month < 13, 0 < Date < 31
    // MODIFIES: this
    // EFFECTS: Return the expiryDate of certain item
    public abstract LocalDate setExpiryDate(int year, int month, int date);

    public abstract LocalDate getExpiryDate();




}
