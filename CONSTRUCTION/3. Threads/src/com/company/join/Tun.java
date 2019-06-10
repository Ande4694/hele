package com.company.join;

import java.util.Random;

public class Tun implements Runnable {

    String name;
    int time;

    Random r = new Random();

    public Tun(String name) {
        this.name = name;
        time = r.nextInt(999); // random tal mellem 0 og 999 i milisek.
    }

    @Override
    public void run(){

            for (int i = 0; i < 5; i++) {
                try{

                    Thread.sleep(500);

                } catch (Exception e){
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "i = " + i);
                }
            }

            //System.out.printf("%s is sleeping for %d\n", name, time);
            //Thread.sleep(time);
            //System.out.printf("%s is done sleeping\n", name);

    }

