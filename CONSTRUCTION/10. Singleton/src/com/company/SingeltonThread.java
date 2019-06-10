package com.company;

public class SingeltonThread implements Runnable {

    private static SingeltonThread single_instance = null;

    public String s;

    private SingeltonThread(){
        s = "Hej jeg er en meget unik string i denne Singelton klasse - 0123456789";
    }


    // sync gør at kun en thread har adgang til denne  konstructor
    public static synchronized SingeltonThread getInstance(){
        if (single_instance == null) {
            single_instance = new SingeltonThread();
        }

        return single_instance;
    }

    public void setS(String s){
        this.s = s;
    }

    @Override
    public void run(){
        System.out.println(s);
    }
}
