package com.company.join;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Thread t0 = new Thread(new Tun("Tun-tråd 1"));
        Thread t1 = new Thread(new Tun("Tun-tråd 2"));
        Thread t2 = new Thread(new Tun("Tun-tråd 3"));
        //Thread t4 = new Thread(new Tun("Tun-tråd 4"));


        // t1 & t2 kører færdigt, hvorefter join på t2 tvinger t3 til først at starte når t2 er færdig

        t0.start();
        t1.start();
        t1.wait();
        t0.notify();
        t1.join();
        t2.start();



    }
}
