package com.nguyen27;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

// This class is responsible for taking in the word input and wordle output and generating a new dictionary
// based on the valid characters. This class also recommends the top 3 words based on letter frequency
// from the remaining dictionary words

public class WordIterator {
    private Map<String,Float> weightedDict;
    private ArrayList<Character> charArray = new ArrayList<>();
    private String charsOutput;
    private String wordleWord;

    public WordIterator(String charsOutput, String wordleWord, Map<String,Float> weightedDict){
        this.charsOutput = charsOutput;
        this.wordleWord = wordleWord;
        this.weightedDict = weightedDict;
    }

    // Returns a string with the valid characters in the output word
    // Prevents words from being eliminated when thinning out the dictionary with duplicate letters
    private String validChars(String charsOutput, String wordleWord){
        for(int k = 0; k < charsOutput.length(); k++){
            if(charsOutput.charAt(k) == 'G' || charsOutput.charAt(k) == 'Y'){
                charArray.add(wordleWord.charAt(k));
            }
        }
        if (charArray.size()==0){
            charArray.add('*');
        }
        // Converts Character Array to string, multiply by 3 to account for 'letter -> comma -> space'
        // Index starts at 1 to ignore [ and ends early to ignore ]
        String validCharsString = charArray.toString()
                .substring(1,3*charArray.size() - 1)
                .replaceAll(", ", "");
        return validCharsString;
    }

    // Removes words from the dictionary based on the corresponding output
    // The output of either G,Y or X is assigned to each letter in the word input to determine which words
    // in the dictionary are still valid
    private Map<String,Float> eliminateWords() {
        String validCharsString = validChars(charsOutput, wordleWord);
        for (Map.Entry<String, Float> entry : this.weightedDict.entrySet()) {
            String dictkey = entry.getKey();
            for (int i = 0; i < charsOutput.length(); i++) {
                if (charsOutput.charAt(i) == 'G') {
                    if (dictkey.charAt(i) != wordleWord.charAt(i)) {
                        this.weightedDict.remove(dictkey);
                        break;
                    }
                } else if (charsOutput.charAt(i) == 'Y') {
                    if (dictkey.contains("" + wordleWord.charAt(i))) {
                        if (dictkey.charAt(i) == wordleWord.charAt(i)) {
                            this.weightedDict.remove(dictkey);
                            break;
                        }

                    } else {
                        this.weightedDict.remove(dictkey);
                        break;
                    }

                } else {
                    if (dictkey.contains("" + wordleWord.charAt(i))) {
                        for (int j = 0; j < validCharsString.length(); j++) {
                            if (!(dictkey.contains("" + validCharsString.charAt(j)))) {
                                this.weightedDict.remove(dictkey);
                                break;
                            } else {
                                this.weightedDict.remove(dictkey);
                                break;
                            }
                        }

                    }
                }
            }

        }
        return this.weightedDict;
    }

    // Creates a queue with the remaining top score words at the top of the queue
    private PriorityQueue<WordleSort> sortTopWords(){
        Map<String,Float> tempMap = eliminateWords();
        PriorityQueue<WordleSort> maxHeap= new PriorityQueue<>(Comparator.reverseOrder());
        for(Map.Entry<String, Float> entry : tempMap.entrySet()){
            var topWord = new WordleSort(entry.getKey(), entry.getValue());
            maxHeap.add(topWord);
        }
        return maxHeap;
    }

    // Returns the top 3 words to the user to inform them of the best remaining choices
    public void getTop3(){
        PriorityQueue<WordleSort> maxHeap = sortTopWords();
        System.out.println(maxHeap.size());
        for(int k = 1; k <= 3; k++){
            if (!maxHeap.isEmpty()){
                System.out.println(maxHeap.remove());
            }
            else{
                System.out.println("No more words left!");
            }
        }
    }

    // Generates a new dictionary based on the remaining words left in the dictionary
    public Map<String,Float> getNewDict(){
        Map<String,Float> newDict = eliminateWords();
        return newDict;
    }


}
