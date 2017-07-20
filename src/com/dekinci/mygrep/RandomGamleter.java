package com.dekinci.mygrep;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by Никита on 20.07.2017.
 */
public class RandomGamleter {
    private PrintWriter out;
    private String alphabetEn = "abcdefghijklmnopqrtsuvwxyz";
    private String alphabetRu = "абвгдеёжхийклмнопрстуфхцчшщъыьэюя";
    private int amountOfStrings, amountOfWordsInString, minWordLength, maxWordLength;

    RandomGamleter(String filename, int amountOfStrings, int amountOfWordsInString, int minWordLength, int maxWordLength) {
        try {
            out = new PrintWriter(filename);
        } catch (FileNotFoundException e) {
            System.err.print("Gamleter file not found exception");
            System.exit(404);
        }

        this.amountOfStrings = amountOfStrings;
        this.amountOfWordsInString = amountOfWordsInString;
        this.minWordLength = minWordLength;
        this.maxWordLength = maxWordLength;
    }

    public void fill() {
        int progressbar = 0;

        for (int i = 0; i < amountOfStrings; i++) {
            out.println(generateLine());
            if (i % amountOfStrings / 10 == 0)
                System.out.println("Generating: " + progressbar++ * 10 + "%");
        }

        System.out.println("Writing in file...");
        out.close();
    }

    private String generateLine() {
        String stringBuilder = "";
        for (int i = 0; i < amountOfWordsInString; i++)
            stringBuilder += (generateWord() + " ");
        return stringBuilder;
    }

    private String generateWord() {
        String wordBulder = "";
        for (int i = 0; i < getWordLength(); i++)
            wordBulder += alphabetEn.charAt(new Random().nextInt(alphabetEn.length()));
        return wordBulder;
    }

    private int getWordLength() {
        return new Random().nextInt(maxWordLength - minWordLength) + minWordLength;
    }

}
