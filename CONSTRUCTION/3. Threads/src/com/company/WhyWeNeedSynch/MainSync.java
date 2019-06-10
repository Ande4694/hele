package com.company.WhyWeNeedSynch;

public class MainSync {

    static int counter = 1;
    static int syncCounter = 1;


    public static int getId(){
        return counter++;
    }


    public static synchronized int getIdSync(){
        return syncCounter++;
    }

    public static void main(String[] args) throws InterruptedException {

        Runnable r = () ->
        {
            System.out.println("ID = "+getId() + "  by: " + Thread.currentThread().getName());
        };

        Runnable rSync = () ->
        {
            System.out.println("ID = "+getIdSync() + "  by: " + Thread.currentThread().getName());
        };

        //unsynced threads
        // man risikere at flere tilgår "counter" på samme tid og ikke får ++ ordentligt
        Thread u1 = new Thread(r, "u1");
        Thread u2 = new Thread(r, "u2");
        Thread u3 = new Thread(r, "u3");
        Thread u4 = new Thread(r, "u4");
        Thread u5 = new Thread(r, "u5");
        Thread u6 = new Thread(r, "u6");
        Thread u7 = new Thread(r, "u7");

        //synced threads
        Thread s1 = new Thread(rSync, "s1");
        Thread s2 = new Thread(rSync, "s2");
        Thread s3 = new Thread(rSync, "s3");
        Thread s4 = new Thread(rSync, "s4");
        Thread s5 = new Thread(rSync, "s5");
        Thread s6 = new Thread(rSync, "s6");
        Thread s7 = new Thread(rSync, "s7");



        u1.start();
        u2.start();
        u3.start();
        u4.start();
        u5.start();
        u6.start();
        u7.start();

        //for at adskille dem
        Thread.sleep(50);
        System.out.println();

        s1.start();
        s2.start();
        s3.start();
        s4.start();
        s5.start();
        s6.start();
        s7.start();



    }
}
