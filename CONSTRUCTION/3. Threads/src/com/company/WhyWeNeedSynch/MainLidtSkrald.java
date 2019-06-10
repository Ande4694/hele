package com.company.WhyWeNeedSynch;

public class MainLidtSkrald {


    static int a = 10;
    static int b;

    public static void main(String[] args) throws InterruptedException {

        Runnable r1 = () ->
        {
            if (a == 10){
                try {


                    // med 0 bliver b nogle gange 5, med 500 bliver det 6 hver gang
                    Thread.sleep(0);
                    b = a/2;
                    System.out.println(Thread.currentThread().getName() + ": b = " + b);



                } catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }
        };

        Runnable r2 = () ->
        {
            a = 12;
        };

        Thread tråd1 = new Thread(r1, "tråd 1");
        Thread tråd2 = new Thread(r2, "tråd 2");

        tråd1.start();
        // join kan skifte "outcome" evt fjern den
        tråd1.join();
        tråd2.start();
    }
}
