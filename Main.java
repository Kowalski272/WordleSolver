package com.nguyen27;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File(); // Import Dictionary
        var testDict = new ImportDictionary(5,file); //Specify word length and file to pull words from
        int attempts = 6; // Attempts Allowable for Program
        var console = new Console(testDict);
        String prompt1 = "Please enter an English 5 letter word";
        String prompt2 = "Please submit what the Wordle output was?";
        String prompt3 = "G = Green, Y = Yellow, X = Gray";
        Map<String,Float> weightedDict = testDict.getWeightedDict();
        for (int i = 1; i <= attempts; i++){
            System.out.println("Attempt " + i + " out of " + attempts);
            String wordleWord = console.readWord(prompt1);
            String wordleOutput = console.readOutput(prompt2, prompt3);
            var samples = new WordIterator(wordleOutput,wordleWord,weightedDict);
            samples.getTop3();
            weightedDict = samples.getNewDict();
        }


    }

}