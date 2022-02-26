 package com.nguyen27;

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
    @Override
    public String toString(){
        return word + " = " + weightedValue;
    }
}
