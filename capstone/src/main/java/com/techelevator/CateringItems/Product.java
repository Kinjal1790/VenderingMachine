package com.techelevator.CateringItems;

import java.math.BigDecimal;

public abstract class Product {

    private String slot;
    private String itemName;
    private BigDecimal price;
    private int remaningQuantity;



    public Product(String slot, String itemName, String price){
        this.slot = slot;
        this.price = new BigDecimal(price);
        this.itemName = itemName;
        this.remaningQuantity = 7;
    }

    public String getSlot() {
        return slot;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getRemaningQuantity() {
        //itemRemaining == 0; print that item is no longer available
        return remaningQuantity;
    }

    @Override
    public String toString(){
        String template = "%s) %s $ %.2f (%d %s)";
        return String.format(template,getSlot(),getItemName(), getPrice(), getRemaningQuantity(), "remaining");
    }


}
