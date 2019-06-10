package com.company.JonsBurgerJoint;

public class RunThis {

    public static void main(String[] args) {

        new RunThis();
    }

    public RunThis(){
        Slider resource = new Slider(2);
        Thread prodThread = new Thread(new Prod(resource));
        prodThread.start();
        Thread consThread = new Thread(new Cons(resource));
        consThread.start();
    }
}
