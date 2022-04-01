package com.techelevator.CateringItems;

public class Sandwich extends Product {



    public Sandwich(String slot, String itemName, String price, String productType) {
        super(slot, itemName, price, productType);
    }

    @Override
    public String dispense() {
        return "Sandwich So Delicious, Yum!";
    }
}
