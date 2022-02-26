 package com.nguyen27;

// This class is used for the priority queue to properly handle the hashmap used in WordIterator
public class WordleSort implements Comparable <WordleSort> {
    private String word;
    private float weightedValue;

    public WordleSort (String word, float weightedValue){
        this.weightedValue = weightedValue;
        this.word = word;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }

    public float getWeightedValue(){
        return weightedValue;
    }

    public void setWeightedValue(float weightedValue){
        this.weightedValue = weightedValue;
    }

    // Overrides the compareTo method in PriorityQueue to properly handle the keys and values of the wordle words
    @Override
    public int compareTo (WordleSort hash1) {
        if (this.getWeightedValue() < hash1.getWeightedValue()) {
            return -1;
        }
        else if(this.getWeightedValue() > hash1.getWeightedValue()) {
            return 1;
        }
        else {
            return 0;
        }
    }

    // Overrides the toString method to properly print the requested keys and values of the words and their weighted value
    @Override
    public String toString(){
        return word + " = " + weightedValue;
    }
}
