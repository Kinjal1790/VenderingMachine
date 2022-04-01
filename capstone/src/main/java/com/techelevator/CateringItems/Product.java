package com.techelevator.CateringItems;

import java.math.BigDecimal;
import java.sql.SQLOutput;

public abstract class Product {

    private String slot;
    private String itemName;
    private BigDecimal price;
    private int quantity;
    private String productType;



    public Product(String slot, String itemName, String price, String productType){
        this.slot = slot;
        this.price = new BigDecimal(price);
        this.itemName = itemName;
        this.quantity = 7;
        this.productType = productType;
    }

    public String getSlot() {
        return slot;
    }

    public String getItemName() {
        return itemName;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() { return quantity; }

    public boolean purchaseItem(){
        if(quantity>0) {
            quantity = quantity - 1;
            return true;
        }
        else{
            return false;
        }
    }

    public abstract String dispense();



    @Override
    public String toString(){
        String template = "%s) %s $ %.2f (%d %s)";
        return String.format(template,getSlot(),getItemName(), getPrice(), getQuantity(), "remaining");
    }


}

