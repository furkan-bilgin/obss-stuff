package org.furkanbilgin.obssjavastuff.example5;

public class Main {
    public static void main(String[] args) {
        System.out.println(Boolean.toString(compareWordFrequency("This is not not", "is", "not")));
    }

    public static boolean compareWordFrequency(String original, String firstWord, String secondWord) {
        return getWordCount(original, firstWord) == getWordCount(original, secondWord);
    }

    public static int getWordCount(String original, String word) {
        /*
         * Count any occurances of words--in better terms, any string, whether they are
         * independent or inside another word
         */
        int count = 0;
        for (int i = 0; i < original.length() - word.length() + 1; i++) {
            if (original.substring(i, i + word.length()).equalsIgnoreCase(word)) {
                count++;
            }
        }
        System.out.println(Integer.toString(count));
        return count;
    }
}
