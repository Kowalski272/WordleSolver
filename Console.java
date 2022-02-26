package com.nguyen27;

import java.util.Scanner;

// This class handles reading the word and output submissions from the console
public class Console {
    private Scanner scanner = new Scanner(System.in);
    private ImportDictionary dictionary;
    String wordleWord = "";
    String wordleOutput = "";
    public Console (ImportDictionary dictionary){
        this.dictionary = dictionary;
    }

    public String readWord(String prompt){
        while(true){
            System.out.println(prompt);
            wordleWord = scanner.next();
            if(wordleWord.length() == 5 && this.dictionary.getWeightedDict().containsKey(wordleWord)){
                break;
            }
            System.out.println("Please submit a real word that is 5 characters long");
        }
        return wordleWord;
    }

    public String readOutput(String prompt1, String prompt2){
        while(true) {
            System.out.println(prompt1);
            System.out.println(prompt2);
            wordleOutput = scanner.next();
            if (wordleOutput.length() == 5) {
                if (!(wordleOutput.contains("X") || (wordleOutput.contains("G")) || (wordleOutput.contains("Y")))) {
                    System.out.println("Please submit the wordle output with the correct key values");
                } else {
                    break;
                }
            } else {
                System.out.println("Please submit the wordle output in 5 characters, with the correct keys");
            }
        }
        return wordleOutput;
    }

}
