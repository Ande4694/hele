package com.company;

public class Main {

    public static void main(String[] args) {

        Singelton x = Singelton.getInstance();

        Singelton y = Singelton.getInstance();

        Singelton z = Singelton.getInstance();

        System.out.println("Stringen fra x er: " + x.s);
        System.out.println("Stringen fra y er: " + y.s);
        System.out.println("Stringen fra z er: " + z.s);

        x.s = (x.s).toUpperCase();
        System.out.println();

        System.out.println("Stringen fra x er: " + x.s);
        System.out.println("Stringen fra y er: " + y.s);
        System.out.println("Stringen fra z er: " + z.s);
        System.out.println("\n\n\n\n");


        // med threads

        SingeltonThread a = SingeltonThread.getInstance();
        SingeltonThread b = SingeltonThread.getInstance();
        SingeltonThread c = SingeltonThread.getInstance();

        a.run();
        a.setS("will this work?");
        b.run();
        c.run();
    }
}
