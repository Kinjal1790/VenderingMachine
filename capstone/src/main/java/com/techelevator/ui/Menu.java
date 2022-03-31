package com.techelevator.ui;

import org.w3c.dom.ls.LSOutput;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Menu {

    private static final Scanner input = new Scanner(System.in);

    public static String getHOmeScreenChoice(){


        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("D) Display caTEring Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");
        System.out.println();
        System.out.print("Please select an option: ");

        return input.nextLine().trim().toLowerCase().substring(0,1);
    }

    public static String displayItems(String[] products){

        System.out.println();
       //slot  //name // price // items quality (default 7) //


        for (int i = 0; i < products.length ; i++) {
            String product = products[i];
            String template = "%s, ) %s, $ %0.2f (%d %s)";
          //  System.out.println(template, product[i] );

        }


        // A1) Munchy $3.85 (7 remaining)



    }




}
