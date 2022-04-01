package com.techelevator.CateringItems;

public class Munchy extends Product {



    public Munchy(String slot, String itemName, String price, String productType) {
        super(slot, itemName, price, productType);
    }

    @Override
    public String dispense() {
        return "Munchy, Munchy, so Good!";
    }
}
