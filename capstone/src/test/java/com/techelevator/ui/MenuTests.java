package com.techelevator.ui;

import com.techelevator.CaTEringCapstoneCLI;
import com.techelevator.CateringItems.*;
import org.junit.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class MenuTests {

    CaTEringCapstoneCLI catering = new CaTEringCapstoneCLI();
    Map<String, Product> productMap = new TreeMap<>();
    BigDecimal money = new BigDecimal(0);
    BigDecimal zero = BigDecimal.ZERO;

    @Test
    public void processingFedMoney_shouldProcessAndUpdateMoney_whenUserEnterValidOption1(){

        BigDecimal bill = BigDecimal.ONE;

        BigDecimal expected = bill;
        catering.processingFedMoney(BigDecimal.ZERO,1);
        String message = "It should process and update money when user enter valid option1";


        BigDecimal actual = catering.getMoney();
        assertEquals(expected, actual);

    }

    @Test
    public void processingFedMoney_shouldProcessAndUpdateMoney_whenUserEnterValidOption2(){

        BigDecimal bill = BigDecimal.valueOf(5);

        BigDecimal expected = bill;
        catering.processingFedMoney(BigDecimal.ZERO,2);
        String message = "It should process and update money when user enter valid option2";


        BigDecimal actual = catering.getMoney();
        assertEquals(expected, actual);

    }

    @Test
    public void processingFedMoney_shouldProcessAndUpdateMoney_whenUserEnterValidOption3(){

        BigDecimal bill = BigDecimal.valueOf(10);

        BigDecimal expected = bill;
        catering.processingFedMoney(BigDecimal.ZERO,3);
        String message = "It should process and update money when user enter valid option3";

        BigDecimal actual = catering.getMoney();
        assertEquals(expected, actual);

    }

    @Test
    public void processingFedMoney_shouldProcessAndUpdateMoney_whenUserEnterValidOption4(){

        BigDecimal bill = BigDecimal.valueOf(20);

        BigDecimal expected = bill;
        catering.processingFedMoney(BigDecimal.ZERO,4);
        String message = "It should process and update money when user enter valid option4";


        BigDecimal actual = catering.getMoney();
        assertEquals(message, expected, actual);

    }

    @Test
    public void processingFedMoney_shouldKeepMoneyAmountSame_whenUserEnterInvalidOption0(){

        BigDecimal bill = BigDecimal.valueOf(0);

        BigDecimal expected = bill;
        catering.processingFedMoney(BigDecimal.ZERO,0);
        String message = "It should keep money amount same when user enter invalid option 0";


        BigDecimal actual = catering.getMoney();
        assertEquals(expected, actual);

    }

    @Test
    public void processingFedMoney_shouldKeepMoneyAmountSame_whenUserEnterInvalidOption5(){

        BigDecimal bill = BigDecimal.valueOf(0);

        BigDecimal expected = bill;
        catering.processingFedMoney(BigDecimal.ZERO,5);
        String message = "It should keep money amount same when user enter invalid option 5";


        BigDecimal actual = catering.getMoney();
        assertEquals(expected, actual);

    }


    @Test
    public void removeMoney_shouldUpdateMoney_whenPurchaseItemMunchy(){

        //fed money
        BigDecimal bill = BigDecimal.valueOf(5);
        catering.processingFedMoney(BigDecimal.ZERO,2);
        BigDecimal expected = BigDecimal.valueOf(1.15);
        String message = "It should remove money from available money when Nachos is purchased.";

        Product Nachos = new Munchy("A1", "Nachos", "3.85", "Munchy");
        catering.dispensingItemByVerifyingAvailablityOfItemAndMoney(Nachos);

        BigDecimal actual = catering.getMoney();

        assertEquals(message, expected, actual);


    }

    @Test
    public void removeMoney_shouldUpdateMoney_whenPurchaseItemDrink(){

        BigDecimal bill = BigDecimal.valueOf(5);
        catering.processingFedMoney(BigDecimal.ZERO,2);
        BigDecimal expected = BigDecimal.valueOf(3.15);
        String message = "It should remove money from available money when Ginger Ayle is purchased.";

        Product gingerAyle = new Drink("D3", "Ginger Ayle", "1.85", "Drink");
        catering.dispensingItemByVerifyingAvailablityOfItemAndMoney(gingerAyle);

        BigDecimal actual = catering.getMoney();


        assertEquals(message, expected, actual);

    }

    @Test
    public void removeMoney_shouldUpdateMoney_whenPurchaseItemDessert(){

        BigDecimal bill = BigDecimal.valueOf(5);
        catering.processingFedMoney(BigDecimal.ZERO,2);
        BigDecimal expected = BigDecimal.valueOf(3.25);
        String message = "It should remove money from available money when Chocolate Bar is purchased.";

        Product chocolateBar = new Dessert("A4", "Chocolate Bar", "1.75", "Dessert");
        catering.dispensingItemByVerifyingAvailablityOfItemAndMoney(chocolateBar);

        BigDecimal actual = catering.getMoney();

        assertEquals(message, expected, actual);

    }

    @Test
    public void removeMoney_shouldUpdateMoney_whenPurchaseItemSandwich(){

        BigDecimal bill = BigDecimal.valueOf(5);
        catering.processingFedMoney(BigDecimal.ZERO,2);
        BigDecimal expected = BigDecimal.valueOf(0.15);
        String message = "It should remove money from available money when Turkey Sandwich is purchased.";

        Product turkeySandwich = new Sandwich("B2", "Turkey Sandwich", "4.85", "Sandwich");
        catering.dispensingItemByVerifyingAvailablityOfItemAndMoney(turkeySandwich);

        BigDecimal actual = catering.getMoney();

        assertEquals(expected, actual);

    }

    @Test
    public void moneyLeftFromRemovedMoney_shouldBeReturnedToCustomer_howManyDollarsQuartersDimesNickelsAreOwed() {

        catering.processingFedMoney(BigDecimal.ZERO, 3);
        BigDecimal totalChange = BigDecimal.valueOf(5.15);
        int nickelExpected = 1;
        int dimesExpected= 1;
        int quartersExpected= 0;
        int dollarsExpected= 5;

        Product turkeySandwich = new Sandwich("B2", "Turkey Sandwich", "4.85", "Sandwich");
        catering.dispensingItemByVerifyingAvailablityOfItemAndMoney(turkeySandwich);

        BigDecimal actual = catering.getMoney();
        BigDecimal balance = actual.multiply(BigDecimal.valueOf(100));
        int dollarActual = balance.intValue() / 100;
        balance = balance.subtract(new BigDecimal(dollarActual * 100));
        int quarterActual = balance.intValue() / 25;
        balance = balance.subtract(new BigDecimal(quarterActual * 25));
        int dimeActual = balance.intValue() / 10;
        balance = balance.subtract(new BigDecimal(dimeActual * 10));
        int nickleActual = balance.intValue() / 5;


        assertEquals(dollarsExpected,dollarActual);
        assertEquals(quartersExpected, quarterActual);
        assertEquals(dimesExpected, dimeActual);
        assertEquals(nickelExpected, nickleActual);

    }

    @Test
    public void moneyLeftFromRemovedMoney_shouldBeReturnedToCustomer_howManyDollarsQuartersDimesNickelsAreOwedButWithAPennyValue() {

        catering.processingFedMoney(BigDecimal.ZERO, 2);
        BigDecimal totalChange = BigDecimal.valueOf(2.61);
        int nickelExpected = 0;
        int dimesExpected= 1;
        int quartersExpected= 2;
        int dollarsExpected= 2;

        Product turkeySandwich = new Sandwich("E1", "Pack Of Gum", "2.31", "Munchy");
        catering.dispensingItemByVerifyingAvailablityOfItemAndMoney(turkeySandwich);

        BigDecimal actual = catering.getMoney();
        BigDecimal balance = actual.multiply(BigDecimal.valueOf(100));
        int dollarActual = balance.intValue() / 100;
        balance = balance.subtract(new BigDecimal(dollarActual * 100));
        int quarterActual = balance.intValue() / 25;
        balance = balance.subtract(new BigDecimal(quarterActual * 25));
        int dimeActual = balance.intValue() / 10;
        balance = balance.subtract(new BigDecimal(dimeActual * 10));
        int nickleActual = balance.intValue() / 5;
        if (nickleActual%5!=0) {
            nickleActual = 0;
        }

        assertEquals(dollarsExpected,dollarActual);
        assertEquals(quartersExpected, quarterActual);
        assertEquals(dimesExpected, dimeActual);
        assertEquals(nickelExpected, nickleActual);

    }

    }
