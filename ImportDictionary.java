package com.nguyen27;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// This class generates a dictionary with weighted values based on the frequency of each letter represented
public class ImportDictionary {

    private int length; // User specified valid word length
    private String word;
    private int wordCount = 0;
    private int letterCounter = 0;
    private File file;
    private Scanner scan;
    private ArrayList<String> dictionary = new ArrayList<String>();
    private HashMap<Character, Float> letterList = new HashMap<Character,Float>();
    private Map<String,Float> weightedDict = new ConcurrentHashMap<String,Float>();

    // Imports text file containing valid words
    public ImportDictionary(int length, File file) throws FileNotFoundException {
        this.length = length;
        this.file = file;
        this.scan = new Scanner(file);
        createScoredDict();
    }

    // Creates a valid dictionary list with the specified letter count
    private ArrayList<String> createDictionary() {
        while (scan.hasNextLine()) {
            word = scan.nextLine();
            if (word.length() == length && !word.contains("'")) {
                dictionary.add(wordCount, word);
                wordCount++;
            }
        }
        return dictionary;
    }

    // Assigns a score to each letter in the English alphabet
    // Assigned score is later used to create a weighted score for each word in the dictionary
    private HashMap<Character, Float> createLetterScore() {
        ArrayList<String> dictionary = createDictionary();
        for(int i = 0; i < dictionary.size(); i++){
            char [] dictWord = dictionary.get(i).toCharArray();
            for (char c : dictWord) {
                if (letterList.containsKey(c)){
                    letterList.put(c,letterList.get(c) + 1);
                }
                else{
                    letterList.put(c,(float) 1);
                }
                letterCounter++;
            }
        }
        for(Map.Entry<Character, Float> entry : letterList.entrySet()){
            float weightedScore = entry.getValue()/letterCounter;
            letterList.put(entry.getKey(),weightedScore);
        }
        return letterList;
    }

    // Creates a hashmap with a weighted score assigned to each word in the dictionary
    private Map<String,Float> createScoredDict() {
        ArrayList<String> dictionary = createDictionary();
        HashMap<Character, Float> letterList = createLetterScore();
        float weightedValue = 0;
        int penalty = 0;
        for(int j = 0; j < dictionary.size(); j++){
            char [] storedWord = dictionary.get(j).toCharArray();
            // for loop creates the weighted score, an increased penalty is applied if the same letter appears multiple times
            for (char c:storedWord){
                if(letterList.containsKey(c)){
                    weightedValue = weightedValue + letterList.get(c);
                    String storedLetter = "" + c;
                    penalty = penalty + dictionary.get(j).length() - dictionary.get(j).replaceAll(storedLetter,"").length();
                }
            }
            weightedValue = weightedValue/(penalty + 1);
            penalty = 0;
            this.weightedDict.put(dictionary.get(j),weightedValue);
            weightedValue = 0;
        }
        return this.weightedDict;
    }

    // Returns the weighted dictionary for a user to see all of the assigned scores
    public Map<String, Float> getWeightedDict() {
        return this.weightedDict;
    }



}
