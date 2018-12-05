package com.marcin.hashtagmessenger.badWordsDictionary;
/************************************************************
 * Class used to check the message content against dictionary
 * of bad words stored in list.txt file (words in that file
 * using special characters to subsidize some of the letters
 * so the file is less disturbing when looking at it)
 * The server checks the words again that dictionary as well as
 * with the Python server (if python server is not available
 * words are only checked against dictionary)
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
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

    //reads the content of the file and modifies the words to their original form
    private static ArrayList<String> readFile(String fileName){
        //ArrayList to store the stock daily history
        ArrayList<String> words = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if(!words.contains(sCurrentLine)) {
                    String tempString = sCurrentLine.replace("%","u");
                    tempString = tempString.replace("*", "f");
                    tempString = tempString.replace("^", "a");
                    tempString = tempString.replace("&", "e");
                    tempString = tempString.replace("(", "t");
                    tempString = tempString.replace(")", "c");
                    tempString = tempString.replace("$", "s");
                    tempString = tempString.replace("-", "i");
                    tempString = tempString.replace("?", "o");
                    words.add(tempString);
                }
            }

        } catch (IOException e) {
            System.out.println("***** IOException*****");
        }
        return words;
    }
}
