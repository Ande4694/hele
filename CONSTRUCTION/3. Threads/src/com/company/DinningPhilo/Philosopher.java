package com.company.DinningPhilo;

import java.util.Random;

public class Philosopher implements Runnable {

    private Object leftFork;
    private Object rightFork;
    Random random = new Random();

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try{
            while(true) {

                //Waits
                Action(": Waiting");
                synchronized (leftFork) {
                    Action(": Picked up left fork");
                    synchronized (rightFork) {
                        //eating
                        Action(": picked up right fork - eating");
                        Action(": Putdown right fork");
                    }
                    // Not hungry anymore
                    Action(": Put down left fork. Not hungry anymore");

                }

            }
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
    private void Action(String actionTxt) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + actionTxt);
        Thread.sleep( (random.nextInt(1000)));
    }
}