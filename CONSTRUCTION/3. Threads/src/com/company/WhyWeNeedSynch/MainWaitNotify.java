package com.company.WhyWeNeedSynch;

public class MainWaitNotify {

    public static void main(String[] args) {

        ThreadB b = new ThreadB();
        b.start();

        synchronized (b) {
           /*
            try{
                System.out.println("venter på at den anden tråd bliver færdig...");
                b.wait();
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
            */


           //FORDIG MAIN THREAD IKKE VENTER PÅ B, derfor er total 0

            System.out.println("venter på at den anden tråd bliver færdig...");


            System.out.println("Total er :" +b.total);
        }
    }
}

class ThreadB extends Thread{
    int total;

    @Override
    public void run(){
        synchronized(this){
            for (int i = 0; i < 10 ; i++) {
                total += i;
            }
            notifyAll();
        }
    }
}


