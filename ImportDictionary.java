package com.nguyen27;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ImportDictionary {

    private int length;
    private String word;
    private int count = 0;
    private int counter = 0;
    private File file;
    private Scanner scan;
    private ArrayList<String> dictionary = new ArrayList<String>();
    private HashMap<Character, Float> letterList = new HashMap<Character,Float>();
    private Map<String,Float> weightedDict = new ConcurrentHashMap<String,Float>();
    private PriorityQueue<WordleSort> bestWords;


    public ImportDictionary(int length, File file) throws FileNotFoundException {
        this.length = length;
        this.file = file;
        this.scan = new Scanner(file);
        createScoredDict();
    }

    private ArrayList<String> createDictionary() {
        while (scan.hasNextLine()) {
            word = scan.nextLine();
            if (word.length() == length && !word.contains("'")) {
                dictionary.add(count, word);
                count++;
            }
        }
        return dictionary;
    }

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
                counter++;
            }
        }
        for(Map.Entry<Character, Float> entry : letterList.entrySet()){
            float weightedScore = entry.getValue()/counter;
            letterList.put(entry.getKey(),weightedScore);
        }
        return letterList;
    }

    private Map<String,Float> createScoredDict() {
        ArrayList<String> dictionary = createDictionary();
        HashMap<Character, Float> letterList = createLetterScore();
        float weightedValue = 0;
        int count1 = 0;
        for(int j = 0; j < dictionary.size(); j++){
            char [] tempStorage = dictionary.get(j).toCharArray();
            for (char c:tempStorage){
                if(letterList.containsKey(c)){
                    weightedValue = weightedValue + letterList.get(c);
                    String temp9 = "" + c;
                    count1 = count1 + dictionary.get(j).length() - dictionary.get(j).replaceAll(temp9,"").length();
                }
            }
            weightedValue = weightedValue/(count1 + 1);
            count1 = 0;
            this.weightedDict.put(dictionary.get(j),weightedValue);
            weightedValue = 0;
        }
        return this.weightedDict;
    }

    public Map<String, Float> getWeightedDict() {
        return this.weightedDict;
    }



}
