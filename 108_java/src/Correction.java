package src;

import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Correction {
    public static class Word implements Comparable {
        private String word;
        private int nbOccurences;

        public Word(String word, int nbOccurences){
            this.word = word;
            this.nbOccurences = nbOccurences;
        }

        @Override
        public int compareTo(Object word) {
            Word wordCompare = (Word) word;
            return this.word.compareTo(wordCompare.word);
        }

        @Override
        public boolean equals(Object o) {

            if (o == this) { 
                return true; 
            } 

            if (!(o instanceof Word)) {
                return false; 
            } 

            Word w = (Word) o;

            return this.word.equals(w.word) && this.nbOccurences == w.nbOccurences; 
        }
    }
    
    public static class Dictionary{
        private Word[] words;

        public Dictionary(String filename){
            File file = new File(filename);
            Scanner input = null;

            try {
                input = new Scanner(file);
            } catch(FileNotFoundException e) {
                System.exit(1);
            }

            Map<String, Integer> mapMotsOccurences = new HashMap<String, Integer>();

            int nbWords = 0;
            while (input.hasNext()) {
                String word  = input.next();
                nbWords++;

                if(!mapMotsOccurences.containsKey(word)){
                    mapMotsOccurences.put(word, 1);
                } else {
                    int nbOccurences = mapMotsOccurences.get(word);
                    mapMotsOccurences.put(word, + 1);
                }
            }

            this.words = new Word[mapMotsOccurences.keySet().size()];
            int i = 0;
            for (Map.Entry<String, Integer> entry : mapMotsOccurences.entrySet()) {
                Word word = new Word(entry.getKey(), entry.getValue());
                words[i] = word;
                i++;
            }
            
            sortDico();
        }

        public void sortDico(){
            Arrays.sort(words);
        }

        public boolean isInDico(Word word){
            return Arrays.binarySearch(words, word) > 0;
        }

        public String motLePlusProche(Word word){
            int index = Arrays.binarySearch(words, word);

            if(index < 0) index = (-index) - 1;
            if(index > words.length - 1) index = words.length - 1;

            return words[index].word;
        }
    }
}