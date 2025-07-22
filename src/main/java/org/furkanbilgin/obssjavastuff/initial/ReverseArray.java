package org.furkanbilgin.obssjavastuff.initial;

public class ReverseArray {

    public static void main(String[] args) {
        int[] arrayToReverse = new int[]{1, 2, 3, 4, 5};
        int[] reversedArray = new int[arrayToReverse.length];
        for (int i = arrayToReverse.length - 1; i >= 0; i--) {
            // 5 --> reversedArray[1]
            // 4 --> reversedArray[2]
            reversedArray[i] = arrayToReverse[arrayToReverse.length - i - 1];
        }
        System.out.printf("Reversed array: ");
        for (int i = 0; i < reversedArray.length; i++) {
            System.out.printf("%d ", reversedArray[i]);
        }
    }
}
