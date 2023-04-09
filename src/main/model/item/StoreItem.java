package model.item;

import org.json.JSONObject;
import persistance.Writable;

import java.time.LocalDate;

// Represent a StoreItem having name, brand, price, the amount Bought, in what unit, amount change over tine,
// and boughtPrice
public abstract class StoreItem implements Writable {

    private String name;
    private String brand;
    private double price;
    private int amount;
    private int initialAmount;
    private double boughtPrice;
    private String unit;
    LocalDate expiryDate;



    // EFFECTS: Construct a StoreItem, with name, brand, price, the amount Bought, in what unit,
    // amount change over tine, and boughtPrice. The field has not yet been set
    public StoreItem() {

    }

    // EFFECTS:
    public String setName(String name) {
        return this.name = name;
    }

    public String setBrand(String brand) {
        return this.brand = brand;
    }

    public double setBoughtPrice(double boughtPrice) {
        return this.boughtPrice = boughtPrice;
    }

    public int setAmount(int amount) {
        this.amount = amount;
        return this.amount;
    }

    public int setInitialAmount(int amount) {
        this.initialAmount = amount;
        return this.initialAmount;
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
        return this.price;
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
    public double setPrice() {
        this.price = this.boughtPrice / this.initialAmount;
        return this.price;
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

    protected LocalDate defaultExpiryDate() {
        return expiryDate = LocalDate.MAX;

    }

    // REQUIRES: Year >= 2023 or current year, 0 < Month < 13, 0 < Date < 31
    // MODIFIES: this
    // EFFECTS: Return the expiryDate of certain item
    public abstract LocalDate setExpiryDate(int year, int month, int date);

    public LocalDate getExpiryDate() {
        return expiryDate;
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("brand",brand);
        json.put("price",price);
        json.put("amount",amount);
        json.put("initialAmount",this.initialAmount);
        json.put("boughtPrice",boughtPrice);
        json.put("unit",unit);
        json.put("year",expiryDate.getYear());
        json.put("month",expiryDate.getMonthValue());
        json.put("date",expiryDate.getDayOfMonth());
        return json;
    }




}
