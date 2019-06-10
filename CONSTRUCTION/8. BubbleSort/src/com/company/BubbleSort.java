package com.company;

// https://www.youtube.com/watch?v=F13_wsHDIG4
public class BubbleSort {

    static void bubbleSort(int[]arr){

        int n = arr.length;
        int temp;

        //for loop igennem hele arrayet
        for (int i = 0; i < n; i++) {

            //
            for (int j = 1; j < (n-i) ; j++) {

                //hvis pladsen til venstre for "j" er større end j, skal der swappes
                if (arr[j-1] > arr[j]){

                    //swapper
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }

            }
        }
    }

    public static void main(String[] args) {

        int arrTest[] = {123,2,543,845,2342,6435,24233};

        //udskrift af array før sort
        for (int i = 0; i < arrTest.length; i++) {
            System.out.print(arrTest[i] + " - ");
        }
        System.out.println();


        //sortere
        bubbleSort(arrTest);

        //udskrift efter sortering
        for (int i = 0; i < arrTest.length; i++) {
            System.out.print(arrTest[i] + " - ");
        }
        System.out.println();
    }
}
