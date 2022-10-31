package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {2, 4, 6, 8, 10};

        takeNumbers(arr1, arr2);
    }

    public static int takeNumbers(int[] arr1, int[] arr2) {

        List<Integer> unMatched = new ArrayList<>();
        int flag = 0;
        for (int i = 0; i < arr1.length; i++) {
            flag = 0;
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    flag = 1;
                    break;
                }
            }

            if (flag == 0) {
                unMatched.add(arr1[i]);
            }
        }

        System.out.println(unMatched);

        return unMatched.size();

    }

}




