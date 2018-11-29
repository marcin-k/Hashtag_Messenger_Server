package com.marcin.hashtagmessenger.badWordsDictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary {

    ArrayList<String> badWords;
    private static Dictionary ourInstance = new Dictionary();

    public static Dictionary getInstance() {
        return ourInstance;
    }

    private Dictionary() {
        badWords = readFile("list.txt");
    }

    //returns true if inappropriate
    public boolean checkWordIsBad(String word){
        if (badWords.contains(word)){
            return true;
        }
        return false;
    }

    private static ArrayList<String> readFile(String fileName){
        //ArrayList to store the stock daily history
        ArrayList<String> words = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if(!words.contains(sCurrentLine)) {
                    words.add(sCurrentLine);
                }
            }

        } catch (IOException e) {
            System.out.println("***** IOException*****");
        }
        return words;
    }
}
