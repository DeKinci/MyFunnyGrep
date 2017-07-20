package com.dekinci.mygrep;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Никита on 20.07.2017.
 */
public class MyGrep {
    private Scanner in;
    private String keyword;
    private boolean funMode;

    public static void main (String[] args) { // Argument: filename string


        MyGrep app = new MyGrep();
        app.init(args);
        if (app.funMode) {
            RandomGamleter gamlet = new RandomGamleter(args[0], 10000, 50, 2, 10);
            gamlet.fill();
        }

        app.finder();
    }

    private void init(String[] args) {
        System.out.println("Scanning...");

        try {
            in = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.err.print("File not found");
            System.exit(404);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.print("File is not specified");
            System.exit(1);
        }

        try {
            funMode = (args[1].contains("-fun"));

            if (funMode)
                keyword = "gamlet";
            else {
                keyword = args[1];
                for (int i = 2; i < args.length; i++)
                    keyword += args[i];
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.err.print("Keyword is not specified");
            System.exit(1);
        }
    }

    private void finder() {
        int foundStringIndex = 0; //count from the first string
        boolean isFirstNotFound = true;

        while (in.hasNextLine()) {
            foundStringIndex++;
            if (foundStringIndex % 1000 == 0)
                System.out.println("Scanned: " + foundStringIndex + " strings");

            String inputLine = in.nextLine();
            if (inputLine.contains(keyword)) {
                if (isFirstNotFound) {
                    System.out.println("Найдены строки:");
                    isFirstNotFound = false;
                }
                System.out.println(foundStringIndex + " \t" + inputLine);
            }
        }

        if (isFirstNotFound)
            System.out.println("No matches found");
    }
}
