package com.techelevator.ui;

import com.techelevator.CateringItems.Product;
import org.w3c.dom.ls.LSOutput;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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

   public static String getSelectedProductChoice(Map<String, Product> productMap) {

       System.out.println();
       //slot  //name // price // items quality (default 7) //

       Set<String> slots = productMap.keySet();
       for (String slot : slots) {
           System.out.println(productMap.get(slot));
       }
       getHOmeScreenChoice();
       return input.nextLine().trim().toLowerCase();
   }

    public static String purchasingProcessMenuDisplay() {

        System.out.println();
        System.out.println("(M) Feed Money");
        System.out.println("(S) Select Item");
        System.out.println("(F) Finish Transaction");
        System.out.println();

        System.out.println("Current Money Provided: $");

        return input.nextLine().trim().toLowerCase().substring(0, 1);
    }


    public static String moneyInputByUser() {
        System.out.print("Current Money Provided: $");
            return input.nextLine().trim();

    }
}
