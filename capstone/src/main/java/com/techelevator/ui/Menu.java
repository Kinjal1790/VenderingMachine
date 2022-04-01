package com.techelevator.ui;

import com.techelevator.CateringItems.Product;
import org.w3c.dom.ls.LSOutput;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Menu {

    private static final Scanner input = new Scanner(System.in);


    public static String getHOmeScreenChoice() {


        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("(D) Display caTEring Items");
        System.out.println("(P) Purchase");
        System.out.println("(E) Exit");
        System.out.println();
        System.out.print("Please select an option: ");


        return input.nextLine().trim().toLowerCase().substring(0, 1);
    }


    public static String purchasingProcessMenuDisplay(BigDecimal money) {

        System.out.println();
        System.out.println("(M) Feed Money");
        System.out.println("(S) Select Item");
        System.out.println("(F) Finish Transaction");
        System.out.println();

        System.out.println("Current Money Provided: $" + money);

        return input.nextLine().trim().toLowerCase().substring(0, 1);
    }


    public static int addMoney(){

        System.out.println();

        System.out.println("(1) $1");
        System.out.println("(2) $5");
        System.out.println("(3) $10");
        System.out.println("(4) $20");

        System.out.print("Please select an option: ");
        return Integer.parseInt(input.nextLine().trim());

    }

    public static String selectItem(){
        System.out.print("Enter the slot of item you want?: ");
        return input.nextLine().trim().toUpperCase().substring(0,2);
    }


}
