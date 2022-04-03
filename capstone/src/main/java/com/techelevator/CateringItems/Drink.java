package com.techelevator.CateringItems;

import java.math.BigDecimal;

public class Drink extends Product {

    public Drink(String slot, String itemName, String price, String productType) {
        super(slot, itemName, price, productType);
    }

    @Override
    public String dispense() {
        return "Drinky, Drinky, Slurp Slurp!";
    }
}
