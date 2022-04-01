package com.techelevator.CateringItems;

public class Dessert extends Product {

    public Dessert(String slot, String itemName, String price, String productType) {
        super(slot, itemName, price, productType);
    }

    @Override
    public String dispense() {
        return "Sugar, Sugar, so Sweet!";
    }
}
