package com.techelevator.auditLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class log {

    public static void purchaseLog(String transactionMessage){

        String path = "logs.txt";
        File file = new File(path);


        try (FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter writer = new PrintWriter(fileWriter)
        ) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");


            String template = "%s %s";
            String message = String.format(template, now.format(targetFormat).toString(), transactionMessage);
            writer.println(message);
        }
        catch (IOException e)
        {

        }

    }

}
